package com.yuntu.dpm.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yuntu.dpm.bean.Applicant;
import com.yuntu.dpm.bean.ProjectInfo;
import com.yuntu.dpm.service.ApplicantService;
import com.yuntu.dpm.service.ProjectInfoService;

public class IndexTest {
	public static void main(String[] args) throws Exception {
		
		
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		ApplicantService as =   (ApplicantService) ac.getBean("appService");
		
		ProjectInfoService ps =  (ProjectInfoService) ac.getBean("proService");
		List<ProjectInfo> all = ps.getAll();
		for (ProjectInfo projectInfo : all) {
			System.out.println(projectInfo.toString());
		}
//		List<Applicant> findAll = as.findAll(null);
//		for (Applicant applicant : findAll) {
//			System.out.println(applicant.toString());
//		}
//		
		
		
	}
}
