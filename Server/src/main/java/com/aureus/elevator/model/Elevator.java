package com.aureus.elevator.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.springframework.stereotype.Component;

/**
 * @author shivani.tulaskar
 * Elevator class 
 */
@Component("elevator")
public class Elevator 
{
	public static final int DIRECTION_UP = 1;
	public static final int DIRECTION_DOWN = 2;
	public static final int DIRECTION_NONE = 3;
	
	public static final int STATUS_ACTIVE = 1;
	public static final int STATUS_STILL = 0;
	public static final int STATUS_OUT_OF_SERVICE = -1;
	
	//In kgs
	public static final int MAX_CAPACITY = 600;
	
	/**
	 * Default floor when elevator is initialized 
	 */
	private int initialFloor;
	
	/**
	 * Direction in which elevator is moving
	 */
	private int direction;
	
	/**
	 * Floor where elevator is present
	 */
	private int floorIndicator;
	
	/**
	 * Last floor which elevator can reach
	 */
	private final int topFloor = 100;
	
	/**
	 * Lowest floor where elevator can be 
	 */
	private final int lowerFloor = 0;
	
	/**
	 * Time taken by elevator to travel one floor. In seconds
	 */
	private int timeTakenPerFloor;
	
	
	/**
	 * Time for which elevator is stopped at a floor. In seconds
	 */
	private int timeTakenPerStop;
	
	/**
	 * If direction is upwards then tillfloor is maxfloor from the requested floor.
	 * If direction is downwards then tillfloor is minfloor from the requested floor.
	 *  
	 */
	private int tillFloor;
	
	
	/**
	 * Status of elevator 
	 */
	private int status;
	
	/**
	 * Floor to be served
	 */
	private TreeSet<Integer> floorsToVisit = null;
	
	/**
	 * Mapping of floors with the requestedIds to that floor. 
	 */
	private Map<Integer, List<String>> mapFloor = null;
	
	Elevator()
	{
		initialFloor = lowerFloor;
		tillFloor = -1;
		status = STATUS_STILL;
		direction = DIRECTION_NONE;
		timeTakenPerFloor = 2;
		timeTakenPerStop  = 10;
		floorsToVisit = new TreeSet<Integer>();
		mapFloor= new HashMap<Integer, List<String>>();
	}
	public int getFloorIndicator() 
	{
		return floorIndicator;
	}
	
	public void setFloorIndicator(int floorIndicator) 
	{
		this.floorIndicator = floorIndicator;
	}
	
	public TreeSet<Integer> getFloorsToVisit() 
	{
		return floorsToVisit;
	}
	public void setFloorsToVisit(TreeSet<Integer> floorsToVisit) 
	{
		this.floorsToVisit = floorsToVisit;
	}
	public int getInitialFloor()
	{
		return initialFloor;
	}
	public void setInitialFloor(int initialFloor)
	{
		this.initialFloor = initialFloor;
	}
	public void setDirection(int direction)
	{
		this.direction = direction;
	}
	public int getDirection()
	{
		return direction;
	}

	public int getTillFloor()
	{
		return tillFloor;
	}
	public void setTillFloor(int tillFloor)
	{
		this.tillFloor = tillFloor;
	}
	public Map<Integer, List<String>> getMapFloor()
	{
		return mapFloor;
	}
	public void setMapFloor(Map<Integer, List<String>> mapFloor)
	{
		this.mapFloor = mapFloor;
	}
	public int getStatus()
	{
		return status;
	}
	public void setStatus(int status)
	{
		this.status = status;
	}
	public int getTopFloor()
	{
		return topFloor;
	}
	public int getLowerFloor()
	{
		return lowerFloor;
	}
	public int getTimeTakenPerFloor()
	{
		return timeTakenPerFloor;
	}
	public int getTimeTakenPerStop()
	{
		return timeTakenPerStop;
	}
}
