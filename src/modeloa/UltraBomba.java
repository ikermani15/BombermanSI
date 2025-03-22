package modeloa;

import javax.swing.ImageIcon;

public class UltraBomba extends Bomba{
	public UltraBomba(int x, int y) {
        super(x, y, 20); // Radio = 20
        bombImg = new ImageIcon(getClass().getResource("/img/bomb1.png"));
    }

	@Override
    public void eztanda() {
        super.eztanda();
    }
}
