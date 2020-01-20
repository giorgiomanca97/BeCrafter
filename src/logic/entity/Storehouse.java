package logic.entity;

import java.util.ArrayList;
import java.util.List;

public class Storehouse {
    private List<Product> products = new ArrayList<Product> ();

    private List<Container> containers = new ArrayList<Container> ();

    private List<RawMaterial> rawMaterials = new ArrayList<RawMaterial> ();

    public RawMaterial get(RawMaterialType type) {
    	return null;
    }

    public Container get(ContainerType type, int volume) {
    	return null;
    }

    public Product get(Beer beer, ContainerType containerType, int containerVolume) {
    	return null;
    }

    public void add(RawMaterial rawMaterial) {
    }

    public void add(Container container) {
    }

    public void add(Product product) {
    }

    public RawMaterial remove(RawMaterialType type) {
    	return null;
    }

    public Container remove(ContainerType type, int volume) {
    	return null;
    }

    public Product remove(Beer beer, ContainerType containerType, int containerVolume) {
    	return null;
    }

}
