package it.inera.abife.web;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

public class StartupServlet extends HttpServlet {

	private Log log;

	private static Scheduler sched = null;

	public final void init(ServletConfig config) throws ServletException {

		log = LogFactory.getLog(StartupServlet.class);
		log.info("Initialization OpaclibServlet starting...");

		// First we must get a reference to a scheduler
		try {
			SchedulerFactory sf = new StdSchedulerFactory();
			sched = sf.getScheduler();
			log.info("------- Initialization Complete -----------");
			log.info("------- Starting Scheduler ----------------");
			// start the schedule 
			sched.start();
			log.info("------- Started Scheduler -----------------");
		} catch (Throwable e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}

}
