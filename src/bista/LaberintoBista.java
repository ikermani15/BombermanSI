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

		ImageIcon f = new ImageIcon(getClass().getResource("/img/stageBack1.png"));
		Image fondo = f.getImage();

		// Laberinto panela
		laberintoPanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
			}
		};

		// Gelaxkak ondo ezartzeko GridLayout
		laberintoPanel.setLayout(new GridLayout(laberinto.getIlarak(), laberinto.getZutabeak()));
		laberintoPanel
				.setPreferredSize(new Dimension(laberinto.getZutabeak() * cellSize, laberinto.getIlarak() * cellSize));

		// Laberintoko gelaxka bakoitzerako GelaxkaBista sortu
		for (int i = 0; i < laberinto.getIlarak(); i++) {
			for (int j = 0; j < laberinto.getZutabeak(); j++) {
				Gelaxka gelaxka = laberinto.getGelaxka(j, i);
				GelaxkaBista gelaxkaBista = new GelaxkaBista(gelaxka);
				laberintoPanel.add(gelaxkaBista);

				if (gelaxka.getBloke() != null) {
					gelaxka.gehituBloke();
				} else if (gelaxka.getEtsaia() != null) {
					gelaxka.gehituEtsaia();
				}
			}
		}
		String bombermanMota = Bomberman.getBomberman().getMota();
		if ("White".equals(bombermanMota)) {
			laberinto.getGelaxka(0, 0).gehituWhiteBomberman();
		} else if ("Black".equals(bombermanMota)) {
			laberinto.getGelaxka(0, 0).gehituBlackBomberman();
		}

		add(laberintoPanel, BorderLayout.CENTER);
		laberintoPanel.setFocusable(true);

		// Kontrolatzailea ezarri teklaturako
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
			Bomberman bomberman = Bomberman.getBomberman();

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
