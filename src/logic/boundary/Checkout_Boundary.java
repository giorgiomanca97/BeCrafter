package logic.boundary;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import logic.designclasses.PageLoader;

public class Checkout_Boundary {	
	@FXML private Label tbSummary;
	@FXML private Label tbPayment;
	@FXML private Label tbConfirmation;
	@FXML private AnchorPane apCheckout;
	
	private String orderId;
	
	
	public void initialize() {
		openTab(Tab.SUMMARY);
	}
	
	public void openTab(Tab tab) {
		PageLoader pageLoader = null;
		List<Label> greenLabels = new ArrayList<>();
		List<Label> blackLabels = new ArrayList<>();
		
		try {
			switch (tab) {
			case SUMMARY:
				pageLoader = new PageLoader(PageLoader.Page.CHECKOUT_SUMMARY);
				CheckoutSummary_Boundary csBoundary = (CheckoutSummary_Boundary) pageLoader.getController();
				csBoundary.setCheckoutBoundary(this);
				greenLabels.add(tbSummary);
				blackLabels.add(tbPayment);
				blackLabels.add(tbConfirmation);
				break;
			case PAYMENT:
				pageLoader = new PageLoader(PageLoader.Page.CHECKOUT_PAYMENT);
				CheckoutPayment_Boundary cpBoundary = (CheckoutPayment_Boundary) pageLoader.getController();
				cpBoundary.setCheckoutBoundary(this);
				blackLabels.add(tbSummary);
				greenLabels.add(tbPayment);
				blackLabels.add(tbConfirmation);
				break;
			case CONFIRMATION:
				pageLoader = new PageLoader(PageLoader.Page.CHECKOUT_CONFIRMATION);
				CheckoutConfirmation_Boundary ccBoundary = (CheckoutConfirmation_Boundary) pageLoader.getController();
				ccBoundary.setCheckoutBoundary(this);
				blackLabels.add(tbSummary);
				blackLabels.add(tbPayment);
				greenLabels.add(tbConfirmation);
				break;
			}
			
			for (Label label : greenLabels) {
				label.setStyle("-fx-text-fill: green;");
			}
			for (Label label : blackLabels) {
				label.setStyle("-fx-text-fill: black;");
			}
			
			if(pageLoader != null) {
				apCheckout.getChildren().clear();
				apCheckout.getChildren().add(pageLoader.getRootView());
			}
		} catch (IOException ioe) {
			Logger.getGlobal().log(Level.SEVERE, "Page loading error");
		}
	}
	
	
	@FXML 
	public void onBackPressed() {
		try {
			PageLoader pageLoader = new PageLoader(PageLoader.Page.HOME);
			pageLoader.showOnPrimaryStage();
		} catch (IOException ioe) {
			Logger.getGlobal().log(Level.SEVERE, "Page loading error");
		}
	}
	
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	public String getOrderId() {
		return this.orderId;
	}
	
	public enum Tab{
		SUMMARY,
		PAYMENT,
		CONFIRMATION;
	}
}
