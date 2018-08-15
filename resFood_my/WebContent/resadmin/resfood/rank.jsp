<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<script type="text/javascript">
	$(function(){
			$('#dg').datagrid({
				url:'resadmin/resfood.action?op=showFoodSellInfoList',
				pagination:true,
				pageSize:100,
				pageList:[10,50,100,150,200],
			    rownumbers:true,
				singleSelect:true,
				title:"菜品列表",
				fitColumns:true,
				idField:"fid",
				loadMsg:"正在努力为您加载数据",
				nowrap:true,
				fit:true,
				sortName:"sellcount",
				sortOrder:"desc",
			    columns:[[
			        {
			        	field:'fid',
			        	title:'编号',
			        	width:40,
			        	align:'center'
			        },
			        {
			        	field:'fname',
			        	title:'菜品',
			        	width:70,
			        	align:'center'
			        },
			        {
			        	field:'sellcount',
			        	title:'销售次数',
			        	width:70,
			        	sortable:true,
			        	align:'center'
			        }
			    ]]
			}
		);
});
	
</script>
</head>
<body>
	<table id="dg" ></table>
</body>
</html>