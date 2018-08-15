<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<script type="text/javascript">
$(function(){
	var editFlag=undifined;
	var editor = CKEDITOR.replace('detail');  //ckeditor地编辑区
	$("#saveResfood").click(function(){
		var detail = editor.getData();
		detail = encodeURIComponent(escape(detail));
		var resfood={
				"fname":$("#fname").val(),
				"normprice":$("#normprice").val(),
				"realprice":$("#realprice").val(),
				"detail":detail
		};
		//因为这里不是通过form表单提交数据，而是ajax方式，所以需要用ajax来提交
		$.ajaxFileUpload({
			url:"admin/resfood/saveResfood.jsp",
			type:"POST",
			data:resfood,
			dataType:"JSON",
			fileElementId:["fphoto"],
			secureuri:true,
			success:function(data,status){
				if(data.code==1){
					$("#dlg").dialog('close');
					$("#resfoodListTable").dialog('reload');
				}
			}
		});
		
	});
	
	
	$('#resfoodListTable').datagrid({
		url:'resadmin/resfood.action?op=showFoodList',
		pagination:true,
		pageSize:100,
		pageList:[10,50,100,150,200],
	    rownumbers:true,
		singleSelect:true,
		title:"菜品浏览列表",
		fitColumns:true,
		idField:"fid",
		loadMsg:"正在努力为您加载数据",
		nowrap:true,
		fit:true,
		sortName:"fid",
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
	        	title:'菜名',
	        	width:70,
	        	align:'center',
	        	editor:{
	        		type:'validatebox',
	        		options:{
	        			required:true
	        		}
	        	}
	        },
	        {
	        	field:'normprice',
	        	title:'价格',
	        	width:70,
	        	sortable:true,
	        	align:'center',
	        	editor:{
	        		type:'validatebox',
	        		options:{
	        			required:true
	        		}
	        	}
	        },
	        {
	        	field:'realprice',
	        	title:'特价',
	        	width:70,
	        	sortable:true,
	        	align:'center',
	        	editor:{
	        		type:'validatebox',
	        		options:{
	        			required:true
	        		}
	        	}
	        },
	        {
	        	field:'_operate',
	        	title:'操作',
	        	width:70,
	        	sortable:true,
	        	align:'center',
	        	editor:{
	        		type:'validatebox',
	        		options:{
	        			required:true
	        		}
	        	}
	        }
	    ]],
	    toolbar: [{
	    	text:'上架新产品',
			iconCls: 'icon-add',
			handler: function(){
				$.ajax({
					url:'backlogin/product.action?op=getAllProductClass',
					dataType:'JSON',
					type:'POST',
					success:function(data){
						$("#product_class").html('');
						var selectString = "";
						if(data.code==1){
							for(var i=0;i<data.obj.length;i++){
								var item = data.obj[i];
								selectString += '<option value="'+item.protype+'">'+item.protype+'</option>';
							}
							$("#product_class").html(selectString);
						}
					}
				});
				$("#dlg").dialog('open').dialog('center').dialog('setTitle','新产品上架');
			}
		},'-',{
			text:'修改',
			iconCls: 'icon-edit',
			handler: function(){
				//选中一行进行编辑
				var rows=$("resfoodListTable").datagrid('getSelections');
				if(rows.length==1){//选中一行的话触发事件
					//如果当前为编辑状态，则退出编辑状态
					if(editFlag!=undifined){
						//结束编辑，转入之前编辑的行
						$("resfoodListTable").datagrid('endEdit',editFlag);
					}
					if(editFlag==undifined){
						//获取选定行地索引
						var index = $("resfoodListTable").datagrid('getRowIndex',rows[0]);
						//开始编辑并转入要编辑地行
						$("resfoodListTable").datagrid('beginEdit',index);
						editFlag = index;
					}
				}	
			}
		},'-',{
			text:'保存',
			iconCls: 'icon-redo',
			handler: function(){
				$("resfoodListTable").datagrid('endEdit',editFlag);
			}
		},'-',{
			text:'撤销',
			iconCls: 'icon-save',
			handler: function(){
				$("resfoodListTable").datagrid('rejectChanges');
			}
		}
		]
	});
	}
);
</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'center'" style="height:70%">
		<table id="resfoodListTable"></table>
	</div>
	
	<div id="dlg" class="easyui-dialog" style="width:400px;height:400px;padding:10px 20px" closed="true"
            data-options="iconCls:'icon-save',resizable:true,modal:true">
        <form id="fm" method="post" novalidate>
        	<div>
        		<label>菜品名：</label>
        		<input id="fname" class="easyui-textbox" required="true"/><br/>
        	</div>
        	<div>
        		<label>价格：</label>
        		<input id="normprice" class="easyui-textbox" required="true"/><br/>
        	</div>
        	<div>
        		<label>特价：</label>
        		<input id="realprice" class="easyui-textbox" required="true"/><br/>
        	</div>
        	<div>
        		<label>详情：</label>
        		<input id="detail" name="detail" class="ckeditor" required="true"/><br/>
        	</div>
        	<div>
        		<label>图片：</label>
        		<input id="phone" name="phone" type="file" required="true"/><br/>
        	</div>
        	
        </form>
    </div>
	
</body>
</html>