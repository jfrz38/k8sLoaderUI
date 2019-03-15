<%@ page import="tfg.k8s.gui.*"%>

<%
	String[] r = request.getParameterValues("accion");
	String actionToDo = r[0];
	
	if(actionToDo.equals("clearHPA")){
		
		if(Results.arrayHPA == null) return;
		//HpaK8S aux = Results.arrayHPA.get(Results.arrayHPA.size()-1);
		Results.arrayHPA.clear();
		//Results.arrayHPA.add(aux);
		
	}else if(actionToDo.equals("stopHPA") || actionToDo.equals("startHPA")){
		
		
		if(Results.hpaThread==null) return;
		if(actionToDo.equals("stopHPA")){
			//Results.hpaThread.wait();
			Results.hpaThread.suspend();
		}else{
			Results.hpaThread.resume();
		}
		
		/*if(!Results.hpaThread.isAlive()) return;
		Results.hpaThread.stop();*/
		
	}else if(actionToDo.equals("clearDeployment")){
		
		if(Results.arrayDeployment == null) return;
		Results.arrayDeployment.clear();
		
	}else if(actionToDo.equals("stopDeployment") || actionToDo.equals("startDeployment")){
		
		if(Results.deploymentThread==null) return;
		if(actionToDo.equals("stopDeployment")){
			//Results.hpaThread.wait();
			Results.deploymentThread.suspend();
		}else{
			Results.deploymentThread.resume();
		}
		
		/*if(!Results.deploymentThread.isAlive()) return;
		Results.deploymentThread.stop();*/
	}
%>