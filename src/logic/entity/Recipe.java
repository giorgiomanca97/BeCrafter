package logic.entity;

import java.util.ArrayList;

import logic.designclasses.CloneStorableList;
import logic.entity.interfaces.Storable;

public class Recipe {
    private String id;
    private CloneStorableList rawMaterials;

    public Recipe(String id) {
    	this.id = id;
    	this.rawMaterials = new CloneStorableList();
    }

    public String getId() {
        return this.id;
    }

    public ArrayList<RawMaterial> getIngredients() {
    	ArrayList<RawMaterial> result = new ArrayList<RawMaterial>();
    	
    	ArrayList<Storable> storables = rawMaterials.getAll();
    	for (Storable storable : storables) {
			result.add((RawMaterial) storable);
		}
    	
    	return result;
    }
    

    public void addIngredient(RawMaterial rawMaterial) {
    	rawMaterials.add(rawMaterial);
    }
    
    public boolean updateIngredient(RawMaterial rawMaterial) {
    	return rawMaterials.update(rawMaterial);
    }

    public RawMaterial removeIngredient(RawMaterialType type) {
    	RawMaterial rawMaterial = new RawMaterial(type);
    	return (RawMaterial) rawMaterials.remove(rawMaterial);
    }

    @Override
    public String toString() {
    	StringBuilder stringBuilder = new StringBuilder();

    	for (Storable s : rawMaterials.getAll()) {
    		stringBuilder.append(s.toString() + "\n");
		}
    	
    	return stringBuilder.toString();
    }
}
