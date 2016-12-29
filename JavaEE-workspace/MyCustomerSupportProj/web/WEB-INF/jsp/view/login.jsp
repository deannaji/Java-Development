<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>
   <h3>Welcome, please login to be able to access this website:</h3>
   <% if((boolean)request.getAttribute("loginFailed")==true){%>
	  <p style="color:red;">Error: The username/password combination is invalid!
	  Please try again.</p>
	  <br/> 
   <%}%>
   <form action="login" method="POST">
        Username: <input type="text" name="username" /><br/><br/>
        Password: <input type="text" name="password" /><br/><br/>
        <input type="submit" value="Login">
   </form>
</body>
</html>