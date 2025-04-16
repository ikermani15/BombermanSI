package bista;

import javax.swing.*;

import modeloa.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainBista extends JFrame {
	private static final long serialVersionUID = 1L;

	private JComboBox<String> laberintoComboBox;
	private JComboBox<String> bombermanComboBox;
	private JButton jokoaHasiBtn;
	private JLabel fondoLabel;
	private MenuKontrolatzaile kontroler = null;

	public MainBista() {
		// Leihoa definitu
		setTitle("Bomberman - Menu");
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		// Menuaren fondoa
		ImageIcon fondo = new ImageIcon(getClass().getResource("/img/back.png"));
		fondoLabel = new JLabel(new ImageIcon(fondo.getImage().getScaledInstance(400, 300, Image.SCALE_SMOOTH)));
		fondoLabel.setBounds(0, 0, 400, 300);
		getContentPane().add(fondoLabel);

		// Tituluaren irudia
		ImageIcon titulo = new ImageIcon(getClass().getResource("/img/title.png"));
		JLabel tituloLabel = new JLabel(
				new ImageIcon(titulo.getImage().getScaledInstance(200, 60, Image.SCALE_SMOOTH)));
		tituloLabel.setBounds(100, 10, 200, 60);
		fondoLabel.add(tituloLabel);

		// ComboBox laberinto mota aukeratzeko
		JLabel laberintoLabel = new JLabel("LABERINTO");
		laberintoLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
		laberintoLabel.setOpaque(true);
		laberintoLabel.setBackground(new Color(230, 230, 230));
		laberintoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		laberintoLabel.setVerticalAlignment(SwingConstants.CENTER);
		laberintoLabel.setBounds(60, 90, 95, 20);
		fondoLabel.add(laberintoLabel);

		laberintoComboBox = new JComboBox<>(new String[] { "Classic", "Soft", "Empty" });
		laberintoComboBox.setBounds(175, 90, 150, 20);
		fondoLabel.add(laberintoComboBox);

		// ComboBox Bomberman mota aukeratzeko
		JLabel bombermanLabel = new JLabel("BOMBERMAN");
		bombermanLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
		bombermanLabel.setOpaque(true);
		bombermanLabel.setBackground(new Color(230, 230, 230));
		bombermanLabel.setHorizontalAlignment(SwingConstants.CENTER);
		bombermanLabel.setVerticalAlignment(SwingConstants.CENTER);
		bombermanLabel.setBounds(60, 130, 95, 20);
		fondoLabel.add(bombermanLabel);

		bombermanComboBox = new JComboBox<>(new String[] { "White", "Black" });
		bombermanComboBox.setBounds(175, 130, 150, 20);
		fondoLabel.add(bombermanComboBox);

		// Hasieratzeko botoia
		jokoaHasiBtn = new JButton("Jokoa Hasi");
		jokoaHasiBtn.addActionListener(getKontroler()); // Botoiari AL gehitu
		jokoaHasiBtn.setBounds(125, 180, 150, 30);
		fondoLabel.add(jokoaHasiBtn);

		// Kontrolatzailea
		getKontroler();

		setVisible(true);
	}

	private MenuKontrolatzaile getKontroler() {
		if (kontroler == null) {
			kontroler = new MenuKontrolatzaile();
		}
		return kontroler;
	}

	private class MenuKontrolatzaile implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(jokoaHasiBtn)) {
				hasiJokoa();
			}
		}

		// Jokoa hasieratzeko metodoa
		private void hasiJokoa() {
			String laberintoMota = (String) laberintoComboBox.getSelectedItem();
			String bombermanMota = (String) bombermanComboBox.getSelectedItem();

			Jokoa.getJokoa().hasiJokoa(laberintoMota, bombermanMota);
			LaberintoBista lb = new LaberintoBista(); // Bista sortu
			lb.setVisible(true);
			Jokoa.getJokoa().getLaberinto().laberintoaHasieratu(); // Gelaxkak notify egin

			dispose();
		}
	}

}
