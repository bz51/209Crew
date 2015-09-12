package com.nupt.publication;

import java.util.List;

import com.nupt.core.CoreDao;
import com.nupt.core.Parameter;
import com.nupt.entity.PublicationEntity;

public class PublicationService {
	private CoreDao coreDao = new CoreDao();
	
	/**
	 * 获取所有/指定用户出版物列表(user_id=0表示获取全部)
	 * @param user_id
	 * @return
	 */
	public List<PublicationEntity> getAllPublication(long user_id){
		//生成hql
		String hql;
		if(user_id==0)
			hql = "from "+Parameter.PublicationEntity+" where state=1";
		else
			hql = "from PublicationEntity where user_id="+user_id+" and state=1";
		
		//查询
		return coreDao.queryListByHql(hql);
	}
	
	
	/**
	 * 修改指定的出版物
	 * 注：PublicationEntity对象中所有元素都不得为空!(除了state)
	 * @param entity 含有修改的信息和原本的信息
	 * @return
	 */
	public boolean updatePublicationById(PublicationEntity entity){
		//PublicationEntity中若有字段为空(除了state)，则返回false
		if(entity==null || entity.getId()==0 || entity.getPartner()==null
				|| entity.getPub()==null || entity.getTime()==0
				|| entity.getTitle()==null || entity.getUrl()==null
				|| entity.getUser_id()==0)
			return false;
		
		//设置state
		entity.setState(1);
		
		//开始修改
		long id = coreDao.save(entity);
		if(id!=0)
			return true;
		return false;
	}
	
	
	/**
	 * 删除指定的出版物
	 * @param publication_id
	 * @return
	 */
	public boolean deletePublicationById(long publication_id){
		if(publication_id<=0)
			return false;
		
		//开始删除
		return coreDao.deleteUniqueById(publication_id, Parameter.PublicationEntity);
	}
}
