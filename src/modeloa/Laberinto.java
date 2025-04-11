package modeloa;

import java.util.Observable;

public class Laberinto extends Observable {
	private static Laberinto nLab;
    protected final int ilara = 11; 
    protected final int zutabe = 17; 
    protected Gelaxka[][] gelaxka; // Gelaxken matrizea
    private int blokeBigunKop = 0;
    private int etsaiKop = 0;
    private String labMota;

    public Laberinto() {
    	this.gelaxka = new Gelaxka[getIlarak()][getZutabeak()];
    }
   
    public static Laberinto getLaberinto() {
    	return nLab;
    }
    
    public String laberintoMota (String motaLaberinto) {
    	return this.labMota = motaLaberinto;
    }
    
    public String getLaberintoMota() {
    	return labMota;
    }
    
    // Autatutako laberinto mota sortu
    public static Laberinto sortuLaberintoa(String motaLaberinto, String motaBomberman) {
        LaberintoFactory factoryL = LaberintoFactory.getLaberintoFactory();
        nLab = factoryL.createLaberinto(motaLaberinto);
        nLab.laberintoMota(motaLaberinto);
        
        // Bomberman sortu eta gelaxkan ezarri
        Bomberman.sortuBomberman(motaBomberman);
        Bomberman.getBomberman().setPosizioa(0, 0);
        nLab.gelaxka[0][0].gehituBomberman(Bomberman.getBomberman().getMota());

        return nLab;
    }
    
    // Bista notifikatu laberintoa sortu dela
    public void laberintoaHasieratu() {
    	System.out.println("Laberintoa sortu da!");
        setChanged();
        if(nLab.getLaberintoMota().equals("Classic")) {
        	notifyObservers("sortuClassic");
        } else if(nLab.getLaberintoMota().equals("Soft")) {
        	notifyObservers("sortuSoft");
        } else if(nLab.getLaberintoMota().equals("Empty")) {
        	notifyObservers("sortuEmpty");
        }
        gelaxkakEguneratu();
    }
    
    public void gelaxkakEguneratu() {
        for (int i = 0; i < getIlarak(); i++) {
            for (int j = 0; j < getZutabeak(); j++) {
                Gelaxka g = gelaxka[i][j];
                if (g.getBloke() != null) { g.gehituBloke(); }
                if (g.getBomberman() != null) { g.gehituBomberman(Bomberman.getBomberman().getMota()); }
                if (g.getEtsaia() != null) { 
                	g.gehituEtsaia(); 
                	g.getEtsaia().abiaraziEtsaia();
                }
            }
        }
    }
    
    public Gelaxka getGelaxka(int x, int y) { return gelaxka[y][x]; }
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
