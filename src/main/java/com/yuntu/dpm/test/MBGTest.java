package com.yuntu.dpm.test;

import java.io.File;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yuntu.dpm.bean.Applicant;
import com.yuntu.dpm.service.ApplicantService;

public class MBGTest {
	
	
	public static void main(String[] args) throws Exception {
		/*  反向生成要运行的代码
		 List<String> warnings = new ArrayList<String>();
		   boolean overwrite = true;
		   File configFile = new File("mbg.xml");
		   ConfigurationParser cp = new ConfigurationParser(warnings);
		   Configuration config = cp.parseConfiguration(configFile);
		   DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		   MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		   myBatisGenerator.generate(null);
		*/
		ApplicationContext ac  = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		ApplicantService as = (ApplicantService) ac.getBean("service");
		
		List<Applicant> findAll = as.findAll(null);
		for (Applicant applicant : findAll) {
			System.out.println(applicant.toString());
		}
		
	}
	
	
}
