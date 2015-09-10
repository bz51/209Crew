package com.nupt.user.dao;

import java.util.List;

import org.hibernate.Session;

import com.nupt.core.tools.HibernateTemplate;
import com.nupt.entity.UserEntity;

/**
 * 注册函数的实现类
 * @author chibozhou
 *
 */
public class UserDaoLoginImpl extends HibernateTemplate {
	private UserEntity entity;
	private String reason = "注册成功";/** 注册失败原因 */
	
	public UserDaoLoginImpl(UserEntity entity) {
		super();
		this.entity = entity;
	}

	@Override
	protected Session handle(Session session) {
		//判断用户名是否存在
		long count = (long) session.createQuery("select count(username) from UserEntity as user where username=:usernameString")
				.setString("usernameString", entity.getUsername())
				.uniqueResult();
		
		//若不存在，就注册
		if(count==0){
			//注册
			int id = (int) session.save(entity);
			//注册失败
			if(id<1){
				this.entity = null;
				this.reason = "注册发生异常";
			}
			//注册成功
			else{
				entity.setId(id);
			}
		}
		
		//用户名存在
		else{
			this.entity = null;
			this.reason = "用户名存在！";
		}
		return session;
	}

	public UserEntity getEntity() {
		return entity;
	}

	public String getReason() {
		return reason;
	}


	
	

}
