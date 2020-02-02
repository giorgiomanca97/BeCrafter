package logic.entity;

import error.StorableIllegalQuantityException;
import logic.entity.interfaces.Storable;

public class Product implements Storable {
	private Beer beer;
	private Container container;
    
 
    public Product(Beer beer, Container container) {
    	this.beer = beer;
    	this.container = container;
    }

    
    public Beer getBeer() {
        return this.beer;
    }
    
    public Container getContainer() {
        return (Container) this.container.copy();
    }
    
    public float getPrice() {
    	return beer.getPricePerLiter() * container.getVolume() / 100f + container.getType().getPrice();
    }
    
    public float getTotalPrice() {
    	return getPrice() * getQuantity();
    }

    
    @Override
    public int getQuantity() {
		return this.container.getQuantity();
	}

	@Override
	public void setQuantity(int quantity) throws StorableIllegalQuantityException {
		this.container.setQuantity(quantity);
	}

	@Override
	public void addQuantity(int quantity) {
		this.container.addQuantity(quantity);
	}

	@Override
	public void removeQuantity(int quantity) throws StorableIllegalQuantityException {
		this.container.removeQuantity(quantity);
	}
	
	@Override
	public void resetQuantity() {
		this.container.resetQuantity();
	}

	@Override
	public void pull(Storable to, int quantity) throws StorableIllegalQuantityException {
		this.container.pull(to, quantity);
	}
	@Override
	public void pull(Storable from) {
		this.container.pull(from);
	}

	@Override
	public void push(Storable from, int quantity) throws StorableIllegalQuantityException {
		this.container.push(from, quantity);
	}
	@Override
	public void push(Storable to) {
		this.container.push(to);
	}

	@Override
	public boolean areSame(Storable other) {
		Product otherProduct = (Product) other;
		
		boolean result = false;
		
		if(this.getBeer().getId().equals(otherProduct.getBeer().getId())  && 
		   this.getContainer().areSame(otherProduct.getContainer())) {
			result = true;
		}
		else {
			result = false;
		}
		
		return result;
	}
	
	@Override
	public void copyQuantity(Storable other) {
		this.container.copyQuantity(other);
	}

	@Override
	public Storable copy() {
		Container c = getContainer();
		Product result = new Product(getBeer(), new Container(c.getType(), c.getVolume()));
		result.copyQuantity(this);
		return result;
	}
	
	@Override
	public String toString() {
		return "Product:  " + beer.toString() + "  -  " + container.toString();
	}

}
