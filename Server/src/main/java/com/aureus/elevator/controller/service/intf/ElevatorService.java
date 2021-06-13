package com.aureus.elevator.controller.service.intf;

import org.springframework.stereotype.Component;

import com.aureus.elevator.controller.model.ElevatorBean;
import com.aureus.elevator.controller.model.ElevatorConfigBean;
import com.aureus.elevator.controller.model.ElevatorMoveBean;

/**
 * @author shivani.tulaskar
 *T his interface will act as a service which performs elevator operations
 */
@Component
public interface ElevatorService
{
	/**
	 * This method will return the configuration of Elevator
	 * @param pRequestId
	 * @return ElevatorConfigBean
	 * @throws Exception
	 */
	public ElevatorConfigBean getElevatorConfig(String pRequestId) throws Exception;
	
	/**
	 * This method will bring elevator from its current location to specified 
	 * location and will return the elevator instance at that point
	 * @param pRequestId
	 * @param floor
	 * @return ElevatorBean
	 * @throws Exception
	 */
	public ElevatorBean getElevator(String pRequestId, int floor) throws Exception;
	
	/**
	 * 
	 * This method represents call to move to a floor from inside the elevatos
	 * @param pRequestId
	 * @param pMoveBean consist of floor details to visit
	 * @throws Exception
	 */
	public void moveElevator(String pRequestId, ElevatorMoveBean pMoveBean) throws Exception;
	
	/**
	 * This method gives the status of Elevator
	 * @param pRequestId
	 * @return status as ACTIVE, STILL or OUT_OF_USE
	 * @throws Exception
	 */
	public int getStatus(String pRequestId) throws Exception;
	
	/**
	 * This method gives the direction in which the elevator is moving
	 * @param pRequestId
	 * @return direction
	 * @throws Exception
	 */
	public int getDirection(String pRequestId) throws Exception;
	
	/**
	 * This method gives the current floor of Elevator
	 * @param pRequestId
	 * @return floor number
	 * @throws Exception
	 */
	public int getFloorPosition(String pRequestId) throws Exception;
}
