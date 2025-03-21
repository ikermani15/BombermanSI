package modeloa;

import javax.swing.ImageIcon;

public class DefaultBomba extends Bomba {
    public DefaultBomba(int x, int y, Laberinto laberinto) {
        super(x, y, 20, laberinto); // Radio = 1, bomba normala
        bombImg = new ImageIcon(getClass().getResource("/img/bomb1.png"));
    }

    @Override
    public void eztanda() {
        super.eztanda();
    }
}
