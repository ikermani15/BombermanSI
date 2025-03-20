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
	private JPanel laberintoPanel;

	private Kontrolatzaile kontroler = null;

	public LaberintoBista() {
		// ESTO EN EL EREDU
		laberinto = Laberinto.getLaberinto();
		bomberman = Bomberman.getBomberman();

		// LaberintoBista modeloko Laberintoren Oberver-a
		laberinto.addObserver(this);

		// ESTO CAMBIAR, DEBERIA INICIARSE SIEMPRE EN LA GELAXKA (0,0)
		// Lehen gelaxkan Bomberman ezarri
		Gelaxka hasierakoGelaxka = laberinto.getGelaxka(0, 0);
		hasierakoGelaxka.gehituBomberman(bomberman);

		// ESTO BIEN
		setTitle("Bomberman");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		// Fondoaren irudia lortu
		fondo = laberinto.getFondo().getImage();

		// Laberintoaren panela sortu
		laberintoPanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				// Fondoaren irudia ezarri
				g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
			}
		};
		// Matrizea sortu Layout erabiliz
		laberintoPanel.setLayout(new GridLayout(laberinto.getFilas(), laberinto.getColumnas()));

		// Panelaren tamaina ezarri
		laberintoPanel
				.setPreferredSize(new Dimension(laberinto.getColumnas() * cellSize, laberinto.getFilas() * cellSize));

		// UPDATEAN
		// Gelaxka bat sortu laberintoak duen gelaxka bakoitzerako
		for (int i = 0; i < laberinto.getFilas(); i++) {
			for (int j = 0; j < laberinto.getColumnas(); j++) {
				Gelaxka gelaxka = laberinto.getGelaxka(j, i);
				GelaxkaBista gelaxkaBista = new GelaxkaBista(gelaxka);
				laberintoPanel.add(gelaxkaBista);
			}
		}

		add(laberintoPanel, BorderLayout.CENTER);
		laberintoPanel.setFocusable(true);

		// Kontrolatzailea sortu (teklatua irakurtzeko)
		getKontroler();
		laberintoPanel.addKeyListener(kontroler);

		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof String) {
			String event = (String) arg;

			if (event.equals("sortu")) {
				laberintoPanel.repaint();
			}
		}
	}

	private Kontrolatzaile getKontroler() {
		if (kontroler == null) {
			kontroler = new Kontrolatzaile();
		}
		return kontroler;
	}

	// Kontrolatzailerako klase pribatua (teklatua kontrolatzeko)
	private class Kontrolatzaile implements KeyListener {
		Bomberman bomberman = Bomberman.getBomberman();

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
