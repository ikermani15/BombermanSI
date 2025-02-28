package kontrolatzaile;

import modeloa.*;

import java.awt.event.KeyEvent;

import bista.*;

public class JokoaKontrolatzaile {
    private MainBista mainBista;
    private JokoaBista jokoaBista;
    private Laberinto laberinto;
    private Bomberman bomberman;

    public JokoaKontrolatzaile() {
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
        jokoaBista = new JokoaBista(laberinto, bomberman, this);
        
        // Observer-rak gehitu
        laberinto.addObserver(jokoaBista);
        bomberman.addObserver(jokoaBista);
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
