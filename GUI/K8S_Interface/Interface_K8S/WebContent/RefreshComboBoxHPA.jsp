<%@ page import="tfg.k8s.gui.*"%>

<%
	String[] str = request.getParameterValues("namespace");
	String namespace = str[0];
	MainInterface mi = new MainInterface(-1);
	mi.refreshData(namespace);
	String strHPA = "";
	
	for( HpaK8S h : MainInterface.hpaArray ){
		strHPA+="<option value='"+h.getName()+"'>"+h.getName() +"</option>";
	}
	
	out.write(strHPA);
%>
