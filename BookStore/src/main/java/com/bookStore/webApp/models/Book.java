package com.bookStore.webApp.models;

import java.util.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Warehouse")
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

	@Override
	public int hashCode() {
		return Objects.hash(isbnID);
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
		return Objects.equals(isbnID, other.isbnID);
	}
	
	@Override
    public String toString() {
		return "ISBN: " + isbnID + "\nTitle: " + bookName + "\nAuthor: " + author + "\nGenre: " + genre ;
    }

}
