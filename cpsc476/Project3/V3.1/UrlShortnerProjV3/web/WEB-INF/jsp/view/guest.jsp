<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Url Shortner</title>

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

  <h3>Welcome guest..</h3>
<h4>Please <a href="login" class="btn btn-info btn-lg">login</a> , or use public access only.</h4>
</div>

   
    
    
    <%--This is the form used by public users to enter short URL, in order to get the full URL--%>
    <form action="guest" method="POST">
        <input type="hidden" name="action" value="guest" />
        <label><h3>Enter Short URL: </h3></label>
        <input type="text" name="shortUrl" />
        <br/>
        <br/>
        <input class="btn btn-info" type="submit" value="Get Long URL" />
    </form>
    </div>
       <br>
    
    <footer class="footer">
      <div class="container">
        <p class="text-muted">Designed and built with all the love in the world by Karthik, Dean & Neha.</p>
      </div>
    </footer>
</body>
</html>