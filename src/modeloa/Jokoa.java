package modeloa;

public class Jokoa {
    private static Jokoa nJokoa;
    private Laberinto laberinto;
    private Bomberman bomberman;
    
    private Jokoa() {}

    public static Jokoa getJokoa() {
        if (nJokoa == null) {
        	nJokoa = new Jokoa();
        }
        return nJokoa;
    }

    public void hasiJokoa(String laberintoMota, String bombermanMota) {
    	// Sortu laberintoa
        LaberintoFactory labFfactory = LaberintoFactory.getLaberintoFactory();
        laberinto = labFfactory.createLaberinto(laberintoMota);
        laberinto.setLaberintoMota(laberintoMota);

        // Sortu Bomberman Laberintotik
        laberinto.hasieratuBomberman(bombermanMota);;
    }

    public Laberinto getLaberinto() {
        return laberinto;
    }
    
    public void setBomberman(Bomberman bomber) {
        this.bomberman = bomber;
    }

    public Bomberman getBomberman() {
        return bomberman;
    }

    public void amaituJokoa(boolean irabaziDu) {
        if (irabaziDu) { // True bada
            System.out.println("WIN!");
        } else {
            System.out.println("GAME OVER!");
        }

        System.exit(0);
    }
}
