package Ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JList;
import java.awt.List;
import java.awt.Scrollbar;
import java.awt.Label;
import java.awt.TextField;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Alineacion {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Alineacion window = new Alineacion();
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
	public Alineacion() {
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
		
		JList loqmehabiaspedido = new JList();
		loqmehabiaspedido.setBounds(233, 57, -75, -50);
		frame.getContentPane().add(loqmehabiaspedido);
		
		TextField Portero = new TextField();
		Portero.setBounds(163, 209, 78, 22);
		frame.getContentPane().add(Portero);
		
		TextField Defensa2 = new TextField();
		Defensa2.setBounds(25, 167, 64, 22);
		frame.getContentPane().add(Defensa2);
		
		TextField Defensa1 = new TextField();
		Defensa1.setBounds(173, 167, 64, 22);
		frame.getContentPane().add(Defensa1);
		
		TextField Defensa3 = new TextField();
		Defensa3.setBounds(321, 167, 64, 22);
		frame.getContentPane().add(Defensa3);
		
		TextField medio1 = new TextField();
		medio1.setBounds(0, 113, 64, 22);
		frame.getContentPane().add(medio1);
		
		TextField medio2 = new TextField();
		medio2.setBounds(110, 113, 64, 22);
		frame.getContentPane().add(medio2);
		
		TextField medio3 = new TextField();
		medio3.setBounds(241, 113, 64, 22);
		frame.getContentPane().add(medio3);
		
		TextField medio4 = new TextField();
		medio4.setBounds(337, 113, 78, 22);
		frame.getContentPane().add(medio4);
		
		TextField delantero1 = new TextField();
		delantero1.setBounds(44, 48, 64, 22);
		frame.getContentPane().add(delantero1);
		
		TextField delantero2 = new TextField();
		delantero2.setBounds(177, 57, 64, 22);
		frame.getContentPane().add(delantero2);
		
		TextField delantero3 = new TextField();
		delantero3.setBounds(293, 48, 64, 22);
		frame.getContentPane().add(delantero3);
		
		JComboBox comboBoxDel1 = new JComboBox();
		comboBoxDel1.setBounds(99, 50, 28, 20);
		frame.getContentPane().add(comboBoxDel1);
		
		JComboBox comboBoxDel2 = new JComboBox();
		comboBoxDel2.setBounds(239, 57, 28, 20);
		frame.getContentPane().add(comboBoxDel2);
		
		JComboBox comboBoxDel3 = new JComboBox();
		comboBoxDel3.setBounds(349, 48, 28, 20);
		frame.getContentPane().add(comboBoxDel3);
		
		JComboBox comboBoxMedio2 = new JComboBox();
		comboBoxMedio2.setBounds(173, 113, 28, 20);
		frame.getContentPane().add(comboBoxMedio2);
		
		JComboBox comboBoxMedio1 = new JComboBox();
		comboBoxMedio1.setBounds(61, 113, 28, 20);
		frame.getContentPane().add(comboBoxMedio1);
		
		JComboBox comboBoxMedio3 = new JComboBox();
		comboBoxMedio3.setBounds(303, 113, 28, 20);
		frame.getContentPane().add(comboBoxMedio3);
		
		JComboBox comboBoxMedio4 = new JComboBox();
		comboBoxMedio4.setBounds(406, 113, 28, 20);
		frame.getContentPane().add(comboBoxMedio4);
		
		JComboBox comboBoxPortero = new JComboBox();
		comboBoxPortero.setBounds(239, 209, 28, 20);
		frame.getContentPane().add(comboBoxPortero);
		
		JComboBox comboBoxDef2 = new JComboBox();
		comboBoxDef2.setBounds(80, 169, 28, 20);
		frame.getContentPane().add(comboBoxDef2);
		
		JComboBox comboBoxDef1 = new JComboBox();
		comboBoxDef1.setBounds(233, 169, 28, 20);
		frame.getContentPane().add(comboBoxDef1);
		
		JComboBox comboBoxDef3 = new JComboBox();
		comboBoxDef3.setBounds(377, 169, 28, 20);
		frame.getContentPane().add(comboBoxDef3);
		
		JButton btnNewButton = new JButton("GuardarAlineacion");
		btnNewButton.setBounds(321, 227, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JMenuItem mntmMercado = new JMenuItem("Mercado");
		mntmMercado.setBounds(-21, -1, 129, 22);
		frame.getContentPane().add(mntmMercado);
		
		JMenuItem mntmAlineacion = new JMenuItem("Alineacion");
		mntmAlineacion.setBounds(61, 0, 129, 22);
		frame.getContentPane().add(mntmAlineacion);
		
		JMenuItem mntmPuntuacion = new JMenuItem("Puntuacion");
		mntmPuntuacion.setBounds(152, -1, 129, 22);
		frame.getContentPane().add(mntmPuntuacion);
		
		JMenuItem mntmNoticias = new JMenuItem("Noticias");
		mntmNoticias.setBounds(293, -1, 129, 22);
		frame.getContentPane().add(mntmNoticias);
	}
}
