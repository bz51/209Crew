package com.nupt.resume;

import java.util.List;

import org.hibernate.Session;

import com.nupt.entity.ExperienceEntity;
import com.nupt.entity.PublicationEntity;
import com.nupt.entity.ResumeEntity;
import com.nupt.tools.HibernateTemplate;

public class ResumeDaoGetResumeByUserIdImpl extends HibernateTemplate {
	private long user_id;
	private ResumeEntity resumeEntity;
	private List<ExperienceEntity> expList;
	private List<PublicationEntity> pubList;
	
	public ResumeDaoGetResumeByUserIdImpl(long user_id) {
		super();
		this.user_id = user_id;
	}


	@Override
	protected Session handle(Session session) {
		//根据id获取指定用户简历详情
		resumeEntity = (ResumeEntity) session.createQuery("from ResumeEntity where user_id=:idString")
				.setLong("idString", user_id)
				.uniqueResult();
		//根据用户id获取该用户所有项目经验
		expList = session.createQuery("from ExperienceEntity where user_id=:idString")
				.setLong("idString", user_id)
				.list();
		//根据用户id获取该用户所有著作
		pubList = session.createQuery("from PublicationEntity where user_id=:idString")
				.setLong("idString", user_id)
				.list();
		return session;
	}


	public ResumeEntity getResumeEntity() {
		return resumeEntity;
	}


	public List<ExperienceEntity> getExpList() {
		return expList;
	}


	public List<PublicationEntity> getPubList() {
		return pubList;
	}
	
	

}
