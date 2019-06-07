package com.yuntu.dpm.bean;

import java.util.HashMap;
import java.util.Map;

/**
 *	用来与页面进行消息传递的对象 
 *
 */

public class Msg {
	
	private int code; //自己封装的一个请求成功与否的状态码   200成功  400失败
	
	private String msg;   //提示信息（“操作成功”，“操作失败”...）
	
	private Map<String, Object> extend=new HashMap<String, Object>(); //返回给页面浏览器的数据
	
	//成功方法，同时返回Msg对象
	public static Msg success() {
		Msg result = new Msg();
		result.setCode(200);
		result.setMsg("成功");
		return result;
	}
	//失败的方法，同时返回Msg对象
	public static Msg fail() {
		Msg result = new Msg();
		result.setCode(400);
		result.setMsg("失败");
		return result;
	}
	
	//添加信息到Msg对象中，同时返回Msg对象
	public Msg addd(String key,Object value) {
		this.getExtend().put(key, value);
		return this;
	}
	
	//对外方法
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
