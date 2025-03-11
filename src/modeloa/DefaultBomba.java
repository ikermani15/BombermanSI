package modeloa;

import javax.swing.ImageIcon;

public class DefaultBomba extends Bomba {
    public DefaultBomba(int x, int y, Laberinto laberinto) {
        super(x, y, 1, laberinto); // Radio 1 bomba normala
    }

    @Override
    public void explotar() {
        super.explotar(); // Usamos el método de la clase base
    }
}
