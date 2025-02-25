package modeloa;

import java.util.Random;

public class EmptyLaberinto extends Laberinto {
    @Override
    public void generarLaberinto() {
        Random rand = new Random();
        int etsaiKop = 0; // Contador de enemigos

        for (int i = 0; i < getFilas(); i++) {
            for (int j = 0; j < getColumnas(); j++) {
                // Jokalaria hasten den eta alboko blokeak hutsik
                if ((i == 0 && j == 0) || (i == 1 && j == 0) || (i == 0 && j == 1)) {
                    laberinto[i][j] = BIDEA;
                } else {
                    // Con un 95% de probabilidad, colocar un enemigo
                	// %95 probabilitatea etsaia egoteko
                    int prob = rand.nextInt(100);
                    if (prob > 95 && etsaiKop < 10) {
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
