package com.nupt.resume;

import java.util.List;

import com.nupt.entity.ExperienceEntity;
import com.nupt.entity.PublicationEntity;
import com.nupt.entity.ResumeEntity;

public class ResumeDao {
	private ResumeEntity resumeEntity;
	private List<ExperienceEntity> expList;
	private List<PublicationEntity> pubList;
	
	/**
	 * 创建我的简历
	 * @param resumeEntity
	 * @param expList
	 * @param pubList
	 * @return
	 */
	public boolean createResume(ResumeEntity resumeEntity,List<ExperienceEntity> expList,List<PublicationEntity> pubList){
		//执行模板函数
		ResumeDaoCreateResumeImpl resumeDaoCreateResumeImpl = new ResumeDaoCreateResumeImpl(resumeEntity, expList, pubList);
		boolean result = resumeDaoCreateResumeImpl.hibernateOperation();
		return result;
	}

	/**
	 * 获取指定用户的简历详情
	 * @param user_id
	 * @return
	 */
	public boolean getResumeByUserId(long user_id){
		//执行模板函数
		ResumeDaoGetResumeByUserIdImpl resumeDaoGetResumeByUserIdImpl = new ResumeDaoGetResumeByUserIdImpl(user_id);
		boolean result = resumeDaoGetResumeByUserIdImpl.hibernateOperation();
		
		//获取结果
		this.resumeEntity = resumeDaoGetResumeByUserIdImpl.getResumeEntity();
		this.expList = resumeDaoGetResumeByUserIdImpl.getExpList();
		this.pubList = resumeDaoGetResumeByUserIdImpl.getPubList();
		return result;
	}
	
	/**
	 * 修改指定用户的简历
	 * @param resumeEntity
	 * @param expList
	 * @param pubList
	 * @return
	 */
	public boolean updateResume(ResumeEntity resumeEntity,List<ExperienceEntity> expList,List<PublicationEntity> pubList){
		//执行模板函数
		ResumeDaoUpdateResumeImpl resumeDaoUpdateResumeImpl = new ResumeDaoUpdateResumeImpl(resumeEntity, expList, pubList);
		return resumeDaoUpdateResumeImpl.hibernateOperation();
	}
	
	/**
	 * 删除指定用户的简历
	 * @param user_id
	 * @param resume_id
	 * @return
	 */
	public boolean deleteResume(long user_id){
		//执行模板函数
		ResumeDaoDeleteResumeImpl resumeDaoDeleteResumeImpl = new ResumeDaoDeleteResumeImpl(user_id);
		return resumeDaoDeleteResumeImpl.hibernateOperation();
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
