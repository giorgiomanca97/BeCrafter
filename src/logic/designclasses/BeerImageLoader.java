package logic.designclasses;

import javafx.scene.image.Image;
import logic.StandaloneCustomerMain;
import logic.entity.ContainerType;

public class BeerImageLoader {
	private BeerImageLoader() {
		
	}
	
	
	public static Image loadImage(ContainerType type) {
		Image image = null;
		
		switch(type) {
			case BOTTLE:
				image = new Image(StandaloneCustomerMain.class.getResourceAsStream("/res/icons/beer_bottle.png"));
				break;
			case CAN:
				image = new Image(StandaloneCustomerMain.class.getResourceAsStream("/res/icons/beer_can.png"));
				break;
			case BARREL:
				image = new Image(StandaloneCustomerMain.class.getResourceAsStream("/res/icons/beer_barrel.png"));
				break;
			default:
				break;
		}
		
		return image;
	}
}
