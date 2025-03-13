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
    
    public Bomberman getBomberman() {
    	return bomberman;
    }
    
    public Bomba getBomba() {
    	return bomba;
    }
    
    // Gelaxkan Bomberman ezarri eta bista notifikatu
    public void gehituBomberman(Bomberman bomberman) {
        this.bomberman = bomberman;
        System.out.println("Mugitu deitu da! Bomberman gehitu da.");
        setChanged();
        notifyObservers("mugitu");
    }

    public boolean isBombermanDago() {
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
    
    // Bomba gehitzean bista notifikatu
    public void gehituBomba(Bomba bomba) {
    	this.bomba = bomba;
    	System.out.println("Bomba deitu da! Bomba ezarri da.");
        setChanged();
        notifyObservers("bomba");
    }
    
    // Bomba eztanda egitean, gelaxkatik kendu
    public void kenduBomba() {
        if (this.bomba != null) {
            this.bomba = null;
        }
        System.out.println("Bomba deitu da! Bomba kendu da.");
        setChanged();
        notifyObservers("bomba");
    }

    // Gelaxka hutsik dago
    public boolean hutsikDago() {
        return bloke == null;
    }
    
    // Bomba aktibo dagoen konprobatu
    public boolean bombaDago() {
        return this.bomba != null && this.bomba.aktiboDago();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
