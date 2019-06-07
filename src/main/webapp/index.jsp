<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	//将path放到页作用域中，方便EL表达式随时调用
	pageContext.setAttribute("app_path", path);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index title here</title>

<link type="text/css" rel="stylesheet"
	href="${app_path}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css" />

<!-- 引入js脚本时，先引入jquery-1.12.4.js,再引入bootstrap.min.js -->
<script type="text/javascript"
	src="${app_path}/static/js/jquery-1.12.4.js"></script>
<script type="text/javascript"
	src="${app_path}/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</head>
<body>

	<!-- ============================== 修改的模态框 ==================================================================== -->
	<div class="modal fade" id="projectInfoUpdateModel" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<!-- 头部 -->
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">编辑项目</h4>
				</div>
				<!-- 内容 -->
				<div class="modal-body">
					<!-- 模态框的主体内容 -->
					<!-- ==============================模态框的主体内容 start=================================== -->
					<form class="form-horizontal">
						<!-- 项目名称 -->
						<div class="form-group">
							<label for="projectName_update_static"
								class="col-sm-2 control-label">ProjectName</label>
							<div class="col-sm-10">
								<p class="form-control-static" id="projectName_update_static"></p>
							</div>
						</div>
						<!-- 项目的开始日期 -->
						<div class="form-group">
							<label for="startDate_update_input"
								class="col-sm-2 control-label">StartDate</label>
							<div class="col-sm-10">
								<input type="date" name="piStartdate" class="form-control"
									id="startDate_update_input" placeholder="Plact Enetr StartDate">
							</div>
						</div>
						<!-- 项目的结束日期 -->
						<div class="form-group">
							<label for="endDate_update_input" class="col-sm-2 control-label">EndDate</label>
							<div class="col-sm-10">
								<input type="date" name="piEnddate" class="form-control"
									id="endDate_update_input" placeholder="Plact Enetr EndDate">
							</div>
						</div>
						<!-- 申报状态 -->
						<div class="form-group">
							<label for="Status_update_input" class="col-sm-2 control-label">Status</label>
							<div class="col-sm-5">
								<select class="form-control" name="piStatus"
									id="Status_update_input">
									<option value="0">已申报</option>
									<option value="1">审核中</option>
									<option value="2">已审核</option>
								</select>
							</div>
						</div>
						<!-- 申报人 -->
						<div class="form-group">
							<label for="applicant_update_input"
								class="col-sm-2 control-label">applicantName</label>
							<div class="col-sm-5">
								<select class="form-control" name="acid"
									id="applicant_update_input">
									<!-- 动态生成 下拉列别的参数 -->
								</select>
							</div>
						</div>

					</form>
					<!-- ===============================模态框的主体内容 end================================== -->
				</div>
				<!-- 底部 -->
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary"
						id="projectInfo_update_btn">保存</button>
				</div>
			</div>
		</div>
	</div>






	<!-- ================================================================================================== -->

	<!-- =============================================================================================================================== -->
	<!-- 新增的模态框 -->
	<div class="modal fade" id="projectInfoAddModel" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<!-- 头部 -->
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">新增项目</h4>
				</div>
				<!-- 内容 -->
				<div class="modal-body">
					<!-- 模态框的主体内容 -->
					<!-- ==============================模态框的主体内容 start=================================== -->
					<form class="form-horizontal">
						<!-- 项目名称 -->
						<div class="form-group">
							<label for="projectName_add_input" class="col-sm-2 control-label">ProjectName</label>
							<div class="col-sm-10">
								<input type="text" name="piProjectname" class="form-control"
									id="projectName_add_input"
									placeholder="Plact Enetr ProjectName">
								<!-- 用于提示项目名称格式的 错误信息 -->
								<span class="help_block"></span>
							</div>
						</div>
						<!-- 项目的开始日期 -->
						<div class="form-group">
							<label for="startDate_add_input" class="col-sm-2 control-label">StartDate</label>
							<div class="col-sm-10">
								<input type="date" name="piStartdate" class="form-control"
									id="startDate_add_input" placeholder="Plact Enetr StartDate">
							</div>
						</div>
						<!-- 项目的结束日期 -->
						<div class="form-group">
							<label for="endDate_add_input" class="col-sm-2 control-label">EndDate</label>
							<div class="col-sm-10">
								<input type="date" name="piEnddate" class="form-control"
									id="endDate_add_input" placeholder="Plact Enetr EndDate">
							</div>
						</div>
						<!-- 申报状态 -->
						<div class="form-group">
							<label for="Status_add_input" class="col-sm-2 control-label">Status</label>
							<div class="col-sm-5">
								<select class="form-control" name="piStatus"
									id="Status_add_input">
									<option value="0">已申报</option>
									<option value="1">审核中</option>
									<option value="2">已审核</option>
								</select>
							</div>
						</div>
						<!-- 申报人 -->
						<div class="form-group">
							<label for="applicant_add_input" class="col-sm-2 control-label">applicantName</label>
							<div class="col-sm-5">
								<select class="form-control" name="acid"
									id="applicant_add_input">
									<!-- 动态生成 下拉列别的参数 -->
								</select>
							</div>
						</div>

					</form>
					<!-- ===============================模态框的主体内容 end================================== -->
				</div>
				<!-- 底部 -->
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary"
						id="projectInfo_save_btn">保存</button>
				</div>
			</div>
		</div>
	</div>

	<!-- ======================================================================= -->


	<!-- 主题内容 -->
	<div class="container-fluid">

		<!-- 标题 -->
		<div class="row">
			<div class="col-md-12">
				<h1>DPM_增删改查</h1>
			</div>
		</div>

		<!-- 按钮行 Columns start at 50% wide on mobile and bump up to 33.3% wide on desktop -->
		<div class="row">
			<!-- col-md-2 占格的数量（一共12格）  col-md-offset-10(偏移 10格) -->
			<div class="col-md-2 col-md-offset-10">
				<button type="button" class="btn btn-success"
					id="projectInfo_add_btn">新增</button>
				<button type="button" class="btn btn-danger"
					id="projectInfo_delete_all_btn">删除</button>
			</div>
		</div>

		<!-- 显示数据的表格  -->
		<div class="row">
			<div class="col-md-12">
				<!-- 用表格展示出数据 -->
				<table class="table table-striped table table-hover"
					id="projectInfos_table">
					<!-- 列标题 -->
					<thead>
						<tr>
							<th><input id="check_all" type="checkbox" /></th>
							<th>项目编号</th>
							<th>项目名称</th>
							<th>开始日期</th>
							<th>结束日期</th>
							<th>申报状态</th>
							<th>申报人</th>
							<th>性别</th>
							<th>工作年限</th>
							<th>操作</th>
						</tr>
					</thead>
					<!-- 列主体 -->
					<tbody>
						<!-- 动态显示数据 -->
					</tbody>
				</table>
			</div>
		</div>

		<!-- 显示分页信息 -->
		<div class="row">
			<!-- 分页文字描述 -->
			<div class="col-md-4 col-md-offset-2" id="page_info_area"></div>
			<!-- 分页导航条 -->
			<div class="col-md-6" id="page_nav_area">
				<!-- 显示分页条 -->
			</div>
		</div>
	</div>
	<!-- =============================================================================================================================== -->
