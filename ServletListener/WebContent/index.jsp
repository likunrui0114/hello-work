<%@page import="xyz.etesh.test.UserInfoTrace"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="xyz.etesh.test.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
	UserInfoList list = UserInfoList.getInstance();
	UserInfoTrace ut = new UserInfoTrace();
	String name = request.getParameter("user");
	ut.setUser(name);
	session.setAttribute("list", ut);
	list.addUserInfo(ut.getUser());
	session.setMaxInactiveInterval(10);
%>
<body>
	<textarea rows="8" cols="20">
<%
	Vector vector = list.getList();
	if (vector != null && vector.size() > 0) {
		for (int i = 0; i < vector.size(); i++) {
			out.println(vector.elementAt(i));
		}
	}
%>
	</textarea>
</body>
</html>