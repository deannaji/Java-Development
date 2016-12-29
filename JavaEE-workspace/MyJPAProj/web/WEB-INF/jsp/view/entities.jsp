<!--<%--@elvariable id="books" type="java.util.List<com.cpsc476.Book>"--%> -->
<%@ page import="com.cpsc476.Book" %>
<% Book book = (Book)request.getAttribute("Book"); %>


<!DOCTYPE html>
<html>
    <head>
        <title>Entities</title>
    </head>
    <body>

        <b>Books</b>
        <table>
            <tr>
                <th>ISBN</th>
                <th>Title</th>
                <th>Author</th>
                <th>Price</th>
            </tr>
            
                <tr>
                    <td><%= book.getIsbn() %></td>
                    <td><%= book.getAuther() %></td>
                    <td><%= book.getTitle() %></td>
                    <td><%= book.getPrice() %></td> 
                    
                    
                </tr>
 
        </table><br />

        <form method="post" action="<c:url value="/entities" />">
            <input type="submit" value="Add More Entities" />
        </form>
    </body>
</html>
