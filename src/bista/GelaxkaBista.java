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
	private static final int cellSize = 40;

	public GelaxkaBista(Gelaxka gelaxka) {
		this.gelaxka = gelaxka;
		setPreferredSize(new Dimension(cellSize, cellSize));
		gelaxkaIrudi();

		// GelaxkaBista Gelaxka Observer-a izango da
		gelaxka.addObserver(this);
	}

	// ESTO EN EL UPDATE MEJOR, LLAMAR ALGO COMO "SORTU" PARA QUE AL CREARSE SE
	// ASIGNEN LAS IMAGENES
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
				eguneratuBomberman();
			} else if (event.equals("apurtu")) {
				System.out.println("Update apurtu!");
				apurtuBlokea();
			} else if (event.equals("bombaJarri")) {
				System.out.println("Update bombaJarri!");
				bombaJarri();
			} else if (event.equals("bombaKendu")) {
				System.out.println("Update bombaKendu!");
				bombaKendu();
			} else if (event.equals("suaJarri")) {
				suaJarri();
			} else if (event.equals("suaKendu")) {
				suaKendu();
			}
		}
	}

	// HOBETO BI METODOTAN BANATZEA, BOMBERMAN GEHITU/KENDU GELAXKA
	// Bomberman gelaxkan eguneratu
	private void eguneratuBomberman() {
		// Gelaxkan bomberman badago irudia ezarri
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
			// Ez badago, gelaxka hutsa ezarri
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
