package com.yuntu.dpm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuntu.dpm.bean.ProjectInfo;
import com.yuntu.dpm.bean.ProjectInfoExample;
import com.yuntu.dpm.bean.ProjectInfoExample.Criteria;
import com.yuntu.dpm.dao.ProjectInfoMapper;

/**
 * ��Ŀҵ����
 * @author 95491
 *
 */
@Service("proService")
public class ProjectInfoService {
		@Autowired
		private ProjectInfoMapper projectInfoMapper;
		
		/**
		 * ��ѯ���е���Ŀ
		 * @return
		 */
		public List<ProjectInfo> getAll(){
			return projectInfoMapper.selectByExampleWithApplicant(null);
		}
		/**
		 * �����Ŀ��Ϣ
		 * @param projectInfo
		 */
		public void saveProjectInfo(ProjectInfo projectInfo) {
			projectInfoMapper.insertSelective(projectInfo);
		}
		/**
		 * �޸�֮ǰҪ����pi_Id��ѯ��������Ŀ����Ϣ
		 */
		public ProjectInfo getProjectInfo(Integer pi_Id) {
			return projectInfoMapper.selectByPrimaryKeyWithApplicant(pi_Id);
		}
		/**
		 * ���������޸���Ϣ
		 */
		public void modifyProjectInfo(ProjectInfo projectInfo) {
			projectInfoMapper.updateByPrimaryKeySelective(projectInfo);
		}
		/**
		 * ���ݵ�������ɾ�� 
		 */
		public void removeProjectInfoById(Integer pi_Id) {
			projectInfoMapper.deleteByPrimaryKey(pi_Id);
		}
		/**
		 * ���ݶ������ɾ��
		 */
		public void removeProjectInfoByIds(List<Integer> ids) {
			ProjectInfoExample example = new ProjectInfoExample();
			Criteria criteria = example.createCriteria();
			/**
			 * criteria.andPiIdIn(ids); �൱�ڣ�
			 * �����ݿ���ִ��sql��䣺delete form projectinfo where pi_id in�����ϣ�
			 */
			criteria.andPiIdIn(ids);
			projectInfoMapper.deleteByExample(example);
		}
		/**
		 * i ��֤��Ŀ���Ƿ����
		 * @param projectName
		 * @return
		 */
		public boolean checkProjectInfo(String projectName) {
			//������ѯ�õ���ǿ����
			ProjectInfoExample example = new ProjectInfoExample();
			Criteria criteria = example.createCriteria();
			/**
			 * criteria.andPiIdIn(ids); �൱�ڣ�
			 * �����ݿ���ִ��sql��䣺SELECT * FROM PROJECTINFO WHERE PI_PROJECTNAME = ?
			 */
			criteria.andPiProjectnameEqualTo(projectName);
			//����������ѯ��Ŀ��
			long count = projectInfoMapper.countByExample(example);
			
			return count==0;
		}
		
		
	
}
