package kontrolatzailea;

import bista.MainBista;
import modeloa.Jokoa;

public class JokoaKontrolatzaile {
	private Jokoa jokoa;
    private MainBista mainBista;

    public JokoaKontrolatzaile() {
        this.jokoa = new Jokoa();
        this.mainBista = new MainBista(this);
        mainBista.setVisible(true);
    }

    public void hasiJokoa(String laberintoMota, String bombermanMota) {
        jokoa.hasiJokoa(laberintoMota, bombermanMota);
    }
}
