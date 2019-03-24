<%@ page import="tfg.k8s.gui.*"%>

<%
	String[] str = request.getParameterValues("namespace");
	String namespace = str[0];
	MainInterface mi = new MainInterface(-1);
	mi.refreshData(namespace);
	String strService = "";
	
	for( ServiceK8S s : MainInterface.servicesArray ){
		strService+="<option value='"+s.getName()+"'>"+s.getName() +"</option>";
	}
	
	out.write(strService);
%>