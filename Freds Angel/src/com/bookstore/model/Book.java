package com.bookstore.model;

public class Book {
	
	
	
	//declare variables that make up a book
	private String isbnNumber;
	private String bookTitle;
	private double purchaseCost;
	private double retailPrice;
	private boolean active;
	private String genre;
	private int authorId;
	private Author author;
	private Inventory inventory;
	
	
	
	//default constructor
	public Book() {
		
	}
	
	
	
	//parameter constructor
	public Book(String isbnNumber, String bookTitle, double purchaseCost, double retailPrice, boolean active, String genre, int authorId) {
		
		this.isbnNumber = isbnNumber;
		this.bookTitle = bookTitle;
		this.purchaseCost = purchaseCost;
		this.retailPrice = retailPrice;
		this.active = active;
		this.genre = genre;
		this.authorId = authorId;
		
	}
	
	
	
	//getters and setters	
	//isbnNumbers
	public String getIsbnNumber() {
		return isbnNumber;
	}
	public void setIsbnNumber(String isbnNumber) {
		this.isbnNumber = isbnNumber;		
	}
	
	
	//title
	public String getBookTitle() {		
		return bookTitle;		
	}
	public void setBookTitle(String title) {		
		this.bookTitle = title;		
	}
	
	
	//purchase cost
	public double getPurchaseCost() {		
		return purchaseCost;	
	}
	public void setPurchaseCost( double purchaseCost ) {		
		this.purchaseCost = purchaseCost;		
	}
	
	
	//retail price
	public double getRetailPrice() {		
		return retailPrice;		
	}
	public void setRetailPrice( double retailPrice ) {		
		this.retailPrice = retailPrice;		
	}
	
	
	//active status
	public boolean isActive() {		
		return active;		
	}
	public void setActive( boolean active ) {		
		this.active = active;		
	}
	
	
	//genre
	public String getGenre() {		
		return genre;		
	}
	public void setGenre(String genre) {		
		this.genre = genre;		
	}
	
	
	//get author
	public Author getAuthor() {
		return author;		
	}
	public void setAuthor(Author author) {
		this.author = author;		
	}
	public int getAuthorId() {
		return authorId;
	}
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	
	
	
	//get inventory
	public Inventory getInventory() {
		return inventory;
	}
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	
	
	
	//logging set up
	//going to override toString for the logging
	@Override
	public String toString() {
		
		return 	"Book Title: 		" + bookTitle + "\n" +
				"Isbn Number: 		" + isbnNumber + "\n" +
				"Auhtor: 		" + author.getFullName() + "\n" +
				"Genre: 		" + genre + "\n" +
				"Active: 		" + active + "\n" +
				"Purchase Cost: 	$" + purchaseCost + "\n" +
				"Retail Price: 		$" + retailPrice + "\n" + 
				"Inventory Qty:		" + inventory.getQty() + "\n" +
				"Inventory Value: 	$" + purchaseCost * inventory.getQty() + "\n" +
				"Retail Value: 		$" + retailPrice * inventory.getQty() + "\n";
		
	}	
}
