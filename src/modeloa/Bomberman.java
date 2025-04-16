package modeloa;

import java.util.Timer;
import java.util.TimerTask;

public abstract class Bomberman {
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
    
    public String getBombermanMota() { return this.mota; }
    public Bomba sortuBomba(int x, int y) {  return new DefaultBomba(x, y, 1); } // Defektuz normala
    public boolean regenAktibo() { return regenBomba; } // Bombak erregeneratzen dagoen konprobatu
    public int getBombaKop() { return bombaKop; }
    
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
        if (Jokoa.getJokoa().getLaberinto() != null) {
            // Limiteen barruan dagoen konprobatu (matrize barruan)
            if (newX >= 0 && newX < Jokoa.getJokoa().getLaberinto().getZutabeak() && newY >= 0 && newY < Jokoa.getJokoa().getLaberinto().getIlarak()) {
                Gelaxka unekoa = Jokoa.getJokoa().getLaberinto().getGelaxka(x, y);
                Gelaxka berria = Jokoa.getJokoa().getLaberinto().getGelaxka(newX, newY);

                // Gelaxka hutsa bada (bidea) eta bombarik ez dago
                if (berria.hutsikDago() && !berria.bombaDago()) { 
                    unekoa.kenduBomberman(); // Bomberman gelaxka zaharra kendu
                    // Posizio berria ezarri
                    this.x = newX;
                    this.y = newY;
                    
                    // Motaren arabera gelaxka berrian bomberman gehitu
                    if(getBombermanMota().equals("White")) {
                    	berria.gehituWhiteBomberman(norabidea);
                    } else {
                    	berria.gehituBlackBomberman(norabidea);
                    }
                    
                    // Posizio berrian sua baldin badago
                    if(berria.suaDago() || berria.etsaiaDago()) {
                    	Jokoa.getJokoa().amaituJokoa(false);
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
            Gelaxka unekoa = Jokoa.getJokoa().getLaberinto().getGelaxka(x, y);
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
