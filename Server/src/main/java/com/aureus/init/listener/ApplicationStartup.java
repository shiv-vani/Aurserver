package com.aureus.init.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

import com.aureus.init.Global;

/**
 * @author shivani.tulaskar
 * This class is a startup listener which is called when this application runs
 */
@Configuration
public class ApplicationStartup
{
	/**
	 * This class contains initialization methods 
	 */
	@Autowired
	private Global lGlobal;
	
	/**
	 * Entry point.
	 * Application is initialized from here
	 */
	@EventListener(ContextRefreshedEvent.class)
	public void contextRefreshedEvent() 
	{
		lGlobal.init();
	}
	
}
