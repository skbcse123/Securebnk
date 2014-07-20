/**
 * 
 */
package com.mit.DaoImpl;

import java.util.Collection;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import com.mit.Dao.IDao;
import com.securebank.exception.SecureBankException;

/**
 * @author khader
 *
 */
public class DaoImpl implements IDao {
	private SessionFactory sessionFactory;
	private Session session;


	/**
	 * @return the session
	 */
	public Session getSession() {
		return session;
	}

	/**
	 * @param session the session to set
	 */
	public void setSession(Session session) {
		this.session = session;
	}

	/**
	 * @return the sessionFactory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * @param sessionFactory the sessionFactory to set
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	
	private void commitSession() {
		System.out.println("in Dao commitSession()");
		session.getTransaction().commit();
	}
	

	private Session openSession(){
		System.out.println("in Dao openSession()");
		sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();		
		session = sessionFactory.openSession();
		session.beginTransaction();
		return session;
	}
	



	@Override
	public Collection<?> loadList(String query) {
		return openSession().createQuery(query).list();
	}

	@Override
	public void saveInDb(Object object)
	{	openSession().saveOrUpdate(object);
		commitSession();
	}

	@Override
	public void deleteFromDb(String query) {
		Query queryObj = openSession().createQuery(query);
	//	Query queryObj = sessionFactory.getCurrentSession().createQuery(query);
		queryObj.executeUpdate();
		commitSession();
	}

	@Override
	public void updateInDb(Object object) {
		openSession().saveOrUpdate(object);
		commitSession();
	}

	@Override
	public Object loadUniqueObject(String query) {
		Object uniqueObject = null;
		final List objList = openSession().createQuery(query).list();
		
		if (objList != null && objList.size() > 0) 
		{
			uniqueObject = objList.get(0);
		}

		return uniqueObject;
	}
	
	public Object DeleteUniqueObject(String query)
	{
		
		Object uniqueObject = null;
		final List objList = openSession().createQuery(query).list();
		
		if (objList != null && objList.size() > 0) 
		{
			uniqueObject = objList.get(0);
		}

		return uniqueObject;
		
	}

	
	@Override
	public void delete(Object object) {
		session = sessionFactory.getCurrentSession();
		session.delete(object);
	}
	
}
