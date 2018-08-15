<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script language="javascript" src="js/common.js"></script>
			<!-- 用postForm表单向result.jsp用GET请求提交数据，注意method属性和action属性的设置
				loginName参数用来保存用户名 
				loginPass参数用来保存密码
			-->
<form method="POST" name="loginForm" action="backresadmin.action">
<input type="hidden" name="op" value="login"/>
<table width="100%" border="0">
	<tr>
		<td width="15%">&nbsp;</td>
		<td width="12%">&nbsp;</td>
		<td width="29%">&nbsp;</td>
		<td width="44%">&nbsp;</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td valign="middle" align="center">用户名：</td>
		<td valign="top"><input type="text" name="raname" size="19"
			class="input"></td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td valign="middle" align="center">密&nbsp;&nbsp;码：</td>
		<td valign="top"><input type="password" name="rapwd" size="20"
			class="input"></td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td valign="middle" align="center">验证码：</td>
		<td valign="top">
			<input type="text" name="valcode" size="20" class="input">
			<img src="image.jsp" onclick="changeVilidateCode(this)" border="0" title="点击图片刷新验证码" size="10"/>
		</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="2" align="center"><input type="submit"
			name="Submit" value="登录"></td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td height="33" colspan="4">${msg }</td>
	</tr>
</table>
</form>
