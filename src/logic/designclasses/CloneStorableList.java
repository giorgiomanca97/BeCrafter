package logic.designclasses;

import java.util.ArrayList;

import logic.entity.interfaces.Storable;

public class CloneStorableList extends StorableList{

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
	public ArrayList<Storable> getAll() {
		ArrayList<Storable> result = new ArrayList<Storable>();
		
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
