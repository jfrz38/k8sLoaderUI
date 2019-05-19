package tfg.k8s.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import net.miginfocom.swing.MigLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.border.LineBorder;
import java.awt.Component;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import org.json.simple.parser.ParseException;

import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;

public class MainInterface {

	private JFrame frmPeticioneshttp;
	
	//private JPanel panel_peticiones = new JPanel(), panel_k8s = new JPanel();
	//private JSplitPane jsp;
    private JPanel panelPrincipal;
    private JLabel lblPeticionesTotales;
    private JLabel lblPeticionesConcurrentes;
    private JPanel panel_peticiones;
    private JPanel panel_K8S;
    private JLabel lblPeticionesPorSegundo;
    private JLabel lblUrl;
    private JTextField textField_peticionesTotales;
    private JTextField textField_peticionesConcurrentes;
    private JTextField textField_peticionesPorSegundo;
    private JComboBox comboBox_URL;
    private JSeparator separator;
    private JSeparator separator_1;
    private JSeparator separator_2;
    private JSeparator separator_3;
    private JSeparator separator_4;
    private JLabel lblNamespace;
    private JLabel lblDeployment;
    private JLabel lblHpa;
    private JSeparator separator_5;
    private JSeparator separator_6;
    private JComboBox comboBox_namespace;
    private JComboBox comboBox_deployment;
    private JComboBox comboBox_HPA;
    private JSeparator separator_7;
    private JButton btnEnviar;

    public ArrayList<NamespaceK8S> namespacesArray = new ArrayList<NamespaceK8S>();
	public ArrayList<DeploymentK8S> deploymentsArray = new ArrayList<DeploymentK8S>();
	public ArrayList<HpaK8S> hpaArray = new ArrayList<HpaK8S>();
	public ArrayList<ServiceK8S> servicesArray = new ArrayList<ServiceK8S>();
	
