package logic.designclasses;


import java.util.ArrayList;

import logic.entity.interfaces.Storable;

public abstract class StorableList {
	protected ArrayList<Storable> storables = new ArrayList<Storable>();
	
	
	public StorableList() {
		this.storables = new ArrayList<Storable>();
	}
	
	
	public void add(Storable storable) {
		for (Storable s : storables) {
			if(s.areSame(storable)) {
				s.pull(storable);
				return;
			}
		}
		storables.add(storable);
	}
	
	public void addAll(ArrayList<Storable> otherStorables) {
		for (Storable s : otherStorables) {
			add(s);
		}
	}
	
	public abstract Storable get(Storable storable);
	
	public abstract ArrayList<Storable> getAll();
	
	public abstract boolean update(Storable storable);
	
	public abstract boolean updateAll(ArrayList<Storable> otherStorables);
	
	public Storable remove(Storable storable) {
		Storable s;
    	
    	for (int i = 0; i < storables.size(); i++) {
    		s = storables.get(i);
			if(s.areSame(storable)) {
				storables.remove(i);
				return s;
			}
		}
    	
		return null;
	}
	
	public ArrayList<Storable> removeAll() {
		ArrayList<Storable> result = storables;
		storables = new ArrayList<Storable>();
		return result;
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
