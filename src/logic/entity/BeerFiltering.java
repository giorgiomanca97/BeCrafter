package logic.entity;


public enum BeerFiltering {
	FILTERED("Filtered"),
    UNFILTERED("Unfiltered");
	
	private String text;
	
	private BeerFiltering(String text) {
		this.text = text;
	}
	
	@Override
	public String toString() {
		return text;
	}
}
