package modeloa;

import modeloa.*;

import java.awt.event.KeyEvent;

import bista.*;

public class Jokoa {
    private MainBista mainBista;
    private LaberintoBista jokoaBista;
    private Laberinto laberinto;
    private Bomberman bomberman;

    public Jokoa() {
        mainBista = new MainBista(this);
        mainBista.setVisible(true);
    }

    // Jokoa hasieratzen du
    public void hasiJokoa(String laberintoMota, String bombermanMota) {
    	// Laberinto mota lortu eta sortu
        laberinto = laberintoSortu(laberintoMota);
        laberinto.generarLaberinto();
        // Bomberman mota lortu eta laberintoan gehitu
        bomberman = bombermanSortu(bombermanMota);
        bomberman.setLaberinto(laberinto);
        
        // Bistara instantziak pasatu
        jokoaBista = new LaberintoBista(laberinto, bomberman, this);
    }

    // Laberintoa sortu
    private Laberinto laberintoSortu(String mota) {
        return switch (mota) {
            case "Classic" -> new ClassicLaberinto();
            case "Soft" -> new SoftLaberinto();
            case "Empty" -> new EmptyLaberinto();
            default -> throw new IllegalArgumentException("Laberinto mota ez da zuzena.");
        };
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
        switch (keyCode) {
            case KeyEvent.VK_UP -> bomberman.mugituGora();
            case KeyEvent.VK_DOWN -> bomberman.mugituBehera();
            case KeyEvent.VK_LEFT -> bomberman.mugituEzkerra();
            case KeyEvent.VK_RIGHT -> bomberman.mugituEskuma();
        }
    }
}
