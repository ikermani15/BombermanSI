package bista;

import java.awt.Dimension;
import java.awt.Image;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;
import modeloa.Gelaxka;

public class GelaxkaBista extends JLabel implements Observer {
	private static final long serialVersionUID = 1L;
	private Gelaxka gelaxka;
	private Timer bombaTimer;
	private static final int cellSize = 40;

	public GelaxkaBista(Gelaxka gelaxka) {
		this.gelaxka = gelaxka;
		setPreferredSize(new Dimension(cellSize, cellSize));
		gelaxkaIrudi();

		// GelaxkaBista Gelaxka Observer-a izango da
		gelaxka.addObserver(this);
	}

	// Gelaxka bakoitzari irudiak ezarri
	private void gelaxkaIrudi() {
		if (gelaxka != null) {
			if (gelaxka.getBomberman() != null) { // Bomberman dago
				ImageIcon bomberImg = gelaxka.getBomberman().getIrudia();
				if (bomberImg != null) {
					Image img = bomberImg.getImage().getScaledInstance(cellSize, cellSize, Image.SCALE_SMOOTH);
					setIcon(new ImageIcon(img));
				}
			} else if (gelaxka.getBloke() != null) {
				setIcon(gelaxka.getBloke().getBlokeIrudia()); // Blokearen irudia
			} else {
				setIcon(null); // Gelaxka hutsa
			}
		}
	}

	// Gelaxketan gertatzen diren aldaketak
	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof String) {
			String event = (String) arg;

			if (event.equals("mugitu")) {
				System.out.println("Update mugitu!");
				eguneratuBomberman();
			} else if (event.equals("apurtu")) {
				System.out.println("Update apurtu!");
				apurtuBlokea();
			} else if (event.equals("bomba")) {
				System.out.println("Update bomba!");
				bombaJarri();
			}
		}
	}

	// Bomberman gelaxkan eguneratu
	private void eguneratuBomberman() {
		if (gelaxka != null && gelaxka.getBomberman() != null) {
			ImageIcon bomberImg = gelaxka.getBomberman().getIrudia();
			if (bomberImg != null) {
				Image img = bomberImg.getImage().getScaledInstance(cellSize, cellSize, Image.SCALE_SMOOTH);
				setIcon(new ImageIcon(img));
			}
			// Bomba baldin badago
		} else if (gelaxka.getBomba() != null) {
			ImageIcon bombaImg = gelaxka.getBomba().getBombaIrudia();
			// Irudia ezarri
			if (bombaImg != null) {
				Image img = bombaImg.getImage().getScaledInstance(cellSize, cellSize, Image.SCALE_SMOOTH);
				setIcon(new ImageIcon(img));
			}
		} else {
			setIcon(null); // Gelaxka hutsa jartzeko
		}
	}

	// Blokea apurtzean
	private void apurtuBlokea() {
		setIcon(null);
	}

	// Bomba jartzean
	private void bombaJarri() {
		if (gelaxka.getBomba() != null) { // Bomba badago
			ImageIcon bombaImg = gelaxka.getBomba().getBombaIrudia();
			if (bombaImg != null) {
				Image img = bombaImg.getImage().getScaledInstance(cellSize, cellSize, Image.SCALE_SMOOTH);
				setIcon(new ImageIcon(img));
			}
		}

		// Bombako timer-a
		Timer newTimer = new Timer(3000, e -> {
			setIcon(null); // Bomba irudia kendu
		});

		newTimer.start();
	}
}
