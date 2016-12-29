<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>

        <title>Customer Support</title>

    </head>

    <body>

        <h2>Login</h2>

        You must log in to access the customer support site.<br /><br />

        <%

            if(((Boolean)request.getAttribute("loginFailed")))

            {

                %>

        <b>The username or password you entered are not correct. Please try

            again.</b><br /><br />

                <%

            }

        %>

             

          <form method="POST" action="login">

            <input type="hidden" name="action" value="signup" />

            Username<br />

            <input type="text" name="new_user" /><br /><br />

            Password<br />

            <input type="password" name="new_password" /><br /><br />

            <input type="submit" value="Sign Up" />

        </form>   
        <br/>
        <br/>
             

            

        <form method="POST" action="login">

            <input type="hidden" name="action" value="login" />

            Username<br />

            <input type="text" name="username" /><br /><br />

            Password<br />

            <input type="password" name="password" /><br /><br />

            <input type="submit" value="Log In" />

        </form>

    </body>
</html>