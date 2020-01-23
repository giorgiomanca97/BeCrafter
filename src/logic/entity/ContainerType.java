package logic.entity;


public enum ContainerType {
    BOTTLE("Bottle"),
    CAN("Can"),
    BARREL("Barrel");
	
	private String text;
	
	private ContainerType(String text) {
		this.text = text;
	}
	
	@Override
	public String toString() {
		return text;
	}
	
}
