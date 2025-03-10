package modeloa;

import javax.swing.ImageIcon;

public class BlackBomber extends Bomberman {
    public BlackBomber() {
        super(1, 20, "Black"); // 1 bomba ultrabomba y radio de explosión 20
        bombermanIrudia = new ImageIcon(getClass().getResource("/img/blackfront1.png"));
    }
}
