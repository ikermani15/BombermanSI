package modeloa;

public class DefaultBomba extends Bomba {
    public DefaultBomba(int x, int y, Laberinto laberinto) {
        super(x, y, 1, laberinto); // Radio = 1
    }

    @Override
    public void explotar() {
        System.out.println("Default Bomba explotó en (" + x + ", " + y + ")");

        // La bomba explota en su propia celda y en las celdas adyacentes
        laberinto.explotarEn(x, y);    // Celda actual
        laberinto.explotarEn(x - 1, y);   // Izquierda
        laberinto.explotarEn(x + 1, y);   // Derecha
        laberinto.explotarEn(x, y - 1);   // Arriba
        laberinto.explotarEn(x, y + 1);   // Abajo
    }
}
