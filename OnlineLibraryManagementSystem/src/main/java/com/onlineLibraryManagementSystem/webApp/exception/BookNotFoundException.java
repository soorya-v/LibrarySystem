package com.onlineLibraryManagementSystem.webApp.exception;

public class BookNotFoundException extends RuntimeException{
	
	public BookNotFoundException() {
        super("Invalid Book ID");
    }

    public BookNotFoundException(String message) {
        super(message);
    }
	
}
