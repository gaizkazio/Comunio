package Ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import Connection.Bd;
import Entidades.Jugador;
import Utilidades.TestWeb;

import java.awt.BorderLayout;
import javax.swing.JComboBox;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import java.awt.Choice;
import java.awt.Panel;
import java.awt.List;
import java.awt.ScrollPane;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class MercadoDeFichajes extends JPanel{
	private JTextField txtEstasEnMercado;
	DefaultListModel<String> modelo=new DefaultListModel();;
	private static JList lista=new JList();
	private static String nombreUsuario="Pepe";
	Boolean haGanadoOferta=false;
	 
	 private JTextField oferta;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args,Connection con) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame frame = new JFrame();
					frame.setBounds(100, 100, 450, 300);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					MercadoDeFichajes window = new MercadoDeFichajes((JPanel)frame.getContentPane(),con);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MercadoDeFichajes(JPanel mercadopanel,Connection con) {
		initialize(mercadopanel,con);
		lista.setModel(modelo);
		Date horaDespertar = new Date(System.currentTimeMillis());
		Calendar c = Calendar.getInstance();
		c.setTime(horaDespertar);
		JButton btnOferta = new JButton("OFERTA");
		btnOferta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(oferta!=null){
					String ofertaNum=oferta.getText(); // label donde se escribe el dinero de la apuesta
					String jugador=(String) lista.getSelectedValue();
					System.out.println(jugador);
					String nombre=getName(jugador);
					boolean hayJugador=false;
					boolean precioMayor=false;
					ResultSet rpd=null;
					String envio="INSERT INTO oferta VALUES('"+MenuRegistro.getName()+"','"+nombre+"','"+Integer.parseInt(oferta.getText())+"','"+c.get(Calendar.DAY_OF_MONTH)+"-"+c.get(Calendar.MONTH)+"-"+c.get(Calendar.YEAR)+"',"+lista.getSelectedIndex()+" );";
					Statement stt=Bd.usarBD(con);
					int precio1=Integer.parseInt(oferta.getText());
					int precio2=Integer.parseInt(getPrecio(jugador));
					//el valor de la oferta tiene que ser mayor que el del jugador
					if(precio1<precio2){
						precioMayor=false;
						System.out.println("El precio es menor que el valor del jugador");
					}else{
						precioMayor=true;
					}
					try {
						 rpd=stt.executeQuery("SELECT nombreJugador FROM oferta WHERE nombreJugador='"+nombre+"';"); 
						 while(rpd.next()){ 
						if(rpd.getString(1).equals(nombre)){
							hayJugador=true;	
						}else{
							hayJugador=false;
						     }
						                  }
						if(rpd.getRow()==0 && hayJugador==false && precioMayor){
							stt.executeUpdate(envio);
							oferta.setText("");
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						rpd.close();
						stt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		btnOferta.setBounds(390, 579, 89, 23);
		add(btnOferta);
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int pos=lista.getSelectedIndex();
				String jugador=(String)lista.getSelectedValue();
				Statement st=Bd.usarBD(con);
				System.out.println(jugador);
				String nombre=getName(jugador);
				try {
					st.executeUpdate("DELETE FROM oferta WHERE nombreJugador='"+nombre+"';");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		});
		btnCancelar.setBounds(731, 579, 89, 23);
		add(btnCancelar);
		
		oferta = new JTextField();
		oferta.setBounds(528, 580, 142, 20);
		add(oferta);
		oferta.setColumns(10);	
	}

	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(JPanel mercadopanel,Connection con) {
		
		JPanel panel = this;
		setLayout(null);
		if(mercadopanel!=null){
			mercadopanel.setLayout(new BorderLayout());
			mercadopanel.add(panel, BorderLayout.CENTER);
			}
		Date horaDespertar = new Date(System.currentTimeMillis());
		Calendar c = Calendar.getInstance();
		c.setTime(horaDespertar);
		c.set(Calendar.HOUR_OF_DAY, 8);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		Timer timer;
	    timer = new Timer();
	    TimerTask task = new TimerTask(){
			public void run() {
				actualizarMercado(con);
			}
	    	
	    };
	    timer.schedule(task,horaDespertar,86400000);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(390, 100, 605, 450);
		add(scrollPane);
	
		Statement st=Bd.usarBD(con);
		ResultSet sd;
		try {
			sd = st.executeQuery("SELECT * FROM MERCADO");
			while(sd.next()){
				Jugador jugador=new Jugador(sd.getString(1),sd.getString(3),sd.getString(2),"",sd.getString(4),"");
			agregarALista("NOMBRE: "+ jugador.getNombre()+"    PRECIO: "+jugador.getPrecio()+"    EQUIPO: "+jugador.getEquipo()+ "    PUNTOS: "+jugador.getPuntuacioTotal());
			}
			st.close();
			sd.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scrollPane.setViewportView(lista);
	
		txtEstasEnMercado = new JTextField();
		txtEstasEnMercado.setEditable(false);
		txtEstasEnMercado.setText("Estas en mercado de fichajes");
		txtEstasEnMercado.setBounds(540, 30, 302, 30);
		add(txtEstasEnMercado);
		txtEstasEnMercado.setColumns(10);
		
	}
	
	//mete en la lista el jugador
	public void agregarALista(String jugador){	
		modelo.addElement(jugador);
		
	}
	//coge el nombre del jugador teniendo en cuenta el string que devuelve la tabla ofertas

	public static String getName(String nom){
		String nombre="";
		int g=0;
		int h=0;
		int g1=0;
		int h1=0;
		for(int i=0;i<nom.length();i++){
			if(nom.charAt(i)==":".charAt(0) && g1==0){
				g=i+2;
				g1++;
			}
			if(nom.charAt(i)=="P".charAt(0) && h1==0 && nom.charAt(i+1)=="R".charAt(0) ){
				h=i-4;
				h1++;
			}
		}
		
		nombre=nom.substring(g,h);
		nombre=quitarPuntos(nombre);
		return nombre;
	}
	//coge el precio del jugador teniendo en cuenta el string que devuelve la tabla ofertas
	public String getPrecio(String nom){

		String nombre="";
		int g=0;
		int h=0;
		int g1=0;
		int h1=0;
		for(int i=0;i<nom.length();i++){
			if(nom.charAt(i)=="O".charAt(0)&&nom.charAt(i+1)==":".charAt(0) && g1==0){
				g=i+3;
				System.out.println(g);
				g1++;
			}
			if(nom.charAt(i)=="E".charAt(0) && h1==0 && nom.charAt(i+1)=="Q".charAt(0) ){
				h=i-4;
				System.out.println(h);
				h1++;
			}
		}
		nombre=nom.substring(g,h);
		nombre=quitarPuntos(nombre);
		return nombre;	
	}
	//quita los puntos que tienen los numeros
	public static String quitarPuntos(String precio){
		for(int i=0;i<precio.length();i++){
			if(precio.charAt(i)==".".charAt(0)){
				System.out.println("ha encontrado el punto");
				precio=precio.substring(0, i)+precio.substring(i+1,precio.length());
			}
		}
		return precio;
	}
	public static void actualizarMercado(Connection con){
		Statement st=Bd.usarBD(con);
		ResultSet rs;
		String[]jugador=new String[4];
		try {
			
			rs=st.executeQuery("SELECT * from oferta WHERE oferta=(SELECT MAX(oferta) from oferta);");
			while(rs.next()){
				jugador[1]=rs.getString(1);jugador[2]=rs.getString(2);jugador[3]=rs.getInt(3)+"";jugador[4]=rs.getString(4);
			}
			st.executeUpdate("UPDATE usuario SET dinero=dinero-"+jugador[3]+" WHERE USUARIO='"+jugador[1]+"';");
			st.executeUpdate("UPDATE jugador SET dueño='"+jugador[1]+"' WHERE nombre='"+jugador[2]+"';");
			st.executeUpdate("DELETE FROM mercado WHERE nombre='"+jugador[2]+"';");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
