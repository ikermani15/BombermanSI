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

		fondo = laberinto.getFondo().getImage();
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

		// Observadores
		laberinto.addObserver(this);
		bomberman.addObserver(this);
	}

	// Observer-etan aldaketak egonez gero, aldatu bista
	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof Laberinto) {
			gamePanel.repaint();
		} else if (o instanceof Bomberman) {
			bombermanImage = bomberman.getIrudia().getImage();
			gamePanel.repaint();
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

			// Dibujar los bloques del laberinto
			for (int i = 0; i < laberinto.getFilas(); i++) {
				for (int j = 0; j < laberinto.getColumnas(); j++) {
					GelaxkaBista gelaxka = gelaxkaBistak[i][j];

					// Asegurarnos de que cada celda tenga el tamaño de cellSize (40x40)
					int xPos = j * cellSize;
					int yPos = i * cellSize;

					if (gelaxka != null) {
						gelaxka.update(null, null); // Actualiza la celda antes de dibujar
						if (gelaxka.getIcon() != null) {
							g.drawImage(((ImageIcon) gelaxka.getIcon()).getImage(), xPos, yPos, cellSize, cellSize,
									this);
						}
					} else {
						g.fillRect(xPos, yPos, cellSize, cellSize);
					}
				}
			}

			// Dibujar Bomberman encima de todo
			int[] bombermanPos = jokoa.getBombermanPosition();
			g.drawImage(bombermanImage, bombermanPos[0], bombermanPos[1], cellSize, cellSize, this);
		}
	}
}
