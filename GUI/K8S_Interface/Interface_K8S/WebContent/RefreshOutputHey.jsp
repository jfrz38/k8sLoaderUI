<%@ page import="tfg.k8s.gui.*"%>

<%
	while(Results.heyActivo){
		//Está enviando datos
		
		Thread.sleep(100);
	}
	
	out.write(Results.salidaHey);
%>