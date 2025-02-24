package modeloa;

import java.util.Random;

public class ClassicLaberinto extends Laberinto {
    @Override
    public void generarLaberinto() {
        Random rand = new Random();
        int etsaiKop = 0; // Contador de enemigos

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                // Jokalaria hasten den eta alboko blokeak hutsik
                if ((i == 0 && j == 0) || (i == 1 && j == 0) || (i == 0 && j == 1)) {
                    laberinto[i][j] = BIDEA;
                } else {
                    int prob = rand.nextInt(100);
                    
                    if (i % 2 != 0 && j % 2 != 0) {
                        laberinto[i][j] = HARD;  // Bloques HARD en celdas con coordenadas impares
                    } else if (prob > 40) {
                        laberinto[i][j] = SOFT;  // Bloques SOFT con un 40% de probabilidad
                    } else {
                        // Si no es un bloque, con probabilidad del 90% colocar un enemigo
                        prob = rand.nextInt(100);
                        if (prob > 90 && etsaiKop < 6) {
                            laberinto[i][j] = ENEMY;
                            etsaiKop++;
                        } else {
                            laberinto[i][j] = BIDEA;
                        }
                    }
                }
            }
        }
    }
}
