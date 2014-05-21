<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Select Database</title>
<link href='http://fonts.googleapis.com/css?family=Jacques+Francois|Open+Sans:400,600,700,300' rel='stylesheet' type='text/css'>
<link type="text/css" rel="stylesheet" href="css/app.css"/>
<script src="js/jquery.min.js" type="text/javascript"></script>
</head>
<body>
<div id="page">
<h1> Select the database you want to view</h1>
<form action="/CompleteAssignments/DisplayData" method="get">
<select name="database">
<option value="employee">Employee</option>
<option value="student"> Student</option>
</select>
<!-- input type="radio" name="database" value="student"/>Student</br>
<input type="radio" name="database" value="employee"/>Employee</br -->
<input type="submit" value="view"/>
</form>
</div>
</body>
</html>