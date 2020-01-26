package logic.designclasses;

import javafx.scene.image.Image;
import logic.StandaloneCustomerMain;
import logic.entity.ContainerType;

public class BeerImageLoader {
	private static final String IMAGE_BOTTLE_FILEPATH = "/res/icons/beer_bottle.png";
	private static final String IMAGE_CAN_FILEPATH = "/res/icons/beer_can.png";
	private static final String IMAGE_BARREL_FILEPATH = "/res/icons/beer_barrel.png";
	
	public static Image loadImage(ContainerType type) {
		Image image = null;
		switch(type) {
			case BOTTLE:
				image = new Image(StandaloneCustomerMain.class.getResourceAsStream(IMAGE_BOTTLE_FILEPATH));
				break;
			case CAN:
				image = new Image(StandaloneCustomerMain.class.getResourceAsStream(IMAGE_CAN_FILEPATH));
				break;
			case BARREL:
				image = new Image(StandaloneCustomerMain.class.getResourceAsStream(IMAGE_BARREL_FILEPATH));
				break;
		}
		
		return image;
	}
}
