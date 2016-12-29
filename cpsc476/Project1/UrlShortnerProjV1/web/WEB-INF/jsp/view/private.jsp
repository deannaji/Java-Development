<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8" import="com.cpsc476.Url" %>
   <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<% String u[][]=(String[][])request.getAttribute("url"); 
String surl=(String)request.getAttribute("shorturl");
String user=(String)request.getAttribute("username");
String lurl=(String)request.getAttribute("longurl");%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Private Page</title>
</head>
<body>
<h1>Hello <%= user %>, This is you private page!</h1><br>
<a href="login?logout" align="right">Logout</a>

<h3>Shorten your url here!!</h3><br><br>
<h3>Enter a long url:</h3>

<form method="POST" action="private">
<input type="text" name="longurl"><br/><br/>
<input type="submit" value="Submit" />
</form>

<br><h3>Your shortened url for <%= lurl %> is : </h3>
<%if(surl!=null){ %>
<%= surl %>
<%} %>
<br>
<table border="1" width="400"><tr>
<th>Short urls</th>
<th>Clicks</th></tr>
<%if(u!= null){ %>
<%for(int i=0;i<u.length;i++){ %>
<%if(u[i][0]!= null){ %>
<% //request.setAttribute("lurl", lurl); %>
<tr><td><a href="private?action=redirect&url=<%=u[i][0]%>" /><%= u[i][0] %></td>
<td><%= u[i][1] %></td>
</tr>
<%} %>
<%} %>
</table>
<%} %>
</body>
</html>