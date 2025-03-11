package modeloa;

import javax.swing.ImageIcon;

public class WhiteBomber extends Bomberman {
    public WhiteBomber() {
    	super(10, 1, "White"); // 10 bomba eta Radio = 1
    	bombermanIrudia = new ImageIcon(getClass().getResource("/img/whitefront1.png"));
    }
}
