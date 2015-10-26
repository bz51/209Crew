package com.nupt.resume;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.synth.SynthSpinnerUI;

import org.junit.Test;

import com.nupt.entity.ExperienceEntity;
import com.nupt.entity.PublicationEntity;
import com.nupt.entity.ResumeEntity;

public class ResumeServiceTest {
	private ResumeService service = new ResumeService();
	
	@Test
	public void testCreateResume() {
		// 测试用例1：正常创建
		ResumeEntity resumeEntity1 = new ResumeEntity();
		resumeEntity1.setAge(21);
		resumeEntity1.setDegree("硕士");
		resumeEntity1.setHometown("启东");
		resumeEntity1.setInterest("大数据");
		resumeEntity1.setMail("33333@qq.com");
		resumeEntity1.setMotto("dcdcdcdcdcdc");
		resumeEntity1.setName("柴博周");
		resumeEntity1.setPhone("110");
		resumeEntity1.setPic("图片");
		resumeEntity1.setRole(1);
		resumeEntity1.setSchool("南邮");
		resumeEntity1.setSkill("Java");
		resumeEntity1.setUser_id(1);
		
		List<ExperienceEntity> expList = new ArrayList<ExperienceEntity>();
		
		ExperienceEntity experienceEntity1 = new ExperienceEntity();
		experienceEntity1.setEnd_time(100000);
		experienceEntity1.setIntro("介绍介绍…………………………");
		experienceEntity1.setName("经验名字");
		experienceEntity1.setStart_time(2200000);
		experienceEntity1.setTask("任务任务任务………………");
		experienceEntity1.setUrl("www.baidu,com");
		experienceEntity1.setUser_id(1);
		
		ExperienceEntity experienceEntity2 = new ExperienceEntity();
		experienceEntity2.setEnd_time(100000);
		experienceEntity2.setIntro("介绍介绍2…………………………");
		experienceEntity2.setName("经验名字2");
		experienceEntity2.setStart_time(2200000);
		experienceEntity2.setTask("任务任务任务2………………");
		experienceEntity2.setUrl("www.baidu,com2");
		experienceEntity2.setUser_id(1);
		
		expList.add(experienceEntity1);
		expList.add(experienceEntity2);
		
		
		List<PublicationEntity> pubList = new ArrayList<PublicationEntity>();
		
		PublicationEntity publicationEntity1 = new PublicationEntity();
		
		publicationEntity1.setPartner("柴毛毛");
		publicationEntity1.setPub("论文题目");
		publicationEntity1.setTime(200000);
		publicationEntity1.setTitle("标题");
		publicationEntity1.setUrl("www.youku.com");
		publicationEntity1.setUser_id(1);
		
		pubList.add(publicationEntity1);
		
		testCreateResumeSub(resumeEntity1, expList, pubList, 1);

		
	}
	private void testCreateResumeSub(ResumeEntity resumeEntity,List<ExperienceEntity> expList,List<PublicationEntity> pubList,int i){
		System.out.println("执行结果："+service.createResume(resumeEntity, expList, pubList));
	}
	
	
	
	
	@Test
	public void testGetResumeList() {
		
		// 1.获取存在用户的存在的简历
		ResumeEntity entity1 = new ResumeEntity();
		entity1.setUser_id(1);
		testGetResumeListSub(entity1, 1);
		
		// 2.获取一个存在用户的不存在的简历
		ResumeEntity entity2 = new ResumeEntity();
		entity2.setUser_id(3);
		testGetResumeListSub(entity2, 2);
		
		// 3.获取一个不存在用户的简历
		ResumeEntity entity3 = new ResumeEntity();
		entity3.setUser_id(1000);
		testGetResumeListSub(entity3, 3);
		
		// 4.查询全部简历：user_id＝＝0
		ResumeEntity entity4 = new ResumeEntity();
		entity4.setUser_id(0);
		testGetResumeListSub(entity4, 4);
	}
	private void testGetResumeListSub(ResumeEntity entity,int i){
		List<ResumeEntity> list = service.getResumeList(entity.getUser_id());
		if(list==null)
			System.out.println("测试用例"+i+"执行结果：发生异常");
		else if(list.size()==0)
			System.out.println("测试用例"+i+"执行结果：结果个数为0");
		else{
			System.out.println("测试用例"+i+"执行结果：查询到如下结果：");
			for(ResumeEntity e : list){
				System.out.println("age:"+e.getAge());
				System.out.println("degree:"+e.getDegree());
				System.out.println("hometown:"+e.getHometown());
				System.out.println("id:"+e.getId());
				System.out.println("interest:"+e.getInterest());
				System.out.println("mail:"+e.getMail());
				System.out.println("motto:"+e.getMotto());
				System.out.println("name:"+e.getName());
				System.out.println("phone:"+e.getPhone());
				System.out.println("pic:"+e.getPic());
				System.out.println("role:"+e.getRole());
				System.out.println("school:"+e.getSchool());
				System.out.println("skill:"+e.getSkill());
				System.out.println("state:"+e.getState());
				System.out.println("user_id:"+e.getUser_id());
				System.out.println("-------------------");
			}
		}
		
	}

