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

		// GelaxkaBista Gelaxka Observer-a izango da
		gelaxka.addObserver(this);
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

			} else if (event.equals("kenduEtsaia")) {
				kenduEtsaia();
			} else if (event.equals("gehituEtsaia")) {
				gehituEtsaia();
			}
		}
	}

	// Gelaxkan etsaia gehitu
	private void gehituEtsaia() {
		ImageIcon[] hardImages = { new ImageIcon(getClass().getResource("/img/baloon1.png")),
				new ImageIcon(getClass().getResource("/img/baloon2.png")) };
		setIcon(hardImages[new Random().nextInt(hardImages.length)]);
	}

	// Gelaxkatik etsaia kendu
	private void kenduEtsaia() {
		setIcon(null);
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

	// WhiteBomber gelaxkan gehitu
	private void gehituWhiteBomberman() {
		ImageIcon bomberImg = new ImageIcon(getClass().getResource("/img/whitefront1.png"));
		Image img = bomberImg.getImage().getScaledInstance(cellSize, cellSize, Image.SCALE_SMOOTH);
		setIcon(new ImageIcon(img));
	}

	private void goraWhiteBomberman() {
		ImageIcon[] bomberImages = { new ImageIcon(getClass().getResource("/img/whiteup1.png")),
				new ImageIcon(getClass().getResource("/img/whiteup2.png")),
				new ImageIcon(getClass().getResource("/img/whiteup3.png")),
				new ImageIcon(getClass().getResource("/img/whiteup4.png")),
				new ImageIcon(getClass().getResource("/img/whiteup5.png")) };
		setIcon(bomberImages[new Random().nextInt(bomberImages.length)]);
	}

	private void beheraWhiteBomberman() {
		ImageIcon[] bomberImages = { new ImageIcon(getClass().getResource("/img/whitedown1.png")),
				new ImageIcon(getClass().getResource("/img/whitedown2.png")),
				new ImageIcon(getClass().getResource("/img/whitedown3.png")),
				new ImageIcon(getClass().getResource("/img/whitedown4.png")) };
		setIcon(bomberImages[new Random().nextInt(bomberImages.length)]);
	}

	private void ezkerraWhiteBomberman() {
		ImageIcon[] bomberImages = { new ImageIcon(getClass().getResource("/img/whiteleft1.png")),
				new ImageIcon(getClass().getResource("/img/whiteleft2.png")),
				new ImageIcon(getClass().getResource("/img/whiteleft3.png")),
				new ImageIcon(getClass().getResource("/img/whiteleft4.png")),
				new ImageIcon(getClass().getResource("/img/whiteleft5.png")) };
		setIcon(bomberImages[new Random().nextInt(bomberImages.length)]);
	}

	private void eskumaWhiteBomberman() {
		ImageIcon[] bomberImages = { new ImageIcon(getClass().getResource("/img/whiteright1.png")),
				new ImageIcon(getClass().getResource("/img/whiteright2.png")),
				new ImageIcon(getClass().getResource("/img/whiteright3.png")),
				new ImageIcon(getClass().getResource("/img/whiteright4.png")),
				new ImageIcon(getClass().getResource("/img/whiteright5.png")) };
		setIcon(bomberImages[new Random().nextInt(bomberImages.length)]);
	}

	// BlackBomber gelaxkan gehitu
	private void gehituBlackBomberman() {
		ImageIcon bomberImg = new ImageIcon(getClass().getResource("/img/blackfront1.png"));
		Image img = bomberImg.getImage().getScaledInstance(cellSize, cellSize, Image.SCALE_SMOOTH);
		setIcon(new ImageIcon(img));
	}

	private void goraBlackBomberman() {
		ImageIcon[] bomberImages = { new ImageIcon(getClass().getResource("/img/blackup1.png")),
				new ImageIcon(getClass().getResource("/img/blackup2.png")),
				new ImageIcon(getClass().getResource("/img/blackup3.png")),
				new ImageIcon(getClass().getResource("/img/blackup4.png")),
				new ImageIcon(getClass().getResource("/img/blackup5.png")) };
		setIcon(bomberImages[new Random().nextInt(bomberImages.length)]);
	}

	private void beheraBlackBomberman() {
		ImageIcon[] bomberImages = { new ImageIcon(getClass().getResource("/img/blackdown1.png")),
				new ImageIcon(getClass().getResource("/img/blackdown2.png")),
				new ImageIcon(getClass().getResource("/img/blackdown3.png")),
				new ImageIcon(getClass().getResource("/img/blackdown4.png")) };
		setIcon(bomberImages[new Random().nextInt(bomberImages.length)]);
	}

	private void ezkerraBlackBomberman() {
		ImageIcon[] bomberImages = { new ImageIcon(getClass().getResource("/img/blackleft1.png")),
				new ImageIcon(getClass().getResource("/img/blackleft2.png")),
				new ImageIcon(getClass().getResource("/img/blackleft3.png")),
				new ImageIcon(getClass().getResource("/img/blackleft4.png")),
				new ImageIcon(getClass().getResource("/img/blackleft5.png")) };
		setIcon(bomberImages[new Random().nextInt(bomberImages.length)]);
	}

	private void eskumaBlackBomberman() {
		ImageIcon[] bomberImages = { new ImageIcon(getClass().getResource("/img/blackright1.png")),
				new ImageIcon(getClass().getResource("/img/blackright2.png")),
				new ImageIcon(getClass().getResource("/img/blackright3.png")),
				new ImageIcon(getClass().getResource("/img/blackright4.png")),
				new ImageIcon(getClass().getResource("/img/blackright5.png")) };
		setIcon(bomberImages[new Random().nextInt(bomberImages.length)]);
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
