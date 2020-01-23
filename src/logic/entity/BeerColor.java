package logic.entity;


public enum BeerColor {
    LIGHT("Light"),
    AMBER("Amber"),
    RUBY("Ruby"),
    DARK("Dark");
	
	private String text;
	
	private BeerColor(String text) {
		this.text = text;
	}
	
	@Override
	public String toString() {
		return text;
	}
	
}
