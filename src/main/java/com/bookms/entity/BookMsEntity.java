package com.bookms.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class BookMsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer isbn;
	@NotBlank(message = "Title cannot be blank")
	private String title;
	private String publishedDate;
	private Integer totalCopies;
	private Integer issuedCopies;
	private String author;
	
	public Integer getIsbn() {
		return isbn;
	}
	public void setIsbn(Integer isbn) {
		this.isbn = isbn;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPublishedDate() {
		return publishedDate;
	}
	public void setPublishedDate(String publishedDate) {
		this.publishedDate = publishedDate;
	}
	public Integer getTotalCopies() {
		return totalCopies;
	}
	public void setTotalCopies(Integer totalCopies) {
		this.totalCopies = totalCopies;
	}
	public Integer getIssuedCopies() {
		return issuedCopies;
	}
	public void setIssuedCopies(Integer issuedCopies) {
		this.issuedCopies = issuedCopies;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	
	
	
}
