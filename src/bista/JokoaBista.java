package bista;

import javax.swing.*;
import modeloa.Bomberman;
import modeloa.Etsaia;
import modeloa.Laberinto;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

public class JokoaBista extends JFrame implements Observer {
	private static final long serialVersionUID = 1L;

	// Bomberman lortu
	private Bomberman bomberman;

	// Laberintorako
	private JLabel fondoLaberinto;
	private JLabel bombermanLabel;

	// Etsaia lortu
	private Etsaia etsaia;
	private JLabel etsaiaLabel;

	// Laberintoa lortu
	private Laberinto laberinto;

	public JokoaBista(String laberintoTipo, String bombermanTipo) {
		setTitle("Bomberman - Juego");
		setSize(680, 480);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(null);

		// Laberintoari fondo aleatorio bat ezarri
		String[] fondos = { "/img/stageBack1.png", "/img/stageBack2.png", "/img/stageBack3.png" };
		String fondoPath = fondos[new Random().nextInt(fondos.length)];

		ImageIcon fondo = new ImageIcon(getClass().getResource(fondoPath));
		fondoLaberinto = new JLabel(new ImageIcon(fondo.getImage().getScaledInstance(680, 480, Image.SCALE_SMOOTH)));
		fondoLaberinto.setBounds(0, 0, 680, 480);
		add(fondoLaberinto);

		// Crear el laberinto con el tipo seleccionado ('Classic', 'Soft', 'Empty')
		laberinto = new Laberinto(laberintoTipo);

		// Dibujar el laberinto en la interfaz
		laberinto.dibujarLaberinto(fondoLaberinto);

		// Bomberman sortu eta irudia gehitu
		// White edo Black den konprobatu
		bomberman = new Bomberman(bombermanTipo);
		ImageIcon bomberIcon = new ImageIcon(getClass()
				.getResource(bombermanTipo.equals("White") ? "/img/whitefront1.png" : "/img/blackfront1.png"));

		bombermanLabel = new JLabel(new ImageIcon(bomberIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
		bombermanLabel.setBounds(bomberman.getXPixel(), bomberman.getYPixel(), 40, 40);
		fondoLaberinto.add(bombermanLabel);

		// Etsaia sortu
		etsaia = new Etsaia();
		// Etsaia modeloan kargatutako irudia ezarri
		ImageIcon etsaiaIcon = new ImageIcon(getClass().getResource(etsaia.getImagen()));
		etsaiaLabel = new JLabel(new ImageIcon(etsaiaIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
		etsaiaLabel.setBounds(etsaia.getXPixel(), etsaia.getYPixel(), 40, 40);
		fondoLaberinto.add(etsaiaLabel);

		Timer enemyTimer = new Timer(1000, e -> {
			etsaia.moverAleatorio();
			etsaiaLabel.setLocation(etsaia.getXPixel(), etsaia.getYPixel());
		});
		enemyTimer.start();

		setVisible(true);
	}

	// Hemen bistan egongo diren aldaketak inplementatu
	@Override
	public void update(Observable o, Object arg) {

	}
}
