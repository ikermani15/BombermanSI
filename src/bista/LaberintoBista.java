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

	private JPanel laberintoPanel; // Panel principal que contendrá las celdas
	private Kontrolatzaile kontrolatzaile; // Para manejar las entradas de teclado

	public LaberintoBista(Laberinto laberinto, Bomberman bomberman) {
		this.laberinto = laberinto;
		this.bomberman = bomberman;

		setTitle("Bomberman");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		// Cargar fondo
		fondo = laberinto.getFondo().getImage();

		// Cargar imagen de Bomberman
		bombermanImage = bomberman.getIrudia().getImage();

		// Crear el panel de celdas del laberinto
		laberintoPanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);

				// Dibujar el fondo
				g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);

				// Dibujar Bomberman
				g.drawImage(bombermanImage, bomberman.getXPixel(), bomberman.getYPixel(), cellSize, cellSize, this);
			}
		};
		laberintoPanel.setLayout(new GridLayout(laberinto.getFilas(), laberinto.getColumnas()));

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

		// Configurar la ventana
		add(laberintoPanel, BorderLayout.CENTER);

		// Configurar el panel de control
		this.kontrolatzaile = new Kontrolatzaile();
		laberintoPanel.setFocusable(true);
		laberintoPanel.addKeyListener(kontrolatzaile);

		pack();
		setLocationRelativeTo(null);
		setVisible(true);

		// Registrar la vista como observador de Laberinto y Bomberman
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
			case "bomba":
				laberintoPanel.repaint(); // Solo repinta lo necesario
				break;
			}
		}
	}

	// Clase interna para manejar las entradas del teclado
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
