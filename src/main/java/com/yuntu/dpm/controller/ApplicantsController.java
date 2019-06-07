package com.yuntu.dpm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yuntu.dpm.bean.Applicant;
import com.yuntu.dpm.bean.Msg;
import com.yuntu.dpm.service.ApplicantService;

/**
 * 申报人数据交互控制器
 *
 */

@Controller
public class ApplicantsController {
	@Autowired
	private ApplicantService applicantService;
	/**
	 * 查询所有申报人，返回json数据
	 * @throws Exception 
	 */
	@RequestMapping("/applicants")
	@ResponseBody
	public Msg getApplicants() throws Exception {
		
		List<Applicant> applicants = applicantService.findAll(null);
		return Msg.success().addd("applicants", applicants);
	}


}
