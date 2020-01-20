package logic.entity;

import logic.entity.interfaces.Storable;

public class Product implements Storable {
    private Container container;

    private Beer beer;

    private Batch batch;

    public Product(Beer beer, Container container, Batch batch) {
    }

    public Container getContainer() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.container;
    }

    public Beer getBeer() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.beer;
    }

    public Batch getBatch() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.batch;
    }

    public float getPrice() {
    	return 0;
    }

	@Override
	public int get() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void set(int quantity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void add(int quantity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(int quantity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pull(Storable to, int quantity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void push(Storable from, int quantity) {
		// TODO Auto-generated method stub
		
	}

}
