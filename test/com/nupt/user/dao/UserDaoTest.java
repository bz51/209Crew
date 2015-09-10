package com.nupt.user.dao;

import org.junit.BeforeClass;
import org.junit.Test;

import com.nupt.entity.UserEntity;

public class UserDaoTest {
	private UserDao dao = new UserDao();
	
	@Test
	public void testLogin() {
		UserEntity e = new UserEntity();
		e.setUsername("chai");
		e.setPassword("123456");
		e = dao.login(e);
		
		System.out.println("id="+e.getId());
	}

}
