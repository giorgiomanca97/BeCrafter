package logic.designclasses;

import java.util.ArrayList;
import java.util.List;

import logic.entity.interfaces.Storable;

public class StorableRefList extends StorableList{

	@Override
	public void add(Storable storable) {
		for (Storable s : storables) {
			if(s.areSame(storable)) {
				s.pull(storable);
				return;
			}
		}
		storables.add(storable);
	}
	
	@Override
	public Storable get(Storable storable) {
		for (Storable s : storables) {
			if(s.areSame(storable)) {
				return s;
			}
		}
		return null;
	}

	@Override
	public List<Storable> getAll() {
		return storables;
	}

	@Override
	public boolean update(Storable storable) {
		Storable s;
		
		for(int i = 0; i < storables.size(); i++) {
			s = storables.get(i);
			if(s.areSame(storable)) {
				storables.set(i, storable);
				return true;
			}
		}
		return false;
	}
	
	@Override
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
	
	@Override
	public List<Storable> removeAll(){
		List<Storable> result = storables;
		this.storables = new ArrayList<>();
		
		return result;
	}
}
