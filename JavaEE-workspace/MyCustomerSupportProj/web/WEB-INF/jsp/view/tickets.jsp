<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Map" %>    
<%@ page import="com.cpsc476.Ticket" %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Customer Support</title>
</head>
<body>

 <%  Map<Integer, Ticket> ticketsDB =(Map<Integer, Ticket>)request.getAttribute("ticketsDB"); %>  

 <% if (ticketsDB.size()==0){ %>
			
			       <h3> There are no tickets in the system!</h3>
			       <a href='tickets?action=create'>Create a new ticket</a>
			      
	<%	}else{ 
	
		   for (int id : ticketsDB.keySet()){
		      Ticket ticket = ticketsDB.get(id); %>
		           <p>Ticket # <%= id %>  Customer Name: <%= ticket.getName() %> 
		               <a href=''>Download File </a>
		           </p>   
		   <% } %>
		  <a href='tickets?action=create'>Create a new ticket</a>
		   
	<%   }%>

<br>
<br>
<a href="login?logout">Logout</a>
</body>
</html>