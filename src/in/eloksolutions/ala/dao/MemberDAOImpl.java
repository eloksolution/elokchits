package in.eloksolutions.ala.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import in.eloksolutions.ala.dao.jdbc.MemberLotsMapper;
import in.eloksolutions.ala.model.Lot;
import in.eloksolutions.ala.model.Member;

@Repository("memberDAO")
@Transactional
public class MemberDAOImpl extends HibernateDaoSupport implements MemberDAO {
	private static final Log log = LogFactory.getLog(MemberDAOImpl.class);
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
	public void save(Member persistentInstance){
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(persistentInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public Member findById(Integer memberId) {
		log.debug("getting Event instance with id: " + memberId);
		try {
			Member instance = (Member) sessionFactory.getCurrentSession().get("in.eloksolutions.ala.model.Member", memberId);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public List<Member> findAllMembers() {
		String sql="FROM Member where status=1 ORDER BY update_date DESC";
		Query query=getSessionFactory().getCurrentSession().createQuery(sql);
		return query.list();
	}

	@Override
	public List<Member> findByName(String name) {
		try {
			org.hibernate.Session hs=sessionFactory.getCurrentSession();
			Criteria cr = hs.createCriteria(Member.class);
			Criterion fname=Restrictions.like("firstName", "%"+name+"%");
			Criterion lname=Restrictions.like("lastName", "%"+name+"%");
			Criterion completeCondition = Restrictions.disjunction().add(fname).add(lname);
			cr.add(completeCondition);
			List results = cr.list();
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	public List<Lot> findMemberLots(int memid) {
		List<Lot> list = null;
		try {
		String sql = "select L.lot_id,L.code,L.sum_amount,L.no_of_months,L.status from lot L LEFT JOIN lot_members LM ON L.lot_id=LM.lot_id  LEFT JOIN members M ON LM.mem_id=M.mem_id  where M.mem_id=?";
		list =jdbcTemplate.query(sql,new Object[]{memid}, new MemberLotsMapper());	
		} catch (Exception re) {
			System.out.println("the error in lotmembers is"+re);
		}
		 return list;
	}

	@Override
	public List<Member> getMembersForPartner(int partId) {
		org.hibernate.Session hs=sessionFactory.getCurrentSession();
		Criteria cr = hs.createCriteria(Member.class);
		Criterion partnerId=Restrictions.eq("refId",partId);
		Criterion completeCondition = Restrictions.disjunction().add(partnerId);
		cr.add(completeCondition);
		return cr.list();
	}

	@Override
	public void deleteMember(int memid) {
		Member ent =(Member) getSessionFactory().getCurrentSession().load(Member.class, memid);
		ent.setStatus((byte) 0);
		getSessionFactory().getCurrentSession().saveOrUpdate(ent);
	}

}
