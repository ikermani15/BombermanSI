package modeloa;

public class UltraBomba extends Bomba{
	public UltraBomba(int x, int y, Laberinto laberinto) {
        super(x, y, 20, laberinto); // Radio = 20
    }

    @Override
    public void explotar() {
        System.out.println("ULTRABOMBA explotó en (" + x + ", " + y + ") con radio de " + radio);
        //laberinto.explotarEn(x, y, radio);
    }
}
