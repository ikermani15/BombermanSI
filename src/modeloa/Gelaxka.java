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
    public void gehituBomberman() {
        this.bomberman = Bomberman.getBomberman();
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
    public void suaJarri() {
    	setChanged();
        notifyObservers("suaJarri");
    }
    
    // Eztanda amaitzean, bista notifikatu
    public void suaKendu() {
    	setChanged();
        notifyObservers("suaKendu");
    }
    
    // Bomba aktibo dagoen konprobatu
    public boolean bombaDago() {
        return this.bomba != null && this.bomba.aktiboDago();
    }

}
