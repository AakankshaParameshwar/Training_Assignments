<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Database</title>
<link href='http://fonts.googleapis.com/css?family=Jacques+Francois|Open+Sans:400,600,700,300' rel='stylesheet' type='text/css'>
<link type="text/css" rel="stylesheet" href="css/app.css"/>
<script src="js/jquery.min.js" type="text/javascript"></script>
<!--script type="text/javascript" src="js/app.js"></script-->
<script type="text/javascript">
$(document).ready(function(){
	var $insertForm=$('#insert');
	$('.edit-button').click(function(){
		var $parent=$(this).parent().parent();
		$(this).css("display","none");
		$parent.find("#save").css("display","inline-block");
		$parent.find('input').each(function(){
			if($(this).attr('disabled')){
				$(this).removeAttr('disabled');
			}
		});
		//request.setParameter("toBeUpdated",id);
	});
	var modify=function(){
		var $parent=$(this).parent().parent();
		var id=$parent.find("td").first().html();
		request.getSession().setAttribute("UPDATE-ID",id);
		alert(id);
		$("#X").value(id);
		alert($("#X").value());
	};
	/*$('.save-button').click(function(){
		
		$(this).parent().html("<button class='edit-button'><img class='edit' src='images/Edit-icon.png'/></button>");
		$parent=$(this).parent().parent();
		$parent.find('input').each(function(){
			if(!$(this).attr('disabled')){
				
				$(this).attr('disabled');
			}else {
                $(this).attr({
                    'disabled': 'disabled'
                });
            }
		});
	});*/
});
</script>
</head>
<body>
<div id="page">
	<%@page import="assignment.jdbcEample.DBPersister"%>
	<%@page import="assignment.jdbcEample.Student"%>
	<%@page import="assignment.jdbcEample.Employee"%>
	<%@page import="assignment.jdbcEample.ResultSetMapper;"%>
	<%DBPersister obj=null;
	String heading="";

	System.out.println(request.getSession().getAttribute("DB"));
	if(request.getSession().getAttribute("DB").equals("student")){
		heading="STUDENT DETAILS";
		obj=new Student();
	}else if(request.getSession().getAttribute("DB").equals("employee")){
		heading="EMPLOYEE DETAILS";
    	obj=new Employee();
	}
    String data[][];
    %>
    <h2><%=heading %><a class="add-button" href="/CompleteAssignments/InsertData.jsp"><img class="add" src="images/Add-icon.png"/></a ></h2>
    
    <table id="view" align="center">
    	<tr class="add-border">
    		<% obj.connectToDB("assignment2", "root", "aakanksha");
    		data=obj.displayAllData();
    		for(int j=0;j<data[0].length;j++){ %>
    		<th class="add-border"><%=data[0][j] %></th>
    		<%} %>
    	</tr>
    		<%for(int i=1;i<data.length;i++){%>
    		<tr class="add-border">
    			<%for(int j=0;j<data[0].length;j++){
    				if(j!=0){%>
    				<td class="add-border"><input type="text" name=<%=data[0][j]%> value=<%=data[i][j] %> disabled/></td>
    				<%}else{ %>
    				<td class="add-border"><%=data[i][j] %></td>
    			<%} 
    			}%>
    		
    		<td><button class="edit-button"><img class="edit" src="images/Edit-icon.png"/></button>
    		<form id="save" action="/CompleteAssignments/UpdateData" method="get" onsubmit="modify()">
    			<input type="hidden" id="X" name="selected-id" value=<%=request.getSession().getAttribute("UPDATE-ID")%>/>
    			<input type="submit" value="SAVE" />
    		</form></td>
    		<td><a href="/CompleteAssignments/DeleteData"><img class="delete" src="images/Delete-icon.png"/></a></td>
    		</tr>
    		
    		<%}
    		obj.closeConnection();%>
    </table>
</div>
</body>
</html>