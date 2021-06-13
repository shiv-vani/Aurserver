package com.aureus.elevator.util;

import java.util.Set;

import org.springframework.stereotype.Component;

import com.aureus.elevator.exception.InvalidFloorException;
import com.aureus.elevator.model.Elevator;

@Component
public class ElevatorUtil
{
	/**
	 * Changes direction of Elevator.
	 * @param pElevator
	 */
	public static void changeDirection(Elevator pElevator)
	{	
		if(pElevator.getFloorsToVisit().isEmpty())
		{
			pElevator.setDirection(Elevator.DIRECTION_NONE);
		}
		//If direction is up, change to down 
		//and set till floor as min value of available floors
		if(pElevator.getDirection() == Elevator.DIRECTION_UP || pElevator.getDirection() == Elevator.DIRECTION_NONE)
		{
			int startFloor = pElevator.getFloorsToVisit().first();
			if(startFloor <= pElevator.getTillFloor())
			{
				pElevator.setDirection(Elevator.DIRECTION_DOWN);
			}
			else
			{
				pElevator.setDirection(Elevator.DIRECTION_UP);
			}
			pElevator.setTillFloor(startFloor);
		}
		else
			if(pElevator.getDirection() == Elevator.DIRECTION_DOWN)
			{
				int startFloor = pElevator.getFloorsToVisit().last();
				
				pElevator.setDirection(Elevator.DIRECTION_UP);
				pElevator.setTillFloor(startFloor);
			}
	}
	
	public static void validateFloor(int pFloor, Elevator pElevator) throws InvalidFloorException
	{
		if(pFloor < pElevator.getLowerFloor())
		{
			throw new InvalidFloorException("Floor number cannot be less than "+ pElevator.getLowerFloor());
		}
		if(pFloor > pElevator.getTopFloor())
		{
			throw new InvalidFloorException("Floor number cannot be greater than "+ pElevator.getTopFloor());
		}
	}
	
	public static void validateFloors(Set<Integer> pFloors, Elevator pElevator) throws InvalidFloorException
	{
		for(int i : pFloors)
		{
			validateFloor(i,pElevator);
		}
	}
	
}
