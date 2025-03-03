package bista;

import javax.swing.*;

import modeloa.Bloke;
import modeloa.BlokeBiguna;
import modeloa.BlokeGogorra;
import modeloa.Laberinto;

import java.awt.Image;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

public class GelaxkaBista extends JLabel implements Observer {
	private static final long serialVersionUID = 1L;
	private Bloke bloke;
	private ImageIcon[] softImages;
	private ImageIcon[] hardImages;

	public GelaxkaBista(Bloke bloke) {
		this.bloke = bloke;
		if (bloke != null) {
			bloke.addObserver(this); // Blokeari Observer-a gehitu
		}
		kargatuIrudiak();
		eguneratuIrudia();
	}

	private void kargatuIrudiak() {
		// Bloke bigunen irudiak kargatu
		softImages = new ImageIcon[] { new ImageIcon(getClass().getResource("/img/soft1.png")),
				new ImageIcon(getClass().getResource("/img/soft2.png")),
				new ImageIcon(getClass().getResource("/img/soft3.png")),
				new ImageIcon(getClass().getResource("/img/soft4.png")),
				new ImageIcon(getClass().getResource("/img/soft41.png")),
				new ImageIcon(getClass().getResource("/img/soft42.png")),
				new ImageIcon(getClass().getResource("/img/soft43.png")),
				new ImageIcon(getClass().getResource("/img/soft44.png")),
				new ImageIcon(getClass().getResource("/img/soft45.png")),
				new ImageIcon(getClass().getResource("/img/soft46.png")) };

		// Bloke gogorren irudiak kargatu
		hardImages = new ImageIcon[] { new ImageIcon(getClass().getResource("/img/hard1.png")),
				new ImageIcon(getClass().getResource("/img/hard2.png")),
				new ImageIcon(getClass().getResource("/img/hard3.png")),
				new ImageIcon(getClass().getResource("/img/hard4.png")) };
	}

	private void eguneratuIrudia() {
		if (bloke instanceof BlokeBiguna) {
			setIcon(softImages[new Random().nextInt(softImages.length)]);
		} else if (bloke instanceof BlokeGogorra) {
			setIcon(hardImages[new Random().nextInt(hardImages.length)]);
		} else {
			setIcon(null); // Blokerik ez badago, hutsik
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof Bloke) {
			eguneratuIrudia();
		}
	}
}
