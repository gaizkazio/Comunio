package Ventanas;

import java.awt.EventQueue;
import java.awt.Frame;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.util.Timer;
import java.util.TimerTask;
import Connection.Bd;
import Entidades.Jugador;
import javax.swing.JList;
import java.awt.List;
import java.awt.Scrollbar;
import java.awt.Label;
import java.awt.TextField;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Alineacion extends JPanel {
	static JComboBox comboBoxPortero = new JComboBox();
	static	JComboBox comboBoxDef1 = new JComboBox();
	static JComboBox comboBoxDef2 = new JComboBox();
	static JComboBox comboBoxDef3 = new JComboBox();
	static JComboBox comboBoxMedio1 = new JComboBox();
	static JComboBox comboBoxMedio2 = new JComboBox();
	static JComboBox comboBoxMedio3 = new JComboBox();
	static JComboBox comboBoxMedio4 = new JComboBox();
	static JComboBox comboBoxDel1 = new JComboBox();
	static JComboBox comboBoxDel2 = new JComboBox();
	static JComboBox comboBoxDel3 = new JComboBox();
	
	

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
					Alineacion window = new Alineacion((JPanel)frame.getContentPane(),con);
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
	public Alineacion(JPanel alineacionPanel,Connection con) {
		initialize(alineacionPanel,con);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(JPanel alineacionPanel,Connection con) {
		setBounds(100, 100, 989, 741);
		setLayout(null);
		Statement st=Bd.usarBD(con);
		Jugador[]jugadores=new Jugador[25];
		int i=0;
		try {
			ResultSet rs=st.executeQuery("SELECT nombreJugador,posicion FROM alineacion WHERE nombreUsuario='"+MenuRegistro.usuario.getNombre()+"';");
			while(rs.next()){
				Jugador jugador= new Jugador();
				jugador.setNombre(rs.getString(1));jugador.setPosicion(rs.getString(2));
				jugadores[i]=jugador;
				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JList loqmehabiaspedido = new JList();
		loqmehabiaspedido.setBounds(4, 16, 0, 0);
		add(loqmehabiaspedido);
		
		
		comboBoxDel1.setBounds(506, 245, 114, 20);
		add(comboBoxDel1);
		comboBoxDel1.addItem(" ");
		
		comboBoxDel2.setBounds(651, 245, 114, 20);
		add(comboBoxDel2);
		comboBoxDel2.addItem(" ");
		
		comboBoxDel3.setBounds(815, 245, 114, 20);
		add(comboBoxDel3);
		comboBoxDel3.addItem(" ");
		
		comboBoxMedio2.setBounds(425, 311, 114, 20);
		add(comboBoxMedio2);
		comboBoxMedio2.addItem(" ");
		
		comboBoxMedio1.setBounds(578, 311, 114, 20);
		add(comboBoxMedio1);
		comboBoxMedio1.addItem(" ");
		
		comboBoxMedio3.setBounds(732, 311, 119, 20);
		add(comboBoxMedio3);
		comboBoxMedio3.addItem(" ");
		
		comboBoxMedio4.setBounds(889, 311, 114, 20);
		add(comboBoxMedio4);
		comboBoxMedio4.addItem(" ");
		
		comboBoxPortero.setBounds(651, 449, 114, 20);
		add(comboBoxPortero);
		comboBoxPortero.addItem(" ");
		
		comboBoxDef2.setBounds(651, 389, 114, 20);
		add(comboBoxDef2);
		comboBoxDef2.addItem(" ");
		
		comboBoxDef1.setBounds(506, 389, 114, 20);
		add(comboBoxDef1);
		comboBoxDef1.addItem(" ");
		
		comboBoxDef3.setBounds(815, 389, 114, 20);
		add(comboBoxDef3);
		comboBoxDef3.addItem(" ");
		
		for(int j=0;j<i;j++){
			if(jugadores[j].getPosicion().equals("PT")){
				comboBoxPortero.addItem(jugadores[j].getNombre());
			}
			if(jugadores[j].getPosicion().equals("DF")){
				comboBoxDef1.addItem(jugadores[j].getNombre());
				comboBoxDef2.addItem(jugadores[j].getNombre());
				comboBoxDef3.addItem(jugadores[j].getNombre());
				
			}
			if(jugadores[j].getPosicion().equals("MC")){
				comboBoxMedio1.addItem(jugadores[j].getNombre());
				comboBoxMedio2.addItem(jugadores[j].getNombre());
				comboBoxMedio3.addItem(jugadores[j].getNombre());
				comboBoxMedio4.addItem(jugadores[j].getNombre());
			}
			if(jugadores[j].getPosicion().equals("DL")){
				comboBoxDel1.addItem(jugadores[j].getNombre());
				comboBoxDel2.addItem(jugadores[j].getNombre());
				comboBoxDel3.addItem(jugadores[j].getNombre());
			}
		}
		Timer timer;
	    timer = new Timer();
	    TimerTask task = new TimerTask(){

			@Override
			public void run() {

				if(comboBoxDef1.getSelectedIndex()!=0 &&(comboBoxDef1.getSelectedItem().equals(comboBoxDef2.getSelectedItem())||comboBoxDef1.getSelectedItem().equals(comboBoxDef3.getSelectedItem()))){
					comboBoxDef1.setSelectedIndex(0);
				}
				if(comboBoxDef2.getSelectedIndex()!=0 &&(comboBoxDef2.getSelectedItem().equals(comboBoxDef1.getSelectedItem())||comboBoxDef2.getSelectedItem().equals(comboBoxDef3.getSelectedItem()))){
					comboBoxDef2.setSelectedIndex(0);
				}
				if(comboBoxDef3.getSelectedIndex()!=0 &&(comboBoxDef3.getSelectedItem().equals(comboBoxDef1.getSelectedItem())||comboBoxDef3.getSelectedItem().equals(comboBoxDef2.getSelectedItem()))){
					comboBoxDef3.setSelectedIndex(0);
				}
				
				
				if(comboBoxMedio1.getSelectedIndex()!=0 &&(comboBoxMedio1.getSelectedItem().equals(comboBoxMedio2.getSelectedItem())||comboBoxMedio1.getSelectedItem().equals(comboBoxMedio3.getSelectedItem())||comboBoxMedio1.getSelectedItem().equals(comboBoxMedio4.getSelectedItem()))){
					comboBoxMedio1.setSelectedIndex(0);
				}
				if(comboBoxMedio2.getSelectedIndex()!=0 &&(comboBoxMedio2.getSelectedItem().equals(comboBoxMedio1.getSelectedItem())||comboBoxMedio2.getSelectedItem().equals(comboBoxMedio3.getSelectedItem())||comboBoxMedio2.getSelectedItem().equals(comboBoxMedio4.getSelectedItem()))){
					comboBoxMedio2.setSelectedIndex(0);
				}
				if(comboBoxMedio3.getSelectedIndex()!=0 &&(comboBoxMedio3.getSelectedItem().equals(comboBoxMedio1.getSelectedItem())||comboBoxMedio3.getSelectedItem().equals(comboBoxMedio2.getSelectedItem())||comboBoxMedio3.getSelectedItem().equals(comboBoxMedio4.getSelectedItem()))){
					comboBoxMedio3.setSelectedIndex(0);
				}
				if(comboBoxMedio4.getSelectedIndex()!=0 &&(comboBoxMedio4.getSelectedItem().equals(comboBoxMedio1.getSelectedItem())||comboBoxMedio4.getSelectedItem().equals(comboBoxMedio2.getSelectedItem())||comboBoxMedio4.getSelectedItem().equals(comboBoxMedio3.getSelectedItem()))){
					comboBoxMedio4.setSelectedIndex(0);
				}
				
				
				if(comboBoxDel1.getSelectedIndex()!=0 &&(comboBoxDel1.getSelectedItem().equals(comboBoxDel2.getSelectedItem())||comboBoxDel1.getSelectedItem().equals(comboBoxDel3.getSelectedItem()))){
					comboBoxDel1.setSelectedIndex(0);
				}
				if(comboBoxDel2.getSelectedIndex()!=0 &&(comboBoxDel2.getSelectedItem().equals(comboBoxDel1.getSelectedItem())||comboBoxDel2.getSelectedItem().equals(comboBoxDel3.getSelectedItem()))){
					comboBoxDel2.setSelectedIndex(0);
				}
				if(comboBoxDel3.getSelectedIndex()!=0 &&(comboBoxDel3.getSelectedItem().equals(comboBoxDel1.getSelectedItem())||comboBoxDel3.getSelectedItem().equals(comboBoxDel2.getSelectedItem()))){
					comboBoxDel3.setSelectedIndex(0);
				}

			}
	    	
	    };
	    timer.schedule(task, 10, 100);
	final int ia=i;
		JButton btnNewButton = new JButton("GuardarAlineacion");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				guardarAlineacion(con);
			}
		});
		btnNewButton.setBounds(646, 501, 119, 23);
		add(btnNewButton);
		btnVenderjugador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				Frame frame2= new venderJugador(ia,jugadores,con);
				frame2.setVisible(true);
				while(frame2.isActive()){
					
				}
				if(!frame2.isActive()){
					setVisible(true);
				}
			}
		});
		btnVenderjugador.setBounds(815, 501, 114, 23);
		
		add(btnVenderjugador);
		
		
	}
	 public static HiloCambioPantalla hiloCambioPantalla;
	 private final JButton btnVenderjugador = new JButton("venderJugador");
 static class HiloCambioPantalla extends Thread{
		
		 public  void run() {
			
				if(comboBoxDef1.getSelectedIndex()!=0 &&(comboBoxDef1.getSelectedItem().equals(comboBoxDef2.getSelectedItem())||comboBoxDef1.getSelectedItem().equals(comboBoxDef3.getSelectedItem()))){
					comboBoxDef1.setSelectedIndex(0);
				}
				if(comboBoxDef2.getSelectedIndex()!=0 &&(comboBoxDef2.getSelectedItem().equals(comboBoxDef1.getSelectedItem())||comboBoxDef2.getSelectedItem().equals(comboBoxDef3.getSelectedItem()))){
					comboBoxDef2.setSelectedIndex(0);
				}
				if(comboBoxDef3.getSelectedIndex()!=0 &&(comboBoxDef3.getSelectedItem().equals(comboBoxDef1.getSelectedItem())||comboBoxDef3.getSelectedItem().equals(comboBoxDef2.getSelectedItem()))){
					comboBoxDef3.setSelectedIndex(0);
				}
				
				if(comboBoxMedio1.getSelectedIndex()!=0 &&(comboBoxMedio1.getSelectedItem().equals(comboBoxMedio2.getSelectedItem())||comboBoxMedio1.getSelectedItem().equals(comboBoxMedio3.getSelectedItem())||comboBoxMedio1.getSelectedItem().equals(comboBoxMedio4.getSelectedItem()))){
					comboBoxMedio1.setSelectedIndex(0);
				}
				if(comboBoxMedio2.getSelectedIndex()!=0 &&(comboBoxMedio2.getSelectedItem().equals(comboBoxMedio1.getSelectedItem())||comboBoxMedio2.getSelectedItem().equals(comboBoxMedio3.getSelectedItem())||comboBoxMedio2.getSelectedItem().equals(comboBoxMedio4.getSelectedItem()))){
					comboBoxMedio2.setSelectedIndex(0);
				}
				if(comboBoxMedio3.getSelectedIndex()!=0 &&(comboBoxMedio3.getSelectedItem().equals(comboBoxMedio1.getSelectedItem())||comboBoxMedio3.getSelectedItem().equals(comboBoxMedio2.getSelectedItem())||comboBoxMedio3.getSelectedItem().equals(comboBoxMedio4.getSelectedItem()))){
					comboBoxMedio3.setSelectedIndex(0);
				}
				if(comboBoxMedio4.getSelectedIndex()!=0 &&(comboBoxMedio4.getSelectedItem().equals(comboBoxMedio1.getSelectedItem())||comboBoxMedio4.getSelectedItem().equals(comboBoxMedio2.getSelectedItem())||comboBoxMedio4.getSelectedItem().equals(comboBoxMedio3.getSelectedItem()))){
					comboBoxMedio4.setSelectedIndex(0);
				}
				
				if(comboBoxDel1.getSelectedIndex()!=0 &&(comboBoxDel1.getSelectedItem().equals(comboBoxDel2.getSelectedItem())||comboBoxDel1.getSelectedItem().equals(comboBoxDel3.getSelectedItem()))){
					comboBoxDel1.setSelectedIndex(0);
				}
				if(comboBoxDel2.getSelectedIndex()!=0 &&(comboBoxDel2.getSelectedItem().equals(comboBoxDel1.getSelectedItem())||comboBoxDel2.getSelectedItem().equals(comboBoxDel3.getSelectedItem()))){
					comboBoxDel2.setSelectedIndex(0);
				}
				if(comboBoxDel3.getSelectedIndex()!=0 &&(comboBoxDel3.getSelectedItem().equals(comboBoxDel1.getSelectedItem())||comboBoxDel3.getSelectedItem().equals(comboBoxDel2.getSelectedItem()))){
					comboBoxDel3.setSelectedIndex(0);
				}
				
		        }
		
	 }
 public static void guardarAlineacion(Connection con){
	 Statement stt=Bd.usarBD(con);
	 Statement st=Bd.usarBD(con);
	 if(comboBoxPortero.getSelectedIndex()!=0 && comboBoxDef1.getSelectedIndex()!=0 && comboBoxDef2.getSelectedIndex()!=0 && comboBoxDef3.getSelectedIndex()!=0 && comboBoxMedio1.getSelectedIndex()!=0 && comboBoxMedio1.getSelectedIndex()!=0 && comboBoxMedio2.getSelectedIndex()!=0 && comboBoxMedio3.getSelectedIndex()!=0 && comboBoxMedio4.getSelectedIndex()!=0 && comboBoxDel1.getSelectedIndex()!=0 && comboBoxDel2.getSelectedIndex()!=0 && comboBoxDel3.getSelectedIndex()!=0){
		 try {
			 stt.executeUpdate("UPDATE alineacion SET estaAlineado='NO'");
			st.executeUpdate("UPDATE alineacion SET estaAlineado='SI' WHERE nombreJugador IN('"+comboBoxPortero.getSelectedItem()+"','"+comboBoxDef1.getSelectedItem()+"','"+comboBoxDef2.getSelectedItem()+"','"+comboBoxDef3.getSelectedItem()+"','"+comboBoxMedio1.getSelectedItem()+"','"+comboBoxMedio2.getSelectedItem()+"','"+comboBoxMedio3.getSelectedItem()+"','"+comboBoxMedio4.getSelectedItem()+"','"+comboBoxDel1.getSelectedItem()+"','"+comboBoxDel2.getSelectedItem()+"','"+comboBoxDel3.getSelectedItem()+"');");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
 }
 
 
 
}
