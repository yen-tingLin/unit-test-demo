package com.mydemo.book.fakeservice;

import com.mydemo.book.entity.Book;

public interface ExternalBookDataService {
	
	public Book getBook(String isbn);

}
