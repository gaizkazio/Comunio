package Ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;

public class Noticias {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Noticias window = new Noticias();
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
	public Noticias() {
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
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.setBounds(299, 125, 89, 23);
		frame.getContentPane().add(btnEnviar);
	}

}
