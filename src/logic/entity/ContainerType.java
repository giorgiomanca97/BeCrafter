package logic.entity;


public enum ContainerType {
    BOTTLE("Bottle", 1f),
    CAN("Can", 0.5f),
    BARREL("Barrel", 30f);
	
	private String text;
	private float price;
	
	private ContainerType(String text, float price) {
		this.text = text;
		this.price = price;
	}
	
	@Override
	public String toString() {
		return text;
	}
	
	public float getPrice() {
		return price;
	}
}
