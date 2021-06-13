package com.aureus.elevator.exception;

import org.springframework.stereotype.Component;

@Component
public class InvalidFloorException extends ServerException
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidFloorException()
	{
		super();
	}


	public InvalidFloorException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public InvalidFloorException(String message)
	{
		super(message);
	}

	public InvalidFloorException(Throwable cause)
	{
		super(cause);
	}
	
	

}
