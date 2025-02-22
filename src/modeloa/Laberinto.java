package modeloa;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Laberinto {
    private final int filas = 11; // Número de filas del laberinto
    private final int columnas = 17; // Número de columnas
    private final int cellSize = 40; // Tamaño de cada celda
    private int[][] laberinto; // La matriz que representa el laberinto
    private Random random;
    private int etsaiaKop = 0; // Contador de enemigos colocados
    private String tipoLaberinto; // Tipo de laberinto (Classic, Soft, Empty)

    // Tipos de celdas
    private static final int CAMINO = 0;
    private static final int HARD = 1;
    private static final int SOFT = 2;
    private static final int ENEMY = 3;

    // Imágenes de los bloques duros y blandos
    private final String[] hardBlocks = {
        "/img/hard1.png", "/img/hard2.png", "/img/hard3.png", "/img/hard4.png", "/img/hard5.png"
    };
    private final String[] softBlocks = {
        "/img/soft1.png", "/img/soft2.png", "/img/soft3.png", "/img/soft4.png", "/img/soft41.png",
        "/img/soft42.png", "/img/soft43.png", "/img/soft44.png", "/img/soft45.png", "/img/soft46.png"
    };

    public Laberinto(String tipoLaberinto) {
        this.laberinto = new int[filas][columnas];
        this.random = new Random();
        this.tipoLaberinto = tipoLaberinto;
        generarLaberinto();
    }

    // Generar un laberinto aleatorio
    public void generarLaberinto() {
        // Llenamos el laberinto con celdas vacías (CAMINO)
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                laberinto[i][j] = CAMINO; // Inicia todo como camino libre
            }
        }

     // Regenera bloques de acuerdo a las probabilidades y el tipo de laberinto
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
            	// Evitar que haya enemigos ni bloques en (0,1) y (1,0)
                if ((i == 0 && j == 0) || (i == 0 && j == 1) || (i == 1 && j == 0)) {
                    continue; // No hace nada en estas posiciones
                }            	
            	
                // Blokeak sortu
                double probabilidad = random.nextDouble();

                // Classic: Bloques duros en posiciones impares, bloques blandos en el resto con probabilidades
                if (tipoLaberinto.equals("Classic")) {
                	if ((i % 2 != 0) && (j % 2 != 0)) {
                		laberinto[i][j] = HARD; // Bloque duro
                     
                     } else if (probabilidad > 0.40) {
                    	 laberinto[i][j] = SOFT; // Bloque blando

                    	 if (probabilidad > 0.90 && etsaiaKop < 6) {
                    		 laberinto[i][j] = ENEMY; // Crear enemigo
                    		 etsaiaKop++;
                    	 }
                    }

                 // Soft: Bloques blandos en un 40% de las celdas, enemigos en un 90% si hay menos de 8
                 } else if (tipoLaberinto.equals("Soft")) {
                     if (probabilidad > 0.40) {
                         laberinto[i][j] = SOFT; // Bloque blando
                         if (probabilidad > 0.90 && etsaiaKop < 8) {
                        	 laberinto[i][j] = ENEMY; // Crear enemigo
                             etsaiaKop++;
                         }
                     }
                        
                 // Empty: Probabilidad de bloques blandos y enemigos con probabilidad más alta
                 } else if (tipoLaberinto.equals("Empty")) {
                     if (probabilidad > 0.95 && etsaiaKop < 10) {
                        laberinto[i][j] = ENEMY; // Crear enemigo
                        etsaiaKop++;
                    }
                 }
            }
        }
        
    }

 // Dibuja el laberinto en la ventana
    public void dibujarLaberinto(JLabel fondoLaberinto) {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (laberinto[i][j] == HARD) {
                    String imagenSeleccionada = hardBlocks[random.nextInt(hardBlocks.length)];
                    ImageIcon bloqueDuroIcon = new ImageIcon(getClass().getResource(imagenSeleccionada));
                    JLabel bloqueDuroLabel = new JLabel(new ImageIcon(bloqueDuroIcon.getImage().getScaledInstance(cellSize, cellSize, Image.SCALE_SMOOTH)));
                    bloqueDuroLabel.setBounds(j * cellSize, i * cellSize, cellSize, cellSize);
                    fondoLaberinto.add(bloqueDuroLabel);
                } else if (laberinto[i][j] == SOFT) {
                    String imagenSeleccionada = softBlocks[random.nextInt(softBlocks.length)];
                    ImageIcon bloqueBlandoIcon = new ImageIcon(getClass().getResource(imagenSeleccionada));
                    JLabel bloqueBlandoLabel = new JLabel(new ImageIcon(bloqueBlandoIcon.getImage().getScaledInstance(cellSize, cellSize, Image.SCALE_SMOOTH)));
                    bloqueBlandoLabel.setBounds(j * cellSize, i * cellSize, cellSize, cellSize);
                    fondoLaberinto.add(bloqueBlandoLabel);
                } else if (laberinto[i][j] == ENEMY) {
                    // Para dibujar al enemigo, necesitarías agregar lógica similar al de los enemigos (Etsaia).
                    // Para simplificar, lo dejaremos aquí por ahora.
                }
            }
        }
    }

    // Método para obtener el tipo de celda en una posición dada
    public int getTipoDeCelda(int x, int y) {
        return laberinto[y][x];
    }
}
