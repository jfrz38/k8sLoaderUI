<%@ page import="tfg.k8s.gui.*"%>

<%
	String[] str = request.getParameterValues("namespace");
	String namespace = str[0];
	MainInterface mi = new MainInterface(-1);
	mi.refreshData(namespace);
	String strDeployment = "";
	
	for( DeploymentK8S d : MainInterface.deploymentsArray ){
		strDeployment+="<option value='"+d.getName()+"'>"+d.getName() +"</option>";
	}
	
	out.write(strDeployment);
%>


