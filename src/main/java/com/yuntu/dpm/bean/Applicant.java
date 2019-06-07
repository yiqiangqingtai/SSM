package com.yuntu.dpm.bean;

import java.util.Date;

public class Applicant {
    private Integer acId;

    private String acName;

    private Date acBirthday;

    private Integer acSex;

    private Integer workinglife;

    public Integer getAcId() {
        return acId;
    }

    public void setAcId(Integer acId) {
        this.acId = acId;
    }


	public String getAcName() {
        return acName;
    }

    public void setAcName(String acName) {
        this.acName = acName == null ? null : acName.trim();
    }

    public Date getAcBirthday() {
        return acBirthday;
    }

    public void setAcBirthday(Date acBirthday) {
        this.acBirthday = acBirthday;
    }

    public Integer getAcSex() {
        return acSex;
    }

    public void setAcSex(Integer acSex) {
        this.acSex = acSex;
    }

    public Integer getWorkinglife() {
        return workinglife;
    }

    public void setWorkinglife(Integer workinglife) {
        this.workinglife = workinglife;
    }
    
    @Override
	public String toString() {
		return "Applicant [acId=" + acId + ", acName=" + acName + ", acBirthday=" + acBirthday + ", acSex=" + acSex
				+ ", workinglife=" + workinglife + "]";
	}
    
    
}