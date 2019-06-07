package com.yuntu.dpm.dao;

import com.yuntu.dpm.bean.Applicant;
import com.yuntu.dpm.bean.ApplicantExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ApplicantMapper {
	//����������ѯ��Ŀ��
    long countByExample(ApplicantExample example);
    //��������ɾ��
    int deleteByExample(ApplicantExample example);
    //��������ɾ��
    int deleteByPrimaryKey(Integer acId);
    //���
    int insert(Applicant record);
    //��ѡ������
    int insertSelective(Applicant record);
    //����������ѯ����
    List<Applicant> selectByExample(ApplicantExample example);
    //����������ѯ��������
    Applicant selectByPrimaryKey(Integer acId);
    //����������ѡ����޸�
    int updateByExampleSelective(@Param("record") Applicant record, @Param("example") ApplicantExample example);
    //���������޸�
    int updateByExample(@Param("record") Applicant record, @Param("example") ApplicantExample example);
    //����������ѡ����޸�
    int updateByPrimaryKeySelective(Applicant record);
    //���������޸�
    int updateByPrimaryKey(Applicant record);
}