package com.aureus.elevator.controller.model;

import java.util.Set;
import java.util.TreeSet;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * @author shivani.tulaskar
 * Elevator instance when reached at a called floor
 */
@JsonRootName("elevator")
public class ElevatorBean 
{
	/**
	 * current floor of Elevator
	 */
	@JsonProperty("floor")
	private int floor;
	
	/**
	 * Current direction in which Elevator is moving 
	 */
	@JsonProperty("direction")
	private int direction;

	/**
	 * Requested floors on which elevator has to stop 
	 */
	@JsonProperty("destinationFloors")
	private Set<Integer> destinationFloor = new TreeSet<Integer>();

	public Set<Integer> getDestinationFloor()
	{
		return destinationFloor;
	}

	public void setDestinationFloor(Set<Integer> destinationFloor)
	{
		this.destinationFloor = destinationFloor;
	}
	public int getFloor()
	{
		return floor;
	}

	public void setFloor(int floor)
	{
		this.floor = floor;
	}

	public int getDirection()
	{
		return direction;
	}

	public void setDirection(int direction)
	{
		this.direction = direction;
	}

	
}
