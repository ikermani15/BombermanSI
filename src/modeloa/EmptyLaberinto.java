package modeloa;

import java.util.Random;
import java.util.function.*;

public class EmptyLaberinto extends Laberinto {
    public static EmptyLaberinto nEL;
    private int normalEtsaiKop = 0;

    public EmptyLaberinto() {
        laberintoaSortu();
    }

    public static EmptyLaberinto getEmpty() {
        if (nEL == null) {
            nEL = new EmptyLaberinto();
        }
        return nEL;
    }

    private void laberintoaSortu() {
        Random rand = new Random();
        EtsaiaFactory eFac = EtsaiaFactory.getEtsaiaFactory();

        Predicate<Integer> etsaiaGehitu = prob -> prob > 95;
        Supplier<String> etsaiaMota = () -> {
            if (normalEtsaiKop == 2) {
                normalEtsaiKop = 0;
                return "Berezia";
            } else {
                normalEtsaiKop++;
                return "Normala";
            }
        };

        for (int i = 0; i < getIlarak(); i++) {
            for (int j = 0; j < getZutabeak(); j++) {
                Etsaia etsaia = null;
                int prob = rand.nextInt(100);

                if (etsaiaGehitu.test(prob) && getEtsaiKop() < 10) {
                    String mota = etsaiaMota.get();
                    etsaia = eFac.createEtsaia(j, i, mota);
                    gehituEtsaiKop();
                }

                gelaxka[i][j] = new Gelaxka(j, i, null);
                if (etsaia != null) {
                    gelaxka[i][j].sortuEtsaia(etsaia);
                }
            }
        }

        System.out.println("Etsai kopuru totala: " + getEtsaiKop());
    }
}
