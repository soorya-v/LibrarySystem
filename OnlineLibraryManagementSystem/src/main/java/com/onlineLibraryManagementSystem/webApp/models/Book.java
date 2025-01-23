package com.onlineLibraryManagementSystem.webApp.models;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "books")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer book_id;
	
	@Column(unique = true ,nullable = false)
    private String isbnID;
	
	@Column(nullable = false)
    private String bookName;
	
	@Column(nullable = false)
    private String author;
	
	private String genre;
	
	@Column(nullable = false)
	@Builder.Default
	Integer count = 1;
	
	@Column(nullable = false)
	private boolean isAvilable;
	
	double fineRate ;

	@Override
	public int hashCode() {
		return Objects.hash(book_id);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		return Objects.equals(book_id, other.book_id);
	}
	
	@Override
    public String toString() {
		String avialbility = ""; 
		if(isAvilable) {
			avialbility = "available";
		}else {
			avialbility = "not avialble";
		}
        return "ISBN: " + isbnID + "\nTitle: " + bookName + "\nAuthor: " + author + "\nGenre: " + genre + "\nAvailable: " + avialbility + "\nCount: " + count;
    }
    
}
