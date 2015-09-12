package com.nupt.resume;

import java.util.List;

import org.hibernate.Session;

import com.nupt.entity.ExperienceEntity;
import com.nupt.entity.PublicationEntity;
import com.nupt.entity.ResumeEntity;
import com.nupt.tools.HibernateTemplate;

public class ResumeDaoUpdateResumeImpl extends HibernateTemplate {
	private ResumeEntity resumeEntity;
	private List<ExperienceEntity> expList;
	private List<PublicationEntity> pubList;
	
	public ResumeDaoUpdateResumeImpl(ResumeEntity resumeEntity, List<ExperienceEntity> expList,
			List<PublicationEntity> pubList) {
		super();
		this.resumeEntity = resumeEntity;
		this.expList = expList;
		this.pubList = pubList;
	}


	@Override
	protected Session handle(Session session) {
		//修改resume表
		session.save(resumeEntity);
		
		//修改项目经验表
		if(expList.size()>0)
			for(ExperienceEntity e : expList)
				session.save(e);
		
		//修改著作表
		if(pubList.size()>0)
			for(PublicationEntity e : pubList)
				session.save(e);
		
		return session;
	}

}
