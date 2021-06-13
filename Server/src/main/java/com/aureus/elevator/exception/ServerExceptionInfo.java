package com.aureus.elevator.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * @author shivani.tulaskar
 * Exception class which is given in response in case of error during execution
 */
@JsonRootName("exception_info")
public class ServerExceptionInfo
{
	/**
	 * Error code
	 */
	@JsonProperty("http_status")
	private String httpStatus;
	
	/**
	 * Failure message
	 */
	@JsonProperty("error_message")
	private String errorMessage;
	
	public ServerExceptionInfo(String httpStatus, String errorMessage)
	{
		super();
		this.httpStatus = httpStatus;
		this.errorMessage = errorMessage;
	}
	public String getHttpStatus()
	{
		return httpStatus;
	}
	public void setHttpStatus(String httpStatus)
	{
		this.httpStatus = httpStatus;
	}
	public String getErrorMessage()
	{
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage)
	{
		this.errorMessage = errorMessage;
	}

}
