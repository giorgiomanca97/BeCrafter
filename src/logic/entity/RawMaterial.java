package logic.entity;

import error.StorableIllegalQuantityException;
import logic.entity.interfaces.Storable;

public class RawMaterial implements Storable {
    private RawMaterialType type;
    private int mass;

    
    public RawMaterial(RawMaterialType type) {
    	this.type = type;
    	this.mass = 0;
    }

    
    public RawMaterialType getType() {
        return this.type;
    }

    
    @Override
	public int getQuantity() {
		return this.mass;
	}

	@Override
	public void setQuantity(int mass) throws StorableIllegalQuantityException {
		if(mass < 0) {
			throw new StorableIllegalQuantityException();
		}
		
		this.mass = mass;
	}

	@Override
	public void addQuantity(int mass) {
		this.mass += mass;
	}

	@Override
	public void removeQuantity(int mass) throws StorableIllegalQuantityException {
		if(mass > this.mass) {
			throw new StorableIllegalQuantityException();
		}
		this.mass -= mass;
	}

	@Override
	public void resetQuantity() {
		this.mass = 0;
	}
	
	@Override
	public void pull(Storable from, int mass) throws StorableIllegalQuantityException {
		if(from.getQuantity() > mass) {
			throw new StorableIllegalQuantityException();
		}
		
		from.removeQuantity(mass);
		this.addQuantity(mass);
	}
	@Override
	public void pull(Storable from) {
		int q = from.getQuantity();
		from.resetQuantity();
		this.addQuantity(q);
	}

	@Override
	public void push(Storable to, int mass) throws StorableIllegalQuantityException{
		if(this.getQuantity() > mass) {
			throw new StorableIllegalQuantityException();
		}
		
		this.removeQuantity(mass);
		to.addQuantity(mass);
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
		this.mass = other.getQuantity();
	}

	@Override
	public Storable copy() {
		RawMaterial result = new RawMaterial(getType());
		result.copyQuantity(this);
		return result;
	}
	
	@Override
	public String toString() {
		return "RawMaterial: " + type.toString() + " " + mass;
	}
}