	@Test
	public void testGetResumeByUserId() {
		long user_id = 0;
		
		// 1.获取一个存在用户的简历、存在项目经验、存在著作
		testGetResumeByUserIdSub(1,1);
		
		// 2.获取一个不存在用户的简历
		testGetResumeByUserIdSub(100,2);
		
		// 3.获取一个存在用户的简历、不存在项目经验、不存在著作
		testGetResumeByUserIdSub(2,3);
	}
	private void testGetResumeByUserIdSub(long user_id,int i){
		boolean result = service.getResumeByUserId(user_id);
		if(result){
			System.out.println("测试用例"+i+"数据库查询成功！");
			System.out.println("简历如下：");
			ResumeEntity e = service.getResumeEntity();
			System.out.println("age:"+e.getAge());
			System.out.println("degree:"+e.getDegree());
			System.out.println("hometown:"+e.getHometown());
			System.out.println("id:"+e.getId());
			System.out.println("interest:"+e.getInterest());
			System.out.println("mail:"+e.getMail());
			System.out.println("motto:"+e.getMotto());
			System.out.println("name:"+e.getName());
			System.out.println("phone:"+e.getPhone());
			System.out.println("pic:"+e.getPic());
			System.out.println("role:"+e.getRole());
			System.out.println("school:"+e.getSchool());
			System.out.println("skill:"+e.getSkill());
			System.out.println("state:"+e.getState());
			System.out.println("user_id:"+e.getUser_id());
			System.out.println("-------------------");
			System.out.println("项目经验条数："+service.getExpList().size()+",如下：");
			for(ExperienceEntity e2 : service.getExpList()){
				System.out.println("End_time:"+e2.getEnd_time());
				System.out.println("Id:"+e2.getId());
				System.out.println("Intro:"+e2.getIntro());
				System.out.println("Name:"+e2.getName());
				System.out.println("start_time:"+e2.getStart_time());
				System.out.println("state:"+e2.getState());
				System.out.println("task:"+e2.getTask());
				System.out.println("url:"+e2.getUrl());
				System.out.println("User_id:"+e2.getUser_id());
			}
			System.out.println("-------------------");
			System.out.println("著作的条数："+service.getPubList().size()+",如下：");
			for(PublicationEntity e2 : service.getPubList()){
				System.out.println("Id::"+e2.getId());
				System.out.println("Partner:"+e2.getPartner());
				System.out.println("Pub:"+e2.getPub());
				System.out.println("State:"+e2.getState());
				System.out.println("Time:"+e2.getTime());
				System.out.println("Title:"+e2.getTitle());
				System.out.println("Url:"+e2.getUrl());
				System.out.println("User_id:"+e2.getUser_id());
			}
		}else{
			System.out.println("测试用例"+i+"数据库查询失败！！！！！！");
		}
		System.out.println("************************************");
		System.out.println("************************************");
		System.out.println("************************************");
	}

