<%@ page import="tfg.k8s.gui.*"%>
<%
	/*while(Results.heyActivo){
		//Está enviando datos
		
		Thread.sleep(100);
	}*/
	String error = "error = -1 ; not loaded yet";
	if(Results.heyActivo){
		//Enviando datos
		
		System.out.println("return -1");
		out.write(error);
		
	}else{
		System.out.println("return salida");
		out.write(Results.salidaHey);
		
	}
%>