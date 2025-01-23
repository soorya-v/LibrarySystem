package com.onlineLibraryManagementSystem.webApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.onlineLibraryManagementSystem.webApp.models.Book;

@Service
public class BookStoreServiceImplementation implements BookStoreService {
	
	private RestTemplate restTemplate;
	
	public BookStoreServiceImplementation(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	@Override
	public List<Book> searchBooksFromStore(String title) {
		
		 String url = STORE_API_URL + "/search?title=" + title;
		    try {
		    	return restTemplate.getForObject(url,List.class);
		    } catch (RestClientException ex) {
		        throw new RuntimeException("Error while connecting to the external API: " + ex.getMessage());
		    }
	}
	
	
	@Override
	public Book orderBook(String isbn) {
		
		String url = STORE_API_URL + "/order/"+isbn;

	    try {
	    	return restTemplate.getForObject(url,Book.class);
	        
	    } catch (RestClientException ex) {
	        throw new RuntimeException("Error while placing an order: " + ex.getMessage());
	    }	 
		
	}

}
