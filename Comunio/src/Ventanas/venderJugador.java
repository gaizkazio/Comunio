package Ventanas;

import java.awt.EventQueue;
import java.awt.Window;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import Connection.Bd;
import Entidades.Jugador;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class venderJugador extends JFrame{
	private JTextField txtAQuinVenders;
	ResultSet rs=null;
	ResultSet rss=null;
	Jugador jugador=null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args,int i, Jugador[]jugadores,Connection con) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					venderJugador window = new venderJugador(i,jugadores,con);
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public venderJugador(int i, Jugador[]jugadores,Connection con) {
		initialize(i,jugadores,con);
	}

	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int i, Jugador[]jugadores,Connection con) {
		
		setBounds(100, 100, 476, 341);
		setDefaultCloseOperation(this.HIDE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		Statement st=Bd.usarBD(con);
		Statement stt=Bd.usarBD(con);
		JComboBox comboBox = new JComboBox();
		getContentPane().add(comboBox, BorderLayout.CENTER);
		JButton Vender = new JButton("Vender");
		for(int j=0;j<i;j++){
			comboBox.addItem(jugadores[j].getNombre());
		}


		Vender.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					rs=stt.executeQuery("SELECT nombre,puntuacionTotal,precio,equipo FROM jugador where nombre='"+comboBox.getSelectedItem()+"';");
					rss=st.executeQuery("SELECT nombre FROM mercado WHERE nombre='"+comboBox.getSelectedItem()+"';");
					rss.next();
					System.out.println(rs.getRow()+" "+rss.getRow());
					while(rs.next()&& rss.getRow() == 0){
						 jugador= new Jugador();
						jugador.setNombre(rs.getString(1));jugador.setPuntuacioTotal(rs.getString(2));jugador.setPrecio(rs.getString(3));jugador.setEquipo(rs.getString(4));
						st.executeUpdate("INSERT INTO mercado VALUES('"+jugador.getNombre()+"','"+jugador.getPuntuacioTotal()+"','"+jugador.getPrecio()+"','"+jugador.getEquipo()+"');");

					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				

			}
		});
		getContentPane().add(Vender, BorderLayout.SOUTH);
		
		txtAQuinVenders = new JTextField();
		txtAQuinVenders.setEditable(false);
		txtAQuinVenders.setText("                                                         A QUI\u00C9N VENDER\u00C1S?");
		getContentPane().add(txtAQuinVenders, BorderLayout.NORTH);
		txtAQuinVenders.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setEnabled(false);
		getContentPane().add(lblNewLabel, BorderLayout.WEST);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setEnabled(false);
		getContentPane().add(lblNewLabel_1, BorderLayout.EAST);
		
		
	}

}
