package com.nupt.user;

import java.util.List;

import com.nupt.core.CoreDao;
import com.nupt.core.Parameter;
import com.nupt.entity.UserEntity;

public class UserService {
	private CoreDao coreDao = new CoreDao();
	private UserDao userDao = new UserDao();
	private String reason;
	
	/**
	 * 登录
	 * @param entity 
	 * @return 返回null表示出错！错误原因写在reason中
	 */
	public UserEntity signin(UserEntity entity){
		//健壮性判断
		if(entity==null || entity.getPassword()==null || entity.getUsername()==null){
			this.reason = "用户名、密码不能为空!";
			return null;
		}

		//创建hql
		String hql = "from "+Parameter.UserEntity+" where username='"+entity.getUsername()+"' and password='"+entity.getPassword()+"' and state=1";
		
		//执行登录操作
		List<UserEntity> list = coreDao.queryListByHql(hql);
		
		//判断处理结果
		if(list==null){
			this.reason = "数据库查询发生异常，请重试！";
			return null;
		}
		else if(list.size()==0){
			this.reason = "用户名、密码错误！";
			return null;
		}
		else{
			return list.get(0);
		}
	}

	
	/**
	 * 注册
	 * @param entity 
	 * @return 返回null表示出错！错误原因写在reason中
	 */
	public UserEntity login(UserEntity entity){
		//健壮性判断
		if(entity==null || entity.getPassword()==null || entity.getRole()<=0 || entity.getUsername()==null){
			this.reason = "用户名、密码、用户类型不能为空！";
			return null;
		}
		
		//设置state
		entity.setState(1);
		
		//执行注册操作
		entity = userDao.login(entity);
		
		//判断注册结果
		if(entity==null){
			this.reason = userDao.getReason();
			return null;
		}
		else
			return entity;
	}
	
	
	public String getReason() {
		return reason;
	}
	
}
