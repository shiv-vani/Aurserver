package com.aureus.elevator.controller.service.impl;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aureus.elevator.model.Elevator;
import com.aureus.elevator.util.ElevatorUtil;

/**
 * @author shivani.tulaskar
 * This class is a service to run the Elevator
 */
@Service
public class ElevatorExecutorService
{
	/**
	 * Instance of Elevator 
	 */
	@Autowired
	Elevator mElevator;

	/**
	 * This method consists of tasks . 
	 * Task1 makes elevator move from one floor to another
	 * Task2 makes elevator stop at the required floors
	 */
	public void runElevator()
	{
		ExecutorService lService = Executors.newFixedThreadPool(2);
		
		BlockingQueue<Integer> lQueue = new ArrayBlockingQueue<Integer>(1);
		
		Runnable moveElevatorTask = () ->
		{
			try
			{
				while(true)
				{
					
					//If there are no floors to visit
					if(mElevator.getFloorsToVisit().isEmpty())
					{
						Thread.sleep(5 * 1000);
						continue;
					}
					
					//get current floor of elevator
					int i = mElevator.getFloorIndicator();		
					
					//e.g. : If lift is on floor 5 and user also presses 5
					if(mElevator.getFloorsToVisit().contains(i))
					{
						lQueue.put(i);
						Thread.sleep(1 * 1000);
						continue;
					}
					
					//If max requested floor is reached then change the direction of lift.
					//OR if direction is not set, set the direction and tillfloor
					if(i == mElevator.getTillFloor() || 
							mElevator.getDirection() == Elevator.DIRECTION_NONE)
					{
						System.out.println("changing direction ");
						ElevatorUtil.changeDirection(mElevator);
					}
					
					if(mElevator.getDirection() == Elevator.DIRECTION_UP)
					{
						while(i <= mElevator.getTillFloor())
						{
							System.out.println("Elevator moving upwards");
							lQueue.put(i);
							Thread.sleep(mElevator.getTimeTakenPerFloor() * 1000);
							i++;
						}
					}
					if(mElevator.getDirection() == Elevator.DIRECTION_DOWN)
					{
						while(i >= mElevator.getTillFloor() )
						{
							System.out.println("Elevator moving downwards");
							lQueue.put(i);
							Thread.sleep(1 * 1000);
							i--;
						}
					}
				}
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
		};
		
		Runnable StopElevatorTask = () ->
		{
			try
			{
				while(true)
				{
					Integer floor = lQueue.peek();
					if(floor == null)
					{
						continue;
					}
					mElevator.setFloorIndicator(floor);
					lQueue.take();
					
					System.out.println("Elevator Reached floor : "+ floor);
					if(mElevator.getMapFloor().containsKey(floor))
					{
						List<String> lRequests = mElevator.getMapFloor().get(floor);
						System.out.println("Elevator Reached floor : "+ floor+ ". Door opening..." );
						System.out.print("Request ids served: ");
						
						for(String req : lRequests)
						{
							System.out.print(req+ " ,");
						}
						
						//Remove the mapping
						mElevator.getMapFloor().remove(floor);
						
						//Remove the visited floor
						mElevator.getFloorsToVisit().remove(floor);
						
						//Wait for 10 seconds
						Thread.sleep(mElevator.getTimeTakenPerStop() * 1000);
						
						System.out.println("Door Closing on floor : " + floor);
					}
				}
			} 
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		};
		
		lService.execute(moveElevatorTask);
		lService.execute(StopElevatorTask);
		
		lService.shutdown();
	}
	
}
