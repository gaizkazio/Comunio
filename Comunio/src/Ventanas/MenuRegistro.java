package Ventanas;

import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JTextField;

import Connection.Bd;
import Connection.Conexion;
import Entidades.Jugador;
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
	
	
    private static bd_statements bds= new bd_statements();
    private static TestWeb tw= new TestWeb();
    public static Usuario usuario=new Usuario("gaizka","gaizka");
    private static String equipo="";
    private static String[] equipos={"alaves","athletic","atletico","barcelona","betis","celta","deportivo","eibar","espanyol","granada","las palmas","leganes","malaga","osasuna","real madrid","real sociedad","sevilla","sporting","valencia","villareal"};
    private final static int PT=2;
    private final static int DF=5;
    private final static int MC=5;
    private final static int DL=3;
    private static Jugador[] alineacion= new Jugador[30];
    
	/**
	 * Launch the application.
	 */
    public static String getName(){
    	return usuario.getNombre();
    }
    public static Jugador[] ordenarArray(Jugador[] jugadores){
    	for(int i=0;i<jugadores.length && jugadores[i]!=null && jugadores[i+1]!=null;i++){
    		if(jugadores[i].getPosicion().equals("PT")&&(jugadores[i-1].getPosicion().equals("DF")||jugadores[i-1].getPosicion().equals("MC")||jugadores[i-1].getPosicion().equals("DL"))){
    			Jugador jugador=jugadores[i-1];
    			jugadores[i-1]=jugadores[i];
    			jugadores[i]=jugador;
    			i=0;
    		}
    		 if(jugadores[i+1].getPosicion().equals("DF")&&(jugadores[i].getPosicion().equals("MC")||jugadores[i].getPosicion().equals("DL"))){
    			Jugador jugador=jugadores[i];
    			jugadores[i]=jugadores[i+1];
    			jugadores[i+1]=jugador;
    			i=0;
    		}
    		 if(jugadores[i+1].getPosicion().equals("MC")&&(jugadores[i].getPosicion().equals("DL"))){
    			Jugador jugador=jugadores[i];
    			jugadores[i]=jugadores[i+1];
    			jugadores[i+1]=jugador;
    			i=0;
    		}
    	}
    	
    	
    	return jugadores;
    }
    public static void darJugadores(Connection con){
    	ResultSet rs;
    	Statement st=Bd.usarBD(con);
    	Statement stt=Bd.usarBD(con);
    	int cont=0;
    	int contJug=0;
    	int contPT=0;
    	int contDF=0;
    	int contMC=0;
    	int contDL=0;
    	try {
    		 rs=bds.seleccionarValores("*", "jugador", con);
			while(rs.next()){
				if(rs.getString(6).equals(usuario.getNombre())){
					cont++;
				}
			}
			rs.close();
			if(cont==0){
				System.out.println("No tienes ningun jugador");
				while(contJug<15){
				Jugador jugador=tw.generarJugador(con);
				if(jugador.getPosicion().equals("PT")&&jugador.getDueño().equals("computer") && contPT<PT){
					for(int i=0;i<alineacion.length;i++){
						if(alineacion[i]==null){
							alineacion[i]=jugador;
							i=alineacion.length;
							contPT++;
							contJug++;
						}
					}
				}
				
				if(jugador.getPosicion().equals("DF")&&jugador.getDueño().equals("computer") && contDF<DF){
					for(int i=0;i<alineacion.length;i++){
						if(alineacion[i]==null){
							alineacion[i]=jugador;
							i=alineacion.length;
							contDF++;
							contJug++;
						}
					}
				}
				
				if(jugador.getPosicion().equals("MC")&&jugador.getDueño().equals("computer") && contMC<MC){
					for(int i=0;i<alineacion.length;i++){
						if(alineacion[i]==null){
							alineacion[i]=jugador;
							i=alineacion.length;
							contMC++;
							contJug++;
						}
					}
				}
				
				if(jugador.getPosicion().equals("DL")&&jugador.getDueño().equals("computer") && contDL<DL){
					for(int i=0;i<alineacion.length;i++){
						if(alineacion[i]==null){
							alineacion[i]=jugador;
							i=alineacion.length;
							contDL++;
							contJug++;
						}
					}
				}		
				System.out.println(contJug);
				}
				ordenarArray(alineacion);
				for(int i=0;i<15;i++){
					System.out.println("hola");
					System.out.println(alineacion[i].toString());
					alineacion[i].setDueño(MenuRegistro.usuario.getNombre());
					System.out.println("empieza el insert");
					st.executeUpdate("INSERT INTO alineacion VALUES('"+alineacion[i].getNombre()+"','"+alineacion[i].getPosicion()+"','"+alineacion[i].getPuntuacioTotal()+"','"+alineacion[i].getDueño()+"');");
					
						Thread.sleep(100);
					System.out.println("empieza el update");
				
					stt.executeUpdate("UPDATE jugador SET dueño='"+alineacion[i].getDueño()+"' WHERE nombre='"+alineacion[i].getNombre()+"';");
					Thread.sleep(100);
				}
				st.close();
				stt.close();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
	public static void main(String[] args,Connection con) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuRegistro window = new MenuRegistro(con);
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
	public MenuRegistro(Connection con) {
		initialize(con);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Connection con) {
		TestWeb tw=new TestWeb();
		tw.darPuntos(con, "gaizka","85");
		tw.darPuntos(con, "mikel","32");
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
					sd.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(esCorrecto){
					
					darJugadores(con);
					System.out.println();
					
					
				frame.setVisible(false);
					Frame frame2=new  Noticias(con);
					frame2.setVisible(true);
					
					
				}
			}
		});
		Login.setBounds(240, 188, 89, 23);
		frame.getContentPane().add(Login);
	}
	
}
