package logic.entity;

import java.text.DecimalFormat;

public class Price {
	private float priceValue;
	private static DecimalFormat formatter = new DecimalFormat("#0.00");
	
	public Price() {
		this.priceValue = 0;
	}
	
	public Price(float price) {
		this.priceValue = price;
	}
	
	
	public float getPrice() {
		return priceValue;
	}

	public void setPrice(float price) {
		this.priceValue = price;
	}
	
	@Override
	public String toString() {
		return formatter.format(this.priceValue);
	}
	
	
	public static String toText(float value) {
		Price price = new Price(value);
		return price.toString();
	}
}
