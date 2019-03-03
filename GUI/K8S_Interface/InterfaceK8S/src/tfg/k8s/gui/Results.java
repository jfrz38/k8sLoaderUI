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
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Results {

	private JFrame frame_results;
	//private String salidaHey = "";
	private ReadJSON readJson = new ReadJSON();
	JTextPane textPane_deployment;
	JTextPane textPane_hpa;
	JTextPane textPane_heyResult;
	Thread hpaThread;
	Thread deploymentThread;

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
		
		textPane_deployment = new JTextPane();
		textPane_deployment.setEditable(false);
		//textPane_deployment.setAutoscrolls(true);
		textPane_deployment.setText("NAME\tDESIRED\tCURRENT\tUP-TO-DATE\tAVAILABLE\tAGE\n");
		
		textPane_hpa = new JTextPane();
	    textPane_hpa.setEditable(false);
	    textPane_hpa.setText("NAME\tREFERENCE\tTARGETS\tMINPODS\tMAXPODS\tREPLICAS\tAGE\n");
	    
	    textPane_heyResult = new JTextPane();
	    
	    textPane_heyResult.setText("Enviando datos");
	    textPane_heyResult.setEditable(false);
		
		lanzarHilos(p);
		
		frame_results = new JFrame("Resultados");
		Dimension minSize = new Dimension(650,450);
		frame_results.setMinimumSize(minSize);
	    frame_results.getContentPane().setLayout(new GridLayout(0, 2, 0, 0));
	    
	    JPanel panel_izq = new JPanel();
	    frame_results.getContentPane().add(panel_izq);
	    panel_izq.setLayout(new GridLayout(2, 0, 0, 0));
	    
	    JScrollPane scrollPane_HPA = new JScrollPane();
	    panel_izq.add(scrollPane_HPA);
	    
	    //JTextPane textPane_hpa = new JTextPane();
	    //textPane_hpa.setEditable(false);
	    scrollPane_HPA.setViewportView(textPane_hpa);
	    
	    JLabel lbl_title_hpa = new JLabel("HPA");
	    scrollPane_HPA.setColumnHeaderView(lbl_title_hpa);
	    
	    JScrollPane scrollPane_deploy = new JScrollPane();
	    panel_izq.add(scrollPane_deploy);
	    
	    JLabel lbl_title_deployment = new JLabel("Deployment");
	    scrollPane_deploy.setColumnHeaderView(lbl_title_deployment);
	    
	    //textPane_deployment = new JTextPane();
	    scrollPane_deploy.setViewportView(textPane_deployment);
	    //textPane_deployment.setEditable(false);
	    //textPane_deployment.setText("NAME\tDESIRED\tCURRENT\tUP-TO-DATE\tAVAILABLE\tAGE\n");
	    
	    JPanel panel_dcha = new JPanel();
	    frame_results.getContentPane().add(panel_dcha);
	    GridBagLayout gbl_panel_dcha = new GridBagLayout();
	    gbl_panel_dcha.columnWidths = new int[]{89, 341, 0};
	    gbl_panel_dcha.rowHeights = new int[]{37, 23, 151, 23, 135, 0};
	    gbl_panel_dcha.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
	    gbl_panel_dcha.rowWeights = new double[]{1.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
	    panel_dcha.setLayout(gbl_panel_dcha);
	    Dimension minSize2 = new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
	    
	    JPanel panel_botonesHPA = new JPanel();
	    GridBagConstraints gbc_panel_botonesHPA = new GridBagConstraints();
	    gbc_panel_botonesHPA.insets = new Insets(0, 0, 5, 5);
	    gbc_panel_botonesHPA.fill = GridBagConstraints.BOTH;
	    gbc_panel_botonesHPA.gridx = 0;
	    gbc_panel_botonesHPA.gridy = 0;
	    panel_dcha.add(panel_botonesHPA, gbc_panel_botonesHPA);
	    panel_botonesHPA.setLayout(new GridLayout(2, 1, 0, 0));
	    
	    JButton btnStopHPA = new JButton("stop");
	    btnStopHPA.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		//Parar hilo HPA
	    		if(hpaThread.isAlive()) hpaThread.interrupt();
	    	}
	    });
	    panel_botonesHPA.add(btnStopHPA);
	    
	    JButton btnClearHPA = new JButton("clear");
	    btnClearHPA.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		//Clear HPA
	    		textPane_hpa.setText("NAME\tREFERENCE\tTARGETS\tMINPODS\tMAXPODS\tREPLICAS\tAGE\n");
	    	}
	    });
	    panel_botonesHPA.add(btnClearHPA);
	    
	    JScrollPane scrollPane_Resultados = new JScrollPane();
	    GridBagConstraints gbc_scrollPane_Resultados = new GridBagConstraints();
	    gbc_scrollPane_Resultados.fill = GridBagConstraints.BOTH;
	    gbc_scrollPane_Resultados.gridheight = 5;
	    gbc_scrollPane_Resultados.gridx = 1;
	    gbc_scrollPane_Resultados.gridy = 0;
	    panel_dcha.add(scrollPane_Resultados, gbc_scrollPane_Resultados);
	    scrollPane_Resultados.setMinimumSize(minSize2);
	    scrollPane_Resultados.setViewportView(textPane_heyResult);
	    
	    JLabel lblResultados = new JLabel("Resultados");
	    scrollPane_Resultados.setColumnHeaderView(lblResultados);
	    
	    JPanel panel_botonesDeployment = new JPanel();
	    GridBagConstraints gbc_panel_botonesDeployment = new GridBagConstraints();
	    gbc_panel_botonesDeployment.insets = new Insets(0, 0, 5, 5);
	    gbc_panel_botonesDeployment.fill = GridBagConstraints.BOTH;
	    gbc_panel_botonesDeployment.gridx = 0;
	    gbc_panel_botonesDeployment.gridy = 3;
	    panel_dcha.add(panel_botonesDeployment, gbc_panel_botonesDeployment);
	    panel_botonesDeployment.setLayout(new GridLayout(2, 1, 0, 0));
	    
	    JButton btnStopDeployment = new JButton("stop");
	    btnStopDeployment.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		//Stop Deployment
	    		if(deploymentThread.isAlive()) deploymentThread.interrupt();
	    	}
	    });
	    panel_botonesDeployment.add(btnStopDeployment);
	    
	    JButton btnClearDeployment = new JButton("clear");
	    btnClearDeployment.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		//Clear deployment
	    		textPane_deployment.setText("NAME\tDESIRED\tCURRENT\tUP-TO-DATE\tAVAILABLE\tAGE\n");
	    	}
	    });
	    panel_botonesDeployment.add(btnClearDeployment);


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
				//System.out.println("Inicio hilo " + Thread.currentThread().getName());
				runCommand(p);
				//System.out.println("Fin hilo "+Thread.currentThread().getName());
			}
		};
		
		//Hilo HPA
		hpaThread = new Thread() {
			public void run() {
				//System.out.println("Inicio hilo " + Thread.currentThread().getName());
				runHpaThread(p);
				//System.out.println("Fin hilo "+Thread.currentThread().getName());
			}
		};
		
		//Hilo Deployments
		deploymentThread = new Thread() {
			public void run() {
				//System.out.println("Inicio hilo " + Thread.currentThread().getName());
				runDeploymentThread(p);
				//System.out.println("Fin hilo "+Thread.currentThread().getName());
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
		
		if(System.getProperty("os.name").startsWith("Windows")) {
			textPane_heyResult.setText("Windows");
			return false;
		}

		//Conseguir URL
		//minikube service <servicio> --url
		String urlMinikube;
		try {
			Process proc = new ProcessBuilder("bash", "-c", "sudo minikube service "+p.getServicio()+" --url").start();
			proc.waitFor();
			StringBuffer output = new StringBuffer();
			BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			String line = "";
			while ((line = reader.readLine()) != null) {
				output.append(line + "\n");
			}
			urlMinikube = output.toString();
			System.out.println("url minikube = "+urlMinikube);
			System.out.println("Comando = minikube service "+p.getServicio()+" --url");
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
		System.out.println("Comando hey = "+comando);
		try {
			Process proc = new ProcessBuilder("bash", "-c", comando).start();
			proc.waitFor();
			StringBuffer output = new StringBuffer();
			BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			String line = "";
			while ((line = reader.readLine()) != null) {
				output.append(line + "\n");
			}
			//salidaHey = output.toString();
			textPane_heyResult.setText(output.toString());
			System.out.println("Salida = \n"+output.toString());
			return true;
		} catch (Throwable t) {
			t.printStackTrace();
			return false;
		}
	}
	
	public void runHpaThread(Peticion p) {
		while (true) {

			try {
				String[] str = readJson.getHPA(p.getHpa());
				
				for(String s : str) {
					if(s == null)textPane_hpa.setText(textPane_hpa.getText()+"null\t");
					else textPane_hpa.setText(textPane_hpa.getText()+s+"\t");
				}
				textPane_hpa.setText(textPane_hpa.getText()+"\n");
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			
			//Sleep
			try {
				Thread.currentThread();
				Thread.sleep(30000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println("Hilo HPA");
		}
	}

	public void runDeploymentThread(Peticion p) {
		while (true) {

			try {
				String[] str = readJson.getDeployment(p.getDeployment(),p.getNamespace());
				
				for(String s : str) {
					if(s == null)textPane_deployment.setText(textPane_deployment.getText()+"null\t");
					else textPane_deployment.setText(textPane_deployment.getText()+s+"\t");
				}
				textPane_deployment.setText(textPane_deployment.getText()+"\n");
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			
			//Sleep
			try {
				Thread.currentThread();
				Thread.sleep(30000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println("Hilo deployment");
		}
	}
}
