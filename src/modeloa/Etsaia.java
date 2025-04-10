package modeloa;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Etsaia {
    private int x, y;
    private final int cellSize = 40;
    private Random random = new Random();
    private Timer mugTimer;

    public Etsaia(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public void abiaraziEtsaia() {
    	mugTimer = new Timer();
        TimerTask mugimenduTask = new TimerTask() {
            @Override
            public void run() {
                mugituAleatorio();
            }
        };
        mugTimer.scheduleAtFixedRate(mugimenduTask, 0, 1000); // Mugitu segundoro
    }
    
    public void hil() {
    	if (mugTimer != null) {
            mugTimer.cancel();
        }
    }

    public void mugituAleatorio() {
        int mug = random.nextInt(4);
        switch (mug) {
            case 0: mugituPosible(x, y - 1); break; // gora
            case 1: mugituPosible(x, y + 1); break; // behera
            case 2: mugituPosible(x - 1, y); break; // ezker
            case 3: mugituPosible(x + 1, y); break; // eskuin
        }
    }

    private void mugituPosible(int newX, int newY) {
        Laberinto laberinto = Laberinto.getLaberinto();

        if (newX >= 0 && newX < laberinto.getZutabeak() && newY >= 0 && newY < laberinto.getIlarak()) {
            Gelaxka unekoa = laberinto.getGelaxka(x, y);
            Gelaxka berria = laberinto.getGelaxka(newX, newY);

            if (berria.hutsikDago() && !berria.bombaDago() && !berria.etsaiaDago()) { // Ostoporik dagoen konprobatu
                unekoa.kenduEtsaia();
                this.x = newX;
                this.y = newY;
                berria.gehituEtsaia();
                
                if(berria.bombermanDago()) { // Jokalaria arrapatzen badu
                	berria.galdu();
                } else if (berria.suaDago()) { // Eztandak etsaia arrapatzen badu
                	hil();
                	berria.kenduEtsaia();
                	Laberinto.getLaberinto().kenduEtsaiKop();
                	System.out.println("Etsaia hil da pos (" + y + ", " + x + ")");
                	System.out.println("Etsai kopuru totala: " + Laberinto.getLaberinto().getEtsaiKop());
                	
                	// BlokeBigun guztiak apurtu edo etsai guztiak eliminatuz gero irabazi
                    if (Laberinto.getLaberinto().getEtsaiKop() == 0) {
                        berria.irabazi();
                    }
                }
            }
        }
    }

    public int getX() { return x; }
    public int getY() { return y; }

    public int getXPixel() { return x * cellSize; }
    public int getYPixel() { return y * cellSize; }
}
