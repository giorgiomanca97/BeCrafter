package logic.boundary;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import logic.bean.Product_Bean;
import logic.designclasses.BeerImageLoader;
import logic.entity.Price;
import logic.entity.Volume;
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
	
	private CheckoutSummary_Boundary csBoundary;
	private int index;
	private Product_Bean buyBeerBean;
	
	
	public boolean setElement(CheckoutSummary_Boundary csBoundary, int index) {
		this.buyBeerBean = new Product_Bean();
		if(this.buyBeerBean.selectProductInCart(index)) {
			this.csBoundary = csBoundary;
			this.index = index;	
			
			this.buyBeerBean.loadSelectedProduct();
			
			loadProduct();
			updateQuantity();
			return true;
		} else {
			return false;
		}
	}
	
	private void loadProduct() {
		lbl_number.setText(String.valueOf(this.index + 1));
		img_containerType.setImage(BeerImageLoader.loadImage(buyBeerBean.getContainerType()));
		lbl_beerName.setText(buyBeerBean.getBeerName());
		lbl_beerType.setText(buyBeerBean.getBeerType().toString());
		lbl_beerColor.setText(buyBeerBean.getBeerColor().toString());
		lbl_beerAlcohol.setText(String.valueOf(buyBeerBean.getBeerAlcohol()) + "%");
		lbl_beerFiltering.setText(buyBeerBean.getBeerFiltering().toString());
		lbl_containerVolume.setText(Volume.toText(buyBeerBean.getContainerVolume()));
		tf_quantity.setText(String.valueOf(buyBeerBean.getQuantity()));
		lbl_totalVolume.setText(Volume.toText(buyBeerBean.getContainerVolume() * buyBeerBean.getQuantity()));
		lbl_price.setText(Price.toText(buyBeerBean.getPrice() * buyBeerBean.getQuantity()));
	}


	@FXML 
	public void onSubPressed() {
		int q = buyBeerBean.getQuantity() - 1;
		if(q < 1) {
			q = 1;
		}
		buyBeerBean.setQuantity(q);
		tf_quantity.setText(String.valueOf(q));
		
		updateQuantity();
	}


	@FXML 
	public void onQuantityChange() {
		try {
			int q = Integer.parseUnsignedInt(tf_quantity.getText());
			buyBeerBean.setQuantity(q);
		} catch (NumberFormatException nfe) {
			tf_quantity.setText(String.valueOf(buyBeerBean.getQuantity()));
		}
		
		updateQuantity();
	}


	@FXML 
	public void onAddPressed() {
		int q = buyBeerBean.getQuantity() + 1;
		buyBeerBean.setQuantity(q);
		tf_quantity.setText(String.valueOf(q));
		
		updateQuantity();
	}
	
	
	private void updateQuantity() {
		lbl_totalVolume.setText(Volume.toText(buyBeerBean.getContainerVolume() * buyBeerBean.getQuantity()));
		lbl_price.setText(Price.toText(buyBeerBean.getPrice() * buyBeerBean.getQuantity()));
		buyBeerBean.updateProductInsideCart();
		csBoundary.updateOverallPrice(this.index, buyBeerBean.getPrice() * buyBeerBean.getQuantity());
	}
	

	@FXML 
	public void onDelPressed() {
		buyBeerBean.removeProductFromCart();
		csBoundary.displayCart();
	}
}
