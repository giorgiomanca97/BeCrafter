package logic.entity;


import logic.designclasses.CloneStorableList;

public class Storehouse {	
	private CloneStorableList rawMaterials;
    private CloneStorableList containers;
    private CloneStorableList products;

    
    public Storehouse() {
    	this.rawMaterials = new CloneStorableList();
    	this.containers = new CloneStorableList();
    	this.products = new CloneStorableList();
    }
    
    
    public RawMaterial get(RawMaterialType type) {
    	RawMaterial rawMaterial = new RawMaterial(type);
    	return (RawMaterial) rawMaterials.get(rawMaterial);
    }

    public Container get(ContainerType type, int volume) {
    	Container container = new Container(type, volume);
    	return (Container) containers.get(container);
    }

    public Product get(Beer beer, ContainerType containerType, int containerVolume) {
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
    	return null;
    }

    public Container delete(ContainerType type, int volume) {
    	return null;
    }

    public Product delete(Beer beer, ContainerType containerType, int containerVolume) {
    	return null;
    }

}
