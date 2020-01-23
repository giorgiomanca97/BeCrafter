package logic.entity;


import error.StorableIllegalQuantityException;
import logic.entity.dao.Container_dao;
import logic.entity.interfaces.Storable;

public class Container implements Storable {
    private ContainerType type;
    private int volume;
    private int quantity;
    
    
    public Container(ContainerType type, int volume) {
    	this.type = type;
    	this.volume = volume;
    	this.quantity = 0;
    }

    
    public ContainerType getType() {
        return this.type;
    }

    public int getVolume() {
        return this.volume;
    }

    
	@Override
	public int getQuantity() {
		return this.quantity;
	}

	@Override
	public void setQuantity(int quantity) throws StorableIllegalQuantityException {
		if(quantity < 0) {
			throw new StorableIllegalQuantityException();
		}
		
		this.quantity = quantity;
	}

	@Override
	public void addQuantity(int quantity) {
		this.quantity += quantity;
	}

	@Override
	public void removeQuantity(int quantity) throws StorableIllegalQuantityException {
		if(quantity > this.quantity) {
			throw new StorableIllegalQuantityException();
		}
		this.quantity -= quantity;
	}

	@Override
	public void resetQuantity() {
		this.quantity = 0;
	}
	
	@Override
	public void pull(Storable from, int quantity) throws StorableIllegalQuantityException {
		if(from.getQuantity() > quantity) {
			throw new StorableIllegalQuantityException();
		}
		
		from.removeQuantity(quantity);
		this.addQuantity(quantity);
	}
	@Override
	public void pull(Storable from) {
		int q = from.getQuantity();
		from.resetQuantity();
		this.addQuantity(q);
	}

	@Override
	public void push(Storable to, int quantity) throws StorableIllegalQuantityException{
		if(this.getQuantity() > quantity) {
			throw new StorableIllegalQuantityException();
		}
		
		this.removeQuantity(quantity);
		to.addQuantity(quantity);
	}
	@Override
	public void push(Storable to) {
		int q = this.getQuantity();
		this.resetQuantity();
		to.addQuantity(q);
	}

	@Override
	public boolean areSame(Storable other) {
		Container otherContainer = (Container) other;
		boolean result = false;
		
		if(otherContainer.type == this.type && otherContainer.volume == this.volume) {
			result = true;
		}
		else {
			result = false;
		}
		
		return result;
	}

	@Override
	public void copyQuantity(Storable other) {
		this.quantity = other.getQuantity();
	}

	@Override
	public Storable copy() {
		Container result = new Container(getType(), getVolume());
		result.copyQuantity(this);
		return result;
	}
	
	@Override
	public String toString() {
		return Container_dao.containerToText(this);
	}
}
