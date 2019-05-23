package test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

import org.json.simple.parser.ParseException;
import org.junit.Test;

import tfg.k8s.gui.DeploymentK8S;
import tfg.k8s.gui.HpaK8S;
import tfg.k8s.gui.NamespaceK8S;
import tfg.k8s.gui.ReadJSON;
import tfg.k8s.gui.ServiceK8S;

public class ReadJSONTest {

	//Comprobar que los datos son introducidos
	ReadJSON rj = new ReadJSON();
	
	@Test
	public void testReadNamespace() throws FileNotFoundException, IOException, ParseException {
			
		//kubectl get namespace -o=jsonpath='{range .items[*]}{.metadata.name}{", "}'
		//nombre de todos los namespaces separados por comas
		
		String comando = "kubectl get namespace -o=jsonpath='{range .items[*]}{.metadata.name}{\" \"}'";
		String salida = salidaScript(comando);
		String[] str = salida.split(" ");
		ArrayList<String> strAux = new ArrayList<String>(Arrays.asList(str));
		if(strAux.size() > 0)strAux.remove(strAux.size()-1);	//Eliminar último caracter
		
        
		boolean test = true;
		ArrayList<NamespaceK8S> arr = rj.readNamespace();
		ArrayList<String> names = new ArrayList<String>();
		for(NamespaceK8S n : arr) names.add(n.getName());
		
		if(arr.size() != (strAux.size())) test = false;
		for(String s : strAux){
            if(!names.contains(s)) {
            	test = false;
            	break;
            }
        }
		
		assertEquals(test,true);
	}

	@Test
	public void testReadDeployments() throws FileNotFoundException, IOException, ParseException {
		//kubectl get namespace -o=jsonpath='{range .items[*]}{.metadata.name}{", "}'
		//nombre de todos los namespaces separados por comas
		String comando = "kubectl get deployment -o=jsonpath='{range .items[*]}{.metadata.name}{\" \"}'";
		String salida = salidaScript(comando);
		String[] str = salida.split(" ");
		ArrayList<String> strAux = new ArrayList<String>(Arrays.asList(str));
		if(strAux.size() > 0)strAux.remove(strAux.size()-1);	//Eliminar último caracter
		
        
		boolean test = true;
		ArrayList<DeploymentK8S> arr = rj.readDeployments("default");
		ArrayList<String> names = new ArrayList<String>();
		for(DeploymentK8S n : arr) names.add(n.getName());
		
		if(arr.size() != (strAux.size())) test = false;
		for(String s : strAux){
            if(!names.contains(s)) {
            	test = false;
            	break;
            }
        }
		
		assertEquals(test,true);
	}

	@Test
	public void testReadHPA() throws FileNotFoundException, IOException, ParseException {
		
		//kubectl get hpa -o=jsonpath='{range .items[*]}{.metadata.name}{", "}'
		//nombre de todos los namespaces separados por comas
		String comando = "kubectl get hpa -o=jsonpath='{range .items[*]}{.metadata.name}{\" \"}'";
		String salida = salidaScript(comando);
		String[] str = salida.split(" ");
		ArrayList<String> strAux = new ArrayList<String>(Arrays.asList(str));
		if(strAux.size() > 0)strAux.remove(strAux.size()-1);	//Eliminar último caracter
		
        
		boolean test = true;
		ArrayList<HpaK8S> arr = rj.readHPA("default");
		ArrayList<String> names = new ArrayList<String>();
		for(HpaK8S n : arr) names.add(n.getName());
		
		if(arr.size() != (strAux.size())) test = false;
		for(String s : strAux){
            if(!names.contains(s)) {
            	test = false;
            	break;
            }
        }
		
		assertEquals(test,true);
	}

	@Test
	public void testReadServices() throws FileNotFoundException, IOException, ParseException {
		//kubectl get service -o=jsonpath='{range .items[*]}{.metadata.name}{", "}'
		//nombre de todos los namespaces separados por comas
		String comando = "kubectl get service -o=jsonpath='{range .items[*]}{.metadata.name}{\" \"}'";
		String salida = salidaScript(comando);
		String[] str = salida.split(" ");
		ArrayList<String> strAux = new ArrayList<String>(Arrays.asList(str));
		if(strAux.size() > 0)strAux.remove(strAux.size()-1);	//Eliminar último caracter
		
        
		boolean test = true;
		ArrayList<ServiceK8S> arr = rj.readServices("default");
		ArrayList<String> names = new ArrayList<String>();
		for(ServiceK8S n : arr) names.add(n.getName());
		
		if(arr.size() != (strAux.size())) test = false;
		for(String s : strAux){
            if(!names.contains(s)) {
            	test = false;
            	break;
            }
        }
		
		assertEquals(test,true);
	}

	@Test
	public void testGetDeployment() {
		
		// Posiciones:
				// 0 = nombre = metada.name
				// 1 = desired = spec.replicas
				// 2 = current = status.replicas
				// 3 = up-to-date = status.updatedReplicas 
				// 4 = available = status.availableReplicas 
				// 5 = age
		assertEquals(true,true);
	}

	@Test
	public void testGetHPA() {
		// Posiciones:
				// 0 = nombre
				// 1 = reference
				// 2 = targets
				// 3 = minpods
				// 4 = maxpods
				// 5 = replicas
				// 6 = age
		assertEquals(true,true);
	}
	

	public String salidaScript(String command) {

		try {
			Process proc = new ProcessBuilder("bash", "-c", command).start();
			proc.waitFor();
			StringBuffer output = new StringBuffer();
			BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			String line = "";
			while ((line = reader.readLine()) != null) {
				output.append(line + "\n");
			}
			return output.toString();
		} catch (Throwable t) {
			t.printStackTrace();
			return null;
		}
	}

	

}
