package logic.boundary;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import logic.bean.CheckoutSummaryBean;
import logic.designclasses.PageLoader;
import logic.entity.Price;
import javafx.scene.control.Button;

public class CheckoutSummaryBoundary {	
	@FXML private VBox vboxProducts;
	@FXML private Label tbOverallCost;
	@FXML private Button btnConfirmProducts;
	
	private CheckoutBoundary checkoutBoundary = null;
	private List<Float> prices;
	
	CheckoutSummaryBean buyBeerBean = null;
	
	
	public void initialize() {
		buyBeerBean = new CheckoutSummaryBean();
		displayCart();
	}
	
	public void displayCart() {
		prices = new ArrayList<>();
		
		tbOverallCost.setText(Price.toText(0f) + " €");
		vboxProducts.getChildren().clear();
		int cartSize = buyBeerBean.cartSize();
		
		if(cartSize == 0) {
			btnConfirmProducts.setDisable(true);
		} else {
			btnConfirmProducts.setDisable(false);
			try {
				for(int i = 0; i < buyBeerBean.cartSize(); i++) {
					PageLoader pageLoader = new PageLoader(PageLoader.Page.CHECKOUT_SUMMARY_ELEMENT);
					CheckoutSummaryElementBoundary cseBoundary = (CheckoutSummaryElementBoundary) pageLoader.getController();
					prices.add(0f);
					cseBoundary.setElement(this, i);
					vboxProducts.getChildren().add(pageLoader.getRootView());
				}
			} catch (IOException ioe) {
				Logger.getGlobal().log(Level.SEVERE, PageLoader.getErrorMessage());
			}
		}
	}
	
	public void updateOverallPrice(int index, float price) {
		prices.set(index, price);
		float overallPrice = 0;
		
		for (Float f : prices) {
			overallPrice += f;
		}
		
		tbOverallCost.setText(Price.toText(overallPrice) + " €");
	}
	
	public void setCheckoutBoundary(CheckoutBoundary checkoutBoundary) {
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
			checkoutBoundary.openTab(CheckoutBoundary.Tab.PAYMENT);
		}
	}
}
