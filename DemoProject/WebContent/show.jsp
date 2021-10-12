<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.dao.FileDao,java.sql.ResultSet"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Excel Table</title>
</head>
<body>
<br>
<table border="1">
<tr>
<th>Name</th>
<th>City</th>
<th>Country</th>
</tr>
<%
FileDao dao = new FileDao();
ResultSet rs2 = dao.getData();
while(rs2.next()){
%>
<tr>
<td><%= rs2.getString(1) %></td>
<td><%= rs2.getString(2) %></td>
<td><%= rs2.getString(3) %></td>
</tr>
<% } %>
</table>
</body>
</html>