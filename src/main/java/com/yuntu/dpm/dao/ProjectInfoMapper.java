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
    
    
    //根据条件查询所有，同时查出所有的项目和申报人
    List<ProjectInfo> selectByExampleWithApplicant(ProjectInfoExample example);
    //根据主键查询，同时查出项目和对应的申报人
    ProjectInfo selectByPrimaryKeyWithApplicant(Integer piId);
    

    List<ProjectInfo> selectByExample(ProjectInfoExample example);

    ProjectInfo selectByPrimaryKey(Integer piId);

    int updateByExampleSelective(@Param("record") ProjectInfo record, @Param("example") ProjectInfoExample example);

    int updateByExample(@Param("record") ProjectInfo record, @Param("example") ProjectInfoExample example);

    int updateByPrimaryKeySelective(ProjectInfo record);

    int updateByPrimaryKey(ProjectInfo record);
}