package logic.entity;


public enum RawMaterialType {
	HOP("Hop"),
    BARLEY("Barley"),
    RICE("Rice"),
    CORN("Corn"),
    WHEAT("Wheat"),
    SUGAR("Sugar"),
    WATER("Water");
	
	private String text;
	
	private RawMaterialType(String text) {
		this.text = text;
	}
	
	@Override
	public String toString() {
		return text;
	}
	
}
