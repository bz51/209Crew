package com.nupt.resume;

import java.util.List;

import org.hibernate.Session;

import com.nupt.entity.ExperienceEntity;
import com.nupt.entity.PublicationEntity;
import com.nupt.entity.ResumeEntity;
import com.nupt.tools.HibernateTemplate;

public class ResumeDaoCreateResumeImpl extends HibernateTemplate {
	private ResumeEntity resumeEntity;
	private List<ExperienceEntity> expList;
	private List<PublicationEntity> pubList;
	
	public ResumeDaoCreateResumeImpl(ResumeEntity resumeEntity, List<ExperienceEntity> expList,
			List<PublicationEntity> pubList) {
		super();
		this.resumeEntity = resumeEntity;
		this.expList = expList;
		this.pubList = pubList;
	}


	@Override
	protected Session handle(Session session) {
		//往resume表中插入ResumeEntity
		session.save(resumeEntity);
		//往experience 表中插入
		for(ExperienceEntity e : expList)
			session.save(e);
		//往publication表中插入多条记录
		for(PublicationEntity e : pubList)
			session.save(e);
		
		return session;
	}

}
