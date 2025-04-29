package modeloa;

public class EtsaiaFactory {
	private static EtsaiaFactory nEF;
	
	private EtsaiaFactory() {}
	
	public static EtsaiaFactory getEtsaiaFactory() {
		if(nEF == null) {
			nEF = new EtsaiaFactory();
		}
		return nEF;
	}
	
	public Etsaia createEtsaia(int x, int y, String mota) {
		switch(mota) {
			case "Normala": return new EtsaiNormala(x,y);
			case "Berezia": return new EtsaiBerezia(x,y);
			default: throw new IllegalArgumentException("Etsai mota ezezaguna: " + mota);
		}
	}
}
