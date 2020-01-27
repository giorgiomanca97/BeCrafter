package logic.boundary;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.control.TextField;

public class CheckoutSummaryElement_Boundary {
	@FXML private Label lbl_number;
	@FXML private ImageView img_containerType;
	@FXML private Label lbl_beerName;
	@FXML private Label lbl_beerType;
	@FXML private Label lbl_beerColor;
	@FXML private Label lbl_beerAlcohol;
	@FXML private Label lbl_beerFiltering;
	@FXML private Label lbl_containerVolume;
	@FXML private TextField tf_quantity;
	@FXML private Label lbl_totalVolume;
	@FXML private Label lbl_price;
	
	
	public void loadProduct(BuyBeer_Boundary product) {
		
	}


	@FXML 
	public void onSubPressed() {}

	@FXML 
	public void onChangeQuantity() {}

	@FXML 
	public void onAddPressed() {}

	@FXML 
	public void onDelPressed() {}
}
