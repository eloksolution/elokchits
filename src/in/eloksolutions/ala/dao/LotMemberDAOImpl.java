package in.eloksolutions.ala.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import in.eloksolutions.ala.beans.MemberVO;
import in.eloksolutions.ala.dao.jdbc.MemberRowMapper;
import in.eloksolutions.ala.model.LotMember;

@Repository("lotMemberDAO")
@Transactional
public class LotMemberDAOImpl extends HibernateDaoSupport implements LotMemberDAO {
	private static final Log log = LogFactory.getLog(LotMemberDAOImpl.class);
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
	public void save(LotMember persistentInstance) {
		try {
			sessionFactory.getCurrentSession().save(persistentInstance);
		} catch (RuntimeException re) {
			throw re;
		}
		
	}

	
	@Override
	public LotMember searchlotMembers(int lotid) {
		
		return null;
	}

	@Override
	public List<MemberVO> lotsMembers(int lotid) {
		
		String sql="FROM MemberVO where lotId=:lotId";
		Query query=getSessionFactory().getCurrentSession().createQuery(sql);
		query.setParameter("lotId", lotid);
		return query.list();
	}

	@Override
	public List<LotMember> findLotsMembers(int lotid) {
		
		String sql="FROM LotMember where lotId=:lotId";
		Query query=getSessionFactory().getCurrentSession().createQuery(sql);
		query.setParameter("lotId", lotid);
		return query.list();
	}

	@Override
	public List<MemberVO> allLotMembers(int lotid) {
		List<MemberVO> list = null;
		try {
		String sql = "select M.mem_id,M.first_name,M.last_name,M.phone,M.ref_id,M.address,LM.auction_amt,LM.paid_date,LM.id from lot L LEFT JOIN lot_members LM ON L.lot_id=LM.lot_id  LEFT JOIN members M ON LM.mem_id=M.mem_id  where L.lot_id=? ORDER BY L.lot_id DESC";
		list =jdbcTemplate.query(sql,new Object[]{lotid}, new MemberRowMapper());	
		System.out.println("the error in lotmembers is"+list);
		} catch (Exception re) {
			re.printStackTrace();
			System.out.println("the error in lotmembers is"+re);
		}
		 return list;
	}

	
	

}
