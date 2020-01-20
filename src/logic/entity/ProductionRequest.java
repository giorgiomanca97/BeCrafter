package logic.entity;

import java.util.ArrayList;
import java.util.List;

public class ProductionRequest {
    private String id;

    private boolean completed;

    private String date;

    private Beer beer;

    private List<Container> containers = new ArrayList<Container> ();

    public ProductionRequest(String id) {
    }

    public String getId() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.id;
    }

    public Beer getBeer() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.beer;
    }

    public void setBeer(Beer value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.beer = value;
    }

    public boolean isCompleted() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.completed;
    }

    public void setCompleted(boolean value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.completed = value;
    }

    public String getDate() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.date;
    }

    public void setDate(String value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.date = value;
    }

    public Container get(ContainerType type, int volume) {
    	return null;
    }

    public void add(Container container) {
    }

    public Container remove(ContainerType type, int volume) {
    	return null;
    }

    public void addAll(Container containers) {
    }

    public List<Container> getAll() {
    	return null;
    }

    public List<Container> removeAll() {
    	return null;
    }

    public int getTotalVolume() {
    	return 0;
    }

}
