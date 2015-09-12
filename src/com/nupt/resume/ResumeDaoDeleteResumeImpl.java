package com.nupt.resume;

import org.hibernate.Session;

import com.nupt.tools.HibernateTemplate;

public class ResumeDaoDeleteResumeImpl extends HibernateTemplate {
	private long user_id;
	private long resume_id;
	
	public ResumeDaoDeleteResumeImpl(long user_id) {
		super();
		this.user_id = user_id;
	}

	@Override
	protected Session handle(Session session) {
		//删除resume表内容
		session.createQuery("update ResumeEntity set state=0 where id=:idString")
			.setLong("idString", user_id)
			.executeUpdate();
		
		//删除项目经验
		session.createQuery("update ExperienceEntity set state=0 where user_id=:idString")
			.setLong("idString", user_id)
			.executeUpdate();
		
		//删除论著
		session.createQuery("update PublicationEntity set state=0 where user_id=:idString")
			.setLong("idString", user_id)
			.executeUpdate();
		
		return session;
	}

}
