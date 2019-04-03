package cl.wom.middleware.bean;

import java.util.logging.Logger;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import cl.wom.middleware.dao.DatabaseDAO;

public class CamelProcessor implements Processor {

	private final static Logger LOGGER = Logger.getLogger(CamelProcessor.class.toString());

	@Override
	public void process(Exchange exchange) throws Exception {
		LOGGER.info("process init");

		DatabaseDAO databaseDAO = new DatabaseDAO();
		databaseDAO.testQuery();
		databaseDAO.testSysRefcursor();

		LOGGER.info("process end");
	}

}
