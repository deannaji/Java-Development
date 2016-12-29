<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   <title>Url Shortner</title>
</head>

<body>

   <#--Check if the URL's DB is not empty && URL is found in the DB: -->
   <#if (emptyDB != true && itemnotfound != true) >
      The original URL for the shortened one you entered is:
      <h3> ${url} </h3>
      <br/>
      <a href="public">Go Back</a>
   </#if>
   
   <#--Check if the URL is not found in the DB: -->
   <#if (itemnotfound == true)>
	  Sorry, URL is not found. Try another one!
	  <br/> 
	  <a href="public">Go Back</a>
   </#if>
   
   <#--check if the URL DB is empty, i.e no URL's stored in DB yet: -->
   <#if (emptyDB == true)>
	  Sorry, There are no URL saved. Please login and add some URL's!
	  <br/>
	  <a href="public">Go Back</a> 
   </#if>
   
      
</body>
</html>