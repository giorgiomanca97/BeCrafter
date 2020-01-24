package logic.entity;

public enum OrderType {
	SHIPPING("Shipping"),
    PERIODIC("Periodic");
	
	private String text;
	
	private OrderType(String text) {
		this.text = text;
	}
	
	@Override
	public String toString() {
		return text;
	}
}
