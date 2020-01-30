package logic.designclasses;


import java.util.ArrayList;

import logic.entity.interfaces.Storable;

public abstract class StorableList {
	protected ArrayList<Storable> storables = new ArrayList<>();
	
	
	public StorableList() {
		this.storables = new ArrayList<>();
	}
	
	
	public abstract void add(Storable storable);
	
	public void addAll(ArrayList<Storable> otherStorables) {
		for (Storable s : otherStorables) {
			add(s);
		}
	}
	
	public abstract Storable get(Storable storable);
	
	public ArrayList<Storable> getAll() {
		ArrayList<Storable> result = new ArrayList<>();
		
		for (Storable s : storables) {
			result.add(get(s));
		}
		
		return result;
	}
	
	public abstract boolean update(Storable storable);
	
	public boolean updateAll(ArrayList<Storable> otherStorables) {
		boolean result = true;
		boolean r;
		
		for (Storable storable : otherStorables) {
			
			r = update(storable);
			if(!r) {
				result = false;
			}
		}
		
		return result;
	}
	
	public abstract Storable remove(Storable storable);
	
	public ArrayList<Storable> removeAll() {
		ArrayList<Storable> result = new ArrayList<>();
		
		for (Storable storable : storables) {
			result.add(remove(storable));
		}
		return result;
	}
	
	
	public Storable getAt(int index) {
		return this.get(storables.get(index));
	}
	
	public int size() {
		return storables.size();
	}
	
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		
		for (Storable storable : storables) {
			stringBuilder.append(storable.toString() + "\n");
		}
		
		return stringBuilder.toString();
	}
}
