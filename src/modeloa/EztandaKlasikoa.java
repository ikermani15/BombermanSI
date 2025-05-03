package modeloa;

import java.util.List;

public class EztandaKlasikoa implements EztandaStrategy {

	@Override
	public void eztanda(Bomba bomba) {
	    int x = bomba.getX();
	    int y = bomba.getY();

	    bomba.kenduBombaEtaEztanda(x, y);

	    // Java8 aplikatu norabidean eztanda egiteko
	    List<int[]> posizioak = List.of(
	        new int[]{x, y},
	        new int[]{x - 1, y},
	        new int[]{x + 1, y},
	        new int[]{x, y - 1},
	        new int[]{x, y + 1}
	    );

	    posizioak.forEach(p -> eztandaPos(bomba, p[0], p[1]));
	}

    @Override
    public boolean eztandaPos(Bomba bomba, int x, int y) {
        if (x < 0 || x >= Jokoa.getJokoa().getLaberinto().getZutabeak() ||
            y < 0 || y >= Jokoa.getJokoa().getLaberinto().getIlarak()) {
            return false;
        }

        Gelaxka gelaxka = Jokoa.getJokoa().getLaberinto().getGelaxka(x, y);
        Bloke bloke = gelaxka.getBloke();

        if (bloke != null && !bloke.apurtuDaiteke()) return false;

        if (bloke != null && bloke.apurtuDaiteke()) {
            gelaxka.setBloke(null);
            Jokoa.getJokoa().getLaberinto().kenduBlokeBigunKop();
        }

        if (gelaxka.etsaiaDago()) {
            Etsaia etsaia = gelaxka.getEtsaia();
            if (etsaia != null) {
                etsaia.hil();
                gelaxka.kenduEtsaia();
                Jokoa.getJokoa().getLaberinto().kenduEtsaiKop();
                if (Jokoa.getJokoa().getLaberinto().getEtsaiKop() == 0) {
                	Jokoa.getJokoa().amaituJokoa(true);
                }
            }
        }

        gelaxka.suaJarri();

        if (gelaxka.bombermanDago()) {
            Jokoa.getJokoa().amaituJokoa(false);
        }

        if (Jokoa.getJokoa().getLaberinto().getEtsaiKop() == 0) {
        	Jokoa.getJokoa().amaituJokoa(true);
        }

        return true;
    }

    @Override
    public void kenduSua(Bomba bomba) {
        int x = bomba.getX();
        int y = bomba.getY();

        // Java8 erabili suaren posizioetarako (eztandaren berdina aplikatu)
        List<int[]> posizioak = List.of(
            new int[]{x, y},
            new int[]{x - 1, y},
            new int[]{x + 1, y},
            new int[]{x, y - 1},
            new int[]{x, y + 1}
        );

        posizioak.forEach(p -> kenduSuaPos(p[0], p[1]));
    }

    private void kenduSuaPos(int x, int y) {
        if (x < 0 || x >= Jokoa.getJokoa().getLaberinto().getZutabeak() ||
            y < 0 || y >= Jokoa.getJokoa().getLaberinto().getIlarak()) return;

        Gelaxka gelaxka = Jokoa.getJokoa().getLaberinto().getGelaxka(x, y);
        if (gelaxka != null && gelaxka.getBloke() == null &&
            !gelaxka.etsaiaDago() && !gelaxka.bombermanDago() && !gelaxka.bombaDago()) {
            gelaxka.suaKendu();
        }
    }
}
