package com.bookStore.webApp.warehouseService;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bookStore.webApp.exception.BookNotFoundException;
import com.bookStore.webApp.models.Book;
import com.bookStore.webApp.repository.WarehouseRepository;

@Service
public class WarehouseServiceImp implements WarehouseService{
	
	WarehouseRepository warehouseRepository;
	
	public WarehouseServiceImp(WarehouseRepository warehouseRepository) {
		this.warehouseRepository = warehouseRepository;
	}
	

	@Override
	public List<Book> getAllBooks() {
		return warehouseRepository.findAll();
	}

	@Override
	public List<Book> getAllBooksOfTitle(String title) {
		List<Book> books = warehouseRepository.findByBookNameContaining(title);
		if (books.isEmpty()) {
            throw new BookNotFoundException("Books with title '" + title + "' not found.");
        }
		return books;
		
		
	}

	@Override
	public Book orderBook(String isbn) {
		return warehouseRepository.getByisbnID(isbn).orElseThrow(() -> new RuntimeException("Book Not found"));
	}
	

}
