/**
 *
 */
package com.sharobi.yewpos.init;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/*
*//**
 * @author habib, SK A SIDDIK
 *
 */
public class Initializer implements ServletContextListener {
	private static final Logger logger = LoggerFactory.getLogger(Initializer.class);

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		//logger.info("Context destroying[.....] : " + arg0.getServletContext().getContextPath());
		//logger.info("Context destroyed[-----] : " + arg0.getServletContext().getContextPath());
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		//logger.info("Context initializing[.....] : " + arg0.getServletContext().getContextPath());
		//logger.info("Context initialized[+++++] : " + arg0.getServletContext().getContextPath());
	}
}
