package com.nupt.resume;

import java.util.List;

import com.nupt.core.CoreDao;
import com.nupt.core.Parameter;
import com.nupt.entity.ExperienceEntity;
import com.nupt.entity.PublicationEntity;
import com.nupt.entity.ResumeEntity;

public class ResumeService {
	private CoreDao coreDao = new CoreDao();
	private ResumeDao resumeDao = new ResumeDao();
	private ResumeEntity resumeEntity;
	private List<ExperienceEntity> expList;
	private List<PublicationEntity> pubList;
	
	/**
	 * 创建我的简历
	 * @param entity 除了state、id字段，其他所有字段都不能为空！
	 * @return
	 */
	public boolean createResume(ResumeEntity resumeEntity,List<ExperienceEntity> expList,List<PublicationEntity> pubList){
		//健壮性判断
		if(resumeEntity==null || resumeEntity.getAge()==0 || resumeEntity.getDegree()==null
				|| resumeEntity.getHometown()==null || resumeEntity.getInterest()==null || resumeEntity.getMail()==null
				|| resumeEntity.getMotto()==null || resumeEntity.getName()==null || resumeEntity.getPhone()==null
				|| resumeEntity.getPic()==null || resumeEntity.getRole()==0 || resumeEntity.getSchool()==null 
				|| resumeEntity.getSkill()==null || resumeEntity.getUser_id()==0)
			return false;
		
		
		//设置state
		resumeEntity.setState(1);
		for(ExperienceEntity e : expList)
			e.setState(1);
		for(PublicationEntity e : pubList)
			e.setState(1);
		
		//执行插入
		return resumeDao.createResume(resumeEntity, expList, pubList);
	}
	
	
	/**
	 * 获取所有/我的简历列表
	 * @param user_id 当user_id=0表示获取所有简历
	 * @return 返回null表示执行出错
	 */
	public List<ResumeEntity> getResumeList(long user_id){
		//健壮性判断
		if(user_id<0)
			return null;
		
		//创建hql
		String hql;
		if(user_id!=0)
			hql = "from "+Parameter.ResumeEntity+" where user_id="+user_id+" and state=1";
		else
			hql = "from "+Parameter.ResumeEntity+" where state=1";
		
		//执行查询
		return coreDao.queryListByHql(hql);
	}
	
	
	
	/**
	 * 获取指定用户的简历详情
	 * @param user_id
	 * @return
	 */
	public boolean getResumeByUserId(long user_id){
		//健壮性判断
		if(user_id<=0)
			return false;
		
		//执行查询
		boolean result = resumeDao.getResumeByUserId(user_id);
		
		//返回结果
		if(result){
			this.resumeEntity = resumeDao.getResumeEntity();
			this.expList = resumeDao.getExpList();
			this.pubList = resumeDao.getPubList();
		}
		
		//返回结果
		return result;
	}

	
	/**
	 * 修改指定用户的简历
	 * @param resumeEntity
	 * @param expList
	 * @param pubList
	 * @return
	 */
	public boolean updateResumeByUserId(ResumeEntity resumeEntity,List<ExperienceEntity> expList,List<PublicationEntity> pubList){
		// 健壮性判断
		if (resumeEntity == null || resumeEntity.getAge() == 0 || resumeEntity.getDegree() == null
				|| resumeEntity.getHometown() == null || resumeEntity.getInterest() == null
				|| resumeEntity.getMail() == null || resumeEntity.getMotto() == null || resumeEntity.getName() == null
				|| resumeEntity.getPhone() == null || resumeEntity.getPic() == null || resumeEntity.getRole() == 0
				|| resumeEntity.getSchool() == null || resumeEntity.getSkill() == null
				|| resumeEntity.getUser_id() == 0)
			return false;

		// 设置state
		resumeEntity.setState(1);
		for (ExperienceEntity e : expList)
			e.setState(1);
		for (PublicationEntity e : pubList)
			e.setState(1);

		// 执行更新
		return resumeDao.updateResume(resumeEntity, expList, pubList);
	}

	/**
	 * 删除指定用户的简历
	 * @param user_id
	 * @return
	 */
	public boolean deleteResumeByUserId(long user_id){
		//健壮性判断
		if(user_id<=0)
			return false;
		
		//执行删除
		return resumeDao.deleteResume(user_id);
	}


	public ResumeEntity getResumeEntity() {
		return resumeEntity;
	}


	public List<PublicationEntity> getPubList() {
		return pubList;
	}


	public List<ExperienceEntity> getExpList() {
		return expList;
	}
	
	
	
}
