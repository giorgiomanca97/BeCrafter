package logic.entity;


public enum ContainerType {
    BOTTLE("Bottle"),
    BARREL("Barrel"),
    CAN("Can");
	
	private String name;
	
	private ContainerType(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
