package com.onlineLibraryManagementSystem.webApp.service;

import java.util.List;

import com.onlineLibraryManagementSystem.webApp.models.Book;

public interface LibraryService {
	
//	Fine rate per day for overdue books
//	double fineRate = 0.25;
	
	public Book addBook(Book book);	
	public List<Book> getAllBooks();
	public List<Book> getAllBooksByAuthor(String author );
	public List<Book> getAllBooksOfTitle(String title);
	public String CheckOutBook(Integer book_id);
	public String ReturnBook(Integer book_id , int daysOverdue);
	public double CalculateOverdueFine(int daysOverdue,Integer book_id);
	
//	Method to calculate the overdue fine
//	default public double CalculateOverdueFine(int daysOverdue) {
//		return daysOverdue*fineRate;
//	}

}
