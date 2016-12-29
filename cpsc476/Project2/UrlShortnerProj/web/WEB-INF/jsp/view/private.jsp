<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8" import="com.cpsc476.Url" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
   <head>
   <title>Url Shortner</title>
      <% String u[][]=(String[][])request.getAttribute("url"); 
         String surl=(String)request.getAttribute("shorturl");
         String user=(String)request.getAttribute("username");
         String lurl=(String)request.getAttribute("longurl");%>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>Private Page</title>
      <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <style type="text/css">
         <%@include file="/webapp/resources/css/bootstrap.min.css" %>
         <%@include file="/webapp/resources/css/bootstrap-theme.min.css" %>
      </style>
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <script src="/webapp/resources/jquery.js"></script>
      <script src="/webapp/resources/js/bootstrap.js"></script>
   </head>
   <body>
<div class="container">
   <br>
   <div class="jumbotron">
   <img src="/webapp/resources/url.jpg" class=" center-block" height="110" width="535" align="middle"></img>
   
  <h3>Hello <%= user %>, This is your private page!</h3>
  <p>Shorten your url here!!</p>
  <p align="right" ><span class="btn btn-info"><a href="login?logout">Logout</a></span></p>
</div>
      <div class="container" >
      <div class="row">

   <div class="col-md-10" align="left">
            <div class="panel panel-default">
                <div class="panel-body">
            
      <form method="POST" action="private" class="form-horizontal padding-small-horizontal">
              <div class="form-group">
                  <input type="hidden" name="action" value="login" />
                  <label for="inputEmail3" class="col-sm-3 control-label"><h4>Enter a long url:</h4></label>
                  <div class="col-sm-6">
         <input type="text" class="form-control" name="longurl"><br/><br/></div></div>
         <input class="btn btn-info" type="submit" value="Submit" />
      </form>
      </div></div></div></div></div>
      <br><br>
      <h4>Your shortened url is : </h4>
      <%if(surl!=null){ %> 
      <a href="private?action=redirect&url=<%= surl %>" /><h1><%= surl %></h1></a>
      <%} %>
      <br>
      <%
         if( ((Boolean)request.getAttribute("urlpresent") )  )
         
         {
         
             %>
      <h2>Please enter a proper URL or The Url was previously shortened . See the list below</h2>
      <%
         }%>
      <ul class="list-group">
         <table width="400" class="table table-striped" >
            <tr>
               <th>Short urls</th>
               <th>Clicks</th>
            </tr>
           
            <%if(u!= null){ %>
            <%for(int i=0;i<u.length;i++){ %>
            
            <%if(u[i][0]!= null ){ %>
            <% //request.setAttribute("lurl", lurl); %>
            <tr>
               <td>
                  <li class="list-group-item"><a href="private?action=redirect&url=<%=u[i][0]%>" /><%= u[i][0] %></li>
               </td>
               <td align="center"><span class="badge"><%= u[i][1] %></span></td>
            </tr>
            <%} %>
            <%} %>
         </table>
      </ul>
      <%} %>
      </div>
      
      <br><br>
      <footer class="footer">
      <div class="container">
        <p class="text-muted">Designed and built with all the love in the world by Karthik, Dean & Neha.</p>
      </div>
    </footer>
   </body>
</html>