</body>
</html>

<script type="text/javascript">
	/*
		全局变量
		totalCount：添加之后跳转至最后一页
		currnetPage：删除之后跳转至当前页
	*/
	var totalCount,currentPage;
	

	/* 1.页面加载完成，直接发生ajax请求，拿到分页的数据  */ 
	$(function(){
		//页面进入 直接访问第一页的数据
		to_page(1);
	});
	
	
	/* 页面访问函数 */
	function to_page(pn){
		$.ajax({
			url:"${app_path}/projectInfos",
			data:"pn="+pn,
			type:"GET",
			success:function(result){
				//将结果输入到页面控制台上
				//console.log(result);
				//1.解析json数据并显示再申报人的信息表格中
				build_projectInfos_table(result);
				//2.解析json 显示分页文字信息
				build_page_info(result);
				//3.解析json 显示导航分页信息
				build_page_nav(result);
			}
		})
	}
	
	//1..解析json数据并显示再申报人的信息表格中
	function build_projectInfos_table(result){
		//0.构建表格之前，将之前的数据全部清空防止出现多次查询的数据
		$("#projectInfos_table tbody").empty();
		//1、解析json数据，拿到其中的extend值中的pageinfo中的list集合数据
		var projectInfos = result.extend.pageinfo.list;
		//2、遍历数据 (第一个参数是需要遍历的集合，第二个参数是对集合的处理方式)
		//2.1、第二个参数中也有两个参数：第一个是下标，第二个是遍历出每一项
		$.each(projectInfos,function(idnex,item){
			//console.log(item.piProjectname);
			//2.1.1、遍历到表格中
			//节点1：复选框
			var checkBoxTd=$("<td><input type='checkbox' class='check_item'/></td>");
			//节点2：项目编号
			var piIDTD=$("<td></td>").append(item.piId);
			//节点3：项目名称
			var piProjectnameTD=$("<td></td>").append(item.piProjectname);
  			//节点4：开始日期
  			var piStartdateTD=$("<td></td>").append(dateFarmat("yyyy-MM-dd",new Date(item.piStartdate)));
  			//节点5：结束日期
  			var piEnddateTD=$("<td></td>").append(dateFarmat("yyyy-MM-dd",new Date(item.piEnddate)));
  			//节点6：申报状态
	  			if(item.piStatus==0){
			    	item.piStatus="已申报";
			    }else if(item.piStatus==1){
			    	item.piStatus="审核中";
			    }else {
			    	item.piStatus="已审核";
			    } 
  			var piStatusTD=$("<td></td>").append(item.piStatus);
  			//节点7：申报人
  			var acNameTD=$("<td></td>").append(item.applicant.acName);
  			//节点8：性别
  			var acSexTD=$("<td></td>").append(item.applicant.acSex==1?'男':'女');
  			//节点9：工作年限
  			var workinglifeTD=$("<td></td>").append(item.applicant.workinglife);
  			//节点10：操作
	  			//var operationTD=$("<td><button type='button' class='btn btn-danger' id='projectInfo_delete_all_btn'>删除</button></td>")
				var editBtn=$("<button></button>").addClass("btn btn-primary btn-sm edit_btn")
				.append($("<span></span>")).addClass("glyphicon glyphicon-pencil").append(" 编辑");
				//为编辑按钮添加一个用于操作的id(当前项目的编号)
				editBtn.attr("edit-id",item.piId);
				//删除按钮
				var delBtn=$("<button></button>").addClass("btn btn-danger btn-sm delete_btn")
				.append($("<span></span>")).addClass("glyphicon glyphicon-trash").append(" 删除");
				//为编辑按钮添加一个用于操作的id(当前项目的编号)
				delBtn.attr("del-id",item.piId);
				//将两个按钮拼接在一起
				var btnTd=$("<td></td>").append(editBtn).append(" ").append(delBtn);
				 
			//将所有的节点组合
			$("<tr></tr>").append(checkBoxTd)
			.append(piIDTD)
			.append(piProjectnameTD)
			.append(piStartdateTD)
			.append(piEnddateTD)
			.append(piStatusTD)
			.append(acNameTD)
			.append(acSexTD)
			.append(workinglifeTD)
			.append(btnTd)
			.appendTo("#projectInfos_table tbody");
		});
	}
	//==============================================================================================================
	//日期格式化操作
	//dateFarmat("yyyy-MM-dd hh:mm:ss",new Date(item.piStartdate))
	function dateFarmat(fmt,date){    
	  var o = {   
	    "M+" : date.getMonth()+1,                 //月份   
	    "d+" : date.getDate(),                    //日   
	    "h+" : date.getHours(),                   //小时   
	    "m+" : date.getMinutes(),                 //分   
	    "s+" : date.getSeconds(),                 //秒   
	    "q+" : Math.floor((date.getMonth()+3)/3), //季度   
	    "S"  : date.getMilliseconds()             //毫秒   
	  };   
	  if(/(y+)/.test(fmt))   
	    fmt=fmt.replace(RegExp.$1, (date.getFullYear()+"").substr(4 - RegExp.$1.length));   
	  for(var k in o)   
	    if(new RegExp("("+ k +")").test(fmt))   
	  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
	  return fmt;   
	} 
	//==============================================================================================================
		
	//======================================= 显示分页文本信息  start=======================================================================	
	//2.解析json 显示分页文字信息
	function build_page_info(result){
		//0.进入先清空
		$("#page_info_area").empty();
		//1.构建分页信息数据
		$("#page_info_area").append("当前第"+result.extend.pageinfo.pageNum+"页,共"
				+result.extend.pageinfo.pages+"页,共"+result.extend.pageinfo.total+"条记录");
		
		totalCount = result.extend.pageinfo.pages;
		currnetPage = result.extend.pageinfo.pageNum;
	}
	//======================================= 显示分页文本信息 End =======================================================================
		
	//======================================= 显示分页数字按钮 start =======================================================================	
	//3.解析json 显示导航分页信息
	function build_page_nav(result){
		//0.进入先清空
		$("#page_nav_area").empty();
		//1.构建ul
		var ul=$("<ul></ul>").addClass("pagination");
		//2.ul中的首页
		var firstPageLi=$("<li></li>").append($("<a></a>").append("首页").attr("href","#"));
		//3.上一页
		var leftPageLi=$("<li></li>").append($("<a></a>").append("&laquo;").attr("href","#"));
		
		//判断是否还有上一页
		if(result.extend.pageinfo.hasPreviousPage==false){//false每页上一页了就不能点击上一页和首页
			 //给首页和上一页添加disabled属性
			 firstPageLi.addClass("disabled");
			 leftPageLi.addClass("disabled");
		}else{ 
			//元素添加点击翻页事件
			firstPageLi.click(function(){
				to_page(1);
			});
			leftPageLi.click(function(){
				to_page(result.extend.pageinfo.pageNum-1);
			});
		}
		
		//4.下一页
		var rightPageLi=$("<li></li>").append($("<a></a>").append("&raquo;").attr("href","#"));
		//5.ul中的末页
		var lastPageLi=$("<li></li>").append($("<a></a>").append("末页").attr("href","#"));
		
		//判断是否还有下一页
		if(result.extend.pageinfo.hasNextPage==false){//false没有下一页了就不能点击下一页和末页
			//给首页和上一页添加disabled属性
			rightPageLi.addClass("disabled");
			lastPageLi.addClass("disabled");
		}else{
			//元素添加点击翻页事件
			rightPageLi.click(function(){
				to_page(result.extend.pageinfo.pageNum+1);
			});
			lastPageLi.click(function(){
				to_page(result.extend.pageinfo.pages);
			});
		}
		
		//向ul中添加首页和上一页
		ul.append(firstPageLi).append(leftPageLi);
		
		//遍历中间的数字			  navigatepageNums
		$.each(result.extend.pageinfo.navigatepageNums,function(index,item){
			
			//构建页码li对象
			var numLi=$("<li></li>").append($("<a></a>").append(item).attr("href","#"));
			
			//判断数字是否是当前显示页面，如果是，添加选中效果
			if(result.extend.pageinfo.pageNum == item){
				numLi.addClass("active");
			}
			
			//每一个数字li都能点击并跳转相应的页面位置
			numLi.click(function(){
				to_page(item);
			})
			
			//将li放入ul中
			ul.append(numLi);
			
		});
		
		//向ul中添加下一页和末页
		ul.append(rightPageLi).append(lastPageLi);
		//将ul放入nav中
		var navEle=$("<nav></nav>").append(ul);
		navEle.appendTo("#page_nav_area");
	}
	//======================================= 显示分页数字按钮  end =======================================================================	
		
		
	// ====================================================================保存模块============================================================================
	
	
	/*
		保存事件的逻辑需求 （弹出模态框）
		1、主页点击添加按钮，弹出模态框
		2、再模态框中显示出所有申报人信息（查询所有申报人）
		3、将申报人姓名展示再模态框的下拉列表中
		4、使用JQuery进行前端的效验，ajax进行项目名重复查询效验
		5、重要的数据，再后端需要JSR303效验
		6、数据库保存
	*/
	
	//========================== 点击新增按钮的事件 
	$("#projectInfo_add_btn").click(function(){
		//0.表单内容重置（表单数据，表单的样式）
		clear_add_form("#projectInfoAddModel form")
		//1.发送请求查询所有申报人，显示再下拉列表中
		getApplicants("#applicant_add_input");
		//2.弹出模态框
			//模态框的弹出方式
		$('#projectInfoAddModel').modal({
			backdrop: 'static'
		})
	});
	
	//========================== 表单清空
	/*
		clear_add_form(ele)  ele:代表的是传入的 id或class的名称
		1、清空表单中的信息
		2、清空表单上的css样式
		3、清空附加的提示信息
	*/
	function clear_add_form(ele){
		//重置表单
		$(ele)[0].reset();
		//重置样式(将错误和正确的样式重新打开的时候全部移除)
		$(ele).find("*").removeClass("has-success has-error");
		//清空项目附加的提示信息
		$(ele).find(".help_block").text("");
	}
	
	//========================== 查询所有申报人
	/*
		getApplicants(ele)  ele:代表的是传入的 id或class的名称
		使用ajax请求获得
	*/
	function getApplicants(ele){
		//1.清除下来列表原本的数据
		$(ele).empty();
		//发送ajax请求，查询所有申报人的姓名并添加到<select></select>中
		$.ajax({
			url:"${app_path}/applicants",
			type:"GET",
			success:function(result){
				//console.log(result);
				//result.extend.applicants 这个值就是查询出来所有的申报人名称 并循环出来
				$.each(result.extend.applicants,function(){
					//构建option列表项
					var optionEle=$("<option></option>").append(this.acName).attr("value",this.acId);
					//将否建的option列表项添加再select里里面
					optionEle.appendTo(ele);
				})
			}
		});
	}
		
	//======================================================================================================================
	/*	效验之后的提示信息函数：
	
		ele:选择器 ，就是在哪里
		status:状态信息，看看是否成功还是失败
		msg:展示信息，把信息放上面
		
		1、每一次显示验证信息时，都将之前的类样式清空 
			先获取到当前选择器的父标签，然后得到父标签中的子节点
		2、判断传过来的status状态，显示对应的文字信息
		3、给msg添加文字信息
	*/
	//======================给span标签加文字信息 start ===========================================================================
	function show_validate_msg(ele,status,msg){
		//1、每一次显示验证信息时，都将之前的类样式清空
		$(ele).parent().removeClass("has-success has-error");
		//判断状态，确定状态对应的样式
		if("success"==status){
			//正确，添加正确的样式
			$(ele).parent().addClass("has-success");
		}else if("error"==status){
			//错误，添加错误的样式
			$(ele).parent().addClass("has-error");
		}
		//获取到span标签并把查询出的信息放入
		$(ele).next("span").text(msg);
	}
	//========================给span标签加文字信息 end============================================================================
	
	//========================验证 项目名称 是否合格 start===============================================================
	//数据效验
	function validate_add_form(){
		/*	效验项目的名称
			1、拿到项目的名称
			2、正则表达式
		*/
		//1.  这样就拿到了匹配的项目名字
		var projectName=$("#projectName_add_input").val();
		//2. 汉字的正则表达式 /(^[\u2E80-\u9FFF]{2,32}$)/  写正则前面和后必须加/.../
		var regName=/(^[a-zA-Z0-9_-]{6,64}$)|(^[\u2E80-\u9FFF]{2,32}$)/;
		//匹配验证
		if(!regName.test(projectName)){
			//提示错误信息
			show_validate_msg("#projectName_add_input","error","项目名为2~23为中文或者6~64字母,数字，下划线，横杠");
			return false;
		}else{
			//正常显示
			show_validate_msg("#projectName_add_input","success","");
		}
		return true;
	}
	//================================= 验证 项目名称 是否合格 end =========================================================
	
	//================================= 点击模态框中的保存，动态添加项目申报数据  start================================================
	$("#projectInfo_save_btn").click(function(){
		//0.对提交的数据进行效验（前端效验）
		if(!validate_add_form()){
			return false;
		}
		//1.通过ajax请求项目名是否可用（验证数据库重复）
		if($(this).attr("ajax_validate_projectName")=="error"){
			return false;
		}
		//将模态框中的数据提交给用户
		/*
			通过表单进行提交(表单元素的name值，一个一个获取麻烦)，通过JQuery提供的表单序列化进行表单数据提交，
			用serialize()函数自动映射信息
		*/
		//console.log($("#projectInfoAddModel form").serialize());
		$.ajax({
			url:"${app_path}/projectInfo",
			data:$("#projectInfoAddModel form").serialize(),
			type:"POST",
			success:function(result){
				 if(result.code==200){
					//保存成功，关闭模态框
					$("#projectInfoAddModel").modal("hide");
					//跳转至最后一页
					to_page(totalCount);
				} else{
					//保存失败，判断是那个字段失败
					//如果字段的值中不是undefined，代表字段中有错误信息，此时调用信息展示函数
					if(result.extend.errorFields.piProjectname!=undefined){//判断不是undefined
						show_validate_msg("#projectName_add_input","error",result.extend.errorFields.piProjectname);
					}
				}
			}
		});
	})
	//================================= 点击模态框中的保存，动态添加项目申报数据  end================================================
	
	
	//===================================== 进行项目名称输入框的重复验证（输入框里面的信息发生变化时，进行ajax请求） start ====================
	$("#projectName_add_input").change(function(){
		//获取输入框的值
		var projectName=this.value;
		//调用效验方法,如果效验不成功，直接结束重复验证
		if(!validate_add_form()){
			return false;
		}
		//效验成功的话，发送ajax请求，查看项目名是否可用
		$.ajax({
			url:"${app_path}/checkProjectInfo",
			data:"projectName="+projectName,
			type:"POST",
			success:function(result){
				//判断验证之后的Msg对象中的状态码
				if(result.code == 200){
					show_validate_msg("#projectName_add_input","success","v可用");
					//给保存按钮添加成功属性，允许点击保存
					$("#projectInfo_save_btn").attr("ajax_validate_projectName","success");
				}else{
					show_validate_msg("#projectName_add_input","error",result.extend.validate_msg);
					//给保存按钮添加成功属性，允许点击保存
					$("#projectInfo_save_btn").attr("ajax_validate_projectName","error");
				}
				//conlosn.log(result);
			}
		});
	});
	//===================================== 进行项目名称输入框的重复验证（输入框里面的信息发生变化时，进行ajax请求）end ====================
