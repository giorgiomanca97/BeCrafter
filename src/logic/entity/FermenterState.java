package logic.entity;

public enum FermenterState {
	EMPTY("Empty"),
	IN_PREPARATION("In preparation"),
	ACTIVE("Active"),
	READY_TO_EMPTY("Ready to empty"),
	DIRTY("Dirty");
	
	private String text;
	
	private FermenterState(String text) {
		this.text = text;
	}
	
	@Override
	public String toString() {
		return text;
	}
	
}
