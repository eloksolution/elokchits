package in.eloksolutions.ala.dao;

import in.eloksolutions.ala.beans.LotDetailsVO;
import in.eloksolutions.ala.beans.MemberBillVO;
import in.eloksolutions.ala.beans.MemberVO;
import in.eloksolutions.ala.dao.jdbc.LotDetailsMapper;
import in.eloksolutions.ala.dao.jdbc.LotMemberMapper;
import in.eloksolutions.ala.dao.jdbc.MemberBillMapper;
import in.eloksolutions.ala.dao.jdbc.MemberMapper;
import in.eloksolutions.ala.model.LotMember;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

@Repository("auctionDAO")
@Transactional
public class AuctionDAOImpl extends HibernateDaoSupport implements AuctionDAO {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	/* (non-Javadoc)
	 * @see com.websystique.springmvc.dao.LeadsDAO#getJdbcTemplate()
	 */
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	/* (non-Javadoc)
	 * @see com.websystique.springmvc.dao.LeadsDAO#setJdbcTemplate(org.springframework.jdbc.core.JdbcTemplate)
	 */
	protected void initDao() {
		// do nothing
	}
	/* (non-Javadoc)
	 * @see com.websystique.springmvc.dao.LeadsDAO#init(org.hibernate.SessionFactory)
	 */
	@Autowired
	public void init(SessionFactory sessionFactory) {
	    setSessionFactory(sessionFactory);
	    this.sessionFactory=sessionFactory;
	}
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	@Autowired
	protected HibernateTemplate createHibernateTemplate(
			SessionFactory sessionFactory) {
		return super.createHibernateTemplate(sessionFactory);
	}

	@Override
	public List<LotMember> findAllLotMembers() {
		String sql="FROM LotMember";
		Query query=getSessionFactory().getCurrentSession().createQuery(sql);
		return query.list();	
	}
	
	@Override
	public List<LotMember> findAllActiveLotMembers(Integer refId) {
		String sql="SELECT * FROM chitsdb.lot_members LM WHERE LM.ref_id=? and LM.LOT_ID=(SELECT LOT_ID FROM LOT L WHERE L.LOT_ID=LM.lot_id AND L.status=1) ;";
		 return jdbcTemplate.query(sql,new Object[] { refId }, new LotMemberMapper());
	}
	
	@Override
	public List<LotMember> findAllActiveLotMembers() {
		String sql="SELECT * FROM chitsdb.lot_members LM WHERE LM.LOT_ID=(SELECT LOT_ID FROM LOT L WHERE L.LOT_ID=LM.lot_id AND L.status=1) ;";
		 return jdbcTemplate.query(sql,new Object[] { }, new LotMemberMapper());
	}

	@Override
	public void save(LotMember member) {
		sessionFactory.getCurrentSession().save(member);		
	}
	
	@Override
	public void update(LotMember member) {
		sessionFactory.getCurrentSession().update(member);		
	}

	@Override
	public LotMember findById(Integer memberId) {
		return (LotMember) sessionFactory.getCurrentSession().get("in.eloksolutions.ala.model.LotMember", memberId);
	}

	@Override
	public LotMember findByMemIdnLotid(Integer memId, Integer lotId) {
		String sql="FROM LotMember where mem_id=:memId and lot_id=:lotId";
		Query query=getSessionFactory().getCurrentSession().createQuery(sql);
		query.setParameter("memId", memId);
		query.setParameter("lotId", lotId);
		return (LotMember)query.uniqueResult();
	}
	
	@Override
	public List<LotMember> findByYearnMonth(Integer year, Integer month) {
		String sql="SELECT * FROM chitsdb.lot_members LM WHERE  year_lift=? and month_lift=? AND LM.LOT_ID=(SELECT LOT_ID FROM LOT L WHERE L.LOT_ID=LM.lot_id AND L.status=1) ;";
		 return jdbcTemplate.query(sql,new Object[] { year,month}, new LotMemberMapper());
	}

	@Override
	public List<LotDetailsVO> getLotDetails(Integer year, Integer month) {
		String SQL = "select L.lot_id,L.code,L.sum_amount,L.commisionper,L.no_of_months,L.status,L.type,LM.id,LM.mem_id,LM.mem_name,LM.auction_amt,LM.auction_date,LM.mem_name from lot L LEFT JOIN lot_members LM ON L.lot_id=LM.lot_id AND LM.year_lift=? and LM.month_lift=?  WHERE L.status=1";
		System.out.println("SQL IS"+ SQL);
	      return jdbcTemplate.query(SQL,new Object[] { year,month}, new LotDetailsMapper());
	}

	@Override
	public List<MemberVO> searchMembers(String q ,String lotid) {
		String SQL = "SELECT L.code,LM.id,LM.lot_id,M.first_name,M.last_name FROM elokchitsdb.lot_members LM,elokchitsdb.members M,LOT L where L.lot_id=? AND (M.first_name like ? or M.last_name like ?) and M.status=1 and LM.mem_id=M.mem_id and LM.lot_id=L.lot_id and LM.paid_date IS  NULL;";
	      return jdbcTemplate.query(SQL,new Object[] { lotid,"%"+q+"%","%"+q+"%"}, new MemberMapper());
	}

	@Override
	public List<MemberBillVO> billGeneration(Integer mon, Integer year) {
		String SQL="SELECT LM.LOT_ID,L.SUM_AMOUNT,LM.AUCTION_AMT,L.NO_OF_MONTHS ,(TIMESTAMPDIFF(MONTH, L.START_DATE, NOW())+1) AS RUNMON ,L.NO_OF_MONTHS - (TIMESTAMPDIFF(MONTH, L.START_DATE, NOW())+1) AS BALMON, L.SUM_AMOUNT/L.NO_OF_MONTHS PREMIUM,(LM.AUCTION_AMT-(L.SUM_AMOUNT*L.COMMISIONPER/100))/L.NO_OF_MONTHS COMM, (L.SUM_AMOUNT/L.NO_OF_MONTHS)-(LM.AUCTION_AMT-(L.SUM_AMOUNT*L.COMMISIONPER/100))/L.NO_OF_MONTHS AS PAYAMT ,M.FIRST_NAME,M.LAST_NAME FROM LOT_MEMBERS LM,LOT L,MEMBERS M WHERE L.LOT_ID=LM.LOT_ID AND LM.MEM_ID=M.MEM_ID AND LM.MONTH_LIFT=? AND LM.YEAR_LIFT=? ORDER BY LM.mem_id";
		return jdbcTemplate.query(SQL,new Object[] { mon,year}, new MemberBillMapper());
	}
}
