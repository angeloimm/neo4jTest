package it.neo4j.test;

import it.neo4j.test.service.IGraphCreatorService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(value={
		"classpath:application-context.xml"
})

@RunWith(SpringJUnit4ClassRunner.class)
public class GraphCrationTestCase {
	
	private static final Log LOG = LogFactory.getLog(GraphCrationTestCase.class.getName());
	@Autowired
	private IGraphCreatorService svc;
	
	@Test
	public void graphCreationTest() throws Exception {
		
		try {
			svc.createGraph();
		} catch (Exception e) {
			
			String message = "Error during the test: "+e.getMessage();
			LOG.fatal(message, e);
			throw e;
		}
	}
}
