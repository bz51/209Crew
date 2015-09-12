package com.nupt.user;

import com.nupt.entity.UserEntity;

public class UserDao{
	/** 注册失败的原因 */
	private String reason = "注册成功";
	
	/**
	 * 注册
	 * @param entity
	 * @return
	 */
	public UserEntity login(UserEntity entity){
		UserDaoLoginImpl userDaoLoginImpl = new UserDaoLoginImpl(entity);
		userDaoLoginImpl.hibernateOperation();
		
		//对注册结果进行判断
		entity = userDaoLoginImpl.getEntity();
		if(entity==null){
			this.reason = userDaoLoginImpl.getReason();
		}
		
		return entity;
	}

	public String getReason() {
		return reason;
	}
	
	
}
