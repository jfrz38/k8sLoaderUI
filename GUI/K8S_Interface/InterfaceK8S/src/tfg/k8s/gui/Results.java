package tfg.k8s.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JScrollPane;
import javax.swing.JEditorPane;
import javax.swing.JTextPane;

import org.json.simple.parser.ParseException;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class Results {

	private JFrame frame_results;
	private String salidaHey = "";
	private ReadJSON readJson = new ReadJSON();

	/**
	 * Launch the application.
	 */
	//public static void main(String[] args) {
	public static void init(Peticion p) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Results window = new Results(p);
					window.frame_results.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Results(Peticion p) {
		initialize(p);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Peticion p) {
		
		lanzarHilos(p);
		
		frame_results = new JFrame("Resultados");
		Dimension minSize = new Dimension(650,450);
		frame_results.setMinimumSize(minSize);
	    frame_results.getContentPane().setLayout(new GridLayout(1, 2, 0, 0));
	    
	    JPanel panel_izq = new JPanel();
	    frame_results.getContentPane().add(panel_izq);
	    panel_izq.setLayout(new GridLayout(2, 0, 0, 0));
	    
	    JScrollPane scrollPane_HPA = new JScrollPane();
	    panel_izq.add(scrollPane_HPA);
	    
	    JTextPane textPane = new JTextPane();
	    textPane.setEditable(false);
	    scrollPane_HPA.setViewportView(textPane);
	    
	    JLabel lbl_title_hpa = new JLabel("HPA");
	    scrollPane_HPA.setColumnHeaderView(lbl_title_hpa);
	    
	    JScrollPane scrollPane_deploy = new JScrollPane();
	    panel_izq.add(scrollPane_deploy);
	    
	    JLabel lbl_title_deployment = new JLabel("Deployment");
	    scrollPane_deploy.setColumnHeaderView(lbl_title_deployment);
	    
	    JPanel panel_dcha = new JPanel();
	    frame_results.getContentPane().add(panel_dcha);
	    panel_dcha.setLayout(null);
	    
	    JScrollPane scrollPane_Resultados = new JScrollPane();
	    scrollPane_Resultados.setBounds(10, 9, 297, 391);
	    panel_dcha.add(scrollPane_Resultados);
	    Dimension minSize2 = new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
	    scrollPane_Resultados.setMinimumSize(minSize2);
	    
	    JTextPane textPane_heyResult = new JTextPane();
	    scrollPane_Resultados.setViewportView(textPane_heyResult);
	    textPane_heyResult.setText(salidaHey);
	    
	    JLabel lblResultados = new JLabel("Resultados");
	    scrollPane_Resultados.setColumnHeaderView(lblResultados);


	    //frame_results.setExtendedState(JFrame.MAXIMIZED_BOTH);
	    frame_results.pack();
	    frame_results.setVisible(true);
		//window = new JFrame();
		//window.setBounds(100, 100, 450, 300);
		//window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}

	public void lanzarHilos(Peticion p) {

		//Hilo resultados
		Thread resultsThread = new Thread() {
			public void run() {
				System.out.println("Inicio hilo " + Thread.currentThread().getName());
				runCommand(p);
				System.out.println("Fin hilo "+Thread.currentThread().getName());
			}
		};
		
		//Hilo HPA
		Thread hpaThread = new Thread() {
			public void run() {
				System.out.println("Inicio hilo " + Thread.currentThread().getName());
				runHpaThread();
				System.out.println("Fin hilo "+Thread.currentThread().getName());
			}
		};
		
		//Hilo Deployments
		Thread deploymentThread = new Thread() {
			public void run() {
				System.out.println("Inicio hilo " + Thread.currentThread().getName());
				runDeploymentThread();
				System.out.println("Fin hilo "+Thread.currentThread().getName());
			}
		};
		
		resultsThread.setName("resultsThread");
		hpaThread.setName("hpaThread");
		deploymentThread.setName("deploymentThread");
		resultsThread.start();
		hpaThread.start();
		deploymentThread.start();
	}
	
	public boolean runCommand(Peticion p) {
		
		//Conseguir URL
		//minikube service <servicio> --url
		String urlMinikube;
		try {
			Process proc = new ProcessBuilder("bash", "-c", "minikube service "+p.getServicio()+" --url").start();
			proc.waitFor();
			StringBuffer output = new StringBuffer();
			BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			String line = "";
			while ((line = reader.readLine()) != null) {
				output.append(line + "\n");
			}
			urlMinikube = output.toString();
		} catch (Throwable t) {
			t.printStackTrace();
			return false;
		}
		
		//Lamada HEY a la URL
		if(urlMinikube.equals("")) return false;
		
		String comando = "hey";
		if(!p.getPeticionesTotales().equals("")) comando+=" -n "+p.getPeticionesTotales();
		if(!p.getPeticionesConcurrentes().equals("")) comando +=" -c "+p.getPeticionesConcurrentes();
		if(!p.getPeticionesPorSegundo().equals("")) comando += " -q "+p.getPeticionesPorSegundo();
		
		comando+=" "+urlMinikube;
		//System.out.println("comando = "+comando);
		try {
			Process proc = new ProcessBuilder("bash", "-c", comando).start();
			proc.waitFor();
			StringBuffer output = new StringBuffer();
			BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			String line = "";
			while ((line = reader.readLine()) != null) {
				output.append(line + "\n");
			}
			//System.out.println("salida = "+output);
			salidaHey = output.toString();
			return true;
		} catch (Throwable t) {
			t.printStackTrace();
			return false;
		}
	}
	
	public void runHpaThread() {
		while (true) {

			//TODO
			
			//Sleep
			try {
				Thread.currentThread();
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void runDeploymentThread() {
		while (true) {

			//TODO
			try {
				readJson.readDeployments("default");
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			
			//Sleep
			try {
				Thread.currentThread();
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
