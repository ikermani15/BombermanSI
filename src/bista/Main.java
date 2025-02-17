package bista;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panelFondo;
	private JLabel lblBackground;
	private JPanel panelTitulo;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {

		initialize();
	}

	private void initialize() {
		setBounds(400, 200, 650, 425);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().add(getPanelFondo(), BorderLayout.CENTER);
	}

	private JPanel getPanelFondo() {
		if (panelFondo == null) {
			panelFondo = new JPanel();

			String imagePath = "/irudiak/back.png";
			ImageIcon imageIcon = new ImageIcon(getClass().getResource(imagePath));
			panelFondo.setLayout(new BorderLayout(0, 0));

			// JLabel sortu irudia agertzeko
			lblBackground = new JLabel(imageIcon);

			panelFondo.add(lblBackground);
		}
		return panelFondo;
	}
}
