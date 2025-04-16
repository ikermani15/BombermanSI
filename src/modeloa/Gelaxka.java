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
    public boolean hutsikDago() { return bloke == null; }
    public int getX() {  return x; }
    public int getY() { return y;  }
    
    
    // Etsaien atazak
    public Etsaia getEtsaia() { return this.etsai; }
    public void sortuEtsaia(Etsaia etsai) { this.etsai = etsai; }
    public boolean etsaiaDago() { return this.etsaiaDago; }
    
    public void gehituEtsaia() {
    	this.etsaiaDago = true;
        setChanged();
        notifyObservers("gehituEtsaia");
    }
    
    public void kenduEtsaia() {
    	this.etsai = null;
    	this.etsaiaDago = false;
    	setChanged();
    	notifyObservers("kendu");
    }
    
    
    // Bomberman atazak
    public Bomberman getBomberman() { return bomberman; }
    public boolean bombermanDago() { return this.bombermanDago; }
    
    public void gehituBomberman(String mota) {
    	if(mota.equals("White")) {
    		gehituWhiteBomberman("gehitu");
    	} else {
    		gehituBlackBomberman("gehitu");
    	}
    }
    
    // Gelaxkan WhiteBomberm ezarri eta bista notifikatu
    public void gehituWhiteBomberman(String norabidea) {
        this.bomberman = Jokoa.getJokoa().getBomberman();
        this.bombermanDago = true;
        setChanged();
        notifyObservers(norabidea + "WhiteBomberman");
    }
    
    // Gelaxkan BlackBomber ezarri eta bista notifikatu
    public void gehituBlackBomberman(String norabidea) {
        this.bomberman = Jokoa.getJokoa().getBomberman();
        this.bombermanDago = true;
        setChanged();
        notifyObservers(norabidea + "BlackBomberman");
    }

    // Bomberman gelaxka berrira mugitu
    public void kenduBomberman() {
        this.bomberman = null;
        this.bombermanDago = false;
        setChanged();
        
        if (bomba != null) {
            notifyObservers("bombaJarri");  // Bomba jarri baldin badu
        } else {
            notifyObservers("kendu");
        }
    }

    
    // Blokeen atazak
    public Bloke getBloke() { return bloke; }
    
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
        notifyObservers("kendu");
    }
    
    
    // Bomben atazak
    public Bomba getBomba() { return bomba; }
    public boolean bombaDago() { return this.bomba != null && this.bomba.bombaAktiboDago(); }
    public boolean suaDago() { return this.suaDago; }
    
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
        notifyObservers("kendu");
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
            notifyObservers("kendu");
        }
    }

}
