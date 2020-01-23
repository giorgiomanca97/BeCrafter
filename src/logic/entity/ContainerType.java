package logic.entity;


public enum ContainerType {
    BOTTLE("Bottle"),
    BARREL("Barrel"),
    CAN("Can");
	
	private String text;
	
	private ContainerType(String text) {
		this.text = text;
	}
	
	@Override
	public String toString() {
		return text;
	}
	
}
