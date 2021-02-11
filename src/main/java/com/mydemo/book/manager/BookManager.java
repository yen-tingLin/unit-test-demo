package com.mydemo.book.manager;

import com.mydemo.book.entity.Book;
import com.mydemo.book.fakeservice.ExternalBookDataService;


public class BookManager {
	
	private ExternalBookDataService webService;
	private ExternalBookDataService databaseService;
	
	public String createInventoryCode(String isbn) {
		Book newBook = databaseService.getBook(isbn);
		if(newBook == null) {
			newBook = webService.getBook(isbn);
		}
		
		if(newBook.getInventoryCode() != null) {
			return newBook.getInventoryCode();
		}
		
		// inventoryCode = "first letter of author" + "last four digits of isbn" + "date of purchase" + "length of the title"
		StringBuilder inventoryCode = new StringBuilder();
		inventoryCode.append(newBook.getAuthor().substring(0, 1));
		inventoryCode.append(isbn.substring(isbn.length()-4));
		inventoryCode.append(newBook.getPurchaseDate());
		inventoryCode.append(newBook.getTitle().split(" ").length);
		
		return inventoryCode.toString();
	}

	public ExternalBookDataService getWebService() {
		return webService;
	}

	public void setWebService(ExternalBookDataService webService) {
		this.webService = webService;
	}

	public ExternalBookDataService getDatabaseService() {
		return databaseService;
	}

	public void setDatabaseService(ExternalBookDataService databaseService) {
		this.databaseService = databaseService;
	}

}
