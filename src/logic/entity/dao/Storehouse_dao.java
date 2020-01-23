package logic.entity.dao;

import logic.entity.Storehouse;

public class Storehouse_dao {
	private static Storehouse storehouseInstance;
	
	private Storehouse_dao() {
		
	}
	
	synchronized private static Storehouse getStorehouse() {
		if(storehouseInstance == null) {
			//TODO: caricare storehouse da persistenza
			storehouseInstance = new Storehouse();
		}
		
		return storehouseInstance;
	}
	
	private static void updateStorehouse() {
		if(storehouseInstance != null) {
			//TODO: aggioranare persistenza
		}
	}
	
	
}
