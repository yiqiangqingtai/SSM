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

@RunWith(SpringJUnit4ClassRunner.class) //����spring�ṩ�Ĳ���ģ��

@ContextConfiguration(locations= {"classpath:applicationContext.xml"})
public class MapperTest {
	@Autowired
	ApplicantMapper applicantMapper;
	@Autowired
	ProjectInfoMapper projectInfoMapper;
	@Autowired  //��������������SqlSession���Ӵ��л�ȡ������mapper����
	SqlSession sqlSession;
	
	@Test
	public void testUCRD() throws ParseException {
		//����spring��IOC����
		//ApplicationContext ac = new ClassPathXmlApplicationContext("����ȫ����");
		//�������л�ȡmapper����
		//ApplicantMapper mapper =  (ApplicantMapper) ac.getBean("mapper���������");
		System.out.println("applicantMapper");
		//���������Ŀ
		//��������������ӵ�mapper�ӿڶ���
		ProjectInfoMapper infoMapper = sqlSession.getMapper(ProjectInfoMapper.class);
		//ѭ������
		for(int i=0; i<50;i++) {
			//�Զ������ַ���
			String name =UUID.randomUUID().toString().substring(0, 8)+"��Ŀ"+i;
			infoMapper.insertSelective(new ProjectInfo(
					null,name,
					new SimpleDateFormat("yyyy-MM-dd").parse((1997+i)+"-11-11"),
					new SimpleDateFormat("yyyy-MM-dd").parse((1999+i)+"-11-11"),
					i%3,i%7+1
					));
			
			
			
		}
		
	}
	
	
}
