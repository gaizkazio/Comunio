package Ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
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
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JLabel;


public class MercadoDeFichajes extends JPanel{

	

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
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(JPanel mercadopanel) {
		JPanel panel = this;
		if(mercadopanel!=null){
		mercadopanel.setLayout(new BorderLayout());
		mercadopanel.add(panel, BorderLayout.CENTER);
		}
		JMenuItem mntmAlineacion = new JMenuItem("Alineacion");
		panel.add(mntmAlineacion);
		
		JMenuItem mntmMercado = new JMenuItem("Mercado");
		panel.add(mntmMercado);
		
		JMenuItem mntmPuntuacion = new JMenuItem("Puntuacion");
		panel.add(mntmPuntuacion);
		
		JMenuItem mntmNoticias = new JMenuItem("Noticias");
		panel.add(mntmNoticias);
		
		TextArea textArea = new TextArea();
		panel.add(textArea);
		
		JMenuBar menuBar = new JMenuBar();
		panel.add(menuBar);
	}

}
