<%@ page import="tfg.k8s.gui.*"%>

<%
	while(Results.heyActivo){
		//Est� enviando datos
		
		Thread.sleep(100);
	}
	System.out.println("Salida refreshOutputHey");
	out.write(Results.salidaHey);
%>