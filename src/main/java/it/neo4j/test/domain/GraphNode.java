package it.neo4j.test.domain;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedToVia;

@NodeEntity
public class GraphNode {
	@GraphId
	private Long nodeId;
	//DB Record ID
	@Indexed(indexName="NODE_DB_ID",unique=true,numeric=false)
	private String nodeDbId;
	@Indexed(indexName="COORDINATA_X",unique=false,numeric=true)
	private double x;
	@Indexed(indexName="COORDINATA_Y",unique=false,numeric=true)
	private double y;
	@Indexed(indexName="COORDINATA_Z",unique=false,numeric=true)
	private double z;
	@Indexed(indexName="COORDINATA_M",unique=false,numeric=true)
	private double m;
	//Relationships
	@RelatedToVia(direction=Direction.INCOMING,elementClass=GraphRelationship.class)
	private Set<GraphRelationship> incomingRelationships = new HashSet<GraphRelationship>();
	@RelatedToVia(direction=Direction.OUTGOING,elementClass=GraphRelationship.class)
	private Set<GraphRelationship> outcomingRelationships = new HashSet<GraphRelationship>();
	@RelatedToVia(direction=Direction.BOTH, elementClass=GraphRelationship.class)
	private Set<GraphRelationship> bothRelationships = new HashSet<GraphRelationship>();
	
	public Long getNodeId() {
		return nodeId;
	}
	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	public String getNodeDbId() {
		return nodeDbId;
	}
	public void setNodeDbId(String nodeDbId) {
		this.nodeDbId = nodeDbId;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public double getZ() {
		return z;
	}
	public void setZ(double z) {
		this.z = z;
	}
	public double getM() {
		return m;
	}
	public void setM(double m) {
		this.m = m;
	}

	public Set<GraphRelationship> getIncomingRelationships() {
		return incomingRelationships;
	}
	public void setIncomingRelationships(Set<GraphRelationship> incomingRelationships) {
		this.incomingRelationships = incomingRelationships;
	}
	public Set<GraphRelationship> getOutcomingRelationships() {
		return outcomingRelationships;
	}
	public void setOutcomingRelationships(Set<GraphRelationship> outcomingRelationships) {
		this.outcomingRelationships = outcomingRelationships;
	}
	public Set<GraphRelationship> getBothRelationships() {
		return bothRelationships;
	}
	public void setBothRelationships(Set<GraphRelationship> bothRelationships) {
		this.bothRelationships = bothRelationships;
	}
	public void addOutcomingRelationship(GraphNode endNode, float lunghezzaArco, long elementoStrdaleDbId ){
		GraphRelationship relation = new GraphRelationship(this, endNode, lunghezzaArco, elementoStrdaleDbId);
		if( !outcomingRelationships.contains(relation) ){
			outcomingRelationships.add(relation);
		}
	}
	public void addIncomingRelationship(GraphNode endNode, float lunghezzaArco, long elementoStrdaleDbId ){
		GraphRelationship relation = new GraphRelationship(this, endNode, lunghezzaArco, elementoStrdaleDbId);
		if( !incomingRelationships.contains(relation) ){
			incomingRelationships.add(relation);
		}
	}	
	public void addBothRelationship(GraphNode endNode, float lunghezzaArco, long elementoStrdaleDbId ){
		GraphRelationship relation = new GraphRelationship(this, endNode, lunghezzaArco, elementoStrdaleDbId);
		if( !bothRelationships.contains(relation) ){
			bothRelationships.add(relation);
		}
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Record DB ID: ");
		sb.append(getNodeDbId());
		sb.append(". X coordinate ");
		sb.append(getX());
		sb.append(". Y coordinate ");
		sb.append(getY());
		sb.append(". Z coordinate ");
		sb.append(getZ());		
		return sb.toString();
	}
}
