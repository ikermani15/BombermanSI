package bista;

import javax.swing.*;
import modeloa.Bloke;

public class GelaxkaBista extends JLabel {
	private static final long serialVersionUID = 1L;
	private Bloke bloke;

	public GelaxkaBista(Bloke bloke) {
		this.bloke = bloke;
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
}
