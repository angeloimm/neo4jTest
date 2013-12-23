package it.neo4j.test.domain;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;
import org.springframework.util.Assert;
@RelationshipEntity(type="ROAD_ELEMENT")
public class GraphRelationship {
	@GraphId
	private Long relId;
	//DB Record ID
	@Indexed(indexName="RELATIONSHIP_DB_ID", numeric=false, unique=true)
	private String elementoStradaleDbId;
	@StartNode
	private GraphNode nodoIniziale;
	@EndNode
	private GraphNode nodoFinale;
	
	//Peso dell'arco (lunghezza dell'elemento stradale)
	private float lunghezzaArco;
	
	
	public GraphRelationship(GraphNode nodoIniziale, GraphNode nodoFinale, float lunghezzaArco, long elementoStradaleDbId){
		
		setNodoIniziale(nodoIniziale);
		setNodoFinale(nodoFinale);
		setLunghezzaArco(lunghezzaArco);
		setElementoStradaleDbId("elementoStradaleDbId_"+elementoStradaleDbId);
	}
	public GraphRelationship(){
		
	}
	public GraphNode getNodoIniziale() {
		return nodoIniziale;
	}

	public void setNodoIniziale(GraphNode nodoIniziale) {
		Assert.notNull(nodoIniziale, "Null start node...Impossibile to continue"); 
		this.nodoIniziale = nodoIniziale;
	}

	public GraphNode getNodoFinale() {
		return nodoFinale;
	}

	public void setNodoFinale(GraphNode nodoFinale) {
		Assert.notNull(nodoFinale, "Null end node...Impossibile to continue");
		this.nodoFinale = nodoFinale;
	}

	public float getLunghezzaArco() {
		return lunghezzaArco;
	}

	public void setLunghezzaArco(float lunghezzaArco) {
		this.lunghezzaArco = lunghezzaArco;
	}
	public String getElementoStradaleDbId() {
		return elementoStradaleDbId;
	}
	public void setElementoStradaleDbId(String elementoStradaleDbId) {
		this.elementoStradaleDbId = elementoStradaleDbId;
	}	
}
