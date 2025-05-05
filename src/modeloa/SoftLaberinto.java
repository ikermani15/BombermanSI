package modeloa;

import java.util.Random;
import java.util.function.*;

public class SoftLaberinto extends Laberinto {
    public static SoftLaberinto nFL;
    private int normalEtsaiKop = 0;

    public SoftLaberinto() {
        laberintoaSortu();
    }

    public static SoftLaberinto getSoft() {
        if (nFL == null) {
            nFL = new SoftLaberinto();
        }
        return nFL;
    }

    private void laberintoaSortu() {
        Random rand = new Random();
        BlokeFactory bFac = BlokeFactory.getBlokeFactory();
        EtsaiaFactory eFac = EtsaiaFactory.getEtsaiaFactory();

        Predicate<Integer> blokeGehitu = prob -> prob > 40;
        Predicate<Integer> etsaiaGehitu = prob -> prob > 90;
        Supplier<String> etsaiaMotaSupplier = () -> {
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
                Bloke bloke = null;
                Etsaia etsaia = null;

                if (!((i == 0 && j == 0) || (i == 1 && j == 0) || (i == 0 && j == 1))) {
                    int prob = rand.nextInt(100);
                    if (blokeGehitu.test(prob)) {
                        bloke = bFac.createBloke(j, i, true);
                        gehituBlokeBigunKop();
                    } else {
                        int prob2 = rand.nextInt(100);
                        if (etsaiaGehitu.test(prob2) && getEtsaiKop() < 8) {
                            String mota = etsaiaMotaSupplier.get();
                            etsaia = eFac.createEtsaia(j, i, mota);
                            gehituEtsaiKop();
                        }
                    }
                }

                Gelaxka g = new Gelaxka(j, i, bloke);
                if (etsaia != null) {
                    g.sortuEtsaia(etsaia);
                }
                gelaxka[i][j] = g;
            }
        }

        System.out.println("BlokeBigun totala: " + getBlokeBigunKop());
        System.out.println("Etsai kopuru totala: " + getEtsaiKop());
    }
}
