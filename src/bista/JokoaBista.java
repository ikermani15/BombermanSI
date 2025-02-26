package bista;

import javax.swing.*;
import modeloa.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

public class JokoaBista extends JFrame implements Observer, KeyListener {
	private static final long serialVersionUID = 1L;
	private Laberinto laberinto;
	private Bomberman bomberman;
	private final int cellSize = 40;

	// Fondo y Bomberman
	private Image fondo;
	private Image bombermanImageWhite;
	private Image bombermanImageBlack;

	private GamePanel gamePanel;
	private GelaxkaBista[][] gelaxkaBistak;

	public JokoaBista(String laberintoMota, String bombermanMota) {
		setTitle("Bomberman");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		// Inicializar laberinto y bomberman
		laberintoHasieratu(laberintoMota);
		bombermanHasieratu(bombermanMota);
		irudiakKargatu();

		// Crear el panel de juego
		gamePanel = new GamePanel();
		gamePanel.setLayout(new GridLayout(laberinto.getFilas(), laberinto.getColumnas()));
		gamePanel.setPreferredSize(new Dimension(laberinto.getColumnas() * cellSize, laberinto.getFilas() * cellSize));
		gelaxkaBistak = new GelaxkaBista[laberinto.getFilas()][laberinto.getColumnas()];

		// Crear la vista de cada celda del laberinto
		for (int i = 0; i < laberinto.getFilas(); i++) {
			for (int j = 0; j < laberinto.getColumnas(); j++) {
				Bloke bloke = laberinto.getBloke(j, i);
				GelaxkaBista gelaxkaBista;
				if (bloke != null) {
					// Para un bloque, creamos un GelaxkaBista con el bloque
					gelaxkaBista = new GelaxkaBista(bloke);
				} else {
					// En caso de que no haya bloque (vacío), lo tratamos como un camino
					gelaxkaBista = new GelaxkaBista(null); // Crea una celda vacía
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

	private void laberintoHasieratu(String laberintoTipo) {
		switch (laberintoTipo) {
		case "Classic":
			this.laberinto = new ClassicLaberinto();
			fondo = new ImageIcon(getClass().getResource("/img/stageBack1.png")).getImage();
			break;
		case "Soft":
			this.laberinto = new SoftLaberinto();
			fondo = new ImageIcon(getClass().getResource("/img/stageBack3.png")).getImage();
			break;
		case "Empty":
			this.laberinto = new EmptyLaberinto();
			fondo = new ImageIcon(getClass().getResource("/img/stageBack2.png")).getImage();
			break;
		default:
			throw new IllegalArgumentException("Tipo de laberinto no válido");
		}

		laberinto.generarLaberinto();
	}

	private void bombermanHasieratu(String bombermanMota) {
		bomberman = bombermanMota.equals("White") ? new WhiteBomber() : new BlackBomber();
		bomberman.setLaberinto(laberinto);
	}

	private void irudiakKargatu() {
		bombermanImageWhite = new ImageIcon(getClass().getResource("/img/whitefront1.png")).getImage();
		bombermanImageBlack = new ImageIcon(getClass().getResource("/img/blackfront1.png")).getImage();
	}

	@Override
	public void update(Observable o, Object arg) {
		gamePanel.repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP -> bomberman.mugituGora();
		case KeyEvent.VK_DOWN -> bomberman.mugituBehera();
		case KeyEvent.VK_LEFT -> bomberman.mugituEzkerra();
		case KeyEvent.VK_RIGHT -> bomberman.mugituEskuma();
		}
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
			Image bombermanImage = (bomberman instanceof WhiteBomber) ? bombermanImageWhite : bombermanImageBlack;
			g.drawImage(bombermanImage, bomberman.getXPixel(), bomberman.getYPixel(), cellSize, cellSize, this);
		}
	}
}
