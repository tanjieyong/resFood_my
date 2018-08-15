var xmlHttp;
function createXMLHttpRequest(){
	if(window.XMLHttpRequest){
		xmlHttp = new XMLHttpRequest();
	}else if(window.ActiveXObject){
		xmlHttp = new ActiveXObjet("Microsoft.XMLHTTP");
	}
}

//重新获取验证码
function changeVilidateCode(obj){
	var timenow=new Date().getTime();
	obj.src="image.jsp?d=" + timenow
}