package bista;

import java.awt.Dimension;
import java.awt.Image;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javax.swing.*;
import modeloa.Gelaxka;

public class GelaxkaBista extends JLabel implements Observer {
	private static final long serialVersionUID = 1L;
	private Gelaxka gelaxka;
	private static final int cellSize = 40;

	public GelaxkaBista(Gelaxka gelaxka) {
		this.gelaxka = gelaxka;
		setPreferredSize(new Dimension(cellSize, cellSize));

		// GelaxkaBista Gelaxka Observer-a izango da
		gelaxka.addObserver(this);

		gelaxkaIrudi();
	}

	// Gelaxka bakoitzari irudiak ezarri
	private void gelaxkaIrudi() {
		if (gelaxka.getBomberman() != null) { // Bomberman dago
			gehituBomberman();
		} else if (gelaxka.getBloke() != null) {
			if (gelaxka.getBloke().apurtuDaiteke()) {
				blokeBigunaSortu();
			} else {
				blokeGogorraSortu();
			}
		} else {
			setIcon(null); // Gelaxka hutsa
		}
	}

	// Gelaxketan gertatzen diren aldaketak
	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof String) {
			String event = (String) arg;

			if (event.equals("blokeBigunaSortu")) {
				System.out.println("Sortu BIGUN deitu");
				blokeBigunaSortu();
			} else if (event.equals("blokeGogorraSortu")) {
				System.out.println("Sortu GOGOR deitu");
				blokeGogorraSortu();
			} else if (event.equals("gehituBomberman")) {
				System.out.println("BOMBERMAN");
				gehituBomberman();
			} else if (event.equals("kenduBomberman")) {
				kenduBomberman();
			} else if (event.equals("apurtu")) {
				apurtuBlokea();
			} else if (event.equals("bombaJarri")) {
				bombaJarri();
			} else if (event.equals("bombaKendu")) {
				bombaKendu();
			} else if (event.equals("suaJarri")) {
				suaJarri();
			} else if (event.equals("suaKendu")) {
				suaKendu();
			}
		}
	}

	private void blokeBigunaSortu() {
		System.out.println("Sortu BIGUNA deitu");
		ImageIcon[] softImages = { new ImageIcon(getClass().getResource("/img/soft1.png")),
				new ImageIcon(getClass().getResource("/img/soft2.png")),
				new ImageIcon(getClass().getResource("/img/soft3.png")),
				new ImageIcon(getClass().getResource("/img/soft4.png")),
				new ImageIcon(getClass().getResource("/img/soft41.png")),
				new ImageIcon(getClass().getResource("/img/soft42.png")),
				new ImageIcon(getClass().getResource("/img/soft43.png")),
				new ImageIcon(getClass().getResource("/img/soft44.png")),
				new ImageIcon(getClass().getResource("/img/soft45.png")),
				new ImageIcon(getClass().getResource("/img/soft46.png")) };
		setIcon(softImages[new Random().nextInt(softImages.length)]);
	}

	private void blokeGogorraSortu() {
		System.out.println("Sortu GOGORRA deitu");
		ImageIcon[] hardImages = { new ImageIcon(getClass().getResource("/img/hard1.png")),
				new ImageIcon(getClass().getResource("/img/hard2.png")),
				new ImageIcon(getClass().getResource("/img/hard3.png")),
				new ImageIcon(getClass().getResource("/img/hard4.png")) };
		setIcon(hardImages[new Random().nextInt(hardImages.length)]);
	}

	// Bomberman gelaxkan gehitu
	private void gehituBomberman() {
		ImageIcon bomberImg = new ImageIcon(getClass().getResource("/img/whitefront1.png"));
		Image img = bomberImg.getImage().getScaledInstance(cellSize, cellSize, Image.SCALE_SMOOTH);
		setIcon(new ImageIcon(img));
	}

	// Bomberman gelaxkan gehitu
	private void kenduBomberman() {
		setIcon(null);
	}

	// Blokea apurtzean
	private void apurtuBlokea() {
		setIcon(null);
	}

	// Bomba jartzean
	private void bombaJarri() {
		ImageIcon bombaImg = new ImageIcon(getClass().getResource("/img/bomb1.png"));
		Image img = bombaImg.getImage().getScaledInstance(cellSize, cellSize, Image.SCALE_SMOOTH);
		setIcon(new ImageIcon(img));
	}

	// Eztanda egin ondoren bomba kendu
	private void bombaKendu() {
		setIcon(null);
	}

	// Eztanda egitean
	private void suaJarri() {
		ImageIcon suaImg = new ImageIcon(getClass().getResource("/img/kaBomb4.png"));
		Image img = suaImg.getImage().getScaledInstance(cellSize, cellSize, Image.SCALE_SMOOTH);
		setIcon(new ImageIcon(img));
	}

	// Sua kendu
	private void suaKendu() {
		setIcon(null);
	}
}
