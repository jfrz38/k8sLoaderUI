package test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;
import org.junit.Test;

import tfg.k8s.gui.NamespaceK8S;
import tfg.k8s.gui.ReadJSON;

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
        System.out.println("\n\n\n"
        		+ "salida = "+ salida +" ; tamaño = "+str.length);
        for(String s : str) {
        	System.out.println("s = "+s);
        }
        if(str.length>0)str[str.length-1] = null;
		boolean test = true;
		ArrayList<NamespaceK8S> a = rj.readNamespace();
		ArrayList<String> names = new ArrayList<String>();
		for(NamespaceK8S n : a) {
			names.add(n.getName());
			System.out.println("n = "+n.getName());
		}
		System.out.println("\n\n\n"
        		+ "names tamaño = "+names.size()+ " = "+names.toString());
		if(a.size() != (str.length)) test = false;
		System.out.println("test tras tamaño = "+test);
		System.out.println("a.zise = "+a.size() + " ; str.length-1 =" + (str.length-1));
        for(String s : str){
        	if(s == " ") continue;
        	if(s == "") continue;
            if(!names.contains(s)) {
            	System.out.println("names {"+names.toString()+" no contiene "+s);
            	test = false;
            	break;
            }
        }
		
		assertEquals(test,true);
	}

	@Test
	public void testReadDeployments() {
		//kubectl get namespace -o=jsonpath='{range .items[*]}{.metadata.name}{", "}'
		//nombre de todos los namespaces separados por comas
		assertEquals(true,true);
	}

	@Test
	public void testReadHPA() {
		
		//kubectl get hpa -o=jsonpath='{range .items[*]}{.metadata.name}{", "}'
		//nombre de todos los namespaces separados por comas
		assertEquals(true,true);
	}

	@Test
	public void testReadServices() {
		//kubectl get service -o=jsonpath='{range .items[*]}{.metadata.name}{", "}'
		//nombre de todos los namespaces separados por comas
		assertEquals(true,true);
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
