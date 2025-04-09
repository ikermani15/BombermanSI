package modeloa;

import java.util.Observable;

public class Gelaxka extends Observable {
    private Bloke bloke;
    private Bomberman bomberman;
    private Bomba bomba;
    private Etsaia etsai;
    private boolean etsaiaDago;
    private final int x, y;
    private boolean bombermanDago;
    private boolean suaDago = false;
    private int suaKop = 0;

    public Gelaxka(int x, int y, Bloke bloke) {
        this.x = x;
        this.y = y;
        this.bloke = bloke;
    }
    
    // Gelaxka atazak
    public boolean hutsikDago() {
        return bloke == null;
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    
    // Etsaien atazak
    public Etsaia getEtsaia() {
    	return this.etsai;
    }
    
    public void sortuEtsaia(Etsaia etsai) {
    	this.etsai = etsai;
    }
    
    public void gehituEtsaia() {
    	this.etsaiaDago = true;
        setChanged();
        notifyObservers("gehituEtsaia");
    }
    
    public void kenduEtsaia() {
    	this.etsai = null;
    	this.etsaiaDago = false;
    	setChanged();
    	notifyObservers("kenduEtsaia");
    }
    
    // Gelaxkan Etsaia dago
    public boolean etsaiaDago() {
        return this.etsaiaDago;
    }
    
    
    // Bomberman atazak
    public Bomberman getBomberman() {
    	return bomberman;
    }
    
    // Gelaxkan Bomberman ezarri eta bista notifikatu
    public void gehituBomberman() {
        this.bomberman = Bomberman.getBomberman();
        this.bombermanDago = true;
        setChanged();
        notifyObservers("gehituBomberman");
    }

    // Gelaxkan Bomberman dago
    public boolean bombermanDago() {
        return this.bombermanDago;
    }
    
    // Bomberman gelaxka berrira mugitu
    public void kenduBomberman() {
        this.bomberman = null;
        this.bombermanDago = false;
        setChanged();
        
        if (bomba != null) {
            notifyObservers("bombaJarri");  // Bomba jarri baldin badu
        } else {
            notifyObservers("kenduBomberman");
        }
    }

    
    // Blokeen atazak
    public Bloke getBloke() {
        return bloke;
    }
    
    public void gehituBloke() {
    	setChanged();
    	if(bloke.apurtuDaiteke()) {
    		notifyObservers("blokeBigunaSortu");
    	} else {
    		notifyObservers("blokeGogorraSortu");	
    	}
    }

    // Blokea apurtzean bista notifikatu
    public void setBloke(Bloke bloke) {
        this.bloke = bloke;
        setChanged();
        notifyObservers("apurtu");
    }
    
    
    // Bomben atazak
    public Bomba getBomba() {
    	return bomba;
    }
    
    // Bomba gehitzean bista notifikatu
    public void gehituBomba(Bomba bomba) {
    	this.bomba = bomba;
        setChanged();
        notifyObservers("bombaJarri");
    }
    
    // Bomba eztanda egitean, gelaxkatik kendu
    public void kenduBomba() {
        if (this.bomba != null) {
            this.bomba = null;
        }
        setChanged();
        notifyObservers("bombaKendu");
    }
    
    // Eztanda egitean, bista notifikatu
    public void suaJarri() {
    	suaKop++;
    	this.suaDago = true;
    	setChanged();
        notifyObservers("suaJarri");
    }
    
    // Eztanda amaitzean, bista notifikatu
    public void suaKendu() {
    	// Hainbat bomba aldi berean jartzean, ezin dugu sua beti kendu
    	if (suaKop > 0) {
            suaKop--; 
        }
        
        if (suaKop == 0) { // Sua kendu gelaxkan su-rik ez dagoenean
            this.suaDago = false;
            setChanged();
            notifyObservers("suaKendu");
        }
    }
    
    // Bomba aktibo dagoen konprobatu
    public boolean bombaDago() {
        return this.bomba != null && this.bomba.bombaAktiboDago();
    }
    
    // Gelaxkan Sua dago
    public boolean suaDago() {
        return this.suaDago;
    }
    
    
    // BlokeBiguna-rik ez dagoenean
    public void irabazi() {
    	System.out.println("WIN!");
    	System.exit(1);
    }
    
    // Eztanda radio barruan egonez gero
    public void galdu() {
    	System.out.println("GAME OVER!");
    	System.exit(1);
    }


}
