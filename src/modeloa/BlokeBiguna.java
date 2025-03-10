package modeloa;

import javax.swing.ImageIcon;
import java.util.Random;

public class BlokeBiguna extends Bloke {
    private static final ImageIcon[] SOFT_IMAGES = {
            new ImageIcon(BlokeBiguna.class.getResource("/img/soft1.png")),
            new ImageIcon(BlokeBiguna.class.getResource("/img/soft2.png")),
            new ImageIcon(BlokeBiguna.class.getResource("/img/soft3.png")),
            new ImageIcon(BlokeBiguna.class.getResource("/img/soft4.png")),
            new ImageIcon(BlokeBiguna.class.getResource("/img/soft41.png")),
            new ImageIcon(BlokeBiguna.class.getResource("/img/soft42.png")),
            new ImageIcon(BlokeBiguna.class.getResource("/img/soft43.png")),
            new ImageIcon(BlokeBiguna.class.getResource("/img/soft44.png")),
            new ImageIcon(BlokeBiguna.class.getResource("/img/soft45.png")),
            new ImageIcon(BlokeBiguna.class.getResource("/img/soft46.png"))
    };

    public BlokeBiguna(int x, int y) {
        super(x, y);
        this.blokeIrudia = SOFT_IMAGES[new Random().nextInt(SOFT_IMAGES.length)];
    }

    @Override
    public boolean esDestructible() {
        return true;
    }
}
