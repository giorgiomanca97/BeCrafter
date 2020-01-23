package logic.entity;


public enum OperatorRole {
    WORKER("Worker"),
    SELLER("Seller"),
    PORTER("Porter"),
    MANAGER("Manager");
	
	private String text;
	
	private OperatorRole(String text) {
		this.text = text;
	}
	
	@Override
	public String toString() {
		return text;
	}
	
}
