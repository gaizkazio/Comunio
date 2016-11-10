package Ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
public class MenuRegistro {

	private JFrame frame;
	private JTextField txtUser;
	private JTextField txtContr;
	private JTextField textoUsuario;
	private JTextField textoContraseña;
	
	private static Connection conexion = null;
    private static String bd = "ComunioGM"; // Nombre de BD.
    private static String user = ""; // Usuario de BD.
    private static String password = ""; // Password de BD.
    // Driver para MySQL en este caso.
    private static String driver = "com.mysql.jdbc.Driver";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Connection conn =null;
		try{
			conn = DriverManager.getConnection("jdbc:sqlite:n.db");
		}catch(Exception e){
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuRegistro window = new MenuRegistro();
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
	public MenuRegistro() {
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
		
		txtUser = new JTextField();
		txtUser.setEditable(false);
		txtUser.setText("Usuario");
		txtUser.setBounds(100, 78, 86, 20);
		frame.getContentPane().add(txtUser);
		txtUser.setColumns(10);
		
		txtContr = new JTextField();
		txtContr.setText("Contrase\u00F1a");
		txtContr.setBounds(100, 136, 86, 20);
		frame.getContentPane().add(txtContr);
		txtContr.setColumns(10);
		
		textoUsuario = new JTextField();
		textoUsuario.setBounds(243, 78, 86, 20);
		frame.getContentPane().add(textoUsuario);
		textoUsuario.setColumns(10);
		
		textoContraseña = new JTextField();
		textoContraseña.setBounds(243, 136, 86, 20);
		frame.getContentPane().add(textoContraseña);
		textoContraseña.setColumns(10);
		
		JButton BotonRegistro = new JButton("Registrar");
		BotonRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		BotonRegistro.setBounds(97, 188, 89, 23);
		frame.getContentPane().add(BotonRegistro);
		
		JButton Login = new JButton("Login");
		Login.setBounds(240, 188, 89, 23);
		frame.getContentPane().add(Login);
	}
}
