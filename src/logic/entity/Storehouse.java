package logic.entity;


import java.util.ArrayList;

import logic.designclasses.CloneStorableList;
import logic.entity.interfaces.Storable;

public class Storehouse {	
	private CloneStorableList rawMaterials;
    private CloneStorableList containers;
    private CloneStorableList products;

    
    public Storehouse() {
    	this.rawMaterials = new CloneStorableList();
    	this.containers = new CloneStorableList();
    	this.products = new CloneStorableList();
    }
    
    
    public ArrayList<RawMaterial> getAllRawMaterials(){
    	ArrayList<RawMaterial> result = new ArrayList<RawMaterial>();
    	
    	for (Storable storable : rawMaterials.getAll()) {
    		result.add((RawMaterial) storable);
		}
    	
    	return result;
    }
    
    public ArrayList<Container> getAllContainers(){
    	ArrayList<Container> result = new ArrayList<Container>();
    	
    	for (Storable storable : containers.getAll()) {
    		result.add((Container) storable);
		}
    	
    	return result;
    }
    
    public ArrayList<Product> getAllProduct(){
    	ArrayList<Product> result = new ArrayList<Product>();
    	
    	for (Storable storable : products.getAll()) {
    		result.add((Product) storable);
		}
    	
    	return result;
    }
    
    
    public RawMaterial get(RawMaterialType type) {
    	RawMaterial rawMaterial = new RawMaterial(type);
    	return (RawMaterial) rawMaterials.get(rawMaterial);
    }

    public Container get(ContainerType type, int volume) {
    	Container container = new Container(type, volume);
    	return (Container) containers.get(container);
    }

    public Product get(String beerId, ContainerType containerType, int containerVolume) {
    	Beer beer = new Beer(beerId);
    	Container container = new Container(containerType, containerVolume);
    	Product product = new Product(beer, container);
    	return (Product) products.get(product);
    }

    
    public void add(RawMaterial rawMaterial) {
    	rawMaterials.add(rawMaterial);
    }

    public void add(Container container) {
    	containers.add(container);
    }

    public void add(Product product) {
    	products.add(product);
    }
    
    
    public boolean update(RawMaterial rawMaterial) {
    	return rawMaterials.update(rawMaterial);
    }
    
    public boolean update(Container container) {
    	return containers.update(container);
    }
    
    public boolean update(Product product) {
    	return products.update(product);
    }

    
    public RawMaterial delete(RawMaterialType type) {
    	RawMaterial toDelete = new RawMaterial(type);
    	Storable pop = rawMaterials.remove(toDelete);
    	
    	return (RawMaterial) pop;
    }

    public Container delete(ContainerType type, int volume) {
    	Container toDelete = new Container(type, volume);
    	Storable pop = containers.remove(toDelete);
    	
    	return (Container) pop;
    }

    public Product delete(String beerId, ContainerType containerType, int containerVolume) {
    	Beer beer = new Beer(beerId);
    	Container container = new Container(containerType, containerVolume);
    	Product toDelete = new Product(beer, container);
    	Storable pop = products.remove(toDelete);
    	
    	return (Product) pop;
    }

    
    @Override
    public String toString() {
    	StringBuilder stringBuilder = new StringBuilder();
    	int i;
    	
    	i = 1;
    	stringBuilder.append("\nRaw Materials (" + rawMaterials.size() + "): \n");
    	for (Storable s : rawMaterials.getAll()) {
    		stringBuilder.append(i + ": " + s.toString() + "\n");
    		i++;
		}
    	
    	i = 1;
    	stringBuilder.append("\nContainers (" + containers.size() + "): \n");
    	for (Storable s : containers.getAll()) {
    		stringBuilder.append(i + ": " + s.toString() + "\n");
    		i++;
		}
    	
    	i = 1;
    	stringBuilder.append("\nProducts (" + products.size() + "): \n");
    	for (Storable s : products.getAll()) {
    		stringBuilder.append(i + ": " + s.toString() + "\n");
    		i++;
		}
    	stringBuilder.append("\n");
    	
    	return stringBuilder.toString();
    }
}
