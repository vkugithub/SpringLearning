<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>Spring MVC Form Handling</title>
</head>
<body>
	<!--  <h2>Student Information ${display}</h2>-->
	<form:form method="POST" action="/form">
		
		<table>
			
			<tr>
				<td><form:label path="name">Name</form:label></td>
				<td><form:input path="name" /><form:errors path="name" /></td>
				
			</tr>
			<tr>
				<td><form:label path="age">Age</form:label></td>
				<td><form:input path="age" /><form:errors path="age" /></td>
			</tr>
			<tr>
				<td><form:label path="id">id</form:label></td>
				<td><form:input path="id" /><form:errors path="id" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Submit" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>