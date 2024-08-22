package com.bookms.common;

import java.util.Date;

public class DataNotFoundException extends RuntimeException{

		private static final long serialVersionUID = 1L;
	
	    private Date timestamp;
	    private String message;

	    public DataNotFoundException(Date timestamp, String message) {
	        this.timestamp = timestamp;
	        this.message = message;
	    }

	    // Getters and Setters
	    public Date getTimestamp() {
	        return timestamp;
	    }

	    public void setTimestamp(Date timestamp) {
	        this.timestamp = timestamp;
	    }

	    public String getMessage() {
	        return message;
	    }

	    public void setMessage(String message) {
	        this.message = message;
	    }

}
