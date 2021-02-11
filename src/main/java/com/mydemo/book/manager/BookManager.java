package com.mydemo.book.manager;

import com.mydemo.book.entity.Book;
import com.mydemo.book.fakeservice.ExternalBookDataService;


public class BookManager {
	
	private ExternalBookDataService externalService;
	
	public String createInventoryCode(String isbn) {
		Book newBook = externalService.getBook(isbn);
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


	public ExternalBookDataService getExternalService() {
		return externalService;
	}

	public void setExternalService(ExternalBookDataService externalService) {
		this.externalService = externalService;
	}

}
