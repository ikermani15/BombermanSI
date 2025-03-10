package bista;

import javax.swing.*;

import kontrolatzailea.JokoaKontrolatzaile;
import modeloa.Jokoa;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainBista extends JFrame {
	private static final long serialVersionUID = 1L;

	private JComboBox<String> laberintoComboBox;
	private JComboBox<String> bombermanComboBox;
	private JButton jokoaHasiBtn;
	private JLabel fondoLabel;

	private JokoaKontrolatzaile jokoaK;

	public MainBista(JokoaKontrolatzaile jokoaK) {
		this.jokoaK = jokoaK;

		// Leihoaren definitu
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
		JLabel laberintoLabel = new JLabel("Laberinto mota:");
		laberintoLabel.setBounds(50, 80, 100, 20);
		fondoLabel.add(laberintoLabel);

		laberintoComboBox = new JComboBox<>(new String[] { "Classic", "Soft", "Empty" });
		laberintoComboBox.setBounds(150, 80, 150, 20);
		fondoLabel.add(laberintoComboBox);

		// ComboBox Bomberman mota aukeratzeko
		JLabel bombermanLabel = new JLabel("Bomberman:");
		bombermanLabel.setBounds(50, 120, 100, 20);
		fondoLabel.add(bombermanLabel);

		bombermanComboBox = new JComboBox<>(new String[] { "White", "Black" });
		bombermanComboBox.setBounds(150, 120, 150, 20);
		fondoLabel.add(bombermanComboBox);

		// Hasieratzeko botoia
		jokoaHasiBtn = new JButton("Jokoa Hasi");
		jokoaHasiBtn.setBounds(125, 180, 150, 30);
		fondoLabel.add(jokoaHasiBtn);

		// Kontrolatzailea
		// Klik egitearen akzioa
		jokoaHasiBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String laberintoMota = (String) laberintoComboBox.getSelectedItem();
				String bombermanMota = (String) bombermanComboBox.getSelectedItem();

				// Jokoa hasieratu laberinto eta bomberman mota pasata
				jokoaK.hasiJokoa(laberintoMota, bombermanMota);

				dispose(); // Menua itxi
			}
		});

		// "Esc" klikatzean programa itxi
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"), "itxi");
		getRootPane().getActionMap().put("itxi", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0); // Programa itxi
			}
		});

		setVisible(true);
	}

}
