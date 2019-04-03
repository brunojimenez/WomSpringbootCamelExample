package cl.wom.middleware.bean;

import java.util.logging.Logger;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class CamelProcessor implements Processor {

	private final static Logger LOGGER = Logger.getLogger(CamelProcessor.class.toString());

	@Override
	public void process(Exchange exchange) throws Exception {
		LOGGER.info("process init");

		LOGGER.info("process end");
	}

}
