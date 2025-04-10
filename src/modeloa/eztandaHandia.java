package modeloa;

public class eztandaHandia implements EztandaStrategy {

	@Override
	public void eztanda(Bomba bomba) {
		 	int x = bomba.getX();
	        int y = bomba.getY();
	        int radio = bomba.getRadio(); // Ya es 20 para UltraBomba

	        System.out.println("Eztanda HANDIA pos (" + y + ", " + x + ")");

	        Gelaxka gelaxka = Laberinto.getLaberinto().getGelaxka(x, y);
	        if (gelaxka != null) {
	            gelaxka.kenduBomba();
	        }

	        bomba.eztandaPos(x, y); // Centro

	        for (int i = 1; i <= radio; i++) {
	            if (!bomba.eztandaPos(x - i, y)) break;
	        }
	        for (int i = 1; i <= radio; i++) {
	            if (!bomba.eztandaPos(x + i, y)) break;
	        }
	        for (int i = 1; i <= radio; i++) {
	            if (!bomba.eztandaPos(x, y - i)) break;
	        }
	        for (int i = 1; i <= radio; i++) {
	            if (!bomba.eztandaPos(x, y + i)) break;
	        }		
	}

}
