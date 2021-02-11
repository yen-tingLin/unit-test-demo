package com.mydemo.book.manager;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.mydemo.book.entity.Book;
import com.mydemo.book.fakeservice.ExternalBookDataService;

public class BookManagerTest {

	@Test
	public void testCanGetInventoryCode() {		
		ExternalBookDataService externalBookDataService = new ExternalBookDataService() {
			public Book getBook(String isbn) {
				return new Book("DK", "History of the World Map by Map", isbn, "2102");
			}
		};		
				
		BookManager bookManager = new BookManager();
		bookManager.setExternalService(externalBookDataService);
		
		String isbn = "1465475850";
		String inventoryCode = bookManager.createInventoryCode(isbn);
		assertEquals("D585021027", inventoryCode);
	}

}
