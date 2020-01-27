package logic.designclasses;


import logic.entity.BillingInfo;

public class BillingInfoValidator {
	public static boolean validate(BillingInfo billingInfo) {
		String creditCard = billingInfo.getCard();
		
		if(!creditCard.substring(0, 4).equals("4096")) {
			return false;
		}
		
		return true;
	}
}
