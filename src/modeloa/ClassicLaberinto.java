package modeloa;

import java.util.Random;
import java.util.function.*;

public class ClassicLaberinto extends Laberinto {
    public static ClassicLaberinto nCL;
    private int normalEtsaiKop = 0;

    public ClassicLaberinto() {
        laberintoaSortu();
    }

    public static ClassicLaberinto getClassic() {
        if (nCL == null) {
            nCL = new ClassicLaberinto();
        }
        return nCL;
    }

    private void laberintoaSortu() {
        Random rand = new Random();
        BlokeFactory bFac = BlokeFactory.getBlokeFactory();
        EtsaiaFactory eFac = EtsaiaFactory.getEtsaiaFactory();

        Predicate<int[]> hasieraPos = pos ->
                (pos[0] == 0 && pos[1] == 0) ||
                (pos[0] == 1 && pos[1] == 0) ||
                (pos[0] == 0 && pos[1] == 1);

        BiPredicate<Integer, Integer> gogorraPos = (i, j) -> i % 2 != 0 && j % 2 != 0;

        BiFunction<Integer, Integer, Bloke> sortuGogorra = (i, j) -> bFac.createBloke(i, j, false);
        BiFunction<Integer, Integer, Bloke> sortuBiguna = (j, i) -> bFac.createBloke(j, i, true);

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

                int[] pos = {i, j};

                if (hasieraPos.test(pos)) {
                    // Gelaxka hutsak
                    bloke = null;
                } else if (gogorraPos.test(i, j)) {
                    bloke = sortuGogorra.apply(i, j);
                } else {
                    int prob = rand.nextInt(100);
                    if (prob > 40) {
                        bloke = sortuBiguna.apply(j, i);
                        gehituBlokeBigunKop();
                    } else {
                        int prob2 = rand.nextInt(100);
                        if (prob2 > 90 && getEtsaiKop() < 6) {
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
