package logic.designclasses;

import java.util.ArrayList;

import logic.entity.interfaces.Storable;

public class RefStorableList extends StorableList{

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
	public ArrayList<Storable> getAll() {
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
	public boolean updateAll(ArrayList<Storable> otherStorables) {
		boolean result = true;
		boolean r;
		
		for (Storable storable : otherStorables) {
			
			r = update(storable);
			if(r == false) {
				result = false;
			}
		}
		
		return result;
	}

}
