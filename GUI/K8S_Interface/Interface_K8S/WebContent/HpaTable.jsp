<%@ page import="tfg.k8s.gui.*"%>

	<table style="width:99%; margin-left: 0.5%; margin-right: 0.5%;">
  		<tr>
    		<th>Nombre</th>
    		<th>Referencia</th>
    		<th>Objetivos</th>
    		<th>minPods</th>
    		<th>maxPods</th>
    		<th>Réplicas</th>
    		<th>Tiempo</th>
  		</tr>
  		<% //for( HpaK8S h : Results.arrayHPA ){
  			for(int i = Results.arrayHPA.size()-1; i >= 0 ; i--){%>
  		<tr>
  			<td><%=Results.arrayHPA.get(i).getName()%></td>
  			<td><%=Results.arrayHPA.get(i).getReference()%></td>
  			<td><%=Results.arrayHPA.get(i).getTargets()%></td>
  			<td><%=Results.arrayHPA.get(i).getMinReplica()%></td>
  			<td><%=Results.arrayHPA.get(i).getMaxReplica()%></td>
  			<td><%=Results.arrayHPA.get(i).getCurrReplicas()%></td>
  			<td><%=Results.arrayHPA.get(i).getAge()%></td>
  		</tr>
		<%}%>
	</table>
