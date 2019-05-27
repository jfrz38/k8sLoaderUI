package tfg.k8s.gui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadJSON {

	public static boolean OS;	//True = Windows ; False = Unix
	
	public ReadJSON() {
		if(System.getProperty("os.name").startsWith("Windows")) OS = true;
		else OS = false;
	}

	public ArrayList<NamespaceK8S> readNamespace() throws FileNotFoundException, IOException, ParseException {

		ArrayList<NamespaceK8S> namespacesArray = new ArrayList<NamespaceK8S>();

		JSONParser builder = new JSONParser();
		JSONObject json;
		if(OS) {
			//Windows
			json = (JSONObject) builder.parse(new FileReader("C:\\Users\\josef\\Desktop\\namespaces.json"));
		}else {
			json = (JSONObject) builder.parse(getOutputShell("sudo kubectl get namespaces -o json"));
		}

		//for (Object o : json) {

			//JSONObject jsonNamespace = (JSONObject) o;

			// Items es un JSONArray y cada lista tiene un objeto JSONObject
			JSONArray items = (JSONArray) json.get("items");
			for (Object i : items) {
				JSONObject aux = (JSONObject) i;
				JSONObject metadata = (JSONObject) aux.get("metadata");
				String name = (String) metadata.get("name");
				NamespaceK8S namespace = new NamespaceK8S(name);
				namespacesArray.add(namespace);
			}

		//}
		return namespacesArray;
	}

	public ArrayList<DeploymentK8S> readDeployments(String namespace) throws FileNotFoundException, IOException, ParseException {
		
	 	ArrayList<DeploymentK8S> deploymentsArray = new ArrayList<DeploymentK8S>();
		
		JSONParser builder = new JSONParser();
		
		JSONObject json;
		if(OS) {
			//Windows
			json = (JSONObject) builder.parse(new FileReader("C:\\Users\\josef\\Desktop\\deployments.json"));
		}else {
			json = (JSONObject) builder.parse(getOutputShell("sudo kubectl get deployment -n "+namespace+" -o json"));
		}
		
		
		/*if(namespace.equals("kube-system")) {
			DeploymentK8S dk8s = new DeploymentK8S("dep kube system");
			DeploymentK8S dk8s2 = new DeploymentK8S("dep kube system2");
			DeploymentK8S dk8s3 = new DeploymentK8S("dep kube system3");
			deploymentsArray.add(dk8s);
			deploymentsArray.add(dk8s2);
			deploymentsArray.add(dk8s3);
			return deploymentsArray;
		}*/
		//Valores para objeto deployment
		String name; //
		String labels; //
		String replicas; //
		String readyReplicas; //
		String updatedReplicas; //
		String availableReplicas; //
		boolean status; //
		String apiVersion; //
		String fecha; //
		
		//TODO
		/*
		desired replicas = .spec.replicas 
		current replicas = .status.replicas 
		up-to-date replicas = .status.updatedReplicas 
		available replicas  .status.availableReplicas 

		Source: https://kubernetes.io/docs/concepts/workloads/controllers/deployment/
		
		*/
		
		//for (Object o : json) {
			//JSONObject deployment = (JSONObject) o;
			JSONArray items = (JSONArray) json.get("items");
			for(Object i : items) {
				JSONObject aux = (JSONObject) i;
				apiVersion = (String) aux.get("apiVersion");
				JSONObject metadata = (JSONObject) aux.get("metadata");
				fecha = (String) metadata.get("creationTimestamp");
				JSONObject labelsJSON = (JSONObject) metadata.get("labels");
				//labels = (String)labelsJSON.get("app");
				name = (String) metadata.get("name");
				JSONObject statusJSON = (JSONObject) aux.get("status");
				replicas = (String) statusJSON.get("replicas").toString();
				updatedReplicas = (String) statusJSON.get("updatedReplicas").toString();
				Long ar = (Long) statusJSON.get("availableReplicas");//.toString();
				if(ar == null)availableReplicas="0"; 
				else availableReplicas = (String) statusJSON.get("availableReplicas").toString();
				Long rr = (Long) statusJSON.get("readyReplicas");//.toString();
				if(rr == null) readyReplicas = "0";
				else readyReplicas = (String) statusJSON.get("readyReplicas").toString();
				JSONArray conditions = (JSONArray) statusJSON.get("conditions");
				status = true;
				for(Object c : conditions) {
					JSONObject cc = (JSONObject) c;
					String str = (String) cc.get("status");
					if(!str.equals("True")) {
						status = false;
						break;
					}
				}
				
				//DeploymentK8S d = new DeploymentK8S(name, labels, replicas, readyReplicas, updatedReplicas, availableReplicas, status, apiVersion, fecha);
				DeploymentK8S d = new DeploymentK8S(name);
				deploymentsArray.add(d);
				
			}
		//}
		return deploymentsArray;
	}
	
	public ArrayList<HpaK8S> readHPA(String namespace) throws FileNotFoundException, IOException, ParseException {
		
		ArrayList<HpaK8S> hpaArray = new ArrayList<HpaK8S>();
		
		JSONParser builder = new JSONParser();
		
		JSONObject json;
		if(OS) {
			//Windows
			json = (JSONObject) builder.parse(new FileReader("C:\\Users\\josef\\Desktop\\hpa.json"));
		}else {
			json = (JSONObject) builder.parse(getOutputShell("sudo kubectl get hpa -n "+namespace+" -o json"));
		}
		String name;
		
		//for (Object o : json) {
			//JSONObject hpa = (JSONObject) o;
			JSONArray items = (JSONArray) json.get("items");
			for(Object i : items) {
				JSONObject aux = (JSONObject) i;
				JSONObject metadata = (JSONObject) aux.get("metadata");
				name = (String) metadata.get("name");
				//if(name.equals("kubernetes")) continue;
				/*JSONObject specJSON = (JSONObject) aux.get("spec");
				clusterIP = (String) specJSON.get("clusterIP").toString();
				JSONArray ports = (JSONArray) specJSON.get("ports");
				for(Object p : ports) {
					JSONObject pp = (JSONObject) p;
					
					//Node Port de kubernetes es null
					port = pp.get("nodePort").toString();
				}*/
				//JSONObject specJSON = (JSONObject) aux.get("spec");
				
				HpaK8S h = new HpaK8S(name);
				hpaArray.add(h);
				
			}
		//}
		return hpaArray;
	}

	public ArrayList<ServiceK8S> readServices(String namespace) throws FileNotFoundException, IOException, ParseException {
		
		ArrayList<ServiceK8S> servicesArray = new ArrayList<ServiceK8S>();
		
		JSONParser builder = new JSONParser();
		JSONObject json;
		if(OS) {
			//Windows
			json = (JSONObject) builder.parse(new FileReader("C:\\Users\\josef\\Desktop\\services.json"));
		}else {
			json = (JSONObject) builder.parse(getOutputShell("sudo kubectl get svc -n "+namespace+" -o json"));
		}
		
		String name ="";
		//String clusterIP ="";
		//String externalIP ="";
		//String port = "";
		
		//for (Object o : json) {
			//JSONObject service = (JSONObject) o;
			JSONArray items = (JSONArray) json.get("items");
			for(Object i : items) {
				JSONObject aux = (JSONObject) i;
				JSONObject metadata = (JSONObject) aux.get("metadata");
				name = (String) metadata.get("name");
				//if(name.equals("kubernetes")) continue;
				/*JSONObject specJSON = (JSONObject) aux.get("spec");
				clusterIP = (String) specJSON.get("clusterIP").toString();
				JSONArray ports = (JSONArray) specJSON.get("ports");
				for(Object p : ports) {
					JSONObject pp = (JSONObject) p;
					
					//Node Port de kubernetes es null
					port = pp.get("nodePort").toString();
				}*/
				
				ServiceK8S s = new ServiceK8S(name);
				servicesArray.add(s);
				
			}
		//}
		return servicesArray;
	}

	public String[] getDeployment(String deploymentName, String namespaceName) throws ParseException {

		// Posiciones:
		// 0 = nombre
		// 1 = desired
		// 2 = current
		// 3 = up-to-date
		// 4 = available
		// 5 = age
		String[] str = new String[6];

		JSONParser builder = new JSONParser();

		JSONObject json = null;
		if (OS) {
			// Windows
			try {
				json = (JSONObject) builder.parse(new FileReader("C:\\Users\\josef\\Desktop\\getDeployment.json"));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			json = (JSONObject) builder.parse(getOutputShell(
					"sudo kubectl get deployment " + deploymentName + " -n " + namespaceName + " -o json"));
		}

		// TODO
		/*
		 * desired replicas = .spec.replicas 
		 * current replicas = .status.replicas
		 * up-to-date replicas = .status.updatedReplicas 
		 * available replicas = .status.availableReplicas
		 * 
		 * Source: https://kubernetes.io/docs/concepts/workloads/controllers/deployment/
		 * 
		 */
		// Posiciones:
		// 0 = nombre = metada.name
		// 1 = desired = spec.replicas
		// 2 = current = status.replicas
		// 3 = up-to-date = status.updatedReplicas 
		// 4 = available = status.availableReplicas 
		// 5 = age

		JSONObject metadata = (JSONObject) json.get("metadata");
		// fecha = (String) metadata.get("creationTimestamp");
		str[0] = (String) metadata.get("name");
		JSONObject status = (JSONObject) json.get("status");
		JSONObject spec = (JSONObject) json.get("spec");
		str[1] = spec.get("replicas").toString();	//desired
		str[2] = status.get("replicas").toString();	//current
		str[3] = status.get("updatedReplicas").toString();
		Long ar = (Long) status.get("availableReplicas");
		if (ar == null)
			str[4] = "0";
		else
			str[4] = (String) status.get("availableReplicas").toString();
		return str;
		
		/*str[1] = (String) statusJSON.get("replicas").toString();
		str[3] = (String) statusJSON.get("updatedReplicas").toString();
		Long ar = (Long) statusJSON.get("availableReplicas");
		if (ar == null)
			str[4] = "0";
		else
			str[4] = (String) statusJSON.get("availableReplicas").toString();
		Long rr = (Long) statusJSON.get("readyReplicas");
		if (rr == null)
			str[2] = "0";
		else
			str[2] = (String) statusJSON.get("readyReplicas").toString();
		return str;*/
		
		
	}
	
	public String[] getHPA(String nameHPA) throws ParseException {

		// Posiciones:
		// 0 = nombre
		// 1 = reference
		// 2 = targets
		// 3 = minpods
		// 4 = maxpods
		// 5 = replicas
		// 6 = age
		String[] str = new String[7];

		JSONParser builder = new JSONParser();
		/*
		 * JSONArray json = null; if(OS) { //Windows try { json = (JSONArray)
		 * builder.parse(new FileReader("C:\\Users\\josef\\Desktop\\salidaHPA.json")); }
		 * catch (FileNotFoundException e) { e.printStackTrace(); } catch (IOException
		 * e) { e.printStackTrace(); } }else { json = (JSONArray)
		 * builder.parse(getOutputShell("sudo kubectl get hpa " + nameHPA +
		 * " -o json")); }
		 */
		// JSONArray json = (JSONArray) builder.parse(getOutputShell("sudo kubectl get
		// hpa " + nameHPA + " -o json"));

		JSONObject json = null;
		if (OS) {
			// Windows
			try {
				json = (JSONObject) builder.parse(new FileReader("C:\\Users\\josef\\Desktop\\salidaHPA.json"));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			json = (JSONObject) builder.parse(getOutputShell("sudo kubectl get hpa " + nameHPA + " -o json"));
		}

		// for (Object o : json) {
		// JSONObject aux = (JSONObject) o;
		JSONObject metadata = (JSONObject) json.get("metadata");
		// fecha = (String) metadata.get("creationTimestamp");
		str[0] = (String) metadata.get("name");
		JSONObject specJSON = (JSONObject) json.get("spec");
		str[3] = (String) specJSON.get("minReplicas").toString();
		str[4] = (String) specJSON.get("maxReplicas").toString();
		JSONObject scaleTargetRefJSON = (JSONObject) specJSON.get("scaleTargetRef");
		str[1] = (String) scaleTargetRefJSON.get("kind") + "\\" + (String) scaleTargetRefJSON.get("name");
		JSONObject statusJSON = (JSONObject) json.get("status");
		str[5] = (String) statusJSON.get("currentReplicas").toString();
		// Si no es custom metric, se coge el targetCPUUtilizationPercentage
		//

		try {
			String targetCPU = (String) specJSON.get("targetCPUUtilizationPercentage").toString();
			str[2] = targetCPU;
		} catch (java.lang.NullPointerException exception) {
			JSONObject annotations = (JSONObject) metadata.get("annotations");
			String current_metrics = (String) annotations.get("autoscaling.alpha.kubernetes.io/current-metrics");
			String metrics = (String) annotations.get("autoscaling.alpha.kubernetes.io/metrics");
			JSONArray json_current_metrics = (JSONArray) builder.parse(current_metrics);
			JSONArray json_metrics = (JSONArray) builder.parse(metrics);
			JSONObject ocm = (JSONObject) json_current_metrics.get(0);
			JSONObject pods_cm = (JSONObject) ocm.get("pods");
			String metricName = (String) pods_cm.get("metricName");
			//System.out.println("metricName = " + metricName);
			String currentAverageValue = (String) pods_cm.get("currentAverageValue");

			JSONObject om = (JSONObject) json_metrics.get(0);
			JSONObject pods_m = (JSONObject) om.get("pods");
			String targetAverageValue = (String) pods_m.get("targetAverageValue");
			// System.err.println("current_metrics = "+current_metrics.toString());
			// System.err.println("metrics = "+metrics.toString());
			str[2] = currentAverageValue + " / " + targetAverageValue;
		}

		return str;
	}

	public String getOutputShell(String comando) {
		//String str = "[\n";
		//str += salidaScript(comando).toString();
		//str += "\n]";
		//return str;
		return salidaScript(comando).toString();
	}

	public StringBuffer salidaScript(String command) {

		try {
			Process proc = new ProcessBuilder("bash", "-c", command).start();
			proc.waitFor();
			StringBuffer output = new StringBuffer();
			BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			String line = "";
			while ((line = reader.readLine()) != null) {
				output.append(line + "\n");
			}
			return output;
		} catch (Throwable t) {
			t.printStackTrace();
			return null;
		}
	}
	
	public boolean checkCluster() {
		
		if(OS) return true;
		
		String str = this.getOutputShell("sudo kubectl version");
		if(str.equals("")) return false;
		return true;
	}
}
