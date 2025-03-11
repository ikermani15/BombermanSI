package main;

import bista.MainBista;
import kontrolatzailea.JokoaKontrolatzaile;

public class Main {
	public static void main(String[] args) {
		JokoaKontrolatzaile jk = new JokoaKontrolatzaile();
        MainBista mb = new MainBista(jk);
    }
}
