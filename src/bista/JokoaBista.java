package bista;

import javax.swing.*;
import modeloa.*;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

public class JokoaBista extends JPanel implements Observer {
	private static final long serialVersionUID = 1L;
	private Laberinto laberinto;
	private Bomberman bomberman;
	private final int cellSize = 40;

	// Irudiak
	private Image fondo;
	private Image[] softImages;
	private Image[] hardImages;
	private Image bombermanImageWhite;
	private Image bombermanImageBlack;

	public JokoaBista(String laberintoTipo, String bombermanTipo) {
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

		// Generar el laberinto con sus bloques correctamente
		this.laberinto.generarLaberinto();
		this.laberinto.addObserver(this);

		// Cargar imágenes de bloques
		cargarImagenes();

		// Crear Bomberman según el tipo seleccionado
		if (bombermanTipo.equals("White")) {
			bomberman = new WhiteBomber();
		} else {
			bomberman = new BlackBomber();
		}

		setPreferredSize(new Dimension(laberinto.getColumnas() * cellSize, laberinto.getFilas() * cellSize));
	}

	private void cargarImagenes() {
		// Cargar imágenes de bloques suaves (Soft)
		softImages = new Image[] { new ImageIcon(getClass().getResource("/img/soft1.png")).getImage(),
				new ImageIcon(getClass().getResource("/img/soft2.png")).getImage(),
				new ImageIcon(getClass().getResource("/img/soft3.png")).getImage(),
				new ImageIcon(getClass().getResource("/img/soft4.png")).getImage(),
				new ImageIcon(getClass().getResource("/img/soft41.png")).getImage(),
				new ImageIcon(getClass().getResource("/img/soft42.png")).getImage(),
				new ImageIcon(getClass().getResource("/img/soft43.png")).getImage(),
				new ImageIcon(getClass().getResource("/img/soft44.png")).getImage(),
				new ImageIcon(getClass().getResource("/img/soft45.png")).getImage(),
				new ImageIcon(getClass().getResource("/img/soft46.png")).getImage() };

		// Cargar imágenes de bloques duros (Hard)
		hardImages = new Image[] { new ImageIcon(getClass().getResource("/img/hard1.png")).getImage(),
				new ImageIcon(getClass().getResource("/img/hard2.png")).getImage(),
				new ImageIcon(getClass().getResource("/img/hard3.png")).getImage(),
				new ImageIcon(getClass().getResource("/img/hard4.png")).getImage() };

		// Cargar imágenes de Bomberman
		bombermanImageWhite = new ImageIcon(getClass().getResource("/img/whitefront1.png")).getImage();
		bombermanImageBlack = new ImageIcon(getClass().getResource("/img/blackfront1.png")).getImage();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Dibujar el fondo
		g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);

		Random rand = new Random();

		for (int i = 0; i < laberinto.getFilas(); i++) {
			for (int j = 0; j < laberinto.getColumnas(); j++) {
				int tipoCelda = laberinto.getTipoDeCelda(j, i);

				if (tipoCelda == Laberinto.HARD) {
					// Elegir aleatoriamente una imagen de Hard
					Image hardBlock = hardImages[rand.nextInt(hardImages.length)];
					g.drawImage(hardBlock, j * cellSize, i * cellSize, cellSize, cellSize, this);
				} else if (tipoCelda == Laberinto.SOFT) {
					// Elegir aleatoriamente una imagen de Soft
					Image softBlock = softImages[rand.nextInt(softImages.length)];
					g.drawImage(softBlock, j * cellSize, i * cellSize, cellSize, cellSize, this);
				}
			}
		}

		// Dibujar a Bomberman con su imagen
		if (bomberman instanceof WhiteBomber) {
			g.drawImage(bombermanImageWhite, bomberman.getXPixel(), bomberman.getYPixel(), cellSize, cellSize, this);
		} else if (bomberman instanceof BlackBomber) {
			g.drawImage(bombermanImageBlack, bomberman.getXPixel(), bomberman.getYPixel(), cellSize, cellSize, this);
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		repaint();
	}
}
