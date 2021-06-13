package com.aureus.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.aureus.elevator.controller.service.impl.ElevatorExecutorService;
import com.aureus.elevator.model.Elevator;

/**
 * @author shivani.tulaskar
 * This class is responsible for initializing application
 */
@Component
public class Global
{
	@Autowired
	@Qualifier("elevator")
	Elevator lElevator;
	
	/**
	 * Executor Service to run the elevator
	 */
	@Autowired
	ElevatorExecutorService lExecutorService;
	
	/**
	 * Call to service to make elevator running
	 */
	public void init()
	{
		lExecutorService.runElevator();
	}
}
