package Ventanas;

import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JTextField;

import Connection.Bd;
import Connection.Conexion;
import Entidades.Usuario;
import Utilidades.TestWeb;

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
	
	private Connection con =Bd.initBD("ComunioBD");
    private 	bd_statements bds= new bd_statements();
    private Usuario usuario;
    private static String equipo="";
    private static String[] equipos={"alaves","athletic","atletico","barcelona","betis","celta","deportivo","eibar","espanyol","granada","las palmas","leganes","malaga","osasuna","real madrid","real sociedad","sevilla","sporting","valencia","villareal"};
    private final int PT=2;
    private final int DF=5;
    private final int MC=5;
    private final int DL=3;
    private String[] alineacion;
    private static TestWeb tw = new TestWeb();
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
		
		textoContraseña = new JTextField();
		textoContraseña.setBounds(243, 136, 86, 20);
		frame.getContentPane().add(textoContraseña);
		textoContraseña.setColumns(10);
		
		JButton BotonRegistro = new JButton("Registrar");
		BotonRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		
				
				
				
				bds.insertarValoresUsuario(con,textoUsuario.getText(),textoContraseña.getText());
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
				else if(textoContraseña.getText()==""){
					System.out.println("Debes escribir una contraseña");
				}
				
				boolean esCorrecto=false;
			
				try {
					//sacamos los datos de los usuarios y los comparamos con el que nos pasan por pantalla
					ResultSet sd=bds.seleccionarValores("*", "usuario", con);
					System.out.println();
					while(sd.next()){
						if(sd.getString("USUARIO").equals(textoUsuario.getText()) && sd.getString("CONTRASEÑA").equals(textoContraseña.getText())){	
							usuario.setNombre(textoUsuario.getText());
							usuario.setContraseña(textoContraseña.getText());
							System.out.println("Se ha conectado");
							esCorrecto=true;
						}
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(esCorrecto){
					int cont=0;
					int contJug=0;
					int contPT=0;
					int contDF=0;
					int contMC=0;
					int contDL=0;
					ResultSet sd=bds.seleccionarValores("*", "jugadores", con);
					System.out.println();
					try {
						while(sd.next()){
							if(sd.getString("dueño").equals(usuario.getNombre())){
								cont++;
							}
						}
						if(cont==0){
							
							while(contJug<15){
							String jugador=	generarJugadores();
							String nombre=tw.sacarNombre( jugador);
							if(tw.sacarPosiciones(nombre, equipos)=="PT" && contPT<PT){
								for(int i=0;i<alineacion.length;i++){
									if(alineacion[i]==null){
										alineacion[i]=jugador;
										i=alineacion.length;
										contPT++;
										contJug++;
									}
								}
							}
							
							if(tw.sacarPosiciones(nombre, equipos)=="DF" && contDF<DF){
								for(int i=0;i<alineacion.length;i++){
									if(alineacion[i]==null){
										alineacion[i]=jugador;
										i=alineacion.length;
										contDF++;
										contJug++;
									}
								}
							}
							
							if(tw.sacarPosiciones(nombre, equipos)=="MC" && contMC<MC){
								for(int i=0;i<alineacion.length;i++){
									if(alineacion[i]==null){
										alineacion[i]=jugador;
										i=alineacion.length;
										contMC++;
										contJug++;
									}
								}
							}
							
							if(tw.sacarPosiciones(nombre, equipos)=="DL" && contDL<DL){
								for(int i=0;i<alineacion.length;i++){
									if(alineacion[i]==null){
										alineacion[i]=jugador;
										i=alineacion.length;
										contDL++;
										contJug++;
									}
								}
							}
							
							}
							
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				frame.setVisible(false);
					Frame frame2=new  Noticias();
					frame2.setVisible(true);
					
					
				}
			}
		});
		Login.setBounds(240, 188, 89, 23);
		frame.getContentPane().add(Login);
	}
	public static String generarJugadores(){
		int comas=0;
		
		int a = (int)(Math.random()*20);
		System.out.println(a);
		int cont=0;
		 equipo=equipos[a];
		String [] jugadores=tw.pruebaDatosJugadoresComuniazo("http://www.comuniazo.com/comunio/equipos/"+equipo);
		
		for(int i=0;i<jugadores.length && jugadores[i]!=null;i++){
			
			for(int j=0;j<jugadores[i].length();j++){
				if(jugadores[i].charAt(j)==",".charAt(0) && !(jugadores[i].endsWith("[]"))){
					comas++;
				}
			}
			if(comas>0)
			cont++;
			comas=0;
		}
		System.out.println(cont);
		int b=1+(int)(Math.random()*(cont-1));
		System.out.println(b);
		String nomJugador=tw.sacarNombre(b, jugadores);
		System.out.println(nomJugador);
		return jugadores[b];
	}
	
}
