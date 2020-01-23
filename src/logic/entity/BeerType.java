package logic.entity;


public enum BeerType {
    ALE("Ale", 3),
    LAMBIC("Lambic", 2),
    LAGER("Lager", 1);
	
	private String text;
	private int mult;
	
	private BeerType(String text, int mult) {
		this.text = text;
		this.mult = mult;
	}
	
	@Override
	public String toString() {
		return text;
	}
	
	public int getMultiplier() {
		return mult;
	}

}
