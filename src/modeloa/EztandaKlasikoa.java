package modeloa;

public class EztandaKlasikoa implements EztandaStrategy {

    @Override
    public void eztanda(Bomba bomba) {
        int x = bomba.getX();
        int y = bomba.getY();

        System.out.println("Eztanda KLASIKO pos (" + y + ", " + x + ")");

        bomba.kenduBombaEtaEztanda(x, y);
        eztandaPos(bomba, x, y);
        eztandaPos(bomba, x - 1, y);
        eztandaPos(bomba, x + 1, y);
        eztandaPos(bomba, x, y - 1);
        eztandaPos(bomba, x, y + 1);
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
                System.out.println("Etsaia hil da pos (" + y + ", " + x + ")");
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

        kenduSuaPos(x, y);
        kenduSuaPos(x - 1, y);
        kenduSuaPos(x + 1, y);
        kenduSuaPos(x, y - 1);
        kenduSuaPos(x, y + 1);
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
