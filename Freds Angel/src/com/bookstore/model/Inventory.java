package com.bookstore.model;

import java.sql.Timestamp;

public class Inventory {
	
	/**
	 * {@summary} declare variables that make up an inventory record
	 */
	private int inventoryId;
	private String isbnNumber;
	private int qty;
	private Timestamp createdDate;
	private Timestamp updatedDate;
	
	
	
	/**
	 * {@summary} default constructor
	 */
	public Inventory() {
		
	}
	
	
	/**
	 * @param constructor
	 * @param inventoryId
	 * @param isbnNumber
	 * @param qty
	 * @param createdDate
	 * @param updatedDate
	 */
	public Inventory(int inventoryId, String isbnNumber, int qty) {
		this.inventoryId = inventoryId;
		this.isbnNumber = isbnNumber;
		this.qty = qty;
	}
	
	/**
	 * @return the inventoryId
	 */
	public int getInventoryId() {
		return inventoryId;
	}


	/**
	 * @param inventoryId the inventoryId to set
	 */
	public void setInventoryId(int inventoryId) {
		this.inventoryId = inventoryId;
	}


	/**
	 * @return the isbnNumber
	 */
	public String getIsbnNumber() {
		return isbnNumber;
	}


	/**
	 * @param isbnNumber the isbnNumber to set
	 */
	public void setIsbnNumber(String isbnNumber) {
		this.isbnNumber = isbnNumber;
	}


	/**
	 * @return the qty
	 */
	public int getQty() {
		return qty;
	}


	/**
	 * @param qty the qty to set
	 */
	public void setQty(int qty) {
		this.qty = qty;
	}


	/**
	 * @return the createdDate
	 */
	public Timestamp getCreatedDate() {
		return createdDate;
	}


	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}


	/**
	 * @return the updatedDate
	 */
	public Timestamp getUpdatedDate() {
		return updatedDate;
	}


	/**
	 * @param updatedDate the updatedDate to set
	 */
	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	
	//logging set up
	//going to override toString for the logging
	@Override
	public String toString() {
		
		return 	"Inventory ID: 		" + inventoryId + "\n" +
				"Isbn Number: 		" + isbnNumber + "\n" +
				"Quantity: 		" + qty + "\n" +
				"Created Date: 		S" + createdDate + "\n" +
				"Updateed Date: 		" + updatedDate + "\n";
		
	}
}
