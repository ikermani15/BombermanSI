package modeloa;

import java.util.Observable;

public class Gelaxka extends Observable {
    private Bloke bloke;
    private Bomberman bomberman;
    private Bomba bomba;
    private final int x, y;
    private boolean bombermanDago;
    private boolean suaDago = false;
    private int suaKop = 0;

    public Gelaxka(int x, int y, Bloke bloke) {
        this.x = x;
        this.y = y;
        this.bloke = bloke;
        
        // Blokea bada sortu
        if (bloke != null) {
        	System.out.println("Sortu deitu");
            blokeSortu();
        }
        
        // Lehen gelaxka bada
        if (x == 0 && y == 0) {
            gehituBomberman();
        }
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
    
    public void blokeSortu() {
    	System.out.println("Deitu da");
    	setChanged();
    	if(bloke.apurtuDaiteke()) {
    		System.out.println("BIGUNA");
    		notifyObservers("blokeBigunaSortu");
    	} else {
    		System.out.println("GOGORRA");
    		notifyObservers("blokeGogorraSortu");	
    	}
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
        return bombermanDago;
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
    	suaDago = true;
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
            suaDago = false;
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
        return suaDago;
    }
    
    
    // BlokeBiguna-rik ez dagoenean
    public void irabazi() {
    	System.out.println("BlokeBigun guztiak apartu dira!");
    	System.out.println("WIN!");
    	System.exit(1);
    }
    
    // Eztanda radio barruan egonez gero
    public void galdu() {
    	System.out.println("Eztanda arrapatu zaitu!");
    	System.out.println("GAME OVER!");
    	System.exit(1);
    }


}