//点击编辑弹出模态框

//projectInfoUpdateModel
/*
 	为编辑按钮添加点击时事件 
 	此时页面加载时没有办法直接为编辑按钮添加事件，加载dom元素时，还没有编辑按钮，直接调用click函数，没有反应
 	解决办法：动态绑定函数
 	live() 已经过期
 	on() 正常使用
 
 */
 $(document).on("click",".edit_btn",function(){
	 //1、查询项目的信息
	 getProjectInfo($(this).attr("edit-id"));
	 //2、查询所有申报人
	 getApplicants("#projectInfoUpdateModel select[name=acid]");
	 //3、将需要更新的项目id从编辑按钮上传到模态框的保存按钮上，用来修改时
	 $("#projectInfo_update_btn").attr("edit-id",$(this).attr("edit-id"));
	 //4、弹出模态框
	 $("#projectInfoUpdateModel").modal({
		 backdrop: 'static'
	 })
 });
//=======================1、查询项目的信息
function getProjectInfo(id){
	$.ajax({
		url:"${app_path}/projectinfo/"+id,
		type:"GET",
		success:function(result){
			//console.log(result);
			//将返回的数据直接放在模态框对一个位置上
			//获取项目对象
			var projectInfo = result.extend.projectInfo;
			//将项目对象的信息放到对应的模态框中
			$("#projectName_update_static").text(projectInfo.piProjectname);
			$("#startDate_update_input").val(dateFarmat("yyyy-MM-dd",new Date(projectInfo.piStartdate)));
			$("#endDate_update_input").val(dateFarmat("yyyy-MM-dd",new Date(projectInfo.piEnddate)));
			//$("#projectInfoUpdateModel select[name=piStatus]").val([parseInt(projectInfo.piStatus)]);
			//$("#projectInfoUpdateModel select[name=acid]").val([parseInt(projectInfo.acid)]);
			$("#Status_update_input").val([parseInt(projectInfo.piStatus)]);
			$("#applicant_update_input").val([parseInt(projectInfo.acid)]);
			
		}
	})
} 
//========================修改模态框中保存按钮的点击事件
	$("#projectInfo_update_btn").click(function(){
		//1、根据据需求进行数据校验
		//2、ajax请求，员工的数据更新（）
		$.ajax({
			url:"${app_path}/projectInfo/"+$(this).attr("edit-id"),
			type:"PUT",
			data:$("#projectInfoUpdateModel form").serialize(),
			success:function(result){
				//2.1、隐藏模态框
				$("#projectInfoUpdateModel").modal("hide");
				//跳转到当前页
				to_page(currnetPage);
			}
		})
	})
	$("#check_all").click(function(){
		//将全选按钮的状态设置为子项选择的状态
		$(".check_item").prop("checked",$(this).prop("checked"));
	});
	//每个选中，全选按钮也选中
	$(document).on("click",".check_item",function(){
		//获取自选项的选中状态（判断其数量是否等于总数量）
		var flag = $(".check_item:checked").length == $(".check_item").length;
			$("#check_all").prop("checked",flag);
	});
	//单个删除按钮的动态绑定
	$(document).on("click",".delete_btn",function(){
		//1、获取删除的项目名称
		var projectName = $(this).parents("tr").find("td:eq(2)").text();
		//2、获取删除按钮上携带的id
		var delId=$(this).attr("del-id");
		//询问是否删除
		if(confirm("确定删除'"+projectName+"'吗?")){
			$.ajax({
				url:"${app_path}/projectInfoById/"+delId,
				data:"id="+delId,
				type:"DELETE",
				success:function(result){
					//跳转到当前页
					to_page(currnetPage);
				}
				
			})
		}
	
	});
	//点击删除所有
	$("#projectInfo_delete_all_btn").click(function(){
		//1.创建用于存放删除的项目名
		var projectinfoNames = "";
		//2.创建用于存放项目id的字符串
		var del_ids_str = "";
		//3.循环遍历选中的元素id
		$.each($(".check_item:checked"),function(){
			//将项目名拼接起来，用“,”隔开
			projectinfoNames += $(this).parents("tr").find("td:eq(2)").text()+",";
			//将遍历的id拼接在一起，用“-”隔开
			del_ids_str += $(this).parents("tr").find("td:eq(1)").text()+"-";
		});
		
		//去掉最后一给“,”
		projectinfoNames = projectinfoNames.substring(0,projectinfoNames.length-1);
		//去掉最后一给“-”
		del_ids_str = del_ids_str.substring(0,del_ids_str.length-1);
		//询问是否删除
		if(del_ids_str.length>0){
			if(confirm("确定删除'"+projectinfoNames+"'吗?")){
				$.ajax({
					url:"${app_path}/projectInfoById/"+del_ids_str,
					type:"DELETE",
					success:function(result){
						//跳转到当前页
						to_page(currnetPage);
					}
				})
			}
		}else{
			return alert("未选复选框!")
		}
	});
	
	
	
</script>
