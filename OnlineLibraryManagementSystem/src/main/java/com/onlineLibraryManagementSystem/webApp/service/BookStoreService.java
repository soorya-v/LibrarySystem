package com.onlineLibraryManagementSystem.webApp.service;

import java.util.List;

import com.onlineLibraryManagementSystem.webApp.models.Book;

public interface BookStoreService {
	
//	External book distributors or wholesalers provided API
	String STORE_API_URL = "http://localhost:8081/api/store/books";
	
//	Method to get a List<book> from the external API with matching book title.
	public List<Book> searchBooksFromStore(String title);

//	Method to order a book from the store using ISBN number	
	public Book orderBook(String isbn);
	
	
}
