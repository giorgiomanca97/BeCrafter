package logic.dao;

import error.TextParseException;
import logic.entity.BillingInfo;

public class BillingInfoDao {
	private static String sep = ";";
	
	private BillingInfoDao() {
		
	}
	
	public static String billingInfoToText(BillingInfo billingInfo) {
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append(billingInfo.getFirstName());
		stringBuilder.append(sep);
		stringBuilder.append(billingInfo.getLastName());
		stringBuilder.append(sep);
		stringBuilder.append(billingInfo.getAddress());
		stringBuilder.append(sep);
		stringBuilder.append(billingInfo.getCity());
		stringBuilder.append(sep);
		stringBuilder.append(billingInfo.getCountry());
		stringBuilder.append(sep);
		stringBuilder.append(billingInfo.getPostalCode());
		stringBuilder.append(sep);
		stringBuilder.append(billingInfo.getPhone());
		stringBuilder.append(sep);
		stringBuilder.append(billingInfo.getCard());
		
		return stringBuilder.toString();
	}
	
	public static BillingInfo textToBillingInfo(String text) throws TextParseException{	
		String[] pieces = text.split(sep);
		
		if(pieces.length != 8) {
			throw new TextParseException();
		}
		
		try {
			BillingInfo billingInfo = new BillingInfo();
			billingInfo.setFirstName(pieces[0]);
			billingInfo.setLastName(pieces[1]);
			billingInfo.setAddress(pieces[2]);
			billingInfo.setCity(pieces[3]);
			billingInfo.setCountry(pieces[4]);
			billingInfo.setPostalCode(pieces[5]);
			billingInfo.setPhone(pieces[6]);
			billingInfo.setCard(pieces[7]);
			
			return billingInfo;
		} catch (Exception e) {
			throw new TextParseException(e);
		}
	}
}
