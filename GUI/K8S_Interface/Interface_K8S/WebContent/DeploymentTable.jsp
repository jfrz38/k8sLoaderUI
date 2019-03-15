<%@ page import="tfg.k8s.gui.*"%>


	<table style="width:99%; margin-left: 0.5%; margin-right: 0.5%;">
  		<tr>
    		<th id="probando">Nombre</th>
    		<th>Desired</th>
    		<th>Current</th>
    		<th>up-to-date</th>
    		<th>available</th>
    		<th>Age</th>
  		</tr>
  		<% for( DeploymentK8S d : Results.arrayDeployment ){ %>
  		<tr>
  			<td><%=d.getName() %></td>
  			<td><%=d.getDesired()%></td>
  			<td><%=d.getCurrent()%></td>
  			<td><%=d.getUptodate()%></td>
  			<td><%=d.getAvailable()%></td>
  			<td><%=d.getAge()%></td>
  		</tr>
		<%}%>
	</table>
	