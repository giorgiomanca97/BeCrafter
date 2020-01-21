package logic.entity;

public enum FermenterState {
	EMPTY("Empty"),
	IN_PREPARATION("In preparation"),
	ACTIVE("Active"),
	READY_TO_EMPTY("Ready to empty"),
	DIRTY("Dirty");
	
	private String name;
	
	private FermenterState(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
