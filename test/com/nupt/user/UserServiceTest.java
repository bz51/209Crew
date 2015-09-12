package com.nupt.user;

import org.junit.Test;

import com.nupt.entity.UserEntity;

public class UserServiceTest {
	private UserService service = new UserService();
	
	public void testSignin() {
		// 测试用例1：正常登录
		UserEntity entity1 = new UserEntity();
		entity1.setUsername("chai");
		entity1.setPassword("123456");
		testSigninSub(entity1,1);

		
		// 测试用例2：用户名、密码为空
		UserEntity entity2 = new UserEntity();
		entity2.setPassword("123456");
		testSigninSub(entity2,2);
		
		
		// 测试用例3：用户名、密码错误
		UserEntity entity3 = new UserEntity();
		entity3.setUsername("chai");
		entity3.setPassword("1234567");
		testSigninSub(entity3,3);
	}

	private void testSigninSub(UserEntity entity,int i){
		entity = service.signin(entity);
		if(entity==null){
			System.out.println("测试用例"+i+"登录失败，原因："+service.getReason());
		}else{
			System.out.println("测试用例"+i+"登录成功！");
			System.out.println("id:"+entity.getId());
			System.out.println("pass:"+entity.getPassword());
			System.out.println("resume_id:"+entity.getResume_id());
			System.out.println("role:"+entity.getRole());
			System.out.println("state:"+entity.getState());
			System.out.println("username:"+entity.getUsername());
		}
	}
	
	@Test
	public void testLogin() {
		// 测试用例1：正常注册
		UserEntity entity1 = new UserEntity();
		entity1.setUsername("bz51");
		entity1.setPassword("123456");
		entity1.setRole(1);
		testLoginSub(entity1,1);

		
		// 测试用例2：用户名、密码为空
		UserEntity entity2 = new UserEntity();
		entity2.setPassword("123456");
		testLoginSub(entity2,2);
		
		
		// 测试用例3：用户名存在
		UserEntity entity3 = new UserEntity();
		entity3.setUsername("chaibozhou");
		entity3.setPassword("1234567");
		entity3.setRole(1);
		testLoginSub(entity3,3);
	}
	private void testLoginSub(UserEntity entity,int i){
		entity = service.login(entity);
		
		if(entity==null){
			System.out.println("测试用例"+i+"注册失败，原因："+service.getReason());
		}else{
			System.out.println("测试用例"+i+"注册成功！");
			System.out.println("id:"+entity.getId());
			System.out.println("pass:"+entity.getPassword());
			System.out.println("resume_id:"+entity.getResume_id());
			System.out.println("role:"+entity.getRole());
			System.out.println("state:"+entity.getState());
			System.out.println("username:"+entity.getUsername());
		}
	}

}
