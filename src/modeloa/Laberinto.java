package modeloa;

import java.util.Observable;
import javax.swing.ImageIcon;

public class Laberinto extends Observable {
	private static Laberinto nLab;
    protected final int ilara = 11; 
    protected final int zutabe = 17; 
    protected Gelaxka[][] gelaxka; // Gelaxken matrizea
    protected ImageIcon fondo;
    private int blokeBigunKop = 0;
    private int etsaiKop = 0;

    public Laberinto() {
    	this.gelaxka = new Gelaxka[getIlarak()][getZutabeak()];
    }
   
    public static Laberinto getLaberinto() {
    	return nLab;
    }
    
    // Autatutako laberinto mota sortu
    public static Laberinto sortuLaberintoa(String mota) {
		
		LaberintoFactory factoryL = LaberintoFactory.getLaberintoFactory();
		nLab = factoryL.createLaberinto(mota);
		return nLab;
	}
    
    // Bista notifikatu laberintoa sortu dela
    public void laberintoaHasieratu() {
    	System.out.println("Laberintoa sortu da!");
        setChanged();
        notifyObservers("sortu");
    }
    public void abiaraziEtsaiGuztiak() {
        for (int i = 0; i < getIlarak(); i++) {
            for (int j = 0; j < getZutabeak(); j++) {
                Gelaxka g = getGelaxka(j, i);
                if (g.etsaiaDago() && g.getEtsaia() != null) {
                    g.getEtsaia().abiaraziEtsaia();
                }
            }
        }
    }
    
    // BlokeBigun kopurua lortzeko
    public int getBlokeBigunKop() {
        return blokeBigunKop;
    }

    public void gehituBlokeBigunKop() {
        blokeBigunKop++;
    }

    public void kenduBlokeBigunKop() {
        if (blokeBigunKop > 0) {
            blokeBigunKop--;
        }
    }
    
    // Etsai kopurua lortzeko
    public int getEtsaiKop() {
        return etsaiKop;
    }
    
    public void gehituEtsaiKop() {
    	etsaiKop++;
    }

    public void kenduEtsaiKop() {
        if (etsaiKop > 0) {
        	etsaiKop--;
        }
    }


    public Gelaxka getGelaxka(int x, int y) {
        return gelaxka[y][x];
    }

    public void setBloke(int x, int y, Bloke bloke) {
    	gelaxka[y][x].setBloke(bloke);
    }

    public ImageIcon getFondo() {
        return fondo;
    }

    public int getZutabeak() {
        return zutabe;
    }

    public int getIlarak() {
        return ilara;
    }

}
