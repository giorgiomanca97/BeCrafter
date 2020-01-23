package logic.entity.dao;


import error.TextParseException;
import logic.entity.RawMaterial;
import logic.entity.RawMaterialType;

public class RawMaterial_dao {
	private static String SEP = ";";
	
	private RawMaterial_dao () {
		
	}
	
	public static String rawMaterialToText(RawMaterial rawMaterial) {
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append(rawMaterial.getType().name());
		stringBuilder.append(SEP);
		stringBuilder.append(rawMaterial.getQuantity());
		
		return stringBuilder.toString();
	}
	
	public static RawMaterial textToRawMaterial(String text) throws TextParseException {
		RawMaterial rawMaterial;
		RawMaterialType type;
		int quantity;
		
		String[] pieces = text.split(SEP);
		
		if(pieces.length != 2) {
			throw new TextParseException();
		}
		
		try {
			type = RawMaterialType.valueOf(pieces[0]);
			quantity = Integer.valueOf(pieces[1]);
			
			rawMaterial = new RawMaterial(type);
			rawMaterial.setQuantity(quantity);
			
			return rawMaterial;
		} catch (Exception e){
			throw new TextParseException(e);
		}
	}
}
