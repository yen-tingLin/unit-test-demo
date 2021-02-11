package com.mydemo.book.entity;

public class Book {
	private String author;
	private String title;
	private String isbn;
	private String purchaseDate;
	private String inventoryCode;
	
	
	public Book() {}

	public Book(String author, String title, String isbn, String purchaseDate) {
		super();
		this.author = author;
		this.title = title;
		this.isbn = isbn;
		this.purchaseDate = purchaseDate;
	}


	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public String getInventoryCode() {
		return inventoryCode;
	}

	public void setInventoryCode(String inventoryCode) {
		this.inventoryCode = inventoryCode;
	}
	
}
