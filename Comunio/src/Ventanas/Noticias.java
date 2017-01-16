package Ventanas;

import java.awt.EventQueue;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;

public class Noticias extends JFrame{
	private JTextField textField;
	public Noticias(Connection con){

		
		//Parametros asociados a la ventana
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		setVisible(true);
		setTitle("Ejemplo JTabbedPane");

		//Creamos el conjunto de pestañas
		JTabbedPane pestañas=new JTabbedPane();
		JPanel panel3=new Puntuacion(null, con);

		//Componentes del panel3
		JLabel et_p3=new JLabel("Estas en Puntuacion");
		panel3.add(et_p3);

		pestañas.addTab("Puntuacion", panel3);

		JPanel panel4=new JPanel();

		//Componentes del panel4
		JLabel et_p4=new JLabel("Estas en Alineacion");
		panel4.add(et_p4);

		pestañas.addTab("Alineacion", panel4);

		getContentPane().add(pestañas);
				
				
						//Realizamos lo mismo con el resto
						
						JPanel panel2= new MercadoDeFichajes(null,con);
						
								//Componentes del panel2
								
								
								pestañas.addTab("Mercado", panel2);
								
										//Creamos el panel y lo añadimos a las pestañas
										JPanel panel1=new JPanel();
										panel1.setLayout(null);
									

										//Componentes del panel1
										JLabel et_p1=new JLabel("Estas en Noticias");
										et_p1.setBounds(614, 5, 125, 14);
										panel1.add(et_p1);
										
										
										

										//Añadimos un nombre de la pestaña y el panel
										pestañas.addTab("Noticias", panel1);
										textField = new JTextField();
										textField.setBounds(540, 356, 302, 30);
										panel1.add(textField);
										textField.setColumns(10);
										
										JList list = new JList();
										list.setBounds(540, 107, 302, 226);
										list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );
										DefaultListModel modelo = new DefaultListModel();
										
										
										JScrollPane scrollLista = new JScrollPane();
										scrollLista.setBounds(540, 200, 305, 150);
										scrollLista.setViewportView(list);
										panel1.add(scrollLista);
										
										JButton btnAadir = new JButton("A\u00D1ADIR");
										btnAadir.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent arg0) {
												 String nombre=textField.getText();
												 modelo.addElement(nombre);
												 list.setModel(modelo);
												 textField.setText("");
											}
										});
										btnAadir.setBounds(650, 397, 89, 23);
										panel1.add(btnAadir);
										
										
	}

	public static void main(String[] args,Connection con) {


		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Noticias window = new Noticias(con);
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	

	}
}
	 