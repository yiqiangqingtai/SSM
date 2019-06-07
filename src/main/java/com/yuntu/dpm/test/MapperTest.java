package com.yuntu.dpm.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yuntu.dpm.bean.Applicant;
import com.yuntu.dpm.bean.ProjectInfo;
import com.yuntu.dpm.dao.ApplicantMapper;
import com.yuntu.dpm.dao.ProjectInfoMapper;

@RunWith(SpringJUnit4ClassRunner.class) //运行spring提供的测试模块

@ContextConfiguration(locations= {"classpath:applicationContext.xml"})
public class MapperTest {
	@Autowired
	ApplicantMapper applicantMapper;
	@Autowired
	ProjectInfoMapper projectInfoMapper;
	@Autowired  //用于批量操作的SqlSession，从此中获取操作的mapper对象
	SqlSession sqlSession;
	
	@Test
	public void testUCRD() throws ParseException {
		//创建spring的IOC容器
		//ApplicationContext ac = new ClassPathXmlApplicationContext("容器全类名");
		//从容器中获取mapper对象
		//ApplicantMapper mapper =  (ApplicantMapper) ac.getBean("mapper对象的名称");
		System.out.println("applicantMapper");
		//批量添加项目
		//创建用于批量添加的mapper接口对象
		ProjectInfoMapper infoMapper = sqlSession.getMapper(ProjectInfoMapper.class);
		//循环操作
		for(int i=0; i<50;i++) {
			//自动生成字符串
			String name =UUID.randomUUID().toString().substring(0, 8)+"项目"+i;
			infoMapper.insertSelective(new ProjectInfo(
					null,name,
					new SimpleDateFormat("yyyy-MM-dd").parse((1997+i)+"-11-11"),
					new SimpleDateFormat("yyyy-MM-dd").parse((1999+i)+"-11-11"),
					i%3,i%7+1
					));
			
			
			
		}
		
	}
	
	
}
