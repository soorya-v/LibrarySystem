package com.onlineLibraryManagementSystem.webApp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.onlineLibraryManagementSystem.webApp.exception.BookNotFoundException;
import com.onlineLibraryManagementSystem.webApp.models.Book;
import com.onlineLibraryManagementSystem.webApp.repository.BookRepository;

@Service
public class LibraryServiceImplementation implements LibraryService{
	private BookRepository bookRepository;
		
	public LibraryServiceImplementation(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	@Override
	public Book addBook(Book book) {
		Optional<Book> existing =  bookRepository.getByisbnID(book.getIsbnID());
		if(existing.isPresent()) {
			Book existingBook = existing.get();
			existingBook.setCount(existingBook.getCount()+1);
			return bookRepository.save(existingBook);
		}
		book.setAvilable(true);
		return bookRepository.save(book);
	}

	@Override
	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	@Override
	public List<Book> getAllBooksByAuthor(String author) {
		return bookRepository.findByAuthorContaining(author);
	}

	@Override
	public List<Book> getAllBooksOfTitle(String title) {
		return bookRepository.findByBookNameContaining(title);
	}

	@Override
	public String CheckOutBook(Integer book_id) {
		Book book = bookRepository.findById(book_id).orElseThrow(() -> new BookNotFoundException());
		if (book.isAvilable()) {
			book.setCount(book.getCount()-1);
			if(book.getCount()==0) {
				book.setAvilable(false);
			}
			bookRepository.save(book);				
            return "Book " + book.getBookName() + " has been checked out!";
        }
        return "The Book is unavailable!";
	}

	@Override
	public String ReturnBook(Integer book_id, int daysOverdue) {
		Book book = bookRepository.findById(book_id).orElseThrow(() -> new BookNotFoundException());
		book.setAvilable(true);
		book.setCount(book.getCount()+1);
        bookRepository.save(book);
		if (daysOverdue > 0) {
			return "Book returned with Overdue fine: " + CalculateOverdueFine(daysOverdue,book_id); 	
		}
		return "Book returned successfully!";
	}
	
	public  double CalculateOverdueFine(int daysOverdue ,Integer book_id) {
		Book book = bookRepository.findById(book_id).orElseThrow(() -> new BookNotFoundException());
		double fineRate = book.getFineRate();
		return daysOverdue*fineRate;
}

}
