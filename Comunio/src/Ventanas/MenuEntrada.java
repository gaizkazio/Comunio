package Ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;

public class MenuEntrada {

	private JFrame frame;
	private JTextField MeterUsuario;
	private JTextField MeterContrase�a;
	private JTextField Usuario;
	private JTextField Contrase�a;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuEntrada window = new MenuEntrada();
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
	public MenuEntrada() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		MeterUsuario = new JTextField();
		MeterUsuario.setBounds(59, 54, 86, 20);
		frame.getContentPane().add(MeterUsuario);
		MeterUsuario.setColumns(10);
		
		MeterContrase�a = new JTextField();
		MeterContrase�a.setBounds(270, 54, 86, 20);
		frame.getContentPane().add(MeterContrase�a);
		MeterContrase�a.setColumns(10);
		
		JButton Login = new JButton("Login");
		Login.setBounds(170, 153, 89, 23);
		frame.getContentPane().add(Login);
		
		Usuario = new JTextField();
		Usuario.setEditable(false);
		Usuario.setText("Usuario");
		Usuario.setBounds(59, 23, 86, 20);
		frame.getContentPane().add(Usuario);
		Usuario.setColumns(10);
		
		Contrase�a = new JTextField();
		Contrase�a.setEnabled(false);
		Contrase�a.setText("Contrase\u00F1a");
		Contrase�a.setBounds(270, 23, 86, 20);
		frame.getContentPane().add(Contrase�a);
		Contrase�a.setColumns(10);
	}
}
