package logic.designclasses;

import java.util.ArrayList;
import java.util.List;

import logic.entity.interfaces.Storable;

public class StorableCloneList extends StorableList{

	@Override
	public void add(Storable storable) {
		Storable copy = storable.copy();
		
		for (Storable s : storables) {
			if(s.areSame(copy)) {
				s.pull(copy);
				return;
			}
		}
		storables.add(copy);
	}
	
	@Override
	public Storable get(Storable storable) {
		for (Storable s : storables) {
			if(s.areSame(storable)) {
				return s.copy();
			}
		}
		return null;
	}

	@Override
	public List<Storable> getAll() {
		List<Storable> result = new ArrayList<>();
		
		for (Storable s : storables) {
			result.add(s.copy());
		}
		
		return result;
	}

	@Override
	public boolean update(Storable storable) {
		Storable s;
		
		for(int i = 0; i < storables.size(); i++) {
			s = storables.get(i);
			if(s.areSame(storable)) {
				s.copyQuantity(storable);
				return true;
			}
		}
		return false;
	}
	
	@Override
	public Storable remove(Storable storable) {
		Storable c;
    	
    	for (int i = 0; i < storables.size(); i++) {
    		c = storables.get(i).copy();
			if(c.areSame(storable)) {
				storables.remove(i);
				return c;
			}
		}
    	
		return null;
	}
	
	@Override
	public List<Storable> removeAll(){
		List<Storable> result = new ArrayList<>();
		
		for (Storable storable : storables) {
			result.add(storable.copy());
		}
		
		this.storables = new ArrayList<>();
		
		return result;
	}
}