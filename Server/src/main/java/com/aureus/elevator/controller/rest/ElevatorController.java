package com.aureus.elevator.controller.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.aureus.elevator.controller.model.ElevatorBean;
import com.aureus.elevator.controller.model.ElevatorConfigBean;
import com.aureus.elevator.controller.model.ElevatorMoveBean;
import com.aureus.elevator.controller.service.impl.AurElevatorService;
import com.aureus.elevator.exception.InvalidFloorException;
import com.aureus.elevator.exception.ServerException;
import com.aureus.elevator.exception.ServerExceptionInfo;
import com.aureus.elevator.model.Elevator;

/**
 * @author shivani.tulaskar
 * Rest Controller for handling elevator api requests.
 */
@RestController
@RequestMapping("/elevator")
public class ElevatorController 
{
	@Autowired
	@Qualifier("elevator")
	private Elevator mElevator;
	
	/**
	 * Service to perform elevator operations
	 */
	@Autowired
	private AurElevatorService mElevatorService;
	
	/**
	 * This api returns configurations of Elevator
	 * @param pRequest
	 * @param pRequestId
	 * @return ElevatorConfigBean
	 * @throws ServerException
	 */
	@GetMapping(path = "/config", produces = "application/json")
	public ElevatorConfigBean getElevatorConfig(HttpServletRequest pRequest, 
			@RequestHeader("X-Request-Id") String pRequestId) throws ServerException
	{
		if(pRequestId == null || pRequestId.isBlank())
		{
			pRequestId = "-1";
		}
		ElevatorConfigBean lbean = null;
		try
		{
			lbean = mElevatorService.getElevatorConfig(pRequestId);
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lbean;
	}
	
	/**
	 * This api brings elevator from its current location to specified 
	 * location and will return the elevator instance at that point
	 * @param pRequest
	 * @param floor
	 * @param pRequestId
	 * @return ElevatorBean
	 * @throws ServerException
	 */
	@GetMapping(path = "/call/{floor}", produces = "application/json")
	public ElevatorBean getElevator(HttpServletRequest pRequest, 
				@PathVariable("floor") int floor,
				@RequestHeader("X-Request-Id") String pRequestId) throws ServerException
	{
		System.out.println("elevator called to floor : " + floor );
		if(pRequestId == null || pRequestId.isBlank())
		{
			pRequestId = "-1";
		}
		ElevatorBean lbean = null;
		try
		{
			lbean = mElevatorService.getElevator(pRequestId, floor);
		} 
		catch (Exception e)
		{
			 wrapException(e);
		}
		return lbean;
	}
	
	/**
	 * This api will queue the requested floors and responds with ACCEPTED status.
	 * Elevator will then move on requested floors.
	 * @param pRequest
	 * @param pMoveBean
	 * @param pRequestId
	 */
	@PostMapping(path = "/move", produces = "application/json", consumes = "application/json")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public void move(HttpServletRequest pRequest, @RequestBody ElevatorMoveBean pMoveBean, 
			@RequestHeader("X-Request-Id") String pRequestId) throws ServerException
	{	
		if(pRequestId == null || pRequestId.isBlank())
		{
			pRequestId = "-1";
		}
		
		try
		{
			mElevatorService.moveElevator(pRequestId, pMoveBean);
		} 
		catch (Exception e)
		{
			wrapException(e);
		}
	}
	
	
	/**
	 * API to know if Elevator is in working state or not
	 * @param pRequest
	 * @return
	 */
	//TODO
//	@GetMapping(path = "/status", produces = "application/json")
//	public int getStatus(HttpServletRequest pRequest)
//	{
//		return mElevator.getStatus();
//	}
	
	/**
	 * API to get the direction of Elevator
	 * @param pRequest
	 * @return
	 */
	@GetMapping(path = "/direction", produces = "application/json")
	public int getDirection(HttpServletRequest pRequest)
	{
		return mElevator.getDirection();
	}
	
	/**
	 * API to know where Elevator is at present
	 * @param pRequest
	 * @return
	 */
	@GetMapping(path = "/position", produces = "application/json")
	public int getFloorPosition(HttpServletRequest pRequest)
	{
		return mElevator.getFloorIndicator();
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ServerException.class)
	@ResponseBody ServerExceptionInfo
	handleBadRequest(HttpServletRequest req, Exception ex)
	{
	    return new ServerExceptionInfo(HttpStatus.BAD_REQUEST.toString(), ex.getMessage());
	} 
	
	private void wrapException(Exception e) throws ServerException
	{
		if(e instanceof ServerException)
		{
			if(e instanceof InvalidFloorException)
			{
				System.out.println("ERROR: Invalid floor number in request. ");
				throw new InvalidFloorException(e.getMessage(), e);
			}
			else
			{
				System.out.println("ERROR: Empty floor list ");
				throw new ServerException(e.getMessage(), e);
			}
		}
		else
		{
			 throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
					 						   "Something went wrong while getting elevator", e);
		}
	}
	
}

