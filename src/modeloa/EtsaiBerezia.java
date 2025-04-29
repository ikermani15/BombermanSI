package modeloa;

import java.util.Random;

public class EtsaiBerezia extends Etsaia {
    private Random random = new Random();

    public EtsaiBerezia(int x, int y) {
        super(x, y, "Berezia");
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

        int[] norabidea = {0, 1, 2, 3};
        for (int i = 0; i < 4; i++) {
            int index = random.nextInt(4);
            int mug = norabidea[index];
            int newX = x, newY = y;
            switch (mug) {
                case 0: newY = y - 1; break;
                case 1: newY = y + 1; break;
                case 2: newX = x - 1; break;
                case 3: newX = x + 1; break;
            }
            Gelaxka berria = Jokoa.getJokoa().getLaberinto().getGelaxka(newX, newY);
            
            // Sua baldin badago, ez da mugitzen gelaxka horretara
            if (berria != null && !berria.suaDago()) {
                if (mugituPosible(newX, newY)) break;
            }
        }
    }
}

