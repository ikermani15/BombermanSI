package modeloa;

import java.util.Observable;

public abstract class Laberinto extends Observable {
    protected final int ilara = 11; 
    protected final int zutabe = 17; 
    protected Gelaxka[][] gelaxka; // Gelaxken matrizea
    private int blokeBigunKop = 0;
    private int etsaiKop = 0;
    private String labMota;

    public Laberinto() {
    	this.gelaxka = new Gelaxka[getIlarak()][getZutabeak()];
    }
    
    public void setLaberintoMota(String motaLaberinto) {
        this.labMota = motaLaberinto;
    }
    
    public String getLaberintoMota() {
    	return labMota;
    }
    
    public void hasieratuBomberman(String bombermanMota) {
        Bomberman bomber = BombermanFactory.getBombermanFactory().createBomberman(bombermanMota);
        Jokoa.getJokoa().setBomberman(bomber);
        bomber.setPosizioa(0, 0);
        gelaxka[0][0].gehituBomberman(bombermanMota);
    }
    
    // Bista notifikatu laberintoa sortu dela
    public void laberintoaHasieratu() {
    	System.out.println("Laberintoa sortu da!");
        setChanged();
        if(getLaberintoMota().equals("Classic")) {
        	notifyObservers("sortuClassic");
        } else if(getLaberintoMota().equals("Soft")) {
        	notifyObservers("sortuSoft");
        } else if(getLaberintoMota().equals("Empty")) {
        	notifyObservers("sortuEmpty");
        }
        gelaxkakEguneratu();
    }
    
    public void gelaxkakEguneratu() {
        for (int i = 0; i < getIlarak(); i++) {
            for (int j = 0; j < getZutabeak(); j++) {
                Gelaxka g = gelaxka[i][j];
                if (g.getBloke() != null) { g.gehituBloke(); }
                if (g.getBomberman() != null) { g.gehituBomberman(Jokoa.getJokoa().getBomberman().getBombermanMota()); }
                if (g.getEtsaia() != null) { 
                	g.gehituEtsaia(); 
                	g.getEtsaia().abiaraziEtsaia();
                }
            }
        }
    }
    
    public Gelaxka getGelaxka(int x, int y) {
        if (x < 0 || x >= zutabe || y < 0 || y >= ilara) {
            return null;
        }
        return gelaxka[y][x];
    }

    public void setBloke(int x, int y, Bloke bloke) { gelaxka[y][x].setBloke(bloke); }
    
    // BlokeBigun kopurua lortzeko
    public int getBlokeBigunKop() { return blokeBigunKop; }
    public void gehituBlokeBigunKop() { blokeBigunKop++; }

    public void kenduBlokeBigunKop() {
        if (blokeBigunKop > 0) {
            blokeBigunKop--;
        }
    }
    
    // Etsai kopurua lortzeko
    public int getEtsaiKop() { return etsaiKop; }
    public void gehituEtsaiKop() { etsaiKop++; }

    public void kenduEtsaiKop() {
        if (etsaiKop > 0) {
        	etsaiKop--;
        }
    }
    
    public int getZutabeak() { return zutabe; }
    public int getIlarak() { return ilara; }
    
}
