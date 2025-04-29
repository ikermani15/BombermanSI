package modeloa;

import java.util.Random;

public class EtsaiNormala extends Etsaia {
    private Random random = new Random();

    public EtsaiNormala(int x, int y) {
        super(x, y, "Normala");
    }

    @Override
    public void mugituAleatorio() {
        Gelaxka unekoa = Jokoa.getJokoa().getLaberinto().getGelaxka(x, y);
        if (unekoa != null && unekoa.suaDago()) {
            hil();
            unekoa.kenduEtsaia();
            Jokoa.getJokoa().getLaberinto().kenduEtsaiKop();
            System.out.println("Etsaia hil da pos (" + y + ", " + x + ")");
        	System.out.println("Etsai kopuru totala: " + Jokoa.getJokoa().getLaberinto().getEtsaiKop());
        	
            if (Jokoa.getJokoa().getLaberinto().getEtsaiKop() == 0) {
                Jokoa.getJokoa().amaituJokoa(true);
            }
            return;
        }

        int mug = random.nextInt(4);
        switch (mug) {
            case 0: mugituPosible(x, y - 1); break;
            case 1: mugituPosible(x, y + 1); break;
            case 2: mugituPosible(x - 1, y); break;
            case 3: mugituPosible(x + 1, y); break;
        }
    }
}
