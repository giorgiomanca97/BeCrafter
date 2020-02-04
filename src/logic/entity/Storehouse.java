package logic.entity;


import java.util.ArrayList;
import java.util.List;

import logic.designclasses.StorableCloneList;
import logic.entity.interfaces.Storable;

public class Storehouse {	
	private StorableCloneList rawMaterials;
    private StorableCloneList containers;
    private StorableCloneList products;

    
    public Storehouse() {
    	this.rawMaterials = new StorableCloneList();
    	this.containers = new StorableCloneList();
    	this.products = new StorableCloneList();
    }
    
    
    public List<RawMaterial> getAllRawMaterials(){
    	List<RawMaterial> result = new ArrayList<>();
    	
    	for (Storable storable : rawMaterials.getAll()) {
    		result.add((RawMaterial) storable);
		}
    	
    	return result;
    }
    
    public List<Container> getAllContainers(){
    	List<Container> result = new ArrayList<>();
    	
    	for (Storable storable : containers.getAll()) {
    		result.add((Container) storable);
		}
    	
    	return result;
    }
    
    public List<Product> getAllProducts(){
    	List<Product> result = new ArrayList<>();
    	
    	for (Storable storable : products.getAll()) {
    		result.add((Product) storable);
		}
    	
    	return result;
    }
    
    public List<Product> getAllProducts(List<BeerType> beerTypes, List<BeerColor> beerColors, List<ContainerType> containerTypes, List<BeerFiltering> beerFilterings){
    	List<Product> result = new ArrayList<>();
    	List<Product> all = getAllProducts();
    	
    	for (Product product : all) {
    		BeerType beerType = product.getBeer().getType();
    		BeerColor beerColor = product.getBeer().getColor();
    		ContainerType containerType = product.getContainer().getType();
    		BeerFiltering beerFiltering = product.getBeer().getFiltering();
    		
    		if(getBeerTypeSelection(beerTypes).contains(beerType) && 
    		   getBeerColorSelection(beerColors).contains(beerColor) && 
    		   getContainerTypeSelection(containerTypes).contains(containerType) && 
    		   getBeerFilteringSelection(beerFilterings).contains(beerFiltering)) {
    			result.add(product);
    		}
		}
    	
    	return result;
    }
    
    public List<Product> getAllProducts(List<BeerType> beerTypes, List<BeerColor> beerColors, List<ContainerType> containerTypes, List<BeerFiltering> beerFilterings, String searchName){
    	List<Product> result = new ArrayList<>();
    	
    	if(searchName == null || searchName.length() == 0) {
    		result = getAllProducts(beerTypes, beerColors, containerTypes, beerFilterings);
    	} else {
    		List<Product> all = getAllProducts(beerTypes, beerColors, containerTypes, beerFilterings);
        	
        	for (Product product : all) {
        		if( product.getBeer().getName().toLowerCase().contains(searchName.toLowerCase()) ) {
        			result.add(product);
        		}
    		}
    	}
    	
    	return result;
    }
    
    private List<BeerType> getBeerTypeSelection(List<BeerType> beerTypes){
    	List<BeerType> btChoice;
    	
    	if(beerTypes == null || beerTypes.size()==0) {
    		btChoice = new ArrayList<>();
    		for(BeerType beerType: BeerType.values()) {
    			btChoice.add(beerType);
    		}
    	} else {
    		btChoice = beerTypes;
    	}
    	
    	return btChoice;
    }
    
    private List<BeerColor> getBeerColorSelection(List<BeerColor> beerColors) {
    	List<BeerColor> bcChoice;
    	
    	if(beerColors == null || beerColors.size()==0) {
    		bcChoice = new ArrayList<>();
    		for(BeerColor beerColor: BeerColor.values()) {
    			bcChoice.add(beerColor);
    		}
    	} else {
    		bcChoice = beerColors;
    	}
    	
    	return bcChoice;
    }
    
    private List<ContainerType> getContainerTypeSelection(List<ContainerType> containerTypes){
    	List<ContainerType> ctChoice;
    	
    	if(containerTypes == null || containerTypes.size()==0) {
    		ctChoice = new ArrayList<>();
    		for(ContainerType containerType: ContainerType.values()) {
    			ctChoice.add(containerType);
    		}
    	} else {
    		ctChoice = containerTypes;
    	}
    	
    	return ctChoice;
    }
    
    private List<BeerFiltering> getBeerFilteringSelection(List<BeerFiltering> beerFilterings){
    	List<BeerFiltering> bfChoice;
    	
    	if(beerFilterings == null || beerFilterings.size()==0) {
    		bfChoice = new ArrayList<>();
    		for(BeerFiltering beerFiltering: BeerFiltering.values()) {
    			bfChoice.add(beerFiltering);
    		}
    	} else {
    		bfChoice = beerFilterings;
    	}
    	
    	return bfChoice;
    }
    
    
    public RawMaterial get(RawMaterialType type) {
    	RawMaterial rawMaterial = new RawMaterial(type);
    	return (RawMaterial) rawMaterials.get(rawMaterial);
    }

    public Container get(ContainerType type, int volume) {
    	Container container = new Container(type, volume);
    	return (Container) containers.get(container);
    }

    public Product get(String beerId, ContainerType containerType, Volume containerVolume) {
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
    	String newLine = "\n";
    	String end = "): \n";
    	int i;
    	
    	i = 1;
    	stringBuilder.append("\nRaw Materials (" + rawMaterials.size() + end);
    	for (Storable s : rawMaterials.getAll()) {
    		stringBuilder.append(i + ": " + s.toString() + newLine);
    		i++;
		}
    	
    	i = 1;
    	stringBuilder.append("\nContainers (" + containers.size() + end);
    	for (Storable s : containers.getAll()) {
    		stringBuilder.append(i + ": " + s.toString() + newLine);
    		i++;
		}
    	
    	i = 1;
    	stringBuilder.append("\nProducts (" + products.size() + end);
    	for (Storable s : products.getAll()) {
    		stringBuilder.append(i + ": " + s.toString() + newLine);
    		i++;
		}
    	stringBuilder.append(newLine);
    	
    	return stringBuilder.toString();
    }
}
