package logic.boundary;


import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import logic.designclasses.PageLoader;

public class Checkout_Boundary {	
	@FXML private Label tb_summary;
	@FXML private Label tb_payment;
	@FXML private Label tb_confirmation;
	@FXML private AnchorPane ap_checkout;
	
	private String orderId;
	
	
	public void initialize() {
		openTab(Tab.SUMMARY);
	}
	
	public void openTab(Tab tab) {
		PageLoader pageLoader = null;
		ArrayList<Label> greenLabels = new ArrayList<Label>();
		ArrayList<Label> blackLabels = new ArrayList<Label>();
		
		try {
			switch (tab) {
			case SUMMARY:
				pageLoader = new PageLoader(PageLoader.Page.CHECKOUT_SUMMARY);
				CheckoutSummary_Boundary csBoundary = (CheckoutSummary_Boundary) pageLoader.getController();
				csBoundary.setCheckoutBoundary(this);
				greenLabels.add(tb_summary);
				blackLabels.add(tb_payment);
				blackLabels.add(tb_confirmation);
				break;
			case PAYMENT:
				pageLoader = new PageLoader(PageLoader.Page.CHECKOUT_PAYMENT);
				CheckoutPayment_Boundary cpBoundary = (CheckoutPayment_Boundary) pageLoader.getController();
				cpBoundary.setCheckoutBoundary(this);
				blackLabels.add(tb_summary);
				greenLabels.add(tb_payment);
				blackLabels.add(tb_confirmation);
				break;
			case CONFIRMATION:
				pageLoader = new PageLoader(PageLoader.Page.CHECKOUT_CONFIRMATION);
				CheckoutConfirmation_Boundary ccBoundary = (CheckoutConfirmation_Boundary) pageLoader.getController();
				ccBoundary.setCheckoutBoundary(this);
				blackLabels.add(tb_summary);
				blackLabels.add(tb_payment);
				greenLabels.add(tb_confirmation);
				break;
			}
			
			for (Label label : greenLabels) {
				label.setStyle("-fx-text-fill: green;");
			}
			for (Label label : blackLabels) {
				label.setStyle("-fx-text-fill: black;");
			}
			
			if(pageLoader != null) {
				ap_checkout.getChildren().clear();
				ap_checkout.getChildren().add(pageLoader.getRootView());
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	
	@FXML 
	public void onBackPressed() {
		try {
			PageLoader pageLoader = new PageLoader(PageLoader.Page.HOME);
			pageLoader.showOnPrimaryStage();
		} catch (IOException ioe) {
			ioe.printStackTrace();
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
