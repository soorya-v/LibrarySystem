package com.onlineLibraryManagementSystem.webApp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onlineLibraryManagementSystem.webApp.models.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{

//	Returns a List<book> where the book title contains substring (bookName)
	List<Book> findByBookNameContaining(String bookName);
	
//	Returns a List<book> where the author name contains substring (author)
    List<Book> findByAuthorContaining(String author);
    
//  Returns a single book based on ISBN ID.
    Optional<Book> getByisbnID(String isbnID);
    
    
}
