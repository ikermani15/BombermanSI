package bista;

import javax.swing.*;
import modeloa.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

public class JokoaBista extends JFrame implements Observer, KeyListener {
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
	private Image[][] imagesGelaxka;

	// Jokorako panela
	private JPanel gamePanel;

	public JokoaBista(String laberintoMota, String bombermanMota) {
		setTitle("Bomberman");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		// Laberintoa hasieratu
		laberintoHasieratu(laberintoMota);
		irudiakKargatu();
		gelaxkakHasieratu();

		// Bomberman hasieratu
		bombermanHasieratu(bombermanMota);

		// Leihoaren tamaina lortu
		int leihoWide = laberinto.getColumnas() * cellSize;
		int leihoHeight = laberinto.getFilas() * cellSize;

		// Jokoaren panela sortu
		gamePanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				dibujarJuego(g);
			}
		};

		// Leihoaren tamaina
		gamePanel.setPreferredSize(new Dimension(leihoWide, leihoHeight));
		gamePanel.setFocusable(true);
		gamePanel.addKeyListener(this);

		// JFrame-ean panela gehitu
		add(gamePanel);
		pack();
		setLocationRelativeTo(null); // Pantaila erdian erakutsi
		setVisible(true);

		this.laberinto.generarLaberinto(); // Blokeak ezartzeko
		this.laberinto.addObserver(this); // Laberintoa aldatzen bada eguneratu

		// Irudiak kargatu
		irudiakKargatu();

		// Gelaxka bakoitzak izango duen irudia ezarri
		imagesGelaxka = new Image[laberinto.getFilas()][laberinto.getColumnas()];
		Random rand = new Random();
		for (int i = 0; i < laberinto.getFilas(); i++) {
			for (int j = 0; j < laberinto.getColumnas(); j++) {
				int gelaxkaMota = laberinto.getGelaxkaMota(j, i);
				// Gelaxka motaren arabera irudia ezarri
				if (gelaxkaMota == Laberinto.HARD) {
					imagesGelaxka[i][j] = hardImages[rand.nextInt(hardImages.length)];
				} else if (gelaxkaMota == Laberinto.SOFT) {
					imagesGelaxka[i][j] = softImages[rand.nextInt(softImages.length)];
				}
			}
		}

		// Bomberman sortu motaren arabera
		if (bombermanMota.equals("White")) {
			bomberman = new WhiteBomber();
		} else {
			bomberman = new BlackBomber();
		}

		bomberman.setLaberinto(laberinto); // Asignar laberinto a Bomberman
		bomberman.addObserver(this); // Bomberman mugitzean eguneratzeko

		setPreferredSize(new Dimension(laberinto.getColumnas() * cellSize, laberinto.getFilas() * cellSize));
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
		laberinto.addObserver(this);
	}

	private void bombermanHasieratu(String bombermanMota) {
		bomberman = bombermanMota.equals("White") ? new WhiteBomber() : new BlackBomber();
		bomberman.setLaberinto(laberinto);
		bomberman.addObserver(this);
	}

	private void gelaxkakHasieratu() {
		imagesGelaxka = new Image[laberinto.getFilas()][laberinto.getColumnas()];
		Random rand = new Random();

		for (int i = 0; i < laberinto.getFilas(); i++) {
			for (int j = 0; j < laberinto.getColumnas(); j++) {
				int gelaxkaMota = laberinto.getGelaxkaMota(j, i);

				if (gelaxkaMota == Laberinto.HARD) {
					imagesGelaxka[i][j] = hardImages[rand.nextInt(hardImages.length)];
				} else if (gelaxkaMota == Laberinto.SOFT) {
					imagesGelaxka[i][j] = softImages[rand.nextInt(softImages.length)];
				}
			}
		}
	}

	private void irudiakKargatu() {
		// Bloke bigunen irudiak kargatu
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

		// Bloke gogorren irudiak kargatu
		hardImages = new Image[] { new ImageIcon(getClass().getResource("/img/hard1.png")).getImage(),
				new ImageIcon(getClass().getResource("/img/hard2.png")).getImage(),
				new ImageIcon(getClass().getResource("/img/hard3.png")).getImage(),
				new ImageIcon(getClass().getResource("/img/hard4.png")).getImage() };

		// Bomberman irudiak kargatu
		bombermanImageWhite = new ImageIcon(getClass().getResource("/img/whitefront1.png")).getImage();
		bombermanImageBlack = new ImageIcon(getClass().getResource("/img/blackfront1.png")).getImage();
	}

	// Bloke eta Bomberman irudiak bistan
	private void dibujarJuego(Graphics g) {
		g.drawImage(fondo, 0, 0, gamePanel.getWidth(), gamePanel.getHeight(), this);

		for (int i = 0; i < laberinto.getFilas(); i++) {
			for (int j = 0; j < laberinto.getColumnas(); j++) {
				int gelaxkaMota = laberinto.getGelaxkaMota(j, i);
				if (gelaxkaMota == Laberinto.HARD || gelaxkaMota == Laberinto.SOFT) {
					g.drawImage(imagesGelaxka[i][j], j * cellSize, i * cellSize, cellSize, cellSize, this);
				}
			}
		}

		if (bomberman instanceof WhiteBomber) {
			g.drawImage(bombermanImageWhite, bomberman.getXPixel(), bomberman.getYPixel(), cellSize, cellSize, this);
		} else if (bomberman instanceof BlackBomber) {
			g.drawImage(bombermanImageBlack, bomberman.getXPixel(), bomberman.getYPixel(), cellSize, cellSize, this);
		}
	}

	// Observable-etan aldaketak egotean, irudia eguneratu
	@Override
	public void update(Observable o, Object arg) {
		gamePanel.repaint();
	}

	// Teklen mugimendua kontrolatzeko
	public void keyPressed(KeyEvent e) {
		if (bomberman instanceof Bomberman) {
			Bomberman player = bomberman;
			switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
				player.mugituGora();
				break;
			case KeyEvent.VK_DOWN:
				player.mugituBehera();
				break;
			case KeyEvent.VK_LEFT:
				player.mugituEzkerra();
				break;
			case KeyEvent.VK_RIGHT:
				player.mugituEskuma();
				break;
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
}
