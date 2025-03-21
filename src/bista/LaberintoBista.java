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
	private final int cellSize = 40;
	private JPanel laberintoPanel;

	private Kontrolatzaile kontroler = null;

	public LaberintoBista() {
		setTitle("Bomberman");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		// LaberintoBista modeloko Laberintoren Oberver-a
		Laberinto.getLaberinto().addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof String) {
			String event = (String) arg;

			if (event.equals("sortu")) {
				laberintoSortu();
			}
		}
	}

	private void laberintoSortu() {
		Laberinto laberinto = Laberinto.getLaberinto();

		// Lehen gelaxkan Bomberman ezarri
		Gelaxka hasierakoGelaxka = laberinto.getGelaxka(0, 0);
		hasierakoGelaxka.gehituBomberman();

		// Fondo de la imagen
		Image fondo = laberinto.getFondo().getImage();

		// Panel del laberinto
		laberintoPanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
			}
		};

		// Crear la grid layout para las celdas
		laberintoPanel.setLayout(new GridLayout(laberinto.getFilas(), laberinto.getColumnas()));
		laberintoPanel
				.setPreferredSize(new Dimension(laberinto.getColumnas() * cellSize, laberinto.getFilas() * cellSize));

		// Crear cada GelaxkaBista para cada celda en el laberinto
		for (int i = 0; i < laberinto.getFilas(); i++) {
			for (int j = 0; j < laberinto.getColumnas(); j++) {
				Gelaxka gelaxka = laberinto.getGelaxka(j, i);
				GelaxkaBista gelaxkaBista = new GelaxkaBista(gelaxka);
				laberintoPanel.add(gelaxkaBista);
			}
		}

		add(laberintoPanel, BorderLayout.CENTER);
		laberintoPanel.setFocusable(true);

		// Controlador para manejar teclas
		getKontroler();
		laberintoPanel.addKeyListener(kontroler);

		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private Kontrolatzaile getKontroler() {
		if (kontroler == null) {
			kontroler = new Kontrolatzaile();
		}
		return kontroler;
	}

	// Kontrolatzailerako klase pribatua (teklatua kontrolatzeko)
	private class Kontrolatzaile implements KeyListener {
		@Override
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
				Bomberman.getBomberman().mugituGora();
				break;
			case KeyEvent.VK_DOWN:
				Bomberman.getBomberman().mugituBehera();
				break;
			case KeyEvent.VK_LEFT:
				Bomberman.getBomberman().mugituEzkerra();
				break;
			case KeyEvent.VK_RIGHT:
				Bomberman.getBomberman().mugituEskuma();
				break;
			case KeyEvent.VK_SPACE:
				Bomberman.getBomberman().bombaJarri();
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
