package bista;

import javax.swing.*;

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

	private Jokoa jokoa;

	private Image fondo;
	private Image bombermanImage;

	private GamePanel gamePanel;
	private GelaxkaBista[][] gelaxkaBistak;

	public LaberintoBista(Laberinto laberinto, Bomberman bomberman, Jokoa jokoa) {
		this.laberinto = laberinto;
		this.bomberman = bomberman;
		this.jokoa = jokoa;

		setTitle("Bomberman");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		// Fondoa ezarri
		fondo = laberinto.getFondo().getImage();

		// Bomberman irudia ezarri
		bombermanImage = bomberman.getIrudia().getImage();

		// Jokoaren panela sortu
		gamePanel = new GamePanel();
		gamePanel.setLayout(new GridLayout(laberinto.getFilas(), laberinto.getColumnas()));
		gamePanel.setPreferredSize(new Dimension(laberinto.getColumnas() * cellSize, laberinto.getFilas() * cellSize));
		gelaxkaBistak = new GelaxkaBista[laberinto.getFilas()][laberinto.getColumnas()];

		// UPDATE
		// Gelaxka bakoitzerako bista sortu (JPanel GelaxkaBista erabiliz)
		for (int i = 0; i < laberinto.getFilas(); i++) {
			for (int j = 0; j < laberinto.getColumnas(); j++) {
				Bloke bloke = laberinto.getBloke(j, i);
				GelaxkaBista gelaxkaBista;
				if (bloke != null) {
					// Bloke bakoitzerako, GelaxkaBista bat sortu bloke batekin
					gelaxkaBista = new GelaxkaBista(bloke);
				} else {
					// Blokerik ez badago (hutsa), bidea bezala tratatu
					gelaxkaBista = new GelaxkaBista(null); // Gelaxka hutsa sortu
				}
				gelaxkaBistak[i][j] = gelaxkaBista;
				gamePanel.add(gelaxkaBista);
			}
		}

		gamePanel.setFocusable(true);
		gamePanel.addKeyListener(this);
		add(gamePanel);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);

		// Observe-ak
		laberinto.addObserver(this);
		bomberman.addObserver(this);
	}

	// Observer-etan aldaketak egonez gero, aldatu bista
	@Override
	public void update(Observable o, Object arg) {
		// Eztanda badago
		if (arg instanceof int[]) {
			int[] pos = (int[]) arg;
			int x = pos[0], y = pos[1];

			// Actualizar solo la celda afectada por la explosión
			gelaxkaBistak[y][x].update(null, null);
			gamePanel.repaint(x * cellSize, y * cellSize, cellSize, cellSize);
		} else if (arg instanceof String) {
			String event = (String) arg;

			switch (event) {
			case "mugitu":
				gamePanel.repaint(); // Se podría optimizar más
				break;
			case "laberinto":

				gamePanel.repaint();
				break;
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		jokoa.teklaSakatu(e.getKeyCode());
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	// Panel de juego personalizado que dibuja el fondo, el laberinto y a Bomberman
	private class GamePanel extends JPanel {
		private static final long serialVersionUID = 1L;

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			// Dibujar el fondo
			g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);

			// Dibujar Bomberman encima de todo
			int[] bombermanPos = jokoa.getBombermanPosition();
			g.drawImage(bombermanImage, bombermanPos[0], bombermanPos[1], cellSize, cellSize, this);
		}
	}
}
