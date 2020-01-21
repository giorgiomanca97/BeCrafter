package logic.entity;


public enum OperatorRole {
    WORKER("Worker"),
    SELLER("Seller"),
    PORTER("Porter"),
    MANAGER("Manager");
	
	private String name;
	
	private OperatorRole(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
