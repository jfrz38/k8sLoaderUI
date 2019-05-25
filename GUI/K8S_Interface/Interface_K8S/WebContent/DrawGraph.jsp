<%@ page import="tfg.k8s.gui.*"%>

<%
	String str ="";
	if(Results.arrayDeployment.isEmpty()){
		str+="[0, 0, 0, 0, 0]";
	}else{
		for(int i=0; i<Results.arrayDeployment.size(); i++){
			DeploymentK8S d = Results.arrayDeployment.get(i);
			if(i==Results.arrayDeployment.size()-1){
				str+="["+d.getAge()+", "+d.getDesired()+", "+d.getCurrent()+", "+d.getUptodate()+", "+d.getAvailable()+"]";
			}else{
				str+="["+d.getAge()+", "+d.getDesired()+", "+d.getCurrent()+", "+d.getUptodate()+", "+d.getAvailable()+"], ";
			}
		}
	}
	
	out.write(str);
%>
