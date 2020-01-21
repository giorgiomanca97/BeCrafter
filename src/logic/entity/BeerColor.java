package logic.entity;


public enum BeerColor {
    LIGHT("Light"),
    AMBER("Amber"),
    RUBY("Ruby"),
    DARK("Dark");
	
	private String name;
	
	private BeerColor(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
}
