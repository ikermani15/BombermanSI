package bista;

import javax.swing.*;
import modeloa.Bloke;
import java.util.Observable;
import java.util.Observer;

public class GelaxkaBista extends JLabel implements Observer {
	private static final long serialVersionUID = 1L;
	private Bloke bloke;

	public GelaxkaBista(Bloke bloke) {
		this.bloke = bloke;
		if (bloke != null) {
			bloke.addObserver(this);
		}
		eguneratuIrudia(); // Irudia kargatu
	}

	// Blokeak eguneratzeko
	private void eguneratuIrudia() {
		if (bloke != null) {
			setIcon(bloke.getBlokeIrudia()); // Blokearen irudia erabili
		} else {
			setIcon(null); // Gelaxka hutsa blokea apurtu baldin bada
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof Bloke) {
			bloke = (Bloke) o;
			eguneratuIrudia();
		}
	}
}
