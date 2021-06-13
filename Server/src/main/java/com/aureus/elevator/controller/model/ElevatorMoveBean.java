package com.aureus.elevator.controller.model;

import java.util.Set;
import java.util.TreeSet;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * @author shivani.tulaskar
 * This json class consists of floors which needs to be served by elevator
 */
@JsonRootName("elevator_move")
@Component
public class ElevatorMoveBean
{
	/**
	 * Floors to reach by elevator 
	 */
	@JsonProperty("destination_floors")
	private Set<Integer> destinationFloors = new TreeSet<Integer>();
	
	public Set<Integer> getDestinationFloors()
	{
		return destinationFloors;
	}
	public void setDestinationFloors(Set<Integer> destinationFloors)
	{
		this.destinationFloors = destinationFloors;
	}	
}
