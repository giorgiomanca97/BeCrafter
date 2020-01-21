package logic.entity;


public class Operator {
    private int number;
    private String password;
    private OperatorRole role;

    
    public Operator(int number, String password) {
    	this.number = number;
    	this.password = password;
    	this.role = null;
    }
    
    
    public int getNumber() {
    	return this.number;
    }
    
    public String getPassword() {
    	return this.password;
    }
    
    public void setPassword(String password) {
    	this.password = password;
    }
    
    public OperatorRole getRole() {
    	return role;
    }

    public void setRole(OperatorRole role) {
    	this.role = role;
    }
}
