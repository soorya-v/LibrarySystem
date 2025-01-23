package com.bookStore.webApp.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookStore.webApp.exception.BookNotFoundException;
import com.bookStore.webApp.models.Book;
import com.bookStore.webApp.warehouseService.WarehouseService;

@RestController
@RequestMapping("api/store")
public class WarehouseContorller {
	
	WarehouseService warehouseService;
	
	
	public WarehouseContorller (WarehouseService warehouseService) {
		this.warehouseService = warehouseService ;
	}
	
	@GetMapping("/books")
	public List<Book> getMethodName() {
		return warehouseService.getAllBooks();
	}
	
	@GetMapping("/books/search")
	public List<Book> searchBooks(@RequestParam(required = false) String title, @RequestParam(required = false) String author) {
         
		if (title != null) {
			System.out.println(warehouseService.getAllBooksOfTitle(title));
			return warehouseService.getAllBooksOfTitle(title);
        }
		
        return Collections.emptyList();
    }
	
//	@ExceptionHandler(BookNotFoundException.class)
//    public ResponseEntity<String> handleNoTitle(BookNotFoundException ex) {
//        return new ResponseEntity<>(ex.getMessage()+" from Controller", HttpStatus.NOT_FOUND);
//    }
	
	
	@GetMapping("/books/order/{id}")
    public Book orderBook(@PathVariable(name = "id") String isbn) {
		return warehouseService.orderBook(isbn);
    }
	
	

}
