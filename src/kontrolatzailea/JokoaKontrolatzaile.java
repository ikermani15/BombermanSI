package kontrolatzailea;

import bista.MainBista;

import java.awt.event.KeyEvent;

import bista.LaberintoBista;
import modeloa.*;

public class JokoaKontrolatzaile {
    private Laberinto laberinto;
    private Bomberman bomberman;
    private LaberintoBista laberintoBista;

    public JokoaKontrolatzaile() {
    }

    // Jokoa hasieratzen du
    public void hasiJokoa(String laberintoMota, String bombermanMota) {
    	// Laberinto mota lortu eta sortu
        laberinto = laberintoMota(laberintoMota);
        laberinto.laberintoaChanged();
        // Bomberman mota lortu eta laberintoan gehitu
        bomberman = bombermanSortu(bombermanMota);
        bomberman.setLaberinto(laberinto);

        // LaberintoBista hasieratu
        laberintoBista = new LaberintoBista(laberinto, bomberman, this);  // Vista del juego
    }

    // Laberintoa sortu
    private Laberinto laberintoMota(String mota) {
        if ("Classic".equals(mota)) {
            return new ClassicLaberinto();
        } else if ("Soft".equals(mota)) {
            return new SoftLaberinto();
        } else if ("Empty".equals(mota)) {
            return new EmptyLaberinto();
        } else {
            throw new IllegalArgumentException("Laberinto mota ez da zuzena.");
        }
    }

    // Bomberman sortu
    private Bomberman bombermanSortu(String mota) {
        return mota.equals("White") ? new WhiteBomber() : new BlackBomber();
    }
    // Posizioa lortu
    public int[] getBombermanPosition() {
        return new int[]{bomberman.getXPixel(), bomberman.getYPixel()};
    }
    
    // Teklatua kontrolatu
    public void teklaSakatu(int keyCode) {
        if (keyCode == KeyEvent.VK_UP) {
            bomberman.mugituGora();
        } else if (keyCode == KeyEvent.VK_DOWN) {
            bomberman.mugituBehera();
        } else if (keyCode == KeyEvent.VK_LEFT) {
            bomberman.mugituEzkerra();
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            bomberman.mugituEskuma();
        }else if (keyCode == KeyEvent.VK_SPACE) {
            bomberman.bombaJarri();
        }
    }
}
