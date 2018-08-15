package com.yc.web.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.yc.bean.JsonModel;

public class BaseServlet extends HttpServlet{
	private static final long serialVersionUID = 412782235598990733L;
	
	protected String op;
	protected int pages = 1;
	protected int pagesize = 2;
	protected HttpSession session;
	protected JsonModel jm = new JsonModel();
	
	//专用于easyui的
	protected int page;
	protected int rows;
	protected String sort;
	protected String order;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		session = request.getSession();
		op=request.getParameter("op");
		if(request.getParameter("pages") != null){
			pages = Integer.parseInt(request.getParameter("pages"));
		}
		
		if(request.getParameter("pagesize") != null){
			pagesize = Integer.parseInt(request.getParameter("pagesize"));
		}
		if(request.getParameter("page") != null){
			page = Integer.parseInt(request.getParameter("page"));
		}
		if(request.getParameter("rows") != null){
			rows = Integer.parseInt(request.getParameter("rows"));
		}
		if(request.getParameter("sort") != null){
			sort = request.getParameter("sort");
		}
		if(request.getParameter("order") != null){
			order = request.getParameter("order");
		}
		super.service(request, response);
	}
	
	protected void outJsonString(Object obj,HttpServletResponse response) throws IOException{
		Gson gson = new Gson();
		String jsonString = gson.toJson(obj);
		outJsonString(jsonString, response);
	}
	
	protected void outJsonString(String jsonString,HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.println(jsonString);
		out.flush();
	}
	
	protected Object parseParameterToT(HttpServletRequest request,Class class1) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Map<String,String[]> map = request.getParameterMap();
		Map<String,String> params = new HashMap<String, String>();
		for(Map.Entry<String, String[]> entry : map.entrySet()){
			String key = entry.getKey();
			String value = entry.getValue()[0];
			params.put(key, value);
		}
		List<Method> allSetMethods = findAllSetMethods(class1);
		Object obj = class1.newInstance();
		for(Method m : allSetMethods){
			for(Map.Entry<String, String> param : params.entrySet()){
				String paramName = param.getKey();
				String value = param.getValue();
				if(m.getName().equalsIgnoreCase("set" + paramName)){
					Class typeParam = m.getParameterTypes()[0];
					String typeName = typeParam.getName();
					if("int".equals(typeName) || "java.lang.Integer".equals(typeName)){
						m.invoke(obj, Integer.parseInt(value));
					}else if("long".equals(typeName) || "java.lang.Long".equals(typeName)){
						m.invoke(obj, Long.parseLong(value));
					}else if("short".equals(typeName) || "java.lang.Short".equals(typeName)){
						m.invoke(obj, Short.parseShort(value));
					}else if("double".equals(typeName) || "java.lang.Double".equals(typeName)){
						m.invoke(obj, Double.parseDouble(value));
					}else{
						m.invoke(obj, value);
					}
				}
			}
		}
		return obj;  
		
	}
	
	protected List<Method> findAllSetMethods(Class clz){
		List<Method> list = new ArrayList<Method>();
		Method[] methods  = clz.getDeclaredMethods();
		for(Method method : methods){
			if(method.getName().startsWith("set")){
				list.add(method);
			}
		}
		return list;
	}
	
}
