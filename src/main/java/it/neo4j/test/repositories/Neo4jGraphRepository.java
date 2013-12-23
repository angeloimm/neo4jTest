package it.neo4j.test.repositories;

import it.neo4j.test.domain.GraphNode;

import org.springframework.data.neo4j.repository.GraphRepository;

public interface Neo4jGraphRepository extends GraphRepository<GraphNode> {

}
