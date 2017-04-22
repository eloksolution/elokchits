package in.eloksolutions.ala.dao;

import in.eloksolutions.ala.beans.MemberVO;
import in.eloksolutions.ala.dao.jdbc.MemberRowMapper;
import in.eloksolutions.ala.model.Lot;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

@Repository("lotDAO")
@Transactional
public class LotDAOImpl extends HibernateDaoSupport implements LotDAO{
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

	public void add(Lot lot) {
		sessionFactory.getCurrentSession().save(lot);
		sessionFactory.getCurrentSession().flush();
		System.out.println("after flush "+lot);
	}

	public Lot findById(int lotId) {
		return (Lot) sessionFactory.getCurrentSession().get("in.eloksolutions.ala.model.Lot", lotId);
	}

@Override
	public List<Lot> findAllLots() {
		String sql="FROM Lot";
		Query query=getSessionFactory().getCurrentSession().createQuery(sql);
		return query.list();	
	}
	
	@Override
	public List<Lot> findAllActiveLots() {
		String sql="FROM Lot where status=:status";
		Query query=getSessionFactory().getCurrentSession().createQuery(sql);
		query.setParameter("status", 1);
		return query.list();	
	}

	@Override
	public List<MemberVO> findlotMembers(int lotid) {
		List<MemberVO> list = null;
		try {
		String sql = "select M.mem_id,M.first_name,M.last_name,M.phone,M.ref_id,M.address,LM.auction_amt,LM.paid_date,LM.id from lot L LEFT JOIN lot_members LM ON L.lot_id=LM.lot_id  LEFT JOIN members M ON LM.mem_id=M.mem_id  where L.lot_id=? ORDER BY L.lot_id DESC";
		list =jdbcTemplate.query(sql,new Object[]{lotid}, new MemberRowMapper());	
		System.out.println("the error in lotmembers is"+list);
		} catch (Exception re) {
			System.out.println("the error in lotmembers is"+re);
		}
		 return list;
	}


	@Override
	public int getLiftMemberCount(int lotid) {
		  String sql = "SELECT count(*) FROM chitsdb.lot_members Where lot_id=? and auction_amt is null";
	        boolean result = false;

	        //The method queryForInt(String, Object...) from the type JdbcTemplate is deprecated
	        int count = jdbcTemplate.queryForObject(
                    sql, new Object[] {new Integer (lotid) }, Integer.class);
          return count;
	}
	
	@Override
	public void updateDate(Date startDate,int lotId) {
		Lot lot =  (Lot) getSessionFactory().getCurrentSession().load(Lot.class, new Integer(lotId));
		lot.setStartDate(startDate);
		System.out.println("After update startDate "+startDate);
	}

	@Override
	public void deleteMember(int memrowid) {
		//LotMember ent =(LotMember) getSessionFactory().getCurrentSession().load(LotMember.class, memrowid);
		//getSessionFactory().getCurrentSession().delete(ent);
	}
	

}