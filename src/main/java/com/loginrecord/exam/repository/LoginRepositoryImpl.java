package com.loginrecord.exam.repository;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.loginrecord.exam.entity.Login;


@Repository
public class LoginRepositoryImpl implements LoginRepository {
	
	private SessionFactory sessionFactory;
	
	@Autowired
	public LoginRepositoryImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Login> getUniqueDates() {
		return sessionFactory.getCurrentSession().createCriteria(Login.class).addOrder(Order.asc("loginTime"))
				.setProjection(Projections.distinct(Projections.sqlProjection("cast(login_time as date) loginTime",
						new String[] {"loginTime"}, new Type[] {StandardBasicTypes.DATE})))
				.list();
	}

	@Override	
	@SuppressWarnings("unchecked")
	public List<Login> getUsers(Date startDate, Date endDate) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Login.class);
		criteria.setProjection(Projections.distinct(Projections.property("user")));
		criteria.addOrder(Order.asc("user"));
			
		if (startDate != null)
			criteria.add(Restrictions.sqlRestriction("cast(login_time as date) >= ?",
					new Object[] {startDate}, new Type[] {StandardBasicTypes.DATE}));
		if (endDate != null)
			criteria.add(Restrictions.sqlRestriction("cast(login_time as date) <= ?",
					new Object[] {endDate}, new Type[] {StandardBasicTypes.DATE}));
		return criteria.list();	
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Login> getLogins(Date startDate, Date endDate, String[] attribute1, String[] attribute2, String[] attribute3,
			String[] attribute4) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Login.class);

		if (startDate != null)
			criteria.add(Restrictions.sqlRestriction("cast(login_time as date) >= ?",
					new Object[] {startDate}, new Type[] {StandardBasicTypes.DATE}));
		if (endDate != null)
			criteria.add(Restrictions.sqlRestriction("cast(login_time as date) <= ?",
					new Object[] {endDate}, new Type[] {StandardBasicTypes.DATE}));
		
		criteria = addDisjunctions(criteria, attribute1);

		criteria = addDisjunctions(criteria, attribute2);

		criteria = addDisjunctions(criteria, attribute3);

		criteria = addDisjunctions(criteria, attribute4);
		
		return criteria.list();
	}

	@Override
	public long insert() {
		String[] users = new String[] {"Magneto", "Xavier", "Vulcan", "Psylocke", "Storm", "Mystique", "Beast", "Cyclops", "Jean Grey", "Havoc",
				"Iceman", "Angle", "Phoenix", "Mimic", "Changeling", "Polaris", "Nightcrawler", "Wolverine", "Banshee", "Sunfire", "Thunderbird"};
		String[] attributes = new String[] {"good", "bad", "evil", "awesome", "mysterious", "fast", "dark", "lackadaisical", "cute", null};
		long startDate = 1451577600000L;	// Jan 01 2016 00:00:00
		long endDate = 1476030511469L;		// Oct 10 2016 00:28:31
		
		Random rand = new Random();
		Login login = null;
		for (int index = 0 ; index < 100000 ; index++) {
			login = new Login();
			login.setLoginTime(new Date(startDate + (long)(rand.nextDouble() * (endDate - startDate))));
			login.setUser(users[rand.nextInt(users.length)]);
			login.setAttribute1(attributes[rand.nextInt(attributes.length)]);
			login.setAttribute2(attributes[rand.nextInt(attributes.length)]);
			login.setAttribute3(attributes[rand.nextInt(attributes.length)]);
			login.setAttribute4(attributes[rand.nextInt(attributes.length)]);

			sessionFactory.getCurrentSession().save(login);
		}
		
		sessionFactory.getCurrentSession().flush();
		sessionFactory.getCurrentSession().clear();
		return 1;
	}
	
	private Criteria addDisjunctions(Criteria criteria, String[] attribute) {
		if (attribute != null && attribute.length != 0) {
			Disjunction disjunction = Restrictions.disjunction();
			for (int index = 0; index < attribute.length; index++)
				disjunction.add(Restrictions.eq("attribute4", attribute[index]));
			criteria.add(disjunction);
		}
		return criteria;
	}

}
