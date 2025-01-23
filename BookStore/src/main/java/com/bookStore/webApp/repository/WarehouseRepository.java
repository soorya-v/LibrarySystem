package com.bookStore.webApp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookStore.webApp.models.Book;

@Repository
public interface WarehouseRepository extends JpaRepository<Book, Integer>{

	List<Book> findByBookNameContaining(String bookName);
    Optional<Book> getByisbnID(String isbnID);
    
}
