package modeloa;

import java.util.Timer;
import java.util.TimerTask;

public abstract class Etsaia {
    protected int x, y;
    private final int cellSize = 40;
    private Timer mugTimer;
    private String mota;

    public Etsaia(int x, int y, String mota) {
        this.x = x;
        this.y = y;
        this.mota = mota;
    }
    
    public String getEtsaiMota() { return mota; }
    
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

    protected boolean mugituPosible(int newX, int newY) {
        if (newX >= 0 && newX < Jokoa.getJokoa().getLaberinto().getZutabeak() &&
            newY >= 0 && newY < Jokoa.getJokoa().getLaberinto().getIlarak()) {

            Gelaxka unekoa = Jokoa.getJokoa().getLaberinto().getGelaxka(x, y);
            Gelaxka berria = Jokoa.getJokoa().getLaberinto().getGelaxka(newX, newY);

            if (berria.hutsikDago() && !berria.bombaDago() && !berria.etsaiaDago()) {
                unekoa.kenduEtsaia();
                this.x = newX;
                this.y = newY;
                
             // Motaren arabera gelaxka berrian etsaia gehitu
                if(getEtsaiMota().equals("Normala")) {
                	berria.gehituEtsaiNormala();
                } else {
                	berria.gehituEtsaiBerezia();;
                }

                if (berria.bombermanDago()) {
                    Jokoa.getJokoa().amaituJokoa(false);
                } else if (berria.suaDago()) {
                    hil();
                    berria.kenduEtsaia();
                    Jokoa.getJokoa().getLaberinto().kenduEtsaiKop();
                    System.out.println("Etsaia hil da pos (" + y + ", " + x + ")");
                	System.out.println("Etsai kopuru totala: " + Jokoa.getJokoa().getLaberinto().getEtsaiKop());
                	
                    if (Jokoa.getJokoa().getLaberinto().getEtsaiKop() == 0) {
                        Jokoa.getJokoa().amaituJokoa(true);
                    }
                }
                return true;
            }
        }
        return false;
    }
    
    public abstract void mugituAleatorio();

    public int getX() { return x; }
    public int getY() { return y; }
    public int getXPixel() { return x * cellSize; }
    public int getYPixel() { return y * cellSize; }
}
