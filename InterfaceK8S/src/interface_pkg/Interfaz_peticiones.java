package interface_pkg;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Interfaz_peticiones {

	private JFrame frame;
	private JTextField textField_peticionesTotales;
	private JTextField textField_peticionesConcurrentes;
	private JTextField textField_QPS;
	private JTextField textField_URL;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz_peticiones window = new Interfaz_peticiones();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Interfaz_peticiones() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 725, 482);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setBounds(0, 0, 709, 443);
		splitPane.setResizeWeight(.5d);
		frame.getContentPane().add(splitPane);
		
		
		JPanel panel_izq = new JPanel();
		splitPane.setLeftComponent(panel_izq);
		panel_izq.setLayout(null);
		
		JPanel panel_dcha = new JPanel();
		splitPane.setRightComponent(panel_dcha);
		panel_dcha.setLayout(null);
		panel_dcha.setVisible(false);
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel_dcha.setVisible(true);
				splitPane.setDividerLocation(.5);
				lanzar_peticiones(textField_peticionesTotales.getText(), textField_peticionesConcurrentes.getText(), textField_QPS.getText(), textField_URL.getText());
			}
		});
		btnSend.setBounds(252, 407, 89, 23);
		panel_izq.add(btnSend);
		//btnSend.setEnabled(false);
		
		JLabel lblPeticionesHttp = new JLabel("Peticiones HTTP");
		lblPeticionesHttp.setBounds(10, 11, 76, 14);
		panel_izq.add(lblPeticionesHttp);
		
		JLabel lblPeticionesTotales = new JLabel("Peticiones totales");
		lblPeticionesTotales.setBounds(10, 51, 102, 14);
		panel_izq.add(lblPeticionesTotales);
		
		textField_peticionesTotales = new JTextField();
		textField_peticionesTotales.setBounds(10, 76, 86, 20);
		panel_izq.add(textField_peticionesTotales);
		textField_peticionesTotales.setColumns(10);
		
		JLabel lblPeticionesConcurrentes = new JLabel("Peticiones concurrentes");
		lblPeticionesConcurrentes.setBounds(10, 107, 161, 14);
		panel_izq.add(lblPeticionesConcurrentes);
		
		textField_peticionesConcurrentes = new JTextField();
		textField_peticionesConcurrentes.setBounds(10, 132, 86, 20);
		panel_izq.add(textField_peticionesConcurrentes);
		textField_peticionesConcurrentes.setColumns(10);
		
		textField_QPS = new JTextField();
		textField_QPS.setBounds(10, 204, 86, 20);
		panel_izq.add(textField_QPS);
		textField_QPS.setColumns(10);
		
		JLabel lblQueriesPerSecond = new JLabel("Queries per second (QPS)");
		lblQueriesPerSecond.setBounds(10, 179, 137, 14);
		panel_izq.add(lblQueriesPerSecond);
		
		JLabel lblUrl = new JLabel("URL");
		lblUrl.setBounds(10, 246, 46, 14);
		panel_izq.add(lblUrl);
		
		textField_URL = new JTextField();
		textField_URL.setBounds(10, 275, 86, 20);
		panel_izq.add(textField_URL);
		textField_URL.setColumns(10);
		
		JButton btnDatos = new JButton("Datos");
		btnDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Interfaz_datos dataInterface = new Interfaz_datos();
				Interfaz_datos.init();
				
			}
		});
		btnDatos.setBounds(10, 407, 89, 23);
		panel_izq.add(btnDatos);
		
		JPanel panel_graficas = new JPanel();
		panel_graficas.setBounds(10, 11, 331, 257);
		panel_dcha.add(panel_graficas);
		
		JLabel lbl_totalTime = new JLabel("Total:");
		lbl_totalTime.setBounds(10, 285, 46, 14);
		panel_dcha.add(lbl_totalTime);
		
		JLabel lbl_slowTime = new JLabel("Slow:");
		lbl_slowTime.setBounds(10, 310, 46, 14);
		panel_dcha.add(lbl_slowTime);
		
		JLabel lbl_fastTime = new JLabel("Fast:");
		lbl_fastTime.setBounds(10, 335, 46, 14);
		panel_dcha.add(lbl_fastTime);
		
		JLabel lbl_averageTime = new JLabel("Average:");
		lbl_averageTime.setBounds(10, 360, 46, 14);
		panel_dcha.add(lbl_averageTime);
		
		JLabel lbl_rps = new JLabel("Request per second:");
		lbl_rps.setBounds(10, 385, 159, 14);
		panel_dcha.add(lbl_rps);
		
		JLabel lbl_status = new JLabel("Status:");
		lbl_status.setBounds(10, 410, 46, 14);
		panel_dcha.add(lbl_status);
	}
	
	private void lanzar_peticiones(String peticionesTotales, String peticionesConcurrentes, String qps, String URL) {
		
		//Ejecutar comando shell con salida csv
		
		//Leer archivo csv generado
		
		//Mostrar resultados
		
	}
}
