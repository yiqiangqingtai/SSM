package com.yuntu.dpm.dao;

import com.yuntu.dpm.bean.ProjectInfo;
import com.yuntu.dpm.bean.ProjectInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectInfoMapper {
    long countByExample(ProjectInfoExample example);

    int deleteByExample(ProjectInfoExample example);

    int deleteByPrimaryKey(Integer piId);

    int insert(ProjectInfo record);

    int insertSelective(ProjectInfo record);
    
    
    //����������ѯ���У�ͬʱ������е���Ŀ���걨��
    List<ProjectInfo> selectByExampleWithApplicant(ProjectInfoExample example);
    //����������ѯ��ͬʱ�����Ŀ�Ͷ�Ӧ���걨��
    ProjectInfo selectByPrimaryKeyWithApplicant(Integer piId);
    

    List<ProjectInfo> selectByExample(ProjectInfoExample example);

    ProjectInfo selectByPrimaryKey(Integer piId);

    int updateByExampleSelective(@Param("record") ProjectInfo record, @Param("example") ProjectInfoExample example);

    int updateByExample(@Param("record") ProjectInfo record, @Param("example") ProjectInfoExample example);

    int updateByPrimaryKeySelective(ProjectInfo record);

    int updateByPrimaryKey(ProjectInfo record);
}