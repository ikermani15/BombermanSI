package modeloa;

import java.util.Timer;
import java.util.TimerTask;

public class Bomberman {
	private static Bomberman nBomber;
    private int x, y; // Matrizeko posizioa
    private int bombaKop; // Bomba kop
    private int denbRegenBomba = 3; // Bombak erregeneratzeko denbora
    private boolean regenBomba = false;
    private Timer timer;
    private String mota;

    public Bomberman(int bombaKop, String mota) {
        this.bombaKop = bombaKop;
        this.mota = mota;
    }
    
    public static Bomberman getBomberman() { return nBomber; }
    public String getMota() { return this.mota; }
    public Bomba sortuBomba(int x, int y) {  return new DefaultBomba(x, y, 1); } // Defektuz normala
    public boolean regenAktibo() { return regenBomba; } // Bombak erregeneratzen dagoen konprobatu
    public int getBombaKop() { return bombaKop; }
    
    public static Bomberman sortuBomberman(String mota) {
    	BombermanFactory factory = BombermanFactory.getBombermanFactory();
        nBomber = factory.createBomberman(mota);
        return nBomber;
    }
    
    public void setPosizioa(int x, int y) {
    	this.x = x;
    	this.y = y;
    }
    
    // Mugimendu metodoak, talka konprobatuz
    public void mugituGora() { mugituPosible(x, y - 1, "gora"); }
    public void mugituBehera() { mugituPosible(x, y + 1, "behera"); }
    public void mugituEzkerra() { mugituPosible(x - 1, y, "ezkerra"); }
    public void mugituEskuma() { mugituPosible(x + 1, y, "eskuma"); }
    
    // Mugitu al den konprobatu
    private void mugituPosible(int newX, int newY, String norabidea) {
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
                    
                    // Motaren arabera gelaxka berrian bomberman gehitu
                    if(nBomber.getMota().equals("White")) {
                    	berria.gehituWhiteBomberman(norabidea);
                    } else {
                    	berria.gehituBlackBomberman(norabidea);
                    }
                    
                    // Posizio berrian sua baldin badago
                    if(berria.suaDago() || berria.etsaiaDago()) {
                    	Laberinto.getLaberinto().galdu();
                    }
                }
            }
        }
    }

    // Bomba jartzeko
    public void bombaJarri() {
        if (bombaKop > 0) {
            //System.out.println("Bomba ezarri da pos (" + y + ", " + x + ")");
            // Bomberman motaren arabera, bomba desberdina sortu
            Bomba bomba = sortuBomba(x, y);
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
    
}
