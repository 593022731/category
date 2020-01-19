<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<%
	String path = request.getContextPath();    
	String base = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";   
	pageContext.setAttribute("base",base);
%>
<!DOCTYPE html>
<HTML>
<HEAD>
	<base href="<%=base%>">
	<TITLE>编辑分类</TITLE>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	
	<!-- 新 Bootstrap 核心 CSS 文件 -->
	<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">


    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
	 <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
	<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="resources/css/zTreeStyle/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="resources/js/plugin/ztree/jquery.ztree.core.js"></script>
	<script type="text/javascript" src="resources/js/plugin/ztree/jquery.ztree.excheck.js"></script>
	<script type="text/javascript" src="resources/js/plugin/ztree/jquery.ztree.exedit.js"></script>
	<script type="text/javascript" src="resources/js/plugin/jquery.form.min.js"></script>
	<SCRIPT type="text/javascript">
		/* 文档地址： http://www.treejs.cn/v3/api.php */
		var setting = {
			check: {
				enable: true,
				chkboxType : { "Y" : "", "N" : "" }
			},
			edit: {
				enable: true,
				showRemoveBtn: true,
				showRenameBtn: true
			},
			view: {
				addHoverDom: addHoverDom,
				removeHoverDom: removeHoverDom,
				fontCss: setFontCss,
				selectedMulti: false
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
			//	beforeDrop: zTreeBeforeDrop
				onDrop: zTreeOnDrop,
				onClick: zTreeOnClick
			}
		};
		
		function zTreeOnClick(event, treeId, treeNode) {
			if(treeId == 'compare_tree' && treeNode.difFlag == 1){
		    	console.log(treeNode.id + ", " + treeNode.name);
				if(confirm("确定查询"+treeNode.name+"影响的业务数据量吗？")){
					$('#changedata_div').html("正在查询"+treeNode.name+"影响的数据量...");
			    	$.post('category/changedata',{categoryID:treeNode.id},function(data){
			    		$('#changedata_div').html(treeNode.name+":<br />"+data);
			    	},'text');
			   	}
			}
		};

		function setFontCss(treeId, treeNode) {
			if(treeNode.difFlag == 1){
				return {color:"red"};	
			}
			return {};
		};
		
		/* 
		function zTreeBeforeDrop(treeId, treeNodes, targetNode, moveType) {
			var zTree = $.fn.zTree.getZTreeObj(treeId);
			var node = treeNodes[0];
			
			console.log(node.id);
			var pId = targetNode.id;
			var maxId = node.maxId;
			if(targetNode.pId == null && moveType != 'inner'){
				node.id = maxId; 
			}else{
				node.id = pId + "/" + maxId; 
			}
			zTree.updateNode(node);			
			console.log(node.id);
		};
		 */
		
		function zTreeOnDrop(event, treeId, treeNodes, targetNode, moveType) {
			var node = treeNodes[0];
			console.log(node.name+","+node.pId+","+node.id);
			var pId = node.pId;
			var maxId = node.maxId;
			if(pId == null){
				node.id = maxId; 
			}else{
				node.id = pId + "/" + maxId; 
			}
			treeObj.updateNode(node);		
			console.log(node.name+","+node.pId+","+node.id);
			reSetChildNode(node);
		}; 
		
		function reSetChildNode(treeNode){
			if(treeNode.children){
				var child_nodes = treeNode.children;
				var pId = treeNode.id;
				for (var i=0;i<child_nodes.length;i++) {
					var node = child_nodes[i];
					console.log(node.name+","+node.pId+","+node.id);
					var maxId = node.maxId;
					node.pId = pId;
					node.id = pId + "/" + maxId; 
					treeObj.updateNode(node);	
					console.log(node.name+","+node.pId+","+node.id);	
					reSetChildNode(node);
				}
			}
		}
		
		var maxId = '${maxId}';
		function addHoverDom(treeId, treeNode) {
			var sObj = $("#" + treeNode.tId + "_span");
			if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
			var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
				+ "' title='add node' onfocus='this.blur();'></span>";
			sObj.after(addStr);
			var btn = $("#addBtn_"+treeNode.tId);
			if (btn) btn.bind("click", function(){
				treeObj.addNodes(treeNode, {checked:false, url:"http://ztreeapi.iteye.com/", target:"_blank",id:(treeNode.id + "/" + maxId), pId:treeNode.id, name:"new node" + (maxId++),maxId:maxId,oldCode:0});
				return false;
			});
		};
		
		function removeHoverDom(treeId, treeNode) {
			$("#addBtn_"+treeNode.tId).unbind().remove();
		};
		
		$(document).ready(function(){
			$.post('category/data',{tableName:'${tableName}'},function(data){
				$.fn.zTree.init($("#category_tree"), setting, data);
				treeObj = $.fn.zTree.getZTreeObj("category_tree");
			});
		});
		
		function saveCategory(){
			var tableComment = $('#tableComment','#category_form').val();
			if(tableComment == ''){
				alert('版本描述不能为空');
				$('#tableComment','#category_form').focus();
				return;
			}
			var nodes = treeObj.transformToArray(treeObj.getNodes());
			str = '';
			for (var i=0;i<nodes.length;i++) {
				var node = nodes[i];
				var pId = node.pId;
				if(pId == null){
					pId = 0;			
				}
				var id = node.id;
				var name = node.name;
				var level = node.level;
				var flag = 0;
				var checked = node.checked;
				if(checked){
					flag = 1;
				}				
				var oldCode = node.oldCode;
				var maxId = node.maxId;
				
				str += id+","+name+","+pId+","+level+","+flag+","+oldCode+","+maxId+";";
			}
			console.log(str);			
			if(str == ''){
				alert('没有数据');
				return;
			}
			$('#categoryStr','#category_form').val(str);
				 
			 var options = {
				dataType:'text',
			    success: function(data) {
			    	if(data){
			    		$('#sourceVersion').append("<option value='"+data+"' selected='selected'>"+tableComment+"</option>");
			    		alert('保存成功');
			    		$('#tableComment','#category_form').val('');
					}else{
						alert('保存失败');
					}
			    }
			 }
			 $('#category_form').ajaxSubmit(options);
			
		}
		
		function compareCategory(){
			var sourceVersion = $('#sourceVersion','#compare_form').val();
			
			var compareVersion = $('#compareVersion','#compare_form').val();
			
			$.post('category/compareCategory',{sourceVersion:sourceVersion,compareVersion:compareVersion},function(data){
				$.fn.zTree.init($("#category_tree"), setting, data);
				treeObj = $.fn.zTree.getZTreeObj("category_tree");
				$('#tableName','#category_form').val(sourceVersion);
				
			});
			
			$.post('category/compareCategory',{sourceVersion:compareVersion,compareVersion:sourceVersion},function(data){
				$.fn.zTree.init($("#compare_tree"), setting, data);
			});
		}
	</SCRIPT>
	
	<style type="text/css">
		.ztree li span.button.add {margin-left:2px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}
	</style>
