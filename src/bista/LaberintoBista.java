package bista;

import javax.swing.*;

import kontrolatzailea.JokoaKontrolatzaile;
import modeloa.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

public class LaberintoBista extends JFrame implements Observer, KeyListener {
	private static final long serialVersionUID = 1L;
	private Laberinto laberinto;
	private Bomberman bomberman;
	private final int cellSize = 40;

	private JokoaKontrolatzaile jk;

	private Image fondo;
	private Image bombermanImage;

	private GamePanel gamePanel;

	public LaberintoBista(Laberinto laberinto, Bomberman bomberman, JokoaKontrolatzaile jk) {
		this.laberinto = laberinto;
		this.bomberman = bomberman;
		this.jk = jk;

		setTitle("Bomberman");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		// Fondoa lortu
		fondo = laberinto.getFondo().getImage();

		// Bomberman irudia lortu
		bombermanImage = bomberman.getIrudia().getImage();

		// Jokoaren panela sortu
		gamePanel = new GamePanel();
		gamePanel.setPreferredSize(new Dimension(laberinto.getColumnas() * cellSize, laberinto.getFilas() * cellSize));

		gamePanel.setFocusable(true);
		gamePanel.addKeyListener(this);
		add(gamePanel);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);

		// Observer-a ezarri
		laberinto.addObserver(this);
	}

	// Observer-etan aldaketak egonez gero, aldatu bista
	@Override
	public void update(Observable o, Object arg) {
		// Laberintoa sortzean blokeak ezarri
		if (arg instanceof String) {
			String event = (String) arg;

			switch (event) {
			case "laberinto":
				gamePanel.repaint(); // Laberintoa sortzean irudikatu
				break;
			}

			// Bomberman mugitu bada edo bomba ezarri bada
		} else if (arg instanceof int[]) {
			int[] pos = (int[]) arg;

			// Si el arreglo tiene 4 elementos, es un movimiento del Bomberman
			if (pos.length == 4) {
				int lastX = pos[0], lastY = pos[1];
				int newX = pos[2], newY = pos[3];

				// Aldatu behar diren gelaxkak soilik eguneratu
				gamePanel.repaint(lastX * cellSize, lastY * cellSize, cellSize, cellSize);
				gamePanel.repaint(newX * cellSize, newY * cellSize, cellSize, cellSize);
			}

			// Si el arreglo tiene 2 elementos, es una explosión de bomba
			else if (pos.length == 2) {
				int x = pos[0], y = pos[1];
				gamePanel.repaint(x * cellSize, y * cellSize, cellSize, cellSize); // Se destruye el bloque
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		jk.teklaSakatu(e.getKeyCode());
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	// Fondoa, laberinto blokeak eta Bomberman irudikatzen du
	private class GamePanel extends JPanel {
		private static final long serialVersionUID = 1L;

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			// Fondoa ezarri
			g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);

			// Blokeak irudikatu
			for (int i = 0; i < laberinto.getFilas(); i++) {
				for (int j = 0; j < laberinto.getColumnas(); j++) {
					Bloke bloke = laberinto.getBloke(j, i);
					if (bloke != null) {
						g.drawImage(bloke.getBlokeIrudia().getImage(), j * cellSize, i * cellSize, cellSize, cellSize,
								this);
					}
				}
			}

			// Bomberman irudikatu posizioan
			g.drawImage(bombermanImage, bomberman.getXPixel(), bomberman.getYPixel(), cellSize, cellSize, this);
		}
	}
}
