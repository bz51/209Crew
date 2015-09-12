package com.nupt.project;

import java.util.List;

import com.nupt.core.CoreDao;
import com.nupt.core.Parameter;
import com.nupt.entity.ProjectEntity;

public class ProjectService {
	private CoreDao coreDao = new CoreDao();
	
	/**
	 * 创建一个项目
	 * 注意：ProjectEntity所有字段(除了state、id)，都不能为空！
	 * @param entity
	 * @return
	 */
	public boolean createProject(ProjectEntity entity){
		//健壮性判断
		if(entity.getBackgd()==null || entity.getEnd_time()==0 || entity.getLogo()==null 
				|| entity.getName()==null || entity.getPartner()==null ||entity.getPic()==null
				|| entity.getStart_time()==0 || entity.getStructure()==null)
			return false;
		
		//设置state
		entity.setState(1);
		
		//保存项目
		long id = coreDao.save(entity);
		if(id!=0)
			return true;
		return false;
	}
	

	/**
	 * 获取所有项目(所有详情)
	 * @param project_id （project_id=0表示获取所有项目）
	 * @return 返回null表示发生错误
	 */
	public List<ProjectEntity> getProjectList(long project_id){
		if(project_id<0)
			return null;
		
		//创建hql
		String hql;
		if(project_id==0)
			hql = "from "+Parameter.ProjectEntity+" where state=1";
		else
			hql = "from "+Parameter.ProjectEntity+" where id="+project_id+" and state=1";
		
		//开始查找
		return coreDao.queryListByHql(hql);
	}
	
	
	
	/**
	 * 修改一个项目
	 * 注意：ProjectEntity所有字段(除了state)，都不能为空！
	 * @param entity
	 * @return
	 */
	public boolean updateProject(ProjectEntity entity){
		//健壮性判断
		if(entity.getId()==0 || entity.getBackgd()==null || entity.getEnd_time()==0 || entity.getLogo()==null 
				|| entity.getName()==null || entity.getPartner()==null ||entity.getPic()==null
				|| entity.getStart_time()==0 || entity.getStructure()==null)
			return false;
		
		//设置state
		entity.setState(1);
		
		//开始修改
		long id = coreDao.save(entity);
		if(id!=0)
			return true;
		else
			return false;
	}
	
	
	/**
	 * 删除一个项目
	 * @param project_id
	 * @return
	 */
	public boolean deleteProject(long project_id){
		if(project_id<=0)
			return false;
		
		//执行删除
		return coreDao.deleteUniqueById(project_id, Parameter.ProjectEntity);
	}
}
