package logic.entity;

import java.util.List;

public class Fermenter {
    private int number;

    private int volume;

    private int beerVolume;

    private boolean active;

    private Beer beer;

    public Fermenter(int number, int volume) {
    }

    public int getNumber() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.number;
    }

    public int getVolume() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.volume;
    }

    public int getBeerVolume() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.beerVolume;
    }

    public boolean isActive() {
    	return active;
    }

    public Beer getBeer() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.beer;
    }

    public void activate(ProductionRequest productionRequest, List<RawMaterial> rawMaterials) {
    }

    public List<Product> deactivate(List<Container> containers) {
    	return null;
    }

    public float getTemperature() {
    	return 0;
    }

    public float getPressure() {
    	return 0;
    }

    public float getDensity() {
    	return 0;
    }

}
