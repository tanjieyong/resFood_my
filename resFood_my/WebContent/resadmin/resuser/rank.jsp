<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>

<script type="text/javascript">
	$(function(){
		$('#resultListTable').datagrid({
		    url:'resadmin/resuser.action?op=showUserContributionList',
		    pagination:true,
			idField:"userid",
			rownumbers:true,
			singleSelect:true,
			pageSize:100,
			pageList:[10,20,50,100,150,200,500],
			sortName:"dealcount",
			sortOrder:"desc",
			title:"用户列表",
			fit:true,
			fitColumns:true,
		    columns:[[
		        {field:'userid',title:'用户号',width:100},
		        {field:'username',title:'用户名',width:100},
		        {field:'dealcount',title:'消费金额',width:100}
		    ]]
		});

	});
</script>
<body>
	<table id="resultListTable"></table>
</body>
</html>