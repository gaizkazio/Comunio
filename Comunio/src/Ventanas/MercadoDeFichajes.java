package Ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

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
import java.awt.Component;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;


public class MercadoDeFichajes extends JPanel{
	private JTextField txtEstasEnMercado;
	DefaultListModel<String> modelo=new DefaultListModel();;
	private JList lista=new JList();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame frame = new JFrame();
					frame.setBounds(100, 100, 450, 300);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					MercadoDeFichajes window = new MercadoDeFichajes((JPanel)frame.getContentPane());
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
	public MercadoDeFichajes(JPanel mercadopanel) {
		initialize(mercadopanel);
		lista.setModel(modelo);
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(JPanel mercadopanel) {
		
		JPanel panel = this;
		setLayout(null);
		if(mercadopanel!=null){
			mercadopanel.setLayout(new BorderLayout());
			mercadopanel.add(panel, BorderLayout.CENTER);
			}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(390, 100, 605, 450);
		add(scrollPane);
		
		Jugador jugador=new Jugador();
		jugador=TestWeb.generarJugador();
		agregarALista("Nombre: "+ jugador.getNombre()+" precio: "+jugador.getPrecio()+" equipo: "+jugador.getEquipo()+ " puntosTotal: "+jugador.getPuntuacioTotal());
		
		scrollPane.setViewportView(lista);
	
		txtEstasEnMercado = new JTextField();
		txtEstasEnMercado.setEditable(false);
		txtEstasEnMercado.setText("Estas en mercado de fichajes");
		txtEstasEnMercado.setBounds(540, 30, 302, 30);
		add(txtEstasEnMercado);
		txtEstasEnMercado.setColumns(10);
		
		
		
		
		
	}
	
	
	public void agregarALista(String jugador){	
		modelo.addElement(jugador);
		
	}
	public void jugadoresEnMercado(){
		
		
	}
	
	
}
