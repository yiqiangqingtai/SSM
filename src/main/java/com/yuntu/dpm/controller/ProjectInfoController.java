package com.yuntu.dpm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuntu.dpm.bean.Msg;
import com.yuntu.dpm.bean.ProjectInfo;
import com.yuntu.dpm.service.ProjectInfoService;

/**
 * 项目操作的控制器
 */

@Controller
public class ProjectInfoController {
	@Autowired
	private ProjectInfoService projectInfoService;
	
	/**
	 * 删除
	 * 如果是单个 值 ids:1
	 * 如果是多个 值 ids:1-2-3
	 * 需要判断是否包含‘-’ 
	 */
	@RequestMapping(value="/projectInfoById/{ids}",method=RequestMethod.DELETE)
	@ResponseBody
	public Msg deleteProjectInfoById(@PathVariable("ids")String ids) {
			if(ids.contains("-")) {//如果包含代表是多个值
				List<Integer> int_ids=new ArrayList<Integer>();//用于存放删除的id
				String[] str_ids = ids.split("-");
				//循环将字符串数组的数据转化为integer，并放到int-ids中
				for (String str_id : str_ids) {
					int_ids.add(Integer.valueOf(str_id));
				}
				projectInfoService.removeProjectInfoByIds(int_ids);
			}else {
				Integer id = Integer.valueOf(ids);
				projectInfoService.removeProjectInfoById(id);
			}
			return Msg.success();
		
	}
	
	
	/**
	 * 更改数据
	 */
	
	//{piId}：必须是projectinfo对象中的属性，这样的话会自动映射到形参的对象中
	@RequestMapping(value="/projectInfo/{piId}",method=RequestMethod.PUT)
	@ResponseBody
	public Msg saveProjectInfo(ProjectInfo projectInfo) {
		
		projectInfoService.modifyProjectInfo(projectInfo);
		
		return Msg.success();
	}
	
	
	/**
	 * 根据id查询项目信息
	 */
	@RequestMapping(value="/projectinfo/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Msg getProjectInfo(@PathVariable("id")Integer pi_Id) {
		//@PathVariable("id")：将请求映射的路径上参数传递给pi_Id
		ProjectInfo projectInfo = projectInfoService.getProjectInfo(pi_Id);
		
		return Msg.success().addd("projectInfo", projectInfo);
	}
	
	/**
	 * i 验证项目是否存在
	 * 
	 */
	@RequestMapping("/checkProjectInfo")
	@ResponseBody
	public Msg checkProjectInfo(@RequestParam("projectName")String projectName) {
		//再次效验(^[a-zA-Z0-9_-]{6.64}$)|(^[\u2E80-\u9FFF]{2,32}$)
		String regProjectName = "(^[a-zA-Z0-9_-]{6,64}$)|(^[\\u2E80-\\u9FFF]{2,32}$)";
		if(!projectName.matches(regProjectName)) {
			return Msg.fail().addd("validate_msg", "项目名为2~23为中文或者6~64字母,数字，下划线，横杠");
		}
		//执行查询，验证写的项目名是否存在
		boolean flag = projectInfoService.checkProjectInfo(projectName);
		if(flag) {
			return Msg.success();
		}
		return Msg.fail().addd("validate_msg", "x 不可以");
	}
	
	
	/**
	 * i.保存，返回json数据
	 *	uri请求：
	 *			/projectInfo/{id} 	GET方式 		查询
	 *			/projectInfo	  	POST方式		添加
	 *			/projectInfo/{id} 	PUT方式		修改
	 *			/projectInfo/{id} 	DELETE方式	删除
	 */
	@RequestMapping(value="/projectInfo",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveProjectInfo(@Valid ProjectInfo projectInfo,BindingResult result) {
		/*
		 * 传递参数前添加@Valid,进入JSR303数据校验；
		 * BindingResult 绑定验证的结果
		 * 判断验证结果是否有错误信息
		 */
		if(result.hasErrors()) {//有错误
			//因为要返回到页面上，所有创建用于存放错误的map集合
			Map<String, Object> map = new HashMap<String, Object>();
			//实际校验的不是一个所有返回的是所有的字段校验错误（这个项目校验了一个名称）
			List<FieldError> errors = result.getFieldErrors();
			//循环将多个错误放在map集合中
			for (FieldError fieldError : errors) {
				//从错误中获取错误名和错误提示信息
				map.put(fieldError.getField(),fieldError.getDefaultMessage());
			}
			return Msg.fail().addd("errorFields", map);
		}else {//没有错误，正确添加信息
			projectInfoService.saveProjectInfo(projectInfo);
			return Msg.success();
		}
	}
	
	
	/**
	 * ajax请求的分页查询页面数据，返回json格式的数据（通过注解自动将返回的结果转化为json数据进行响应）
	 */
	@RequestMapping("/projectInfos")
	@ResponseBody
	public Msg getProjectInfosWithJson(@RequestParam(value="pn",defaultValue="1")Integer pn) {
		/*
		 * 	查询所有员工getAll 	
		 * 	
		 * 	1、在此时要引入分页插件PageHelper
		 *	2、再查询之前只需调用startPage方法，并传入页面以及每一页的页容量
		 *	3、紧跟着的第一个select方法会被分页
		 *	4、使用PageInfo进行包装结果，只需要将PageInfo对象交给页面
		 *	4.1、PageInfo(封装了详细的分页信息，包括查询的数据，连续显示的页码)
		 *	5、自己创建一个 用来与页面进行消息传递的类（com.yuntu.dpm.bean.Msg类）
		 *	5.1、private int code; 自己封装的一个请求成功与否的状态码   200成功  400失败
		 *	5.2、private String msg; 提示信息（“操作成功”，“操作失败”...）
		 *	5.3、private Map<String, Object> extend=new HashMap<String, Object>(); 返回给页面浏览器的数据
		 *	5.4、写一个成功的方法  把它们的 状态码set为200、提示信息set为“成功”
		 *	5.5、写一个失败的方法  把它们的 状态码set为400、提示信息set为“失败”
		 *	5.6、写一个返回给页面数据的方法（方法体里 this.extend.put(?,?)）这个方法的返回值是这个类对象
		 */
		//再查询之前只需调用startPage方法，并传入页面以及每一页的页容量
		PageHelper.startPage(pn,5);
		//紧跟着的第一个select方法会被分页
		List<ProjectInfo> infos = projectInfoService.getAll();
		//使用PageInfo进行包装结果，只需要将PageInfo对象交给页面
		//PageInfo(封装了详细的分页信息，包括查询的数据，连续显示的页码)
		PageInfo page = new PageInfo(infos,5);
		//将PageInfo对象中封装的数据进行返回（json）
		return Msg.success().addd("pageinfo", page);
//		Msg result = new Msg();
//		result.getExtend().put("pageinfo", page);
//		return result;
	}
	
	
	
	
	
	
}
