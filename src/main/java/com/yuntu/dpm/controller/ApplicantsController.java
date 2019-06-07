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
 * �걨�����ݽ���������
 *
 */

@Controller
public class ApplicantsController {
	@Autowired
	private ApplicantService applicantService;
	/**
	 * ��ѯ�����걨�ˣ�����json����
	 * @throws Exception 
	 */
	@RequestMapping("/applicants")
	@ResponseBody
	public Msg getApplicants() throws Exception {
		
		List<Applicant> applicants = applicantService.findAll(null);
		return Msg.success().addd("applicants", applicants);
	}


}
