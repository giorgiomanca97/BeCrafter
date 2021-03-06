package logic.designclasses;


import java.util.ArrayList;
import java.util.List;

import logic.entity.interfaces.Storable;

public abstract class StorableList {
	protected List<Storable> storables = new ArrayList<>();
	
	
	public StorableList() {
		this.storables = new ArrayList<>();
	}
	
	
	public abstract void add(Storable storable);
	
	public void addAll(List<Storable> otherStorables) {
		for (Storable s : otherStorables) {
			add(s);
		}
	}
	
	public abstract Storable get(Storable identifier);
	
	public List<Storable> getAll() {
		List<Storable> result = new ArrayList<>();
		
		for (Storable s : storables) {
			result.add(get(s));
		}
		
		return result;
	}
	
	public abstract boolean update(Storable storable);
	
	public boolean updateAll(List<Storable> otherStorables) {
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
	
	public abstract Storable remove(Storable identifier);
	
	public List<Storable> removeAll() {
		List<Storable> result = new ArrayList<>();
		
		for (Storable storable : storables) {
			result.add(remove(storable));
		}
		return result;
	}
	
	
	public abstract Storable getAt(int index);
	
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
