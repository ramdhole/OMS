package com.cg.mobileStore.Exception;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ErrorDetails {
	
	private static final Logger logger =  LoggerFactory.getLogger(ErrorDetails.class);
	private Date timestamp;
	private String message;
	private String details;

	public ErrorDetails(Date timestamp, String message, String details) {
		super();
		logger.info("ErrorDetails class");
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}

	public Date getTimestamp() {
		logger.info("timestamp");
		return timestamp;
	}

	public String getMessage() {
		logger.info(message);
		return message;
	}

	public String getDetails() {
		logger.info(details);
		return details;
	}

}
