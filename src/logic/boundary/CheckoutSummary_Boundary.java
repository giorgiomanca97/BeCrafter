package logic.boundary;


import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import logic.bean.BuyBeer_Bean;
import logic.designclasses.PageLoader;
import logic.entity.Price;
import javafx.scene.control.Button;

public class CheckoutSummary_Boundary {	
	@FXML private VBox vbox_products;
	@FXML private Label tb_overallCost;
	@FXML private Button btn_ConfirmProducts;
	
	private Checkout_Boundary checkoutBoundary = null;
	private ArrayList<Float> prices;
	
	
	
	public void initialize() {
		displayCart();
	}
	
	public void displayCart() {
		BuyBeer_Bean buyBeerBean = new BuyBeer_Bean();
		prices = new ArrayList<Float>();
		
		tb_overallCost.setText(Price.toText(0f));
		vbox_products.getChildren().clear();
		int cartSize = buyBeerBean.cartSize();
		
		if(cartSize == 0) {
			btn_ConfirmProducts.setDisable(true);
		} else {
			btn_ConfirmProducts.setDisable(false);
			try {
				for(int i = 0; i < buyBeerBean.cartSize(); i++) {
					PageLoader pageLoader = new PageLoader(PageLoader.Page.CHECKOUT_SUMMARY_ELEMENT);
					CheckoutSummaryElement_Boundary cseBoundary = (CheckoutSummaryElement_Boundary) pageLoader.getController();
					prices.add(0f);
					cseBoundary.setElement(this, i);
					vbox_products.getChildren().add(pageLoader.getRootView());
				}
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}
	
	public void updateOverallPrice(int index, float price) {
		prices.set(index, price);
		float overallPrice = 0;
		
		for (Float f : prices) {
			overallPrice += f;
		}
		
		tb_overallCost.setText(Price.toText(overallPrice));
	}
	
	public void setCheckoutBoundary(Checkout_Boundary checkoutBoundary) {
		this.checkoutBoundary = checkoutBoundary;
	}

	@FXML 
	public void goBackToShoppingPressed() {
		if(checkoutBoundary != null) {
			checkoutBoundary.onBackPressed();
		}
	}

	@FXML 
	public void onConfirmProductsPressed() {
		if(checkoutBoundary != null) {
			checkoutBoundary.openTab(Checkout_Boundary.Tab.PAYMENT);
		}
	}
}
