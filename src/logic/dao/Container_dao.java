package logic.dao;

import error.TextParseException;
import logic.entity.Container;
import logic.entity.ContainerType;

public class Container_dao {
	private static String sep = ";";
	
	
	private Container_dao () {
		
	}
	
	
	public static String containerToText(Container container) {
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append(container.getType().name());
		stringBuilder.append(sep);
		stringBuilder.append(container.getVolume());
		stringBuilder.append(sep);
		stringBuilder.append(container.getQuantity());
		
		return stringBuilder.toString();
	}
	
	public static Container textToContainer(String text) throws TextParseException{		
		String[] pieces = text.split(sep);
		
		if(pieces.length != 3) {
			throw new TextParseException();
		}
		
		try {
			ContainerType type = ContainerType.valueOf(pieces[0]);
			int volume = Integer.valueOf(pieces[1]);
			int quantity = Integer.valueOf(pieces[2]);
			
			Container container = new Container(type, volume);
			container.setQuantity(quantity);
			
			return container;
		} catch (Exception e){
			throw new TextParseException(e);
		}
	}
	
}
