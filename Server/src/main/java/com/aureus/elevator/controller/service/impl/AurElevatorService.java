package com.aureus.elevator.controller.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.aureus.elevator.controller.model.ElevatorBean;
import com.aureus.elevator.controller.model.ElevatorConfigBean;
import com.aureus.elevator.controller.model.ElevatorMoveBean;
import com.aureus.elevator.controller.service.intf.ElevatorService;
import com.aureus.elevator.exception.ServerException;
import com.aureus.elevator.model.Elevator;
import com.aureus.elevator.util.ElevatorUtil;

/**
 * @author shivani.tulaskar
 * This class serves as service for elevator functionalities
 */
@Service
public class AurElevatorService implements ElevatorService
{
	@Autowired
	@Qualifier("elevator")
	private Elevator mElevator;
	
	@Override
	public ElevatorConfigBean getElevatorConfig(String pRequestId) throws Exception
	{
		ElevatorConfigBean lBean = new ElevatorConfigBean();
		lBean.setLowerFloor(mElevator.getLowerFloor());
		lBean.setTopFloor(mElevator.getTopFloor());
		lBean.setMaxCapacity(Elevator.MAX_CAPACITY);
		lBean.setTimeTakenPerFloor(mElevator.getTimeTakenPerFloor());
		lBean.setTimeTakenPerStop(mElevator.getTimeTakenPerStop());
		
		return lBean;
	}
		
	@Override
	public ElevatorBean getElevator(String pRequestId, int floor) throws Exception
	{	
		//Validate wrong input of floor number
		ElevatorUtil.validateFloor(floor, mElevator);
		
		//Add floor details to elevator
		updateElevatorDetails(pRequestId, floor);
		mElevator.getFloorsToVisit().add(floor);
		
		//Wait till the elevator reaches the called floor
		waitForTheElevator(floor);
		
		ElevatorBean lbean = new ElevatorBean();
		lbean.setFloor(mElevator.getFloorIndicator());
		lbean.setDirection(mElevator.getDirection());
		lbean.setDestinationFloor(mElevator.getFloorsToVisit());
		return lbean;
	}

	@Override
	public void moveElevator(String pRequestId, ElevatorMoveBean pMoveBean) throws Exception
	{	
		//Validate wrong input of floor number
		ElevatorUtil.validateFloors(pMoveBean.getDestinationFloors(), mElevator);
		
		//Add floors input to elevator
		Set<Integer> floors = new HashSet<Integer>();
		floors.addAll(pMoveBean.getDestinationFloors());
		if(floors.isEmpty())
		{
			System.out.println("Request-id: "+ pRequestId + ": ERROR : No destination floors provided in request for move. Atleast one floor is required.");
			throw new ServerException("No floors provided from inside the elevator");
		}
		for(int floor: floors)
		{
			updateElevatorDetails(pRequestId, floor);
		}
		mElevator.getFloorsToVisit().addAll(floors);
	}

	@Override
	public int getStatus(String pRequestId) throws Exception
	{
		return mElevator.getStatus();
	}

	@Override
	public int getDirection(String pRequestId) throws Exception
	{
		return mElevator.getDirection();
	}

	@Override
	public int getFloorPosition(String pRequestId) throws Exception
	{
		return mElevator.getFloorIndicator();
	}
	
	private synchronized void updateElevatorDetails(String pRequestId, int floor)
	{
		//Map request-id to the floor
		List<String> lids = mElevator.getMapFloor().getOrDefault(floor, new ArrayList<String>());
		lids.add(pRequestId);
		mElevator.getMapFloor().put(floor, lids);
	}
	
	private void waitForTheElevator(int floor)
	{
		while(mElevator.getFloorIndicator() != floor)
		{			
			try
			{
				Thread.sleep(1 * 1000);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
	
}
