package bista;

import javax.swing.*;
import modeloa.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

public class LaberintoBista extends JFrame implements Observer {
	private static final long serialVersionUID = 1L;
	private Laberinto laberinto;
	private Bomberman bomberman;
	private final int cellSize = 40;
	private Image fondo;
	private Image bombermanImage;

	private JPanel laberintoPanel;
	private Kontrolatzaile kontrolatzaile;

	public LaberintoBista(Laberinto laberinto, Bomberman bomberman) {
		this.laberinto = laberinto;
		this.bomberman = bomberman;

		setTitle("Bomberman");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		// Fondoaren irudia lortu
		fondo = laberinto.getFondo().getImage();

		// Bomberman irudia lortu
		bombermanImage = bomberman.getIrudia().getImage();

		// Laberintoaren panela sortu
		laberintoPanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);

				// Fondoaren irudia ezarri
				g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);

				// Bomberman irudia ezarri
				g.drawImage(bombermanImage, bomberman.getXPixel(), bomberman.getYPixel(), cellSize, cellSize, this);
			}
		};
		// Matrizea sortu Layout erabiliz
		laberintoPanel.setLayout(new GridLayout(laberinto.getFilas(), laberinto.getColumnas()));

		// Panelaren tamaina ezarri
		laberintoPanel
				.setPreferredSize(new Dimension(laberinto.getColumnas() * cellSize, laberinto.getFilas() * cellSize));

		// GelaxkaBista Observer bat sortu laberintoak duen bloke bakoitzerako
		for (int i = 0; i < laberinto.getFilas(); i++) {
			for (int j = 0; j < laberinto.getColumnas(); j++) {
				Bloke bloke = laberinto.getBloke(j, i);
				GelaxkaBista gelaxka = new GelaxkaBista(bloke);
				laberintoPanel.add(gelaxka);
			}
		}

		add(laberintoPanel, BorderLayout.CENTER);

		// Kontrolatzailea sortu (teklatua irakurtzeko)
		this.kontrolatzaile = new Kontrolatzaile();
		laberintoPanel.setFocusable(true);
		laberintoPanel.addKeyListener(kontrolatzaile);

		pack();
		setLocationRelativeTo(null);
		setVisible(true);

		// LaberintoBista modeloko Laberinto eta Bomberman Observer-a ezarri
		laberinto.addObserver(this);
		bomberman.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof String) {
			String evento = (String) arg;

			switch (evento) {
			case "sortu":
			case "mugitu":
				laberintoPanel.repaint();
				break;
			}
		}
	}

	// Kontrolatzailerako klase pribatua (teklatua kontrolatzeko)
	private class Kontrolatzaile implements KeyListener {
		@Override
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
				bomberman.mugituGora();
				break;
			case KeyEvent.VK_DOWN:
				bomberman.mugituBehera();
				break;
			case KeyEvent.VK_LEFT:
				bomberman.mugituEzkerra();
				break;
			case KeyEvent.VK_RIGHT:
				bomberman.mugituEskuma();
				break;
			case KeyEvent.VK_SPACE:
				bomberman.bombaJarri();
				break;
			}
		}

		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}
	}
}
