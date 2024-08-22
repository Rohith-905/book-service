package com.bookms.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bookms.entity.BookMsEntity;
import com.bookms.entity.LogRequest;
import com.bookms.service.BooksMsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/bookService")
public class BooksMsController {

	@Autowired
	private BooksMsService bookMsService;
	
	@Autowired
	private RestTemplate template;
	
	public void logMessage(String level,String message) {
		String url = "http://cross-cutting-service/crossCutting/log";
		LogRequest logRequest = new LogRequest(level,message);
		try {
	        template.postForObject(url, logRequest, LogRequest.class);
	    } catch (Exception e) {
	        throw e;
	    }
	}
	
	@Operation(summary = "Its just a homepage")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Its a home page")
	})
	@GetMapping("/getBooks")
	public List<BookMsEntity> fetchAllBooks() throws Exception{
		try {
			logMessage("INFO","Entered fetchAllBooks method");
			return bookMsService.fetchAllBooks();
		}
		catch(Exception e) {
			logMessage("ERROR", e.getMessage());
			throw e;
		}
	}
	
	@Operation(summary = "Get a book by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found the book"),
        @ApiResponse(responseCode = "404", description = "Book not found")
    })
	@GetMapping("/getBook/{isbn}")
	public Optional<BookMsEntity> getBookByID(@PathVariable Integer isbn) throws Exception{
		try {
			logMessage("INFO","Entered getBooks with "+isbn);
			return bookMsService.fetchBookById(isbn);
		}
		catch(Exception e) {
			logMessage("ERROR", e.getMessage());
			throw e;
		}
	}
	
	@Operation(summary = "Add new book")
	 @ApiResponses(value = {
		        @ApiResponse(responseCode = "200", description = "Book added successfully"),
		        @ApiResponse(responseCode = "400", description = "Invalid input data"),
		        @ApiResponse(responseCode = "500", description = "Internal server error")
		    })
	@PostMapping("/addBook")
	public BookMsEntity addBooks(@Valid @RequestBody BookMsEntity bookMsEntity) throws Exception{
		try {
			logMessage("INFO","Entered addBooks with "+ bookMsEntity);
			return bookMsService.addBook(bookMsEntity);
		}
		catch(Exception e){
			logMessage("ERROR", e.getMessage());
			throw e;
		}
		
	}
	
	@Operation(summary = "Update an existing book by ISBN")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Book updated successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input data"),
        @ApiResponse(responseCode = "404", description = "Book not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
	@PutMapping("/updateBook")
	public BookMsEntity updateBook(@RequestBody BookMsEntity bookMsEntity) throws Exception{
		try {
			logMessage("INFO","Entered updateBook with book entity " + bookMsEntity.toString());
			return bookMsService.updateBook(bookMsEntity);
		}
		catch(Exception e) {
			logMessage("ERROR", e.getMessage());
			throw e;
		}
	}
	
	 @Operation(summary = "Delete a book by ISBN")
	    @ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Book deleted successfully"),
	        @ApiResponse(responseCode = "404", description = "Book not found"),
	        @ApiResponse(responseCode = "500", description = "Internal server error")
	    })
	@DeleteMapping("/deleteBook/{isbn}")
	public ResponseEntity<String> deleteById(@PathVariable Integer isbn) throws Exception{
		try {
			logMessage("INFO","Entered DeleteById with isbn "+ isbn);
			bookMsService.deleteBook(isbn);
			return ResponseEntity.ok().body("Deleted Successfully");
		}
		catch(Exception e) {
			logMessage("ERROR", e.getMessage());
			throw e;
		}
	}
}
