package logic.entity;


public enum BeerType {
    ALE("Ale", 3),
    LAMBIC("Lambic", 2),
    LAGER("Lager", 1);
	
	private String name;
	private int mult;
	
	private BeerType(String name, int mult) {
		this.name = name;
		this.mult = mult;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	public int getMultiplier() {
		return mult;
	}
}
