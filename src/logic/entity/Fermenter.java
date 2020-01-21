package logic.entity;

import java.util.List;

public class Fermenter {
    private int number;
    private int volume;
    private int beerVolume;
    private FermenterState state;
    private Beer beer;

    
    public Fermenter(int number, int volume) {
    	this.number = number;
    	this.volume = volume;
    	this.beerVolume = 0;
    	this.state = FermenterState.EMPTY;
    	this.beer = null;
    }

    
    public int getNumber() {
        return this.number;
    }

    public int getVolume() {
        return this.volume;
    }

    public int getBeerVolume() {
        return this.beerVolume;
    }

    public FermenterState getState() {
    	return state;
    }
    
    public void setState(FermenterState state) {
    	this.state = state;
    }

    public Beer getBeer() {
        return this.beer;
    }

    public void activate(ProductionRequest productionRequest, List<RawMaterial> rawMaterials) {
    }

    public List<Product> deactivate(List<Container> containers) {
    	return null;
    }

    public float getTemperature() {
    	// Socket
    	return 0;
    }

    public float getPressure() {
    	// Socket
    	return 0;
    }

    public float getDensity() {
    	// Socket
    	return 0;
    }

}
