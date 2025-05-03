package modeloa;

import java.util.Arrays;
import java.util.Observable;
import java.util.stream.Stream;

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
    
    // Java 8
    public Stream<Gelaxka> gelaxkaStream() {
        return Arrays.stream(gelaxka).flatMap(Arrays::stream);
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
    
    // Java 8: Gelaxken eguneraketa stream bidez
    public void gelaxkakEguneratu() {
        gelaxkaStream()
            .filter(g -> g.getBloke() != null)
            .forEach(g -> g.gehituBloke());

        gelaxkaStream()
            .filter(g -> g.getBomberman() != null)
            .forEach(g -> g.gehituBomberman(Jokoa.getJokoa().getBomberman().getBombermanMota()));

        gelaxkaStream()
            .filter(g -> g.getEtsaia() != null)
            .forEach(g -> {
                g.gehituEtsaia(g.getEtsaia().getEtsaiMota());
                g.getEtsaia().abiaraziEtsaia();
            });
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
    
    public long contarEtsaiak() {
        return gelaxkaStream()
            .filter(g -> g.getEtsaia() != null) // Filtra las celdas que contienen enemigos
            .count(); // Cuenta cuántos enemigos hay
    }

    public void kenduEtsaiKop() {
        if (etsaiKop > 0) {
        	etsaiKop--;
        }
    }
    
    public int getZutabeak() { return zutabe; }
    public int getIlarak() { return ilara; }
    
}
