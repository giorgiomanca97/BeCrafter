package logic.entity;

import logic.entity.interfaces.Storable;

public class Container implements Storable {
    private ContainerType type;

    private int volume;

    private int quantity;

    public Container(ContainerType type, int volume) {
    }

    public ContainerType getType() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.type;
    }

    public int getVolume() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.volume;
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
