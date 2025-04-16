package bista;

import javax.swing.*;

import modeloa.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
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
		Jokoa.getJokoa().getLaberinto().addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof String) {
			String event = (String) arg;

			if (event.equals("sortuClassic")) {
				ImageIcon f = new ImageIcon(getClass().getResource("/img/stageBack1.png"));
				Image fondoLab = f.getImage();
				laberintoSortu(fondoLab);
			} else if (event.equals("sortuSoft")) {
				ImageIcon f = new ImageIcon(getClass().getResource("/img/stageBack3.png"));
				Image fondoLab = f.getImage();
				laberintoSortu(fondoLab);
			} else if (event.equals("sortuEmpty")) {
				ImageIcon f = new ImageIcon(getClass().getResource("/img/stageBack2.png"));
				Image fondoLab = f.getImage();
				laberintoSortu(fondoLab);
			}
		}
	}

	private void laberintoSortu(Image fondoLab) {
		Image fondo = fondoLab;

		// Laberinto panela
		laberintoPanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
			}
		};

		// Gelaxkak ondo ezartzeko GridLayout
		laberintoPanel.setLayout(new GridLayout(Jokoa.getJokoa().getLaberinto().getIlarak(),
				Jokoa.getJokoa().getLaberinto().getZutabeak()));
		laberintoPanel.setPreferredSize(new Dimension(Jokoa.getJokoa().getLaberinto().getZutabeak() * cellSize,
				Jokoa.getJokoa().getLaberinto().getIlarak() * cellSize));

		// Laberintoko gelaxka bakoitzerako GelaxkaBista sortu
		for (int i = 0; i < Jokoa.getJokoa().getLaberinto().getIlarak(); i++) {
			for (int j = 0; j < Jokoa.getJokoa().getLaberinto().getZutabeak(); j++) {
				Gelaxka gelaxka = Jokoa.getJokoa().getLaberinto().getGelaxka(j, i);
				GelaxkaBista gb = new GelaxkaBista(gelaxka);
				gelaxka.addObserver(gb); // Gelaxkari Observer-a gehitu
				laberintoPanel.add(gb);
			}
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
			Bomberman bomberman = Jokoa.getJokoa().getBomberman();

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
