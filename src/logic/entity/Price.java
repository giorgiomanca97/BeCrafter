package logic.entity;

import java.text.DecimalFormat;

public class Price {
	private float price;
	private static DecimalFormat formatter = new DecimalFormat("#0.00");
	
	public Price() {
		this.price = 0;
	}
	
	public Price(float price) {
		this.price = price;
	}
	
	
	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return formatter.format(this.price);
	}
	
	
	public static String toText(float value) {
		Price price = new Price(value);
		return price.toString();
	}
}
