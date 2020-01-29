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
    
    public ArrayList<Product> getAllProducts(){
    	ArrayList<Product> result = new ArrayList<Product>();
    	
    	for (Storable storable : products.getAll()) {
    		result.add((Product) storable);
		}
    	
    	return result;
    }
    
    public ArrayList<Product> getAllProducts(ArrayList<BeerType> beerTypes, ArrayList<BeerColor> beerColors, ArrayList<ContainerType> containerTypes, ArrayList<BeerFiltering> beerFilterings){
    	ArrayList<Product> result = new ArrayList<Product>();
    	ArrayList<Product> all = getAllProducts();
    	
    	ArrayList<BeerType> btChoice;
    	ArrayList<BeerColor> bcChoice;
    	ArrayList<ContainerType> ctChoice;
    	ArrayList<BeerFiltering> bfChoice;
    	
    	if(beerTypes == null || beerTypes.size()==0) {
    		btChoice = new ArrayList<BeerType>();
    		for(BeerType beerType: BeerType.values()) {
    			btChoice.add(beerType);
    		}
    	} else {
    		btChoice = beerTypes;
    	}
    	
    	if(beerColors == null || beerColors.size()==0) {
    		bcChoice = new ArrayList<BeerColor>();
    		for(BeerColor beerColor: BeerColor.values()) {
    			bcChoice.add(beerColor);
    		}
    	} else {
    		bcChoice = beerColors;
    	}
    	
    	if(containerTypes == null || containerTypes.size()==0) {
    		ctChoice = new ArrayList<ContainerType>();
    		for(ContainerType containerType: ContainerType.values()) {
    			ctChoice.add(containerType);
    		}
    	} else {
    		ctChoice = containerTypes;
    	}
    	
    	if(beerFilterings == null || beerFilterings.size()==0) {
    		bfChoice = new ArrayList<BeerFiltering>();
    		for(BeerFiltering beerFiltering: BeerFiltering.values()) {
    			bfChoice.add(beerFiltering);
    		}
    	} else {
    		bfChoice = beerFilterings;
    	}
    	
    	for (Product product : all) {
    		BeerType beerType = product.getBeer().getType();
    		BeerColor beerColor = product.getBeer().getColor();
    		ContainerType containerType = product.getContainer().getType();
    		BeerFiltering beerFiltering = product.getBeer().getFiltering();
    		
    		if(btChoice.contains(beerType) && bcChoice.contains(beerColor) && ctChoice.contains(containerType) && bfChoice.contains(beerFiltering)) {
    			result.add(product);
    		}
		}
    	
    	return result;
    }
    
    public ArrayList<Product> getAllProducts(ArrayList<BeerType> beerTypes, ArrayList<BeerColor> beerColors, ArrayList<ContainerType> containerTypes, ArrayList<BeerFiltering> beerFilterings, String searchName){
    	ArrayList<Product> result = new ArrayList<Product>();
    	
    	if(searchName == null || searchName.length() == 0) {
    		result = getAllProducts(beerTypes, beerColors, containerTypes, beerFilterings);
    	} else {
    		ArrayList<Product> all = getAllProducts(beerTypes, beerColors, containerTypes, beerFilterings);
        	
        	for (Product product : all) {
        		if( product.getBeer().getName().toLowerCase().contains(searchName.toLowerCase()) ) {
        			result.add(product);
        		}
    		}
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

    
    public int countRawMaterials() {
    	return rawMaterials.size();
    }
    
    public int countContainers() {
    	return containers.size();
    }
    
    public int countProducts() {
    	return products.size();
    }
    
    
    public RawMaterial getRawMaterialsAt(int index) {
    	return (RawMaterial) rawMaterials.getAt(index);
    }
    
    public Container getContainersAt(int index) {
    	return (Container) containers.getAt(index);
    }
    
    public Product getProductsAt(int index) {
    	return (Product) products.getAt(index);
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
