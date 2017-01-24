package Ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Connection.Bd;
import Entidades.Jugador;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class cancelarVenta extends JFrame{
	private JTextField txtAQuinVenders;
	ResultSet rs=null;
	ResultSet rss=null;
	Jugador jugador=null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args,Connection con) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					cancelarVenta window = new cancelarVenta(con);
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
	public cancelarVenta(Connection con) {
		initialize(con);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Connection con) {
		String is;
		setBounds(100, 100, 476, 341);
		setDefaultCloseOperation(this.HIDE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		Statement st=Bd.usarBD(con);
		Statement stt=Bd.usarBD(con);
		JComboBox comboBox = new JComboBox();
		getContentPane().add(comboBox, BorderLayout.CENTER);
		try {
		
			rs=st.executeQuery("SELECT nombre FROM mercado WHERE dueño='"+MenuRegistro.usuario.getNombre()+"';");
			while(rs.next()){
				comboBox.addItem(rs.getString(1));
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
		
		JButton quitarVenta = new JButton("quitarVenta");
		quitarVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					st.executeUpdate("DELETE FROM mercado WHERE nombre='"+comboBox.getSelectedItem()+"';");
					st.executeUpdate("DELETE FROM oferta WHERE nombreJugador='"+comboBox.getSelectedItem()+"';");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
getContentPane().add(quitarVenta, BorderLayout.SOUTH);
		
		txtAQuinVenders = new JTextField();
		txtAQuinVenders.setText("                                                  A QUI\u00C9N DEJAR\u00C1S DE VENDERER?");
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
