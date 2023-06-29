<%@page import="javax.servlet.http.HttpSession" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		HttpSession sess = request.getSession(false);
	String user="";	
		if(sess!=null){
		user += (String) sess.getAttribute("username");
		}
	%>
	<h3 style="text-align: center;">Welcome <%=user %></h3>
	<form action="bookslink" method="post">
		<label>Book name</label>
		<input type="text" name="bookname" required="required"/><br><br>
		<label>Book Genre</label>
		<input type="text" name="bookgenre" required="required"/><br><br>
		<label>Book Author's Name</label>
		<input type="text" name="author" required="required"/><br><br>
		<label>Book Price</label>
		<input type="text" name="bookprice" required="required"/><br><br>
		<input type="submit" value="SUBMIT"/>
		<input type="reset" value="RESET"/><br><br>
		<a href="displaybooks.jsp"><input type="button" value="VIEW"/></a>
	</form>
</body>
</html>