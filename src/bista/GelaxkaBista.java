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
	private static final int cellSize = 40;

	public GelaxkaBista(Gelaxka gelaxka) {
		setPreferredSize(new Dimension(cellSize, cellSize));
	}

	// Gelaxketan gertatzen diren aldaketak
	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof String) {
			String event = (String) arg;

			if (event.equals("blokeBigunaSortu")) {
				blokeBigunaSortu();
			} else if (event.equals("blokeGogorraSortu")) {
				blokeGogorraSortu();

			} else if (event.equals("gehituWhiteBomberman")) {
				gehituWhiteBomberman();
			} else if (event.equals("goraWhiteBomberman")) {
				goraWhiteBomberman();
			} else if (event.equals("beheraWhiteBomberman")) {
				beheraWhiteBomberman();
			} else if (event.equals("ezkerraWhiteBomberman")) {
				ezkerraWhiteBomberman();
			} else if (event.equals("eskumaWhiteBomberman")) {
				eskumaWhiteBomberman();

			} else if (event.equals("gehituBlackBomberman")) {
				gehituBlackBomberman();
			} else if (event.equals("goraBlackBomberman")) {
				goraBlackBomberman();
			} else if (event.equals("beheraBlackBomberman")) {
				beheraBlackBomberman();
			} else if (event.equals("ezkerraBlackBomberman")) {
				ezkerraBlackBomberman();
			} else if (event.equals("eskumaBlackBomberman")) {
				eskumaBlackBomberman();

			} else if (event.equals("bombaJarri")) {
				bombaJarri();
			} else if (event.equals("suaJarri")) {
				suaJarri();

			} else if (event.equals("gehituEtsaia")) {
				gehituEtsaia();

			} else if (event.equals("kendu")) {
				kenduIrudia();
			}
		}
	}

	private void gehituEtsaia() {
		ImageIcon[] hardImages = { new ImageIcon(getClass().getResource("/img/baloon1.png")),
				new ImageIcon(getClass().getResource("/img/baloon2.png")) };
		setIcon(hardImages[new Random().nextInt(hardImages.length)]);
	}

	private void blokeBigunaSortu() {
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
		ImageIcon[] hardImages = { new ImageIcon(getClass().getResource("/img/hard1.png")),
				new ImageIcon(getClass().getResource("/img/hard2.png")),
				new ImageIcon(getClass().getResource("/img/hard3.png")),
				new ImageIcon(getClass().getResource("/img/hard4.png")) };
		setIcon(hardImages[new Random().nextInt(hardImages.length)]);
	}

	private void gehituWhiteBomberman() {
		setScaledIcon("/img/whitefront1.png");
	}

	private void goraWhiteBomberman() {
		String[] gora = { "/img/whiteup1.png", "/img/whiteup2.png", "/img/whiteup3.png", "/img/whiteup4.png",
				"/img/whiteup5.png" };
		setRandomScaledIcon(gora);
	}

	private void beheraWhiteBomberman() {
		String[] behera = { "/img/whitedown1.png", "/img/whitedown2.png", "/img/whitedown3.png",
				"/img/whitedown4.png" };
		setRandomScaledIcon(behera);
	}

	private void ezkerraWhiteBomberman() {
		String[] ezkerra = { "/img/whiteleft1.png", "/img/whiteleft2.png", "/img/whiteleft3.png", "/img/whiteleft4.png",
				"/img/whiteleft5.png" };
		setRandomScaledIcon(ezkerra);
	}

	private void eskumaWhiteBomberman() {
		String[] eskuma = { "/img/whiteright1.png", "/img/whiteright2.png", "/img/whiteright3.png",
				"/img/whiteright4.png", "/img/whiteright5.png" };
		setRandomScaledIcon(eskuma);
	}

	private void gehituBlackBomberman() {
		setScaledIcon("/img/blackfront1.png");
	}

	private void goraBlackBomberman() {
		String[] goraB = { "/img/blackup1.png", "/img/blackup2.png", "/img/blackup3.png", "/img/blackup4.png",
				"/img/blackup5.png" };
		setRandomScaledIcon(goraB);
	}

	private void beheraBlackBomberman() {
		String[] beheraB = { "/img/blackdown1.png", "/img/blackdown2.png", "/img/blackdown3.png",
				"/img/blackdown4.png" };
		setRandomScaledIcon(beheraB);
	}

	private void ezkerraBlackBomberman() {
		String[] ezkerraB = { "/img/blackleft1.png", "/img/blackleft2.png", "/img/blackleft3.png",
				"/img/blackleft4.png", "/img/blackleft5.png" };
		setRandomScaledIcon(ezkerraB);
	}

	private void eskumaBlackBomberman() {
		String[] ezkerraB = { "/img/blackright1.png", "/img/blackright2.png", "/img/blackright3.png",
				"/img/blackright4.png", "/img/blackright5.png" };
		setRandomScaledIcon(ezkerraB);
	}

	private void bombaJarri() {
		setScaledIcon("/img/bomb1.png");
	}

	private void suaJarri() {
		setScaledIcon("/img/kaBomb4.png");
	}

	private void kenduIrudia() {
		setIcon(null);
	}

	// Irudi bakarra eskalatzeko (gelaxka tamainara ezartzeko)
	private void setScaledIcon(String path) {
		ImageIcon icon = new ImageIcon(getClass().getResource(path));
		Image img = icon.getImage().getScaledInstance(cellSize, cellSize, Image.SCALE_SMOOTH);
		setIcon(new ImageIcon(img));
	}

	// Irudia eskalatu eta random bat aukeratu
	private void setRandomScaledIcon(String[] paths) {
		String selectedPath = paths[new Random().nextInt(paths.length)];
		setScaledIcon(selectedPath);
	}

}
