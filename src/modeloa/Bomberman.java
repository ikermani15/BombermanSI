package modeloa;

import java.util.Timer;
import java.util.TimerTask;

public abstract class Bomberman {
	private static Bomberman nBomber;
    private int x, y; // Matrizeko posizioa
    private int bombaKop; // Bomba kop
    private String bomberMota;
    private int denbRegenBomba = 3; // Bombak erregeneratzeko denbora
    private boolean regenBomba = false;
    private Timer timer;

    public Bomberman(int bombaKop, String mota) {
        this.bombaKop = bombaKop;
        this.bomberMota = mota;
    }
    
    public static Bomberman getBomberman() {
    	return nBomber;
    }
    
    public static void sortuBomberman(String mota) {
        if ("White".equals(mota)) {
        	nBomber =  new WhiteBomber();
        } else if ("Black".equals(mota)) {
        	nBomber =  new BlackBomber();
        }
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
        if (Laberinto.getLaberinto() != null) {

            // Limiteen barruan dagoen konprobatu (matrize barruan)
            if (newX >= 0 && newX < Laberinto.getLaberinto().getZutabeak() && newY >= 0 && newY < Laberinto.getLaberinto().getIlarak()) {
                Gelaxka unekoa = Laberinto.getLaberinto().getGelaxka(x, y);
                Gelaxka berria = Laberinto.getLaberinto().getGelaxka(newX, newY);

                // Gelaxka hutsa bada (bidea) eta bombarik ez dago
                if (berria.hutsikDago() && !berria.bombaDago()) { 
                    unekoa.kenduBomberman(); // Bomberman gelaxka zaharra kendu
                    // Posizio berria ezarri
                    this.x = newX;
                    this.y = newY;
                    berria.gehituBomberman(); // Bomberman gelaxka berrian ezarri
                    
                    // Posizio berrian sua baldin badago
                    if(berria.suaDago()) {
                    	berria.galdu();
                    }
                }
            }
        }
    }

    // Bomba jartzeko
    public void bombaJarri() {
        if (bombaKop > 0) {
            System.out.println("Bomba ezarri da pos (" + y + ", " + x + ")");
            
            // Bomberman motaren arabera, bomba desberdina sortu
            Bomba bomba;
            if (bomberMota.equals("Black")) {
                bomba = new UltraBomba(x, y); // UltraBomba radio 20
            } else {
                bomba = new DefaultBomba(x, y); // DefaultBomba radio 1
            }
            
            bomba.bombaTimer();
            
            // Bomba gelaxkan ezarri
            Gelaxka unekoa = Laberinto.getLaberinto().getGelaxka(x, y);
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

    public int getBombaKop() {
        return bombaKop;
    }
}
