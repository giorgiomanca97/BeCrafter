package logic.entity;

import java.util.ArrayList;
import java.util.List;

public class Recipe {
    private String id;

    private List<RawMaterial> rawMaterials = new ArrayList<RawMaterial> ();

    public Recipe(String id) {
    }

    public String getId() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.id;
    }

    public RawMaterial get(RawMaterialType type) {
    	return null;
    }

    public List<RawMaterial> getAll() {
    	return rawMaterials;
    }

    public void add(RawMaterial rawMaterial) {
    }

    public void addAll(List<RawMaterial> rawMaterial) {
    }

    public RawMaterial remove(RawMaterialType type) {
    	return null;
    }

    public List<RawMaterial> removeAll() {
    	return rawMaterials;
    }

}
