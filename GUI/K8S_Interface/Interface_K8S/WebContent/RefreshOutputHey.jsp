<%@ page import="tfg.k8s.gui.*"%>
<%
	String error = "error = -1 ; not loaded yet";
	if(Results.heyActivo){
		//Enviando datos
		
		out.write(error);
		
	}else{
		out.write(Results.salidaHey);
		
	}
%>