package modeloa;

import java.util.Observable;

public class Gelaxka extends Observable {
    private Bloke bloke;
    private Bomberman bomberman;
    private Bomba bomba;
    private final int x, y;
    private boolean bombermanDago;

    public Gelaxka(int x, int y, Bloke bloke) {
        this.x = x;
        this.y = y;
        this.bloke = bloke;
        this.bombermanDago = false;
        this.bomberman = null;
        this.bomba = null;
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
    
    
    // Bomberman atazak
    public Bomberman getBomberman() {
    	return bomberman;
    }
    
    // Gelaxkan Bomberman ezarri eta bista notifikatu
    public void gehituBomberman(Bomberman bomberman) {
        this.bomberman = bomberman;
        System.out.println("Mugitu deitu da! Bomberman gehitu da.");
        setChanged();
        notifyObservers("mugitu");
    }

    // Gelaxkan Bomberman dago
    public boolean bombermanDago() {
        return bombermanDago;
    }

    // Bomberman gelaxkan dagoen konprobatu
    public void setBombermanDago(boolean dago) {
        this.bombermanDago = dago;
    }
    
    // Bomberman gelaxka berrira mugitu
    public void kenduBomberman() {
        this.bomberman = null;
        System.out.println("Mugitu deitu da! Bomberman kendu da.");
        setChanged();
        notifyObservers("mugitu");
    }

    
    // Blokeen atazak
    public Bloke getBloke() {
        return bloke;
    }

    // Blokea apurtzean bista notifikatu
    public void setBloke(Bloke bloke) {
        this.bloke = bloke;
        System.out.println("Apurtu deitu da! Blokea apurtu da.");
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
    	System.out.println("BombaJarri deitu da! Bomba ezarri da.");
        setChanged();
        notifyObservers("bomba");
    }
    
    // Bomba eztanda egitean, gelaxkatik kendu
    public void kenduBomba() {
        if (this.bomba != null) {
            this.bomba = null;
        }
        System.out.println("BombaKendu deitu da! Bomba kendu da.");
        setChanged();
        notifyObservers("bombaKendu");
    }
    
    // Eztanda egitean, bista notifikatu
    public void eztanda() {
    	
    	System.out.println("Eztanda deitu da! Sua ezarri da.");
    	setChanged();
        notifyObservers("bomba");
    }
    
    // Bomba aktibo dagoen konprobatu
    public boolean bombaDago() {
        return this.bomba != null && this.bomba.aktiboDago();
    }

}
