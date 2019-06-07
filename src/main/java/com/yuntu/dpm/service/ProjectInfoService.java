package com.yuntu.dpm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuntu.dpm.bean.ProjectInfo;
import com.yuntu.dpm.bean.ProjectInfoExample;
import com.yuntu.dpm.bean.ProjectInfoExample.Criteria;
import com.yuntu.dpm.dao.ProjectInfoMapper;

/**
 * 项目业务处理
 * @author 95491
 *
 */
@Service("proService")
public class ProjectInfoService {
		@Autowired
		private ProjectInfoMapper projectInfoMapper;
		
		/**
		 * 查询所有的项目
		 * @return
		 */
		public List<ProjectInfo> getAll(){
			return projectInfoMapper.selectByExampleWithApplicant(null);
		}
		/**
		 * 添加项目信息
		 * @param projectInfo
		 */
		public void saveProjectInfo(ProjectInfo projectInfo) {
			projectInfoMapper.insertSelective(projectInfo);
		}
		/**
		 * 修改之前要根据pi_Id查询出单个项目的信息
		 */
		public ProjectInfo getProjectInfo(Integer pi_Id) {
			return projectInfoMapper.selectByPrimaryKeyWithApplicant(pi_Id);
		}
		/**
		 * 根据主键修改信息
		 */
		public void modifyProjectInfo(ProjectInfo projectInfo) {
			projectInfoMapper.updateByPrimaryKeySelective(projectInfo);
		}
		/**
		 * 根据单个主键删除 
		 */
		public void removeProjectInfoById(Integer pi_Id) {
			projectInfoMapper.deleteByPrimaryKey(pi_Id);
		}
		/**
		 * 根据多个主键删除
		 */
		public void removeProjectInfoByIds(List<Integer> ids) {
			ProjectInfoExample example = new ProjectInfoExample();
			Criteria criteria = example.createCriteria();
			/**
			 * criteria.andPiIdIn(ids); 相当于：
			 * 再数据库中执行sql语句：delete form projectinfo where pi_id in（集合）
			 */
			criteria.andPiIdIn(ids);
			projectInfoMapper.deleteByExample(example);
		}
		/**
		 * i 验证项目名是否存在
		 * @param projectName
		 * @return
		 */
		public boolean checkProjectInfo(String projectName) {
			//创建查询用的增强对象
			ProjectInfoExample example = new ProjectInfoExample();
			Criteria criteria = example.createCriteria();
			/**
			 * criteria.andPiIdIn(ids); 相当于：
			 * 再数据库中执行sql语句：SELECT * FROM PROJECTINFO WHERE PI_PROJECTNAME = ?
			 */
			criteria.andPiProjectnameEqualTo(projectName);
			//根据条件查询条目数
			long count = projectInfoMapper.countByExample(example);
			
			return count==0;
		}
		
		
	
}
