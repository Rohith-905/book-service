package com.bookms.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bookms.common.DataNotFoundException;
import com.bookms.entity.BookMsEntity;
import com.bookms.entity.LogRequest;
import com.bookms.repository.BookMsRepo;

@Service
public class BooksMsService {

	@Autowired
	private BookMsRepo bookMsRepo;
	
	@Autowired
	private RestTemplate template;
	
	public void logMessage(String level,String message) {
		String url = "http://cross-cutting-service/crossCutting/log";
		LogRequest logRequest = new LogRequest(level,message);
		try {
			template.postForObject(url, logRequest, LogRequest.class);
		}
		catch(Exception e) {
			throw e;
		}
	}
	
	public List<BookMsEntity> fetchAllBooks(){
		List<BookMsEntity> books = null;
		try {
			logMessage("INFO", "In service trying to fetch all books: ");
			books = bookMsRepo.findAll();
			if(books.isEmpty()) {
				throw new DataNotFoundException(new Date(),"Book details not found");
			}
		}
		catch(Exception e) {
			logMessage("ERROR","No Available books ");
			throw e;
		}
		return books;
	}
	
	public Optional<BookMsEntity> fetchBookById(Integer isbn) throws Exception{
		Optional<BookMsEntity> book = null;
		try {
			logMessage("INFO", "In service trying to find book by id: "+isbn);
			book = bookMsRepo.findById(isbn);
			if(!book.isPresent()) {
				throw new DataNotFoundException(new Date(),"Book details not found for ISBN: " +isbn);
			}
		}
		catch(Exception e) {
			logMessage("ERROR","Book not found for the id: "+isbn);
			throw new DataNotFoundException(new Date(),"Book details not found for ISBN: " +isbn);
		}
		return book;
	}
	
	public BookMsEntity addBook(BookMsEntity newBook) {
		return bookMsRepo.save(newBook);
	}
	
	public BookMsEntity updateBook(BookMsEntity updateBook) throws Exception{
		try {
			logMessage("INFO", "In service trying to find book by id: "+updateBook.getIsbn());
			Optional<BookMsEntity> isbn = bookMsRepo.findById(updateBook.getIsbn());
			if(isbn.isPresent())
				return bookMsRepo.save(updateBook);
			else {
				logMessage("ERROR","Book not found for the id: "+updateBook.getIsbn());
				throw new DataNotFoundException(new Date(),"Book details not found for ISBN: " + updateBook.getIsbn()); 
			}	
		}
		catch(Exception e) {
			throw e;
		}	
	}
	
	public void deleteBook(Integer isbn) {
		logMessage("INFO", "In service trying to find book by id: "+isbn);
		Optional<BookMsEntity> book = null;
		try {
			book = bookMsRepo.findById(isbn);
			if(!book.isPresent()) {
				logMessage("ERROR","Book not found for the id: "+isbn);
				throw new DataNotFoundException(new Date(),"Book details not found for ISBN: " + isbn);
			}
		}
		catch(Exception e) {
			throw e;
		}
		bookMsRepo.deleteById(isbn);
	}
	
}
