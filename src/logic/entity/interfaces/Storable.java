package logic.entity.interfaces;

import error.StorableIllegalQuantityException;

public interface Storable {
    public int getQuantity();

    public void setQuantity(int quantity) throws StorableIllegalQuantityException;

    public void addQuantity(int quantity);

    public void removeQuantity(int quantity) throws StorableIllegalQuantityException;
    
    public void resetQuantity();

    public void pull(Storable from, int quantity) throws StorableIllegalQuantityException;
    public void pull(Storable from);

    public void push(Storable to, int quantity) throws StorableIllegalQuantityException;
    public void push(Storable to);
    
    public boolean areSame(Storable other);
    
    public void copyQuantity(Storable other);
    
    public Storable copy();
}
