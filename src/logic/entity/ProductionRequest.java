package logic.entity;

import java.util.ArrayList;

import logic.designclasses.CloneStorableList;
import logic.entity.interfaces.Storable;

public class ProductionRequest {
    private String id;
    private boolean completed;
    private String date;
    private Beer beer;
    private CloneStorableList containers;

    
    public ProductionRequest(String id, String date, Beer beer) {
    	this.id = id;
    	this.completed = false;
    	this.date = date;
    	this.beer = beer;
    	this.containers = new CloneStorableList();
    }

    
    public String getId() {
        return this.id;
    }

    public Beer getBeer() {
        return this.beer;
    }

    public void setBeer(Beer beer) {
        this.beer = beer;
    }

    public boolean isCompleted() {
        return this.completed;
    }

    public void setCompleted(boolean value) {
        this.completed = value;
    }

    public String getDate() {
        return this.date;
    }

    public ArrayList<Container> getContainers() {
    	ArrayList<Container> result = new ArrayList<Container>();
    	
    	ArrayList<Storable> storables = containers.getAll();
    	for (Storable storable : storables) {
			result.add((Container) storable);
		}
    	
    	return result;
    }

    public void addContainer(Container container) {
    	containers.add(container);
    }
    
    public void updateContainer(Container container) {
    	boolean ret = containers.update(container);
    	
    	if(ret && container.getQuantity() == 0) {
    		containers.remove(container);
    	}
    }


    public int getTotalVolume() {
    	int totalVolume = 0;
    	Container container;
    	
    	for (Storable storable : containers.getAll()) {
    		container = (Container) storable;
			totalVolume += container.getVolume() * container.getQuantity();
		}
    	
    	return totalVolume;
    }

}
