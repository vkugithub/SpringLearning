<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<title>satsang</title>
<spring:url value="/" var="urlHome" />
<spring:url value="/form" var="urlAddUser" />
<body>
	<table bgcolor="" height=70 width="100%">
		<colgroup span="2" width="100" />

		<tr>
			<td ><img src="pics/swastika.jpg"  width="102"
				height="102" /></td>
			<td width=89%><h1>
					<font size=10 textcolor="blue"> </font>
					<center>
						<font size="10" textcolor="blue"> 
							<!-- <marquee behavior="alternate"> -->
								<span class="style6"><font color="#660000">OM TATSAT</font></span>
							<!--</marquee>-->
						</font>
					</center>
				</h1></td>
			<td><img src="pics/swastika.jpg" width="102"
				height="102" /></td>	
		</tr>
	</table>
	<br>
<center>
<table bgcolor="" height="10" width="100%">
<td width="13%">
<tr aling="center">
<td><span class="style6"><a href=main.jsp target="main">HOME</a>
    </h2>
</span></td>
<td>
</td>
<td width="16%"><span class="style6"><a href=form.jsp target="main">Registration</a>
    </h2>
</span></td>
<td width="20%"><span class="style6"><a href=list.jsp target="main">List</a>
    </h2>
</span></td>
<td width="19%"><span class="style6"><a href=login.jsp target="main">Login</a>
    </h2>
</span></td>
<td width="15%"><span class="style6"><a href=experiment.jsp target="main">Experiment</a>
    </h2>
</span></td>
<td width="16%"><span class="style6"><a href=suggestion.jsp target="main">Suggestion</a>
    </h2>
</span></td>
</tr>
</table>
<br>
<br>


</body>
</head>
</html>
