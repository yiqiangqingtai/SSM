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
 * ��Ŀ�����Ŀ�����
 */

@Controller
public class ProjectInfoController {
	@Autowired
	private ProjectInfoService projectInfoService;
	
	/**
	 * ɾ��
	 * ����ǵ��� ֵ ids:1
	 * ����Ƕ�� ֵ ids:1-2-3
	 * ��Ҫ�ж��Ƿ������-�� 
	 */
	@RequestMapping(value="/projectInfoById/{ids}",method=RequestMethod.DELETE)
	@ResponseBody
	public Msg deleteProjectInfoById(@PathVariable("ids")String ids) {
			if(ids.contains("-")) {//������������Ƕ��ֵ
				List<Integer> int_ids=new ArrayList<Integer>();//���ڴ��ɾ����id
				String[] str_ids = ids.split("-");
				//ѭ�����ַ������������ת��Ϊinteger�����ŵ�int-ids��
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
	 * ��������
	 */
	
	//{piId}��������projectinfo�����е����ԣ������Ļ����Զ�ӳ�䵽�βεĶ�����
	@RequestMapping(value="/projectInfo/{piId}",method=RequestMethod.PUT)
	@ResponseBody
	public Msg saveProjectInfo(ProjectInfo projectInfo) {
		
		projectInfoService.modifyProjectInfo(projectInfo);
		
		return Msg.success();
	}
	
	
	/**
	 * ����id��ѯ��Ŀ��Ϣ
	 */
	@RequestMapping(value="/projectinfo/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Msg getProjectInfo(@PathVariable("id")Integer pi_Id) {
		//@PathVariable("id")��������ӳ���·���ϲ������ݸ�pi_Id
		ProjectInfo projectInfo = projectInfoService.getProjectInfo(pi_Id);
		
		return Msg.success().addd("projectInfo", projectInfo);
	}
	
	/**
	 * i ��֤��Ŀ�Ƿ����
	 * 
	 */
	@RequestMapping("/checkProjectInfo")
	@ResponseBody
	public Msg checkProjectInfo(@RequestParam("projectName")String projectName) {
		//�ٴ�Ч��(^[a-zA-Z0-9_-]{6.64}$)|(^[\u2E80-\u9FFF]{2,32}$)
		String regProjectName = "(^[a-zA-Z0-9_-]{6,64}$)|(^[\\u2E80-\\u9FFF]{2,32}$)";
		if(!projectName.matches(regProjectName)) {
			return Msg.fail().addd("validate_msg", "��Ŀ��Ϊ2~23Ϊ���Ļ���6~64��ĸ,���֣��»��ߣ����");
		}
		//ִ�в�ѯ����֤д����Ŀ���Ƿ����
		boolean flag = projectInfoService.checkProjectInfo(projectName);
		if(flag) {
			return Msg.success();
		}
		return Msg.fail().addd("validate_msg", "x ������");
	}
	
	
	/**
	 * i.���棬����json����
	 *	uri����
	 *			/projectInfo/{id} 	GET��ʽ 		��ѯ
	 *			/projectInfo	  	POST��ʽ		���
	 *			/projectInfo/{id} 	PUT��ʽ		�޸�
	 *			/projectInfo/{id} 	DELETE��ʽ	ɾ��
	 */
	@RequestMapping(value="/projectInfo",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveProjectInfo(@Valid ProjectInfo projectInfo,BindingResult result) {
		/*
		 * ���ݲ���ǰ���@Valid,����JSR303����У�飻
		 * BindingResult ����֤�Ľ��
		 * �ж���֤����Ƿ��д�����Ϣ
		 */
		if(result.hasErrors()) {//�д���
			//��ΪҪ���ص�ҳ���ϣ����д������ڴ�Ŵ����map����
			Map<String, Object> map = new HashMap<String, Object>();
			//ʵ��У��Ĳ���һ�����з��ص������е��ֶ�У����������ĿУ����һ�����ƣ�
			List<FieldError> errors = result.getFieldErrors();
			//ѭ��������������map������
			for (FieldError fieldError : errors) {
				//�Ӵ����л�ȡ�������ʹ�����ʾ��Ϣ
				map.put(fieldError.getField(),fieldError.getDefaultMessage());
			}
			return Msg.fail().addd("errorFields", map);
		}else {//û�д�����ȷ�����Ϣ
			projectInfoService.saveProjectInfo(projectInfo);
			return Msg.success();
		}
	}
	
	
	/**
	 * ajax����ķ�ҳ��ѯҳ�����ݣ�����json��ʽ�����ݣ�ͨ��ע���Զ������صĽ��ת��Ϊjson���ݽ�����Ӧ��
	 */
	@RequestMapping("/projectInfos")
	@ResponseBody
	public Msg getProjectInfosWithJson(@RequestParam(value="pn",defaultValue="1")Integer pn) {
		/*
		 * 	��ѯ����Ա��getAll 	
		 * 	
		 * 	1���ڴ�ʱҪ�����ҳ���PageHelper
		 *	2���ٲ�ѯ֮ǰֻ�����startPage������������ҳ���Լ�ÿһҳ��ҳ����
		 *	3�������ŵĵ�һ��select�����ᱻ��ҳ
		 *	4��ʹ��PageInfo���а�װ�����ֻ��Ҫ��PageInfo���󽻸�ҳ��
		 *	4.1��PageInfo(��װ����ϸ�ķ�ҳ��Ϣ��������ѯ�����ݣ�������ʾ��ҳ��)
		 *	5���Լ�����һ�� ������ҳ�������Ϣ���ݵ��ࣨcom.yuntu.dpm.bean.Msg�ࣩ
		 *	5.1��private int code; �Լ���װ��һ������ɹ�����״̬��   200�ɹ�  400ʧ��
		 *	5.2��private String msg; ��ʾ��Ϣ���������ɹ�����������ʧ�ܡ�...��
		 *	5.3��private Map<String, Object> extend=new HashMap<String, Object>(); ���ظ�ҳ�������������
		 *	5.4��дһ���ɹ��ķ���  �����ǵ� ״̬��setΪ200����ʾ��ϢsetΪ���ɹ���
		 *	5.5��дһ��ʧ�ܵķ���  �����ǵ� ״̬��setΪ400����ʾ��ϢsetΪ��ʧ�ܡ�
		 *	5.6��дһ�����ظ�ҳ�����ݵķ������������� this.extend.put(?,?)����������ķ���ֵ����������
		 */
		//�ٲ�ѯ֮ǰֻ�����startPage������������ҳ���Լ�ÿһҳ��ҳ����
		PageHelper.startPage(pn,5);
		//�����ŵĵ�һ��select�����ᱻ��ҳ
		List<ProjectInfo> infos = projectInfoService.getAll();
		//ʹ��PageInfo���а�װ�����ֻ��Ҫ��PageInfo���󽻸�ҳ��
		//PageInfo(��װ����ϸ�ķ�ҳ��Ϣ��������ѯ�����ݣ�������ʾ��ҳ��)
		PageInfo page = new PageInfo(infos,5);
		//��PageInfo�����з�װ�����ݽ��з��أ�json��
		return Msg.success().addd("pageinfo", page);
//		Msg result = new Msg();
//		result.getExtend().put("pageinfo", page);
//		return result;
	}
	
	
	
	
	
	
}
