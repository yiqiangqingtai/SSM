package com.yuntu.dpm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuntu.dpm.bean.Applicant;
import com.yuntu.dpm.bean.ApplicantExample;
import com.yuntu.dpm.dao.ApplicantMapper;

/**
 * 申报人业务处理
 * @author 95491
 *
 */

@Service("appService")
public class ApplicantService {
	
	@Autowired
	ApplicantMapper mapper;
	
	public List<Applicant> findAll(ApplicantExample example) throws Exception {
		try {
			return mapper.selectByExample(example);
		} catch (Exception e) {
			throw e;
		}
	}

}
