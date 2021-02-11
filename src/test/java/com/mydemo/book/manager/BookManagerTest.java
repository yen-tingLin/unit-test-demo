package com.mydemo.book.manager;

import static org.junit.Assert.*;

import org.junit.Test;

import com.mydemo.book.entity.Book;
import com.mydemo.book.fakeservice.ExternalBookDataService;
import static org.mockito.Mockito.*;

public class BookManagerTest {

	@Test
	public void testCanGetInventoryCode() {		
		ExternalBookDataService webService = new ExternalBookDataService() {
			public Book getBook(String isbn) {
				return new Book("DK", "History of the World Map by Map", isbn, "2102");
			}
		};		
		
		ExternalBookDataService databaseService = new ExternalBookDataService() {
			public Book getBook(String isbn) {
				return null;
			}
		};	
				
		BookManager bookManager = new BookManager();
		bookManager.setWebService(webService);
		bookManager.setDatabaseService(databaseService);
		
		String isbn = "1465475850";
		String inventoryCode = bookManager.createInventoryCode(isbn);
		assertEquals("D585021027", inventoryCode);
	}
	
	@Test
	public void databaseServiceIsUsedIfDataIsPresentInDatabase() {
		// mock() only reflect a Book class for you, with no data in it
		ExternalBookDataService databaseService = mock(ExternalBookDataService.class);
		ExternalBookDataService webService = mock(ExternalBookDataService.class);
		
		String isbn = "1465475850";
		// If databaseService.getBook(isbn) is called, mockito should return : 
		// new Book("aa", "bb", isbn, "0000")
		// to create a valid object
		when(databaseService.getBook(isbn)).thenReturn(
				new Book("aa", "bb", isbn, "0000"));
		
		BookManager bookManager = new BookManager();
		bookManager.setDatabaseService(databaseService);
		bookManager.setWebService(webService);

		String inventoryCode = bookManager.createInventoryCode(isbn);
		
		// Verify how many times this method(a.k.a. getBook()) has been called.
		// It has the same meaning as assert() but ignoring the result returned by this method
		
		// databaseService.getBook(isbn) should be called 1 time
		verify(databaseService, times(1)).getBook(isbn);
		// webService.getBook(input) should not be called with any string input
		verify(webService, never()).getBook(anyString());
		
	}
	
	@Test
	public void webServiceIsUsedIfDataIsNotPresentInDatabase() {
		ExternalBookDataService databaseService = mock(ExternalBookDataService.class);
		ExternalBookDataService webService = mock(ExternalBookDataService.class);
		
		String isbn = "1465475850";
		when(databaseService.getBook(isbn)).thenReturn(null);
		// If webService.getBook(isbn) is called, mockito should return : 
		// new Book("cc", "dd", isbn, "1111")
		// to create a valid object
		when(webService.getBook(isbn)).thenReturn(
				new Book("cc", "dd", isbn, "1111"));
		
		BookManager bookManager = new BookManager();
		bookManager.setDatabaseService(databaseService);
		bookManager.setWebService(webService);		
		
		String inventoryCode = bookManager.createInventoryCode(isbn);

		// databaseService.getBook(isbn) should be called 1 time
		verify(databaseService, times(1)).getBook(isbn);
		// webService.getBook(isbn) should be called 1 time
		verify(webService, times(1)).getBook(isbn);
	}

}
