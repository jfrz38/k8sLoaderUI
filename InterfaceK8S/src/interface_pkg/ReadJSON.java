package interface_pkg;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadJSON {

	public ArrayList<NamespaceK8S> namespacesArray = new ArrayList<NamespaceK8S>();
	public ArrayList<DeploymentK8S> deploymentsArray = new ArrayList<DeploymentK8S>();
	public ArrayList<NamespaceK8S> podsArray = new ArrayList<NamespaceK8S>();
	public ArrayList<NamespaceK8S> servicesArray = new ArrayList<NamespaceK8S>();
	public ArrayList<NamespaceK8S> hpaArray = new ArrayList<NamespaceK8S>();
	
	public ReadJSON(int selec) throws FileNotFoundException, IOException, org.json.simple.parser.ParseException {
		
		//selec = selección;
		//0 = read namespaces
		//1 = read deployments
		//2 = read PODs
		//3 = read services
		//4 = read HPA
		
		switch(selec) {
		case 0: readNamespace();
			break;
		case 1: readDeployments();
			break;
		case 2: readPODs();
			break;
		case 3: readServices();
			break;
		case 4: readHPA();
			break;
		}
		
	}
	
	public boolean readNamespace() throws FileNotFoundException, IOException, ParseException {
		JSONParser builder = new JSONParser();
		String salida = getOutputShell();
		JSONArray json = (JSONArray) builder.parse(new FileReader("C:\\Users\\josef\\Desktop\\namespaces.json"));
		
		
		//Crear hilos para leer JSON constantemente y actualizar valores
		
		for (Object o : json){
			
		    JSONObject namespace = (JSONObject) o;
		    
		    //Items es un JSONArray y cada lista tiene un objeto JSONObject
		    JSONArray items = (JSONArray) namespace.get("items");
		    for(Object i : items) {
		    	JSONObject aux = (JSONObject) i;
		    	String apiVersion = (String) aux.get("apiVersion");
		    	String kind = (String) aux.get("kind");
		    	JSONObject metadata = (JSONObject) aux.get("metadata");
		    	String name = (String)metadata.get("name");
		    	JSONObject status = (JSONObject) aux.get("status");
		    	String phase = (String)status.get("phase");
		    	boolean b;
		    	if(phase.equals("Active")) b = true;
		    	else b = false;
		    	NamespaceK8S kubernetes = new NamespaceK8S(kind,name,apiVersion,b);
			    namespacesArray.add(kubernetes);
		    }
		    
		    
		  }
		readDeployments();
		return true;
	}
	
	public boolean readDeployments() throws FileNotFoundException, IOException, ParseException {
		JSONParser builder = new JSONParser();
		String salida = getOutputShell();
		JSONArray json = (JSONArray) builder.parse(new FileReader("C:\\Users\\josef\\Desktop\\deployments.json"));
		
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
		
		for (Object o : json) {
			JSONObject deployment = (JSONObject) o;
			JSONArray items = (JSONArray) deployment.get("items");
			for(Object i : items) {
				JSONObject aux = (JSONObject) i;
				apiVersion = (String) aux.get("apiVersion");
				JSONObject metadata = (JSONObject) aux.get("metadata");
				fecha = (String) metadata.get("creationTimestamp");
				JSONObject labelsJSON = (JSONObject) metadata.get("labels");
				labels = (String)labelsJSON.get("app");
				name = (String) metadata.get("name");
				JSONObject statusJSON = (JSONObject) aux.get("status");
				replicas = (String) statusJSON.get("replicas").toString();
				updatedReplicas = (String) statusJSON.get("updatedReplicas").toString();
				availableReplicas = (String) statusJSON.get("availableReplicas").toString();
				readyReplicas = (String) statusJSON.get("readyReplicas").toString();
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
				
				DeploymentK8S d = new DeploymentK8S(name, labels, replicas, readyReplicas, updatedReplicas, availableReplicas, status, apiVersion, fecha);
				deploymentsArray.add(d);
				/*System.out.println("name = "+name);
				System.out.println("labels = "+labels);
				System.out.println("replicas = "+replicas);
				System.out.println("readyReplicas = "+readyReplicas);
				System.out.println("updatedReplicas = "+updatedReplicas);
				System.out.println("availableReplicas = "+availableReplicas);
				System.out.println("status = "+status);
				System.out.println("apiVersion = "+apiVersion);
				System.out.println("fecha = "+fecha);*/
				
			}
		}
		return true;
	}
	
	public boolean readPODs() throws FileNotFoundException, IOException, ParseException {
		JSONParser builder = new JSONParser();
		String salida = getOutputShell();
		JSONArray json = (JSONArray) builder.parse(new FileReader("C:\\Users\\josef\\Desktop\\pods.json"));
		
		return true;
	}

	public boolean readServices() throws FileNotFoundException, IOException, ParseException {
		JSONParser builder = new JSONParser();
		String salida = getOutputShell();
		JSONArray json = (JSONArray) builder.parse(new FileReader("C:\\Users\\josef\\Desktop\\services.json"));
		return true;
	}

	public boolean readHPA() throws FileNotFoundException, IOException, ParseException {
		JSONParser builder = new JSONParser();
		String salida = getOutputShell();
		JSONArray json = (JSONArray) builder.parse(new FileReader("C:\\Users\\josef\\Desktop\\hpa.json"));
		return true;
	}
	
	public String getOutputShell() {
		String str = "[\n";
		//str += salida de la consola
		str += "\n]";
		return str;
	}
}
