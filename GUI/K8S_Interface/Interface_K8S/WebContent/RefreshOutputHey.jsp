<%@ page import="tfg.k8s.gui.*"%>
<%
	String error = "error = -1 ; not loaded yet";
	if(Results.heyActivo){
		//Enviando datos
		
		out.write(error);
		
	}else{
		Results.salidaHey = Results.salidaHey.replace("\n","&#013;");
		//Results.salidaHey = Results.salidaHey.replace("?","&#9632;");
		//Results.salidaHey = Results.salidaHey.replace("?",">");
		Results.salidaHey = Results.salidaHey.replace("?","&#9632;");
		out.write(Results.salidaHey);
		
	}
%>
