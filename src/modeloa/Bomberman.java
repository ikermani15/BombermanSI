package modeloa;

import java.awt.*;

import javax.swing.ImageIcon;
import java.util.Timer;
import java.util.TimerTask;

public abstract class Bomberman {
	private static Bomberman nBomber;
    private int x, y; // Matrizeko posizioa
    private final int cellSize = 40; // Gelaxka bakoitzaren tamaina
    private int bombaKop; // Bomba kop
    private int radioExplosion; // Bomba eztanda radioa
    private String bomberMota;
    protected Laberinto laberinto; // Laberinto erreferentzia talka lortzeko
    protected ImageIcon bombermanIrudia;
    protected int denbRegenBomba = 3; // Bombak erregeneratzeko denbora
    private boolean regenBomba = false;
    private Timer timer;

    public Bomberman(int bombaKop, int radioExplosion, String tipo) {
        this.x = 0; // (0, 0)-n hasieratu
        this.y = 0;
        this.bombaKop = bombaKop;
        this.radioExplosion = radioExplosion;
        this.bomberMota = tipo;
    }
    
    public static Bomberman getBomberman() {
    	if(nBomber == null) {
    		nBomber = new WhiteBomber(); // Defektuz
    	}
    	
    	return nBomber;
    }
    
    public void sortuBomberman(String mota) {
        if ("White".equals(mota)) {
        	nBomber =  new WhiteBomber();
        } else if ("Black".equals(mota)) {
        	nBomber =  new BlackBomber();
        }
        
        nBomber.setLaberinto(Laberinto.getLaberinto()); // Relacionar con el laberinto
    }
    
    public void setLaberinto(Laberinto laberinto) {
        this.laberinto = laberinto;
    }    

    // Mugimendu metodoak, talka konprobatuz
    public void mugituGora() { 
    	mugituPosible(x, y - 1); 
    }
    public void mugituBehera() { 
    	mugituPosible(x, y + 1); 
    }
    public void mugituEzkerra() { 
    	mugituPosible(x - 1, y); 
    }
    public void mugituEskuma() { 
    	mugituPosible(x + 1, y); 
    }
    
    // Mugitu al den konprobatu
    private void mugituPosible(int newX, int newY) {
        if (laberinto != null) {
            int filas = laberinto.getFilas();
            int columnas = laberinto.getColumnas();

            // Limiteen barruan dagoen konprobatu (matrize barruan)
            if (newX >= 0 && newX < columnas && newY >= 0 && newY < filas) {
                Gelaxka unekoa = laberinto.getGelaxka(x, y);
                Gelaxka berria = laberinto.getGelaxka(newX, newY);

                // Gelaxka hutsa bada (bidea) eta bombarik ez dago
                if (berria.hutsikDago() && !berria.bombaDago()) { 
                    unekoa.kenduBomberman(); // Bomberman gelaxka zaharra kendu
                    // Posizio berria ezarri
                    this.x = newX;
                    this.y = newY;
                    berria.gehituBomberman(this); // Bomberman gelaxka berrian ezarri
                }
            }
        }
    }

    // Bomba jartzeko
    public void bombaJarri() {
        if (bombaKop > 0) {
            System.out.println("Bomberman bomba ezarri du pos (" + y + ", " + x + ")");
            
            // Bomberman motaren arabera, bomba desberdina sortu
            Bomba bomba;
            if (bomberMota.equals("Black")) {
                bomba = new UltraBomba(x, y, laberinto); // UltraBomba radio 20
            } else {
                bomba = new DefaultBomba(x, y, laberinto); // DefaultBomba radio 1
            }
            
            bomba.bombaTimer();
            
            // Bomba gelaxkan ezarri
            Gelaxka unekoa = laberinto.getGelaxka(x, y);
            unekoa.gehituBomba(bomba);
            
            bombaKop--;
            
         // Bombarik ez baditu eta ez dago erregeneratzen, timer-a hasi 3 segundo ondoren bomba bat gehitzeko
        } else if (bombaKop == 0 && !regenAktibo()) {
        	bombaRegeneratu();            
        }
    }
    
    // Bombak gehitzeko behin amaituta
    private void bombaRegeneratu() {
    	regenBomba = true;
        System.out.println("Bomba regeneratzen...");
        timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                bombaKop++;
                regenBomba = false; // Berriro bomba barik geratzen bada, erregeneratu ahal izateko
                System.out.println("Bomba bat erregeneratu da, oraingo kopurua: " + bombaKop);
            }
        };
        timer.schedule(timerTask, denbRegenBomba * 1000);
    }
    
    // Bombak erregeneratzen dagoen konprobatu
    public boolean regenAktibo() {
        return regenBomba;
    }

    public ImageIcon getIrudia() {
        return bombermanIrudia;
    }
    
    // Posizioa lortu
    public int getXPixel() { 
    	return this.x * cellSize; 
    }
    public int getYPixel() { 
    	return this.y * cellSize; 
    }

    public int getBombaKop() {
        return bombaKop;
    }

    public int getRadioExplosion() {
        return radioExplosion;
    }
}
