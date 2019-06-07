package com.yuntu.dpm.bean;

import java.util.HashMap;
import java.util.Map;

/**
 *	������ҳ�������Ϣ���ݵĶ��� 
 *
 */

public class Msg {
	
	private int code; //�Լ���װ��һ������ɹ�����״̬��   200�ɹ�  400ʧ��
	
	private String msg;   //��ʾ��Ϣ���������ɹ�����������ʧ�ܡ�...��
	
	private Map<String, Object> extend=new HashMap<String, Object>(); //���ظ�ҳ�������������
	
	//�ɹ�������ͬʱ����Msg����
	public static Msg success() {
		Msg result = new Msg();
		result.setCode(200);
		result.setMsg("�ɹ�");
		return result;
	}
	//ʧ�ܵķ�����ͬʱ����Msg����
	public static Msg fail() {
		Msg result = new Msg();
		result.setCode(400);
		result.setMsg("ʧ��");
		return result;
	}
	
	//�����Ϣ��Msg�����У�ͬʱ����Msg����
	public Msg addd(String key,Object value) {
		this.getExtend().put(key, value);
		return this;
	}
	
	//���ⷽ��
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Map<String, Object> getExtend() {
		return extend;
	}

	public void setExtend(Map<String, Object> extend) {
		this.extend = extend;
	}
	
	
	
	
}
