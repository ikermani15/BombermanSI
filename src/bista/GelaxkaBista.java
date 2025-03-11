package bista;

import java.util.Observable;
import java.util.Observer;

import javax.swing.*;
import modeloa.Bloke;

public class GelaxkaBista extends JLabel implements Observer {
	private static final long serialVersionUID = 1L;
	private Bloke bloke;
	private boolean sutan = false;

	public GelaxkaBista(Bloke bloke) {
		this.bloke = bloke;
		eguneratuIrudia(); // Irudia kargatu

		// GelaxkaBista Blokearen Observer-a izango da
		if (bloke != null) {
			bloke.addObserver(this);
		}
	}

	// Blokeak eguneratzeko
	private void eguneratuIrudia() {
		if (bloke != null) {
			setIcon(bloke.getBlokeIrudia()); // Blokearen irudia erabili
		} else {
			setIcon(null); // Gelaxka hutsa blokea apurtu baldin bada
		}
	}

	// Gelaxketan gertatzen diren aldaketak
	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof String) {
			String evento = (String) arg;

			switch (evento) {
			case "apurtu":
				this.bloke = null; // Blokea ezabatu
				eguneratuIrudia(); // Blokearen irudia eguneratu
				break;
			case "eztanda":
				suaIrudia();
				break;
			}
		}
	}

	private void suaIrudia() {
		setIcon(new ImageIcon(getClass().getResource("/img/kaBomb3.png")));
		sutan = true;

		// 2 segundo ondoren amata
		new Timer(2000, e -> {
			sutan = false;
			eguneratuIrudia();
		}).start();
	}
}
