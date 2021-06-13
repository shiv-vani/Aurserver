package com.aureus.elevator.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * @author shivani.tulaskar
 * Configuration response class of Elevator
 */
@JsonRootName("elevator_config")
public class ElevatorConfigBean 
{
	/**
	 * Last floor of the elevator
	 */
	@JsonProperty("top_floor")
	private int topFloor;
	
	/**
	 * Lowest floor of the elevator
	 */
	@JsonProperty("lower_floor")
	private int lowerFloor;
	
	/**
	 * Max weight of the elevator 
	 */
	@JsonProperty("capacity")
	private int maxCapacity;

	/**
	 * Time taken by elevator to travel from one floor to another
	 */
	@JsonProperty("time_taken_per_floor")
	private int timeTakenPerFloor; 
	
	/**
	 * Halt which elevator takes on a requested floor
	 */
	@JsonProperty("time_taken_per_stop")
	private int timeTakenPerStop;
	
	public int getMaxCapacity()
	{
		return maxCapacity;
	}

	public int getTopFloor()
	{
		return topFloor;
	}

	public void setTopFloor(int topFloor)
	{
		this.topFloor = topFloor;
	}

	public int getLowerFloor()
	{
		return lowerFloor;
	}

	public void setLowerFloor(int lowerFloor)
	{
		this.lowerFloor = lowerFloor;
	}

	public int getTimeTakenPerFloor()
	{
		return timeTakenPerFloor;
	}

	public void setTimeTakenPerFloor(int timeTakenPerFloor)
	{
		this.timeTakenPerFloor = timeTakenPerFloor;
	}

	public int getTimeTakenPerStop()
	{
		return timeTakenPerStop;
	}

	public void setTimeTakenPerStop(int timeTakenPerStop)
	{
		this.timeTakenPerStop = timeTakenPerStop;
	}

	public void setMaxCapacity(int maxCapacity)
	{
		this.maxCapacity = maxCapacity;
	}
}
