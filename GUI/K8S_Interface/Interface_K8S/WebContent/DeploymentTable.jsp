<%@ page import="tfg.k8s.gui.*"%>


	<table style="width:99%; margin-left: 0.5%; margin-right: 0.5%;">
  		<tr>
    		<th id="deployment_table">Nombre</th>
    		<th>Deseado</th>
    		<th>Actual</th>
    		<th>Por actualizar</th>
    		<th>Disponible</th>
    		<th>Tiempo</th>
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