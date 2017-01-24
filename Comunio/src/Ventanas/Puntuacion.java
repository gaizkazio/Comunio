package Ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Connection.Bd;
import Entidades.Usuario;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Puntuacion extends JPanel {

	private JFrame frame;
	private JTable table;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args,Connection con) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame frame = new JFrame();
					frame.setBounds(100, 100, 450, 300);
					Puntuacion window = new Puntuacion((JPanel)frame.getContentPane(),con);
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
	public Puntuacion(JPanel puntuacionPanel,Connection con) {
		initialize(puntuacionPanel,con);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(JPanel puntuacionPanel,Connection con) {
		JPanel panel = this;
		setLayout(null);
		if(puntuacionPanel!=null){
			puntuacionPanel.setLayout(new BorderLayout());
			puntuacionPanel.add(panel, BorderLayout.CENTER);
			}
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(390, 100, 605, 342);
		ResultSet rs;
		Statement st=Bd.usarBD(con);
		// 20 usuarios 2 atributos: nombre y puntuacion
		String[][] datos = new String[20][2];
		// se actualiza el jTable
		try {
			rs=st.executeQuery("SELECT USUARIO,puntuacion FROM USUARIO;");
			int i=0;
			int j=0;
			while(rs.next()){
				while(j<2){
					if(j==0){
				datos[i][j]=rs.getString(1);
					}else if(j==1){
						datos[i][j]=rs.getString(2);
					}
				j++;
				}
				i++;
				j=0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Usuario[]usuario=new Usuario[20];
		
				String[] columnNames = {"Usuario","Puntuacion"};
				DefaultTableModel dtm= new DefaultTableModel(datos,columnNames);
				final JTable table = new JTable(dtm);
				table.setBounds(10, 61, 414, 189);
				scrollPane.setViewportView(table);
				add(scrollPane);
				
	}
}
