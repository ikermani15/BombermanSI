package modeloa;

import javax.swing.ImageIcon;
import java.util.Random;

public class BlokeGogorra extends Bloke {
    private static final ImageIcon[] HARD_IMAGES = {
            new ImageIcon(BlokeGogorra.class.getResource("/img/hard1.png")),
            new ImageIcon(BlokeGogorra.class.getResource("/img/hard2.png")),
            new ImageIcon(BlokeGogorra.class.getResource("/img/hard3.png")),
            new ImageIcon(BlokeGogorra.class.getResource("/img/hard4.png"))
    };

    public BlokeGogorra(int x, int y) {
        super(x, y);
        this.blokeIrudia = HARD_IMAGES[new Random().nextInt(HARD_IMAGES.length)];
    }
}
