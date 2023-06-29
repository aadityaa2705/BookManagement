<%@page import="model.*" %>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Book Collection</title>
<style type="text/css">
	.container{
		display:flex;
		justify-content: center;
		align-items: center;	
	}
	table{
		width:800px;
		height: 350px;
		}
	table,th,td{
		border-collapse: collapse;
		border: 2px solid black;
	}
	td{
		padding: 10px;
	}
	
	th,td{
		text-align: center;
	}
</style>
</head>
<body>
	<%
		HttpSession ses = request.getSession(false);
		BookService service = new BookService();
		ArrayList<Book> bookList = new ArrayList<>();
		if(ses!=null){
		bookList =  service.displayBooks();
		}
	%>
	<div class = "container">
	<table>
	<tr>
	<th colspan="5">Book Information</th>
	</tr>
	<tr>
	<th>Id</th>
	<th>Name</th>
	<th>Genre</th>
	<th>Author</th>
	<th>Price</th>
	</tr>
	<% for(Book book : bookList){ %>
	<tr>
	<td><%=book.getBookId() %></td>
	<td><%=book.getBookName() %></td>
	<td><%=book.getBookGenre() %></td>
	<td><%=book.getBookAuthor() %></td>
	<td><%=book.getBookPrice() %></td>
	</tr>
	<%} %>
	</table>
	</div>
</body>
</html>