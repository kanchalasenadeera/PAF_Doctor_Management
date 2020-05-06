<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="model.Doctor" %>
    <%@ page import="com.DoctorAPI" %>
    	
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Doctor_Insert</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="./Components/Doctor.js"></script>

 <style>
table {
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 90%;
  margin-left: auto;
  margin-right: auto;
}

td, th {
  border: 1px solid #dddddd;
  text-align: left;
  padding: 8px;
}

tr:nth-child(even) {
  background-color: #dddddd;
}
.card{
	padding: 20px;
	border: 1px solid black;
	border-radius: 10px;
	margin-bottom: 20px;
	font-size: 15px;
	margin-top: 15px;
}
#btnSave{
	margin-top: 15px;
	font-size: px;
	width: 100%;
}
.alert{
	width: 80%;
	margin-left: auto;
	margin-right: auto;
	padding: 15px;
	text-align: center;
}

  </style>
</head>
<body>

<div class="jumbotron text-center" style="padding: 10px;;">
		<h1>Doctor Profile</h1>
	
	</div>

<div class="container">
		<div class="row"><div class="col-md-3"></div>
			<div class="col-md-6">
				<div class="card">



<form id="formDoctor" name="formDoctor" method="post" action="Doctor_Insert.jsp">
		Doctor Name: 
		<input id="docName" name="docName" type="text"
			class="form-control form-control-sm" required> <br>
	    Doctor Age:
	     <input id="docAge" name="docAge" type="text"
	      class="form-control form-control-sm"required > <br> 
		Doctor Gender: 
		<input id="docGender" name="docGender" type="text"
			class="form-control form-control-sm" required> <br> 
		Doctor Specialization:
		 <input id="docSpecialization" name="docSpecialization" type="text"  
			class="form-control form-control-sm" required> <br> 
		Hospital ID:
		<input id="hosID" name="hosID" type="text"  
			class="form-control form-control-sm" required> <br>
			
		<input id="btnSave" name="btnSave" type="button" value="Save" 
			class="btn btn-primary"  > 
		<input type="hidden" id="didDoctorIDSave" name="didDoctorIDSave" value="">
	</form>
	</div>
</div>
</div>
</div>

	<div id="alertSuccess" class="alert alert-success">
		
</div>
<div id="alertError" class="alert alert-danger"></div>
		
     <div id="divItemsGrid">

	<%
	 Doctor d1 = new Doctor();
	 out.print(d1.readDoctor());
	%>
	</div>
		
</body>
</html>