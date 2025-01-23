package com.onlineLibraryManagementSystem.webApp.controller;

import org.springframework.web.bind.annotation.RestController;
import com.onlineLibraryManagementSystem.webApp.models.Book;
import com.onlineLibraryManagementSystem.webApp.service.BookStoreService;
import com.onlineLibraryManagementSystem.webApp.service.LibraryService;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



//  Controller class to handle library and External API operations
 
@RestController
@RequestMapping("api/library")
public class LibraryController {

	
//	libraryService    Service handling library operations.
//	bookStoreService  Service handling the external Vendor.

	private LibraryService libraryService;
	private BookStoreService bookStoreService;

	public LibraryController(LibraryService libraryService , BookStoreService bookStoreService) {
		this.libraryService = libraryService;
		this.bookStoreService = bookStoreService;
	} 
	
//	Adding a new book to the library.
	@PostMapping("/books/add")
	public ResponseEntity<Book> addBook(@RequestBody Book book) {
		return new ResponseEntity<>(libraryService.addBook(book), HttpStatus.CREATED);
	}
	
//	Returns all books in the library catalog.
	@GetMapping("/books")
	public List<Book> getMethodName() {
		return libraryService.getAllBooks();
	}
	
//	Search books by title or author
	@GetMapping("/books/search")
	public Set<Book> searchBooks(@RequestParam(required = false) String title, @RequestParam(required = false) String author) {
        
		Set<Book> books = Collections.emptySet();
		
		if (title != null) {
			books = Stream.concat(books.stream(), libraryService.getAllBooksOfTitle(title).stream()).collect(Collectors.toSet());	
        }
        if (author != null) {
        	books = Stream.concat(books.stream(), libraryService.getAllBooksOfTitle(author).stream()).collect(Collectors.toSet());
        }
        return books;
    }
	
//	Checkout a book from the library.
	@PutMapping("/books/checkout/{id}")
    public String checkoutBook(@PathVariable(name = "id") Integer bookId) {
        return libraryService.CheckOutBook(bookId);
    }
	
//	Return a book to the library.
	@PutMapping("/books/return/{id}")
    public String returnBook(@PathVariable (name = "id") Integer bookId, @RequestParam(defaultValue = "0" ) int overdueDays) {
        return libraryService.ReturnBook(bookId, overdueDays);
    }
	
//	Search books from the external store by title
	@GetMapping("/ExternalVendor/store/search")
	public List<Book> searchBooksFromStore(@RequestParam String title) {
	    return bookStoreService.searchBooksFromStore(title);
	}
	
//	Order a book from an external store by ISBN and add it to the library 
	@PostMapping("/ExternalVendor/store/order")
	public String orderBook(@RequestParam String isbn) {
	    
	    Book book = bookStoreService.orderBook(isbn);
	    libraryService.addBook(book);
	    
	    return "Order Successful\n"+ book.toString();
	}	
	
}
