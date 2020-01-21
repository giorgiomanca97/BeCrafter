package logic.entity;


public enum RawMaterialType {
	HOP("Hop"),
    BARLEY("Barley"),
    RICE("Rice"),
    CORN("Corn"),
    WHEAT("Wheat"),
    SUGAR("Sugar"),
    WATER("Water");
	
	private String name;
	
	private RawMaterialType(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
