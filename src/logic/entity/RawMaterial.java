package logic.entity;

import error.StorableIllegalQuantityException;
import logic.entity.dao.Container_dao;
import logic.entity.dao.RawMaterial_dao;
import logic.entity.interfaces.Storable;

public class RawMaterial implements Storable {
    private RawMaterialType type;
    private int quantity;

    
    public RawMaterial(RawMaterialType type) {
    	this.type = type;
    	this.quantity = 0;
    }

    
    public RawMaterialType getType() {
        return this.type;
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
		RawMaterial otherRawMaterial = (RawMaterial) other;
		
		boolean result = false;
		
		if(otherRawMaterial.type == this.type) {
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
		RawMaterial result = new RawMaterial(getType());
		result.copyQuantity(this);
		return result;
	}
	
	@Override
	public String toString() {
		return RawMaterial_dao.rawMaterialToText(this);
	}
}