	@Test
	/**
	 * 问题1:使用merge进行更新仍然是insert而不是update
	 * 问题2:当用户删掉一条项目经验或著作后，减少的那条仍然存在
	 */
	public void testUpdateResumeByUserId() {
		// 测试用例1：正常创建
		ResumeEntity resumeEntity1 = new ResumeEntity();
		resumeEntity1.setAge(21);
		resumeEntity1.setDegree("硕士修改");
		resumeEntity1.setHometown("启东");
		resumeEntity1.setInterest("大数据");
		resumeEntity1.setMail("33333@qq.com");
		resumeEntity1.setMotto("dcdcdcdcdcdc");
		resumeEntity1.setName("柴博周");
		resumeEntity1.setPhone("110");
		resumeEntity1.setPic("图片");
		resumeEntity1.setRole(1);
		resumeEntity1.setSchool("南邮");
		resumeEntity1.setSkill("Java");
		resumeEntity1.setUser_id(1);
		
		List<ExperienceEntity> expList = new ArrayList<ExperienceEntity>();
		
		ExperienceEntity experienceEntity1 = new ExperienceEntity();
		experienceEntity1.setEnd_time(100000);
		experienceEntity1.setIntro("介绍介绍修改…………………………");
		experienceEntity1.setName("经验名字");
		experienceEntity1.setStart_time(2200000);
		experienceEntity1.setTask("任务任务任务………………");
		experienceEntity1.setUrl("www.baidu,com");
		experienceEntity1.setUser_id(1);
		
		ExperienceEntity experienceEntity2 = new ExperienceEntity();
		experienceEntity2.setEnd_time(100000);
		experienceEntity2.setIntro("介绍介绍2修改…………………………");
		experienceEntity2.setName("经验名字2");
		experienceEntity2.setStart_time(2200000);
		experienceEntity2.setTask("任务任务任务2………………");
		experienceEntity2.setUrl("www.baidu,com2");
		experienceEntity2.setUser_id(1);
		
		expList.add(experienceEntity1);
		expList.add(experienceEntity2);
		
		
		List<PublicationEntity> pubList = new ArrayList<PublicationEntity>();
		
		PublicationEntity publicationEntity1 = new PublicationEntity();
		
		publicationEntity1.setPartner("柴毛毛");
		publicationEntity1.setPub("论文题目修改");
		publicationEntity1.setTime(200000);
		publicationEntity1.setTitle("标题");
		publicationEntity1.setUrl("www.youku.com");
		publicationEntity1.setUser_id(1);
		
		pubList.add(publicationEntity1);
		
		testUpdateResumeByUserIdSub(resumeEntity1, expList, pubList, 1);
	}
	private void testUpdateResumeByUserIdSub(ResumeEntity resumeEntity,List<ExperienceEntity> expList,List<PublicationEntity> pubList,int i){
		boolean result = service.updateResumeByUserId(resumeEntity, expList, pubList);
		System.out.println("更新结果："+result);
	}

	
	
	//待测试……
	public void testDeleteResumeByUserId() {
		// 1.删除一个存在的用户的简历，存在的论著，存在的项目经验
		testDeleteResumeByUserIdSub(1,1);
		
		// 2.删除一个存在的用户的简历，不存在的论著，不存在的项目经验
		testDeleteResumeByUserIdSub(2,2);
		
		// 3.删除一个不存在的用户的简历
		testDeleteResumeByUserIdSub(1000,3);
	}
	private void testDeleteResumeByUserIdSub(long user_id,int i){
		boolean result = service.deleteResumeByUserId(user_id);
		if(result)
			System.out.println("测试用例"+i+"删除成！");
		else
			System.out.println("测试用例"+i+"删除失败！！！");
	}

}
