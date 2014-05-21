<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Enter values</title>
<link href='http://fonts.googleapis.com/css?family=Jacques+Francois|Open+Sans:400,600,700,300' rel='stylesheet' type='text/css'>
<link type="text/css" rel="stylesheet" href="css/app.css"/>
</head>
<body>
 <div id="page">
	<div id="stud-info">
	<h2>Student Info</h2>
	<%@page import="assignment.jdbcEample.DBPersister"%>
	<%@page import="assignment.jdbcEample.Student"%>
	<%@page import="assignment.jdbcEample.Employee"%>
	<%@page import="assignment.jdbcEample.ResultSetMapper;"%>
	<%
    String insertAction="/CompleteAssignments/InsertData";
    String insertMethod="get";
    %>

    <form id="insert" action=<%=insertAction %> method=<%=insertMethod %>>
    <table  class="add-border" align="center">
    	<tr class="add-border">
    		<td>Name : </td>
    		<td><input type="text" name="name" /></td>
    	</tr >
    	<tr class="add-border">
    		<td >Marks 1 : </td>
    		<td><input type="text" name="marks1" /></td>
    	</tr>
    	<tr class="add-border">
    		<td>Marks 2 : </td>
    		<td><input type="text" name="marks2" /></td>
    	</tr>
    	<tr class="add-border">
    		<td>Percentage : </td>
    		<td><input type="text" name="percentage" /></td>
    	</tr>
    	<tr>
    		<td><input type="submit" value="INSERT"/></td>
    		<td><button class="cancel-button">CANCEL</button></td>
    	</tr>
    </table>
    
    </form>
	</div>
</div>
</body>
</html>