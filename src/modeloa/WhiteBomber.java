package modeloa;

import javax.swing.ImageIcon;

public class WhiteBomber extends Bomberman {
    public WhiteBomber() {
    	super(10, 1, "White"); // 10 bombas y radio de explosión 1
    	bombermanIrudia = new ImageIcon(getClass().getResource("/img/whitefront1.png"));
    }
}