</HEAD>

<BODY>

<table class="table">
  <tr>
  	<td style="width: 40%">
  		<h4>注：勾选代表只读属性</h4>
  		<div id="category_tree" class="ztree"></div>
  		<form action="category/saveCategory" method="post" id="category_form">
		  <input type="hidden" name="categoryStr" id="categoryStr"/>
		  <input type="hidden" name="tableName" id="tableName" value="${tableName }"/>
		  <input type="text" class="form-control" id="tableComment" name="tableComment" placeholder="版本描述">
		  <a class="btn btn-default" href="javascript:void(0);" onclick="saveCategory()" role="button">保存修改</a>
		</form>
  	</td>

  	<td style="width: 20%">
		<form id="compare_form">
			<table class="table">
				<tr>
					<td>
					   	<select class="form-control" name="sourceVersion" id="sourceVersion">
					    	<c:forEach items="${tables }" var="item">	
						  		<option value="${item.tableName }" <c:if test="${tableName == item.tableName}">selected="selected"</c:if>>${item.tableComment }</option>
						  	</c:forEach>	
						</select>
					</td>
				</tr>
				<tr>
					<td>
					  	<a class="btn btn-default" href="javascript:void(0);" onclick="compareCategory()" role="button">版本比对</a>
					</td>
				</tr>
				<tr>
					<td>
					    <select class="form-control" name="compareVersion" id="compareVersion">
					    	<c:forEach items="${tables }" var="item">	
						  		<option value="${item.tableName }" <c:if test="${tableName == item.tableName}">selected="selected"</c:if>>${item.tableComment }</option>
						  	</c:forEach>	
						</select>
					</td>
				</tr>
				<tr>
					<td>
					  	<h4>注：红色代表差异性，左边代表增加，右边代表删除,右边红色部分可以点击查询影响业务数据的总量</h4>
					</td>
				</tr>
				<tr>
					<td>
					  	<h5 id="changedata_div"></h5>
					</td>
				</tr>
			</table>
		
			
		  
		</form>
  	</td>
  	
  	<td style="width: 40%">
  		<h4>注：勾选代表只读属性</h4>
  		<div id="compare_tree" class="ztree"></div>
  	</td>
  </tr>
</table>

</BODY>

	
</HTML>