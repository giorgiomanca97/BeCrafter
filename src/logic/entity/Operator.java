package logic.entity;

import java.util.List;

public class Operator {
    private int number;

    private String password;

    private List<JobName> roles;

    public boolean hasRole(JobName role) {
    	return false;
    }

    public List<JobName> getRoles() {
    	return roles;
    }

    public void addRole(JobName role) {
    }

    public void addRoles(List<JobName> roles) {
    }

    public void removeRole(JobName role) {
    }

    public void removeRoles(List<JobName> roles) {
    }

    public void removeAllRoles() {
    }

}
