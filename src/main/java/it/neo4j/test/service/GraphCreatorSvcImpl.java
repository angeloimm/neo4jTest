package it.neo4j.test.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import it.neo4j.test.domain.GraphNode;
import it.neo4j.test.repositories.Neo4jGraphRepository;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

@Service
public class GraphCreatorSvcImpl implements IGraphCreatorService {
	
	private static final Log LOG = LogFactory.getLog(GraphCreatorSvcImpl.class.getName());
	@Autowired
	private Neo4jGraphRepository repo;
	@Override
	public void createGraph() throws Exception {
		if( LOG.isDebugEnabled() ){
			LOG.debug("Starting test case");
		}
		try {
			StopWatch sw = new StopWatch();
			sw.start("neo4j node and relationships creation");
			Map<Long, GraphNode> nodes = new HashMap<Long, GraphNode>();
			for ( int i = 0; i < 5; i++){
				
				GraphNode aNode = createGraphNode();
				nodes.put(new Long(aNode.getNodeId()), aNode);
			}		
			createRelationshipsForNode1(nodes);
			createRelationshipsForNode2(nodes);
			createRelationshipsForNode3(nodes);
			createRelationshipsForNode4(nodes);
			createRelationshipsForNode5(nodes);
//			//Starting nodes and relationships creation by filling them with random values with incoming direction relationships
//			for (int i = 0; i < nodesNumber; i++) {
//				GraphNode startNode = createGraphNode();
//				GraphNode endNode = createGraphNode();
//				startNode.addIncomingRelationship(endNode, (float)Math.random(), ((long)(Math.random()*100)));
////				repo.save(startNode);
//			}
//			//Starting nodes and relationships creation by filling them with random values with outcoming direction relationships
//			for (int i = 0; i < nodesNumber; i++) {
//				GraphNode startNode = createGraphNode();
//				GraphNode endNode = createGraphNode();
//				startNode.addOutcomingRelationship(endNode, (float)Math.random(), ((long)(Math.random()*100)));
////				repo.save(startNode);
//			}
//			//Starting nodes and relationships creation by filling them with random values with both direction relationships
//			for (int i = 0; i < nodesNumber; i++) {
//				GraphNode startNode = createGraphNode();
//				GraphNode endNode = createGraphNode();
//				startNode.addBothRelationship(endNode, (float)Math.random(), ((long)(Math.random()*100)));
////				repo.save(startNode);
//			}
			sw.stop();
			if( LOG.isInfoEnabled() ){
				
				LOG.info("Task "+sw.getLastTaskName()+" finished in "+sw.getLastTaskTimeMillis()+" millis");
			}
		} catch (Exception e) {
			
			String message = "Error during graph creation; error message: "+e.getMessage();
			LOG.fatal(message, e);
			throw e;
		}
	}
	private GraphNode createGraphNode(){
		GraphNode element = new GraphNode();
		element.setNodeDbId("GRAPH_NODE_DB_ID_"+(UUID.randomUUID().toString()));
		element.setM(Math.random());
		element.setX(Math.random());
		element.setY(Math.random());
		element.setZ(Math.random());
		GraphNode result = repo.save(element);
		if( LOG.isInfoEnabled() ){
			LOG.info("Created node with ID: "+result.getNodeId());
		}
		return result;
	}
	private void createRelationshipsForNode1(Map<Long, GraphNode> nodes){
		
		GraphNode nodo1 = nodes.get(new Long(1));
		nodo1.addOutcomingRelationship(nodes.get(new Long(2)), ( (float)(Math.random()*10) ), ( (long)(Math.random()*1000) ));
		nodo1.addBothRelationship(nodes.get(new Long(4)), ( (float)(Math.random()*10) ), ( (long)(Math.random()*1000) ));
	}
	private void createRelationshipsForNode2(Map<Long, GraphNode> nodes){
		
		GraphNode nodo1 = nodes.get(new Long(2));
		nodo1.addIncomingRelationship(nodes.get(new Long(4)), ( (float)(Math.random()*10) ), ( (long)(Math.random()*1000) ));
		nodo1.addBothRelationship(nodes.get(new Long(3)), ( (float)(Math.random()*10) ), ( (long)(Math.random()*1000) ));
	}
	private void createRelationshipsForNode3(Map<Long, GraphNode> nodes){
		
		GraphNode nodo1 = nodes.get(new Long(3));
		nodo1.addBothRelationship(nodes.get(new Long(1)), ( (float)(Math.random()*10) ), ( (long)(Math.random()*1000) ));
	}
	private void createRelationshipsForNode4(Map<Long, GraphNode> nodes){
		
		GraphNode nodo1 = nodes.get(new Long(4));
		nodo1.addOutcomingRelationship(nodes.get(new Long(5)), ( (float)(Math.random()*10) ), ( (long)(Math.random()*1000) ));
	}
	private void createRelationshipsForNode5(Map<Long, GraphNode> nodes){
		
		GraphNode nodo1 = nodes.get(new Long(5));
		nodo1.addOutcomingRelationship(nodes.get(new Long(1)), ( (float)(Math.random()*10) ), ( (long)(Math.random()*1000) ));
		nodo1.addIncomingRelationship(nodes.get(new Long(2)), ( (float)(Math.random()*10) ), ( (long)(Math.random()*1000) ));
		nodo1.addBothRelationship(nodes.get(new Long(3)), ( (float)(Math.random()*10) ), ( (long)(Math.random()*1000) ));
		nodo1.addBothRelationship(nodes.get(new Long(4)), ( (float)(Math.random()*10) ), ( (long)(Math.random()*1000) ));
	}
}
