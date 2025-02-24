package modeloa;

public class DefaultBomba extends Bomba{
	public DefaultBomba(int x, int y, Laberinto laberinto) {
        super(x, y, 1, laberinto); // Radio = 1
    }

    @Override
    public void explotar() {
        System.out.println("Default Bomba explotó en (" + x + ", " + y + ")");
        //laberinto.explotarEn(x, y, radio);
    }
}
