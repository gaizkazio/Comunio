package Ventanas;

import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JTextField;

import Connection.Bd;
import Connection.Conexion;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
public class MenuRegistro {

	private JFrame frame;
	private JTextField txtUser;
	private JTextField txtContr;
	private JTextField textoUsuario;
	private JTextField textoContrase�a;
	
	private Connection con =Bd.initBD("ComunioBD");
    private 	bd_statements bds= new bd_statements();
    
    

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
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
		txtContr.setEditable(false);
		txtContr.setText("Contrase\u00F1a");
		txtContr.setBounds(100, 136, 86, 20);
		frame.getContentPane().add(txtContr);
		txtContr.setColumns(10);
		
		textoUsuario = new JTextField();
		textoUsuario.setBounds(243, 78, 86, 20);
		frame.getContentPane().add(textoUsuario);
		textoUsuario.setColumns(10);
		
		textoContrase�a = new JTextField();
		textoContrase�a.setBounds(243, 136, 86, 20);
		frame.getContentPane().add(textoContrase�a);
		textoContrase�a.setColumns(10);
		
		JButton BotonRegistro = new JButton("Registrar");
		BotonRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		
				
				
				
				bds.insertarValoresUsuario(con,textoUsuario.getText(),textoContrase�a.getText());
			}
		});
		BotonRegistro.setBounds(97, 188, 89, 23);
		frame.getContentPane().add(BotonRegistro);
		
		JButton Login = new JButton("Login");
		Login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textoUsuario.getText()==""){
					System.out.println("Debes escribir un nombre");
				}
				else if(textoContrase�a.getText()==""){
					System.out.println("Debes escribir una contrase�a");
				}
				
				boolean esCorrecto=false;
			
				
			
				
			
				
				try {
					ResultSet sd=bds.seleccionarValores("*", "usuario", con);
					System.out.println();
					while(sd.next()){
						
						if(sd.getString("USUARIO").equals(textoUsuario.getText()) && sd.getString("CONTRASE�A").equals(textoContrase�a.getText())){
							
							System.out.println("Se ha conectado");
							esCorrecto=true;
						}
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(esCorrecto){
				frame.setVisible(false);
					Frame frame2=new  Noticias();
					frame2.setVisible(true);
					
					
				}
			}
		});
		Login.setBounds(240, 188, 89, 23);
		frame.getContentPane().add(Login);
	}

	
}
