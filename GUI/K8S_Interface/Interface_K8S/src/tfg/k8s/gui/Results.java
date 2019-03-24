package tfg.k8s.gui;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

import org.json.simple.parser.ParseException;

public class Results {

	private static ReadJSON readJson = new ReadJSON();
	public static Thread hpaThread;
	public static Thread deploymentThread;
	public static Thread resultsThread;
	public static String salidaHey = "";
	public static ArrayList<HpaK8S> arrayHPA;
	public static ArrayList<DeploymentK8S> arrayDeployment;
	public static Date startDate;
	public static boolean heyActivo = true;
	
	public Results() {
		arrayHPA = new ArrayList<HpaK8S>();
		arrayDeployment = new ArrayList<DeploymentK8S>();
		salidaHey = "";
	}
	
	public Results(Peticion p) {
		arrayHPA = new ArrayList<HpaK8S>();
		arrayDeployment = new ArrayList<DeploymentK8S>();
		salidaHey = "";
		lanzarHilos(p);
	}
	
	public void cargarArrayHPA(Peticion p) {
		try {
			String[] rjHPA = readJson.getHPA(p.getHpa());
			Date endDate = new Date();
			long dif = (endDate.getTime() - startDate.getTime())/1000;
			int totalSecs = Math.toIntExact(dif);
			int hours = totalSecs / 3600;
			int minutes = (totalSecs % 3600) / 60;
			int seconds = totalSecs % 60;
			String timeString;
			if(hours<1) {
				timeString = String.format("%02d:%02d s", minutes, seconds);
			}else {
				timeString = String.format("%02d:%02d:%02d s", hours, minutes, seconds);
			}
			
			rjHPA[6] = timeString;
			
			//rjHPA[6] = Long.toString((new Date().getTime() - startDate.getTime())/1000)+"s";
			HpaK8S h = new HpaK8S(rjHPA[0], rjHPA[1], rjHPA[2], rjHPA[3], 
					rjHPA[4], rjHPA[5], rjHPA[6]);
			arrayHPA.add(h);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
	
	public void cargarArrayDeployment(Peticion p) {
		try {
			String[] rjDep = readJson.getDeployment(p.getDeployment(), p.getNamespace());
			//TODO : if tiempo > 60 -> parsear a min/seg
			rjDep[5] = Long.toString((new Date().getTime() - startDate.getTime())/1000);
			DeploymentK8S d = new DeploymentK8S(rjDep[0], rjDep[1], rjDep[2], rjDep[3], 
					rjDep[4], rjDep[5]);
			arrayDeployment.add(d);
			
			if(arrayDeployment.size()>10) {	
				arrayDeployment.remove(0);
				//TODO : Shift down all elements
				/*ArrayList<DeploymentK8S> aux = new ArrayList<DeploymentK8S>();
				for(int i=1; i<arrayDeployment.size(); i++) {
					aux.add(arrayDeployment.get(i));
				}
				arrayDeployment.clear();
				arrayDeployment.addAll(aux);*/
				
			}
			
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}

	public void lanzarHilos(Peticion p) {

		startDate = new Date();
		
		lanzarResultsThread(p);
		lanzarHpaThread(p);
		lanzarDeploymentThread(p);

	}

	public void lanzarResultsThread(Peticion p) {
		// Resultados
		// Hilo resultados
		if (resultsThread == null) {
			resultsThread = new Thread() {
				public void run() {
					runCommand(p);
				}
			};

			resultsThread.setName("resultsThread");
			resultsThread.start();
		}
	}

	public void lanzarHpaThread(Peticion p) {
		if (hpaThread == null) {
			// if(!hpaThread.isAlive()) {
			// Hilo HPA
			hpaThread = new Thread() {
				public void run() {
					while (true) {

						cargarArrayHPA(p);

						// Sleep
						try {
							Thread.currentThread();
							Thread.sleep(4000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

					}
				}
			};

			hpaThread.setName("hpaThread");
			hpaThread.start();
			// }
		}
	}

	public void lanzarDeploymentThread(Peticion p) {
		if (deploymentThread == null) {
			// if(!deploymentThread.isAlive()) {
			// Hilo Deployments
			deploymentThread = new Thread() {
				public void run() {
					while (true) {

						cargarArrayDeployment(p);

						// Sleep
						try {
							Thread.currentThread();
							Thread.sleep(4000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

					}

				}
			};

			deploymentThread.start();
			deploymentThread.setName("deploymentThread");
		}
		// }
	}

	public boolean runCommand(Peticion p) {

		if (System.getProperty("os.name").startsWith("Windows")) {
			// textPane_heyResult.setText("Windows");
			salidaHey = "Windows";
			System.out.println("salidaHey = Windows");
			return false;
		}

		// Conseguir URL
		// minikube service <servicio> --url
		String urlMinikube;
		try {
			Process proc = new ProcessBuilder("bash", "-c", "sudo minikube service " + p.getServicio() + " --url")
					.start();
			proc.waitFor();
			StringBuffer output = new StringBuffer();
			BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			String line = "";
			while ((line = reader.readLine()) != null) {
				output.append(line + "\n");
			}
			urlMinikube = output.toString();
			System.out.println("url minikube = " + urlMinikube);
			System.out.println("Comando = minikube service " + p.getServicio() + " --url");
		} catch (Throwable t) {
			t.printStackTrace();
			return false;
		}

		// Lamada HEY a la URL
		if (urlMinikube.equals(""))
			return false;

		String comando = "hey";
		if (!p.getPeticionesTotales().equals(""))
			comando += " -n " + p.getPeticionesTotales();
		if (!p.getPeticionesConcurrentes().equals(""))
			comando += " -c " + p.getPeticionesConcurrentes();
		if (!p.getPeticionesPorSegundo().equals(""))
			comando += " -q " + p.getPeticionesPorSegundo();

		comando += " " + urlMinikube;
		System.out.println("Comando hey = " + comando);
		try {
			Process proc = new ProcessBuilder("bash", "-c", comando).start();
			proc.waitFor();
			StringBuffer output = new StringBuffer();
			BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			String line = "";
			while ((line = reader.readLine()) != null) {
				output.append(line + "\n");
			}
			// salidaHey = output.toString();
			// textPane_heyResult.setText(output.toString());
			salidaHey = output.toString();
			System.out.println("Salida hey = \n" + output.toString());
			heyActivo = false;
			return true;
		} catch (Throwable t) {
			t.printStackTrace();
			heyActivo = false;
			return false;
		}
	}

}
