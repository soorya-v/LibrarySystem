package com.bookStore.webApp.warehouseService;

import java.util.List;

import com.bookStore.webApp.models.Book;

public interface WarehouseService {
	
	public List<Book> getAllBooks();
	public List<Book> getAllBooksOfTitle(String title);
	public Book orderBook(String isbn);

}
