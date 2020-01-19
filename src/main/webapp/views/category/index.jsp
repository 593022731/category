<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<%
	String path = request.getContextPath();    
	String base = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";   
	pageContext.setAttribute("base",base);
%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
  	<base href="<%=base%>">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>分类系统</title>

   	<!-- 新 Bootstrap 核心 CSS 文件 -->
	<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">


    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    
    <script>
    	function activeCategory(tableName){
    		if(confirm("确定激活此版本(会影响线上所有业务线分类数据)？")){
	    		$.post('category/activeCategory',{tableName:tableName},function(data){
	    			if(data){
	    				alert('激活成功');
	    				 window.location.reload();
	    			}
	    		},'text');
    		}
    	}
    </script>
    
  </head>
  <body>
	<table class="table table-striped">
		<thead>
	      <tr>
	         <th>版本描述</th>
	         <th>版本</th>
	         <th>创建时间</th>
	         <th>操作</th>
	    </tr>
	</thead>
	
	<tbody>
		<c:forEach items="${tables }" var="item">
		<tr>
	         <td><a href="category/edit?tableName=${item.tableName }">${item.tableComment }</a></td>
	         <td><a href="category/edit?tableName=${item.tableName }">${item.tableName }</a></td>
	         <td>${item.createDT }</td>
	         <td>
	         <c:if test="${item.isUse == 0 }">
	         	<a class="btn btn-default" href="javascript:void(0);" onclick="activeCategory('${item.tableName }')" role="button">激活使用</a>
	         </c:if>
	         <c:if test="${item.isUse == 1 }">
	         	已激活
	         </c:if>
	         </td>
	    </tr>
	    </c:forEach>
	</tbody>
	</table>
	
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
	<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
	
	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
  </body>
</html>