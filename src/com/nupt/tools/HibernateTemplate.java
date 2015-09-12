package com.nupt.tools;

import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 * 实现对数据库操作的模板类，具体的操作定义在函数
 * @author chibozhou
 */
public abstract class HibernateTemplate {

	/**
	 * 将具体的操作封装在一个事务里
	 */
	public boolean hibernateOperation(){
		boolean result = true;

		try {
			Session session = HibernateSessionFactory.getSession();
			session.beginTransaction();
		
			session = handle(session);
		
			session.getTransaction().commit();
		} catch (HibernateException e) {
			result = false;
			e.printStackTrace();
		}finally{
			HibernateSessionFactory.closeSession();
		}
		return result;
	}
	
	/**
	 * 具体的操作
	 */
	protected abstract Session handle(Session session);
	
}