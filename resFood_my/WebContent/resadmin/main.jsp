<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<script type="text/javascript">
	$(function(){
		//菜品树
		var resfoodTreeData=[
        	
        	{
        		"id":2,
        		"text":"菜品浏览",
        		"attributes":{
        			"url":"<iframe width='100%' height='100%' src='resadmin/resfood/show.jsp' />"
        		}
        	},
        	{
        		"id":3,
        		"text":"销售排行",
        		"attributes":{
        			"url":"<iframe width='100%' height='100%' src='resadmin/resfood/rank.jsp' />"
        		}
        	},
        ];
		
		var resuserTreeData=[
           	{
           		"id":1,
           		"text":"客户浏览",
           		"attributes":{
           			"url":"<iframe width='100%' height='100%' src='resadmin/resuser/rank.jsp' />"
           		}
           	},
           	{
           		"id":2,
           		"text":"客户贡献排名",
           		"attributes":{
           			"url":"<iframe width='100%' height='100%' src='resadmin/resorder/resorderManage.jsp' />"
           		}
           	}
	    ];
		
		//订单管理的树
		var resorderTreeData=[
           	{
           		"text":"订单处理",
           		"attributes":{
           			"url":"<iframe width='100%' height='100%' src='resadmin/resorder/resorderManage.jsp' />"
           		}
           	}
	    ];
		
		//数据库管理的树
		var dataBaseTreeData=[
           	{
           		"id":1,
           		"text":"浏览表",
           		"attributes":{
           			"url":""
           		}
           	},
           	{
           		"id":2,
           		"text":"查询表",
           		"attributes":{
           			"url":""
           		}
           	},
           	{
           		"id":3,
           		"text":"备份",
           		"attributes":{
           			"url":" "
           		}
           	},
           	{
           		"id":4,
           		"text":"还原",
           		"attributes":{
           			"url":" "
           		}
           	}
	    ];
		
		//数据字典的树
		var websiteTreeData=[
           	{
           		"id":1,
           		"text":"搜索引擎优化",
           		"attributes":{
           			"url":""
           		}
           	},
           	{
           		"id":2,
           		"text":"版权",
           		"attributes":{
           			"url":""
           		}
           	},
           	{
           		"id":3,
           		"text":"广告位管理",
           		"attributes":{
           			"url":" "
           		}
           	},
           	{
           		"id":4,
           		"text":"在线客服",
           		"attributes":{
           			"url":" "
           		}
           	},
           	{
           		"id":5,
           		"text":"开放数据API",
           		"attributes":{
           			"url":" "
           		}
           	}
	    ];
		showTree("resfoodTree",resfoodTreeData);
		showTree("resuserTree",resuserTreeData);
		showTree("databaseTree",dataBaseTreeData);
		showTree("websiteTree",websiteTreeData);
		showTree("resorderTree",resorderTreeData);
	});
	
	function showTree(treeId,treeData){
		$("#"+treeId).tree({
			data:treeData,
			onClick:function(node){
				//alert("click");
				if(node && node.attributes){
					openTab(node);
				}
			}
		});
	}
	
	function openTab(node){
		if($('#mainTabs').tabs('exists',node.text)){
			$('#mainTabs').tabs('select',node.text);
		}else{
			$('#mainTabs').tabs('add',{
				title: node.text,
				selected: true,
				closable:true,
				content:node.attributes.url
			});
		}
	}
</script>
<body class="easyui-layout">
	<div data-options="region:'north',title:'North Title',split:true" style="height:100px;"></div>
    <div data-options="region:'south',title:'South Title',split:true" style="height:100px;"></div>
    <div data-options="region:'east',title:'通知',split:true" style="width:100px;"></div>
    <div data-options="region:'west',title:'导航',split:true" style="width:100px;">
    
       <div class="easyui-accordion" style="fit:true;">
	       <div title="菜品管理" style="overflow:auto;padding:10px;">
	      		<ul class="easyui-tree" id="resfoodTree">
                </ul>
	       </div>
	       <div title="订单管理" style="overflow:auto;padding:10px;">
	      		<ul class="easyui-tree" id="resorderTree">
                </ul>
	       </div>
	       <div title="客户管理" style="overflow:auto;padding:10px;">
	      		<ul class="easyui-tree" id="resuserTree">
                </ul>
	       </div>
	       <div title="数据库管理" style="overflow:auto;padding:10px;">
	      		<ul class="easyui-tree" id="databaseTree">
                </ul>
	       </div>
	       <div title="网站管理" style="overflow:auto;padding:10px;">
	      		<ul class="easyui-tree" id="websiteTree">
                </ul>
	       </div>
	       
	   </div>
    
    </div>
    <div data-options="region:'center',title:'Main Title',iconCls:'icon-ok'">
    	<div id="mainTabs" class="easyui-tabs" style="fit: true;width: 100%;height: 100%" >
			<div title="欢迎面板" style="padding:10px" >
				订餐系统欢迎您，今天是：
				<%
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
					Date date = new Date();
				%>
				<%=sdf.format(date) %>
			</div>
		</div>
   </div>

<%@ include file="bottom.jsp" %>
