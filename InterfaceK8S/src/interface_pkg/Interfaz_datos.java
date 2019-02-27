package interface_pkg;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.json.simple.parser.ParseException;

import javax.swing.JCheckBox;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Choice;
import javax.swing.JTabbedPane;

public class Interfaz_datos {

	private JFrame frame;
	public ReadJSON json;
	public ArrayList<NamespaceK8S> k8s;
	
	JLabel lblNombre;
	Canvas canvas;
	JLabel lblAp;

	/**
	 * Launch the application.
	 */
	public static void init() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz_datos window = new Interfaz_datos();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws ParseException 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public Interfaz_datos() throws FileNotFoundException, IOException, ParseException {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 601, 429);
		frame.getContentPane().setLayout(null);
		
		json = new ReadJSON(0);
		k8s = json.namespacesArray;
		
		/*System.out.println("Mostrar resultados:");
		System.out.println("Longitud = "+json.arrk8s.size());
		for(DatosK8S k8s : json.arrk8s) {
			System.out.println("kind = "+k8s.getKind()+" ; name = "+k8s.getName()+" ; apiVersion = "+k8s.getApiVersion()+" ; activo = "+k8s.isStatus());
		}*/
		
		/*DatosK8S dk8s;
		if(k8s.get(0) != null) {
			dk8s = k8s.get(0);
		}else {
			dk8s = new DatosK8S();
		}*/
		if(k8s.get(0) == null) return;
		
		initialize(k8s.get(0));
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(NamespaceK8S datos) {
		
		
		
		//SwingUtilities.updateComponentTreeUI(frame);
		//frame.repaint();
		String[] datosComboBox = new String[k8s.size()];
		for(int i = 0; i<k8s.size(); i++) {
			datosComboBox[i] = k8s.get(i).getName();
		}
		JComboBox comboBox = new JComboBox(datosComboBox);
		comboBox.setBounds(10, 42, 150, 20);
		frame.getContentPane().add(comboBox);
		comboBox.setEditable(false);
		comboBox.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                //Execute when a selection has been made
            	System.out.println("Selected index = "+comboBox.getSelectedIndex());
            	valores(k8s.get(comboBox.getSelectedIndex()));
            	//frame.repaint();

            }
        });   
		
		
		JLabel lblNamespaces = new JLabel("Namespaces");
		lblNamespaces.setBounds(10, 17, 150, 14);
		frame.getContentPane().add(lblNamespaces);
		
		
		lblNombre = new JLabel("Nombre: "+datos.getName());
		lblNombre.setBounds(183, 17, 150, 14);
		frame.getContentPane().add(lblNombre);
		
		lblAp = new JLabel("apiVersion: "+datos.getApiVersion());
		lblAp.setBounds(183, 69, 150, 14);
		frame.getContentPane().add(lblAp);
		
		JLabel lblActivo = new JLabel("Activo");
		lblActivo.setBounds(209, 47, 46, 14);
		frame.getContentPane().add(lblActivo);
		
		canvas = new Canvas();
		if(datos.isStatus()) canvas.setBackground(Color.GREEN);
		else canvas.setBackground(Color.RED);
		canvas.setBounds(183, 42, 20, 20);
		frame.getContentPane().add(canvas);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 132, 565, 247);
		frame.getContentPane().add(tabbedPane);
		JComponent panel1 = new JPanel();
		tabbedPane.addTab("Deployments", null, panel1,
		                  "Deployments in namespace");
		
		JLabel lblDeploymentTab = new JLabel("No resources found");
		panel1.add(lblDeploymentTab);

		JComponent panel2 = new JPanel();
		tabbedPane.addTab("PODs", null, panel2,
		                  "PODs in namespace");
		
		JLabel lblPodsTab = new JLabel("No resources found");
		panel2.add(lblPodsTab);

		JComponent panel3 = new JPanel();
		tabbedPane.addTab("HPA", null, panel3,
		                  "HPA existing");
		
		JLabel lblHpaTab = new JLabel("No resources found");
		panel3.add(lblHpaTab);

		JComponent panel4 = new JPanel();
		panel4.setPreferredSize(new Dimension(410, 50));
		tabbedPane.addTab("Services", null, panel4,
		                      "Services existing");
		
		JLabel lblServicesTab = new JLabel("No resources found");
		panel4.add(lblServicesTab);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void valores(NamespaceK8S datos) {
		//Nombre
		lblNombre.setText("Nombre: "+datos.getName());
		//Canvas
		if(datos.isStatus()) canvas.setBackground(Color.GREEN);
		else canvas.setBackground(Color.RED);
		//apiVersion
		lblAp.setText("apiVersion: "+datos.getApiVersion());
		
	}
}