	public ReadJSON rj = new ReadJSON();
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainInterface window = new MainInterface();
					window.frmPeticioneshttp.setVisible(true);
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
	public MainInterface() throws FileNotFoundException, IOException, ParseException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws ParseException 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	private void initialize() throws FileNotFoundException, IOException, ParseException {
		
		leerDatosJson();
		
		frmPeticioneshttp = new JFrame("PeticionesHTTP");
		BorderLayout borderLayout = (BorderLayout) frmPeticioneshttp.getContentPane().getLayout();
		borderLayout.setVgap(10);
		borderLayout.setHgap(10);
		Dimension minSize = new Dimension(650,450);
		frmPeticioneshttp.setMinimumSize(minSize);
		
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(null);
		frmPeticioneshttp.getContentPane().add(panelPrincipal, BorderLayout.CENTER);
		GridLayout gl_panelPrincipal = new GridLayout(1, 2);
		gl_panelPrincipal.setHgap(10);
		panelPrincipal.setLayout(gl_panelPrincipal);
		
		Dimension maxSize = new Dimension(400,1);
		//Dimension maxSizePanel = new Dimension(Integer.MAX_VALUE,10);
		panel_peticiones = new JPanel();
		panel_peticiones.setBorder(new EmptyBorder(10, 10, 10, 5));
		panelPrincipal.add(panel_peticiones);
		panel_peticiones.setLayout(new BoxLayout(panel_peticiones, BoxLayout.Y_AXIS));
		panel_peticiones.add(Box.createVerticalStrut(10));
		//panel_peticiones.setMaximumSize(maxSizePanel);
		//panel_peticiones.setPreferredSize(maxSizePanel);
		JLabel lblDatosPeticiones = new JLabel("Datos peticiones");
		panel_peticiones.add(lblDatosPeticiones);
		panel_peticiones.add(Box.createVerticalStrut(10));
		
		separator = new JSeparator();
		panel_peticiones.add(separator);
		panel_peticiones.add(Box.createVerticalStrut(20));
		
		lblPeticionesTotales = new JLabel("Peticiones totales");
		panel_peticiones.add(lblPeticionesTotales);
		panel_peticiones.add(Box.createVerticalStrut(10));
		
		textField_peticionesTotales = new JTextField();
		panel_peticiones.add(textField_peticionesTotales);
		//textField_peticionesTotales.setColumns(10);
		textField_peticionesTotales.setMaximumSize(maxSize);
		panel_peticiones.add(Box.createVerticalStrut(10));
		
		separator_1 = new JSeparator();
		panel_peticiones.add(separator_1);
		panel_peticiones.add(Box.createVerticalStrut(10));

		lblPeticionesConcurrentes = new JLabel("Peticiones concurrentes");
		panel_peticiones.add(lblPeticionesConcurrentes);
		panel_peticiones.add(Box.createVerticalStrut(10));
		
		textField_peticionesConcurrentes = new JTextField();
		panel_peticiones.add(textField_peticionesConcurrentes);
		//textField_peticionesConcurrentes.setColumns(10);
		textField_peticionesConcurrentes.setMaximumSize(maxSize);
		panel_peticiones.add(Box.createVerticalStrut(10));
		
		separator_2 = new JSeparator();
		panel_peticiones.add(separator_2);
		panel_peticiones.add(Box.createVerticalStrut(10));
		
		lblPeticionesPorSegundo = new JLabel("Peticiones por segundo");
		panel_peticiones.add(lblPeticionesPorSegundo);
		panel_peticiones.add(Box.createVerticalStrut(10));
		
		textField_peticionesPorSegundo = new JTextField();
		panel_peticiones.add(textField_peticionesPorSegundo);
		//textField_peticionesPorSegundo.setColumns(10);
		textField_peticionesPorSegundo.setMaximumSize(maxSize);
		panel_peticiones.add(Box.createVerticalStrut(10));
		
		separator_3 = new JSeparator();
		panel_peticiones.add(separator_3);
		panel_peticiones.add(Box.createVerticalStrut(10));
		
		lblUrl = new JLabel("Servicio");
		panel_peticiones.add(lblUrl);
		panel_peticiones.add(Box.createVerticalStrut(10));
		
		//comboBox_URL = new JComboBox();
		panel_peticiones.add(comboBox_URL);
		comboBox_URL.setMaximumSize(maxSize);
		panel_peticiones.add(Box.createVerticalStrut(10));
		comboBox_URL.setEditable(true);
		
		panel_K8S = new JPanel();
		panel_K8S.setBorder(new EmptyBorder(10, 10, 10, 5));
		panelPrincipal.add(panel_K8S);
		panel_K8S.setLayout(new BoxLayout(panel_K8S, BoxLayout.Y_AXIS));
		panel_K8S.add(Box.createVerticalStrut(10));
		JLabel lblDatosK8S = new JLabel("Datos K8S");
		panel_K8S.add(lblDatosK8S);
		panel_K8S.add(Box.createVerticalStrut(10));

		separator_4 = new JSeparator();
		panel_K8S.add(separator_4);
		panel_K8S.add(Box.createVerticalStrut(20));

		lblNamespace = new JLabel("Namespace");
		panel_K8S.add(lblNamespace);
		panel_K8S.add(Box.createVerticalStrut(10));

		//comboBox_namespace = new JComboBox();
		panel_K8S.add(comboBox_namespace);
		comboBox_namespace.setMaximumSize(maxSize);
		comboBox_namespace.setSize(10, 10);
		panel_K8S.add(Box.createVerticalStrut(10));
		ActionListener actionListener = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		        try {
					actualizarComboBox();
				} catch (IOException | ParseException e) {
					e.printStackTrace();
				}
		      }
		    };
		    comboBox_namespace.addActionListener(actionListener);
		

		separator_5 = new JSeparator();
		panel_K8S.add(separator_5);
		panel_K8S.add(Box.createVerticalStrut(10));

		lblDeployment = new JLabel("Deployment");
		panel_K8S.add(lblDeployment);
		panel_K8S.add(Box.createVerticalStrut(10));

		//comboBox_deployment = new JComboBox();
		panel_K8S.add(comboBox_deployment);
		comboBox_deployment.setMaximumSize(maxSize);
		panel_K8S.add(Box.createVerticalStrut(10));

		separator_6 = new JSeparator();
		panel_K8S.add(separator_6);
		panel_K8S.add(Box.createVerticalStrut(10));

		lblHpa = new JLabel("HPA");
		panel_K8S.add(lblHpa);
		panel_K8S.add(Box.createVerticalStrut(10));

		//comboBox_HPA = new JComboBox();
		panel_K8S.add(comboBox_HPA);
		comboBox_HPA.setMaximumSize(maxSize);
		panel_K8S.add(Box.createVerticalStrut(10));

		separator_7 = new JSeparator();
		panel_K8S.add(separator_7);
		panel_K8S.add(Box.createVerticalStrut(10));

		btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboBox_URL.getSelectedIndex() < 0 || comboBox_namespace.getSelectedIndex() < 0 || comboBox_deployment.getSelectedIndex() < 0 || comboBox_HPA.getSelectedIndex() < 0) return;
				//System.out.println("comboBox URL listener = "+comboBox_URL.getSelectedIndex());
				//System.out.println("comboBox namespace listener = "+comboBox_namespace.getSelectedIndex());
				//System.out.println("comboBox deployment listener = "+comboBox_deployment.getSelectedIndex());
				//System.out.println("comboBox hpa listener = "+comboBox_HPA.getSelectedIndex());
				if(!comprobarCampos()) return;
				Peticion p = new Peticion(textField_peticionesTotales.getText(), textField_peticionesConcurrentes.getText(), textField_peticionesPorSegundo.getText(),
						comboBox_URL.getSelectedItem().toString(), comboBox_namespace.getSelectedItem().toString(), comboBox_deployment.getSelectedItem().toString(),
						comboBox_HPA.getSelectedItem().toString());
				Results.init(p);
			}

		});
		panel_K8S.add(btnEnviar);
		panel_K8S.add(Box.createVerticalStrut(10));
		
		
		//frmPeticioneshttp.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frmPeticioneshttp.pack();
		frmPeticioneshttp.setVisible(true);
		frmPeticioneshttp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}

	public void leerDatosJson() throws FileNotFoundException, IOException, ParseException {
		
		namespacesArray = rj.readNamespace();
		if(namespacesArray.isEmpty()) {
			comboBox_namespace = new JComboBox();
			comboBox_deployment = new JComboBox();
			comboBox_HPA = new JComboBox();
			comboBox_URL = new JComboBox();
		}else {
			deploymentsArray = rj.readDeployments(namespacesArray.get(0).getName());
			hpaArray = rj.readHPA(namespacesArray.get(0).getName());
			servicesArray = rj.readServices();
			inicializarComboBoxDatos();
		}
		
	}
	
	public void inicializarComboBoxDatos() {

		// Namespaces
		String[] namespacesComboBox = new String[namespacesArray.size()];
		for (int i = 0; i < namespacesArray.size(); i++) {
			namespacesComboBox[i] = namespacesArray.get(i).getName();
		}
		comboBox_namespace = new JComboBox(namespacesComboBox);

		// Deployments
		String[] deploymentsComboBox = new String[deploymentsArray.size()];
		for (int i = 0; i < deploymentsArray.size(); i++) {
			deploymentsComboBox[i] = deploymentsArray.get(i).getName();
		}
		comboBox_deployment = new JComboBox(deploymentsComboBox);

		// HPA
		String[] hpaComboBox = new String[hpaArray.size()];
		for (int i = 0; i < hpaArray.size(); i++) {
			hpaComboBox[i] = hpaArray.get(i).getName();
		}
		comboBox_HPA = new JComboBox(hpaComboBox);

		// URLs
		String[] urlComboBox = new String[servicesArray.size()];
		for (int i = 0; i < servicesArray.size(); i++) {
			urlComboBox[i] = servicesArray.get(i).getName() ;
		}
		comboBox_URL = new JComboBox(urlComboBox);
	}

	public void actualizarComboBox() throws FileNotFoundException, IOException, ParseException {

		// Deployments
		deploymentsArray = rj.readDeployments(namespacesArray.get(comboBox_namespace.getSelectedIndex()).getName());
		String[] deploymentsComboBox = new String[deploymentsArray.size()];
		for (int i = 0; i < deploymentsArray.size(); i++) {
			deploymentsComboBox[i] = deploymentsArray.get(i).getName();
		}
		DefaultComboBoxModel newDeployment = new DefaultComboBoxModel(deploymentsComboBox);
		comboBox_deployment.setModel(newDeployment);

		// HPA
		hpaArray = rj.readHPA(namespacesArray.get(comboBox_namespace.getSelectedIndex()).getName());
		String[] hpaComboBox = new String[hpaArray.size()];
		for (int i = 0; i < hpaArray.size(); i++) {
			hpaComboBox[i] = hpaArray.get(i).getName();
		}
		DefaultComboBoxModel newHpa = new DefaultComboBoxModel(hpaComboBox);
	}
	
	private boolean comprobarCampos() {
		
		//Pasar campos a número ; si da error es falso
		return true;
	}
}

