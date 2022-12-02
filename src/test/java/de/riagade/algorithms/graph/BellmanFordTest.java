package de.riagade.algorithms.graph;

import de.riagade.algorithms.graph.BellmanFord.Point;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


class BellmanFordTest {
	public List<Point> points;

	@BeforeEach
	void setUp() {
		points = new LinkedList<>();
		var pointA = new Point();
		var pointB = new Point();
		var pointC = new Point();
		var pointD = new Point();
		var pointE = new Point();
		var pointF = new Point();
		points.addAll(List.of(pointA, pointB, pointC, pointD, pointE, pointF));
		pointA.addEdge(pointB, 10);
		pointA.addEdge(pointC, 3);
		pointB.addEdge(pointC, -1);
		pointB.addEdge(pointD, 2);
		pointC.addEdge(pointB, 4);
		pointC.addEdge(pointD, -8);
		pointC.addEdge(pointE, 2);
		pointD.addEdge(pointE, 7);
		pointD.addEdge(pointF, -4);
		pointE.addEdge(pointF, 5);
	}

	@Test
	void calculateShortestPaths() {
		// Given
		var bellmanFord = new BellmanFord(points);
		var start = points.get(0);

		// When
		var shortestPaths = bellmanFord.calculateShortestPaths(start);

		// Then
		assertEquals(0, shortestPaths.get(points.get(0)));
		assertEquals(7, shortestPaths.get(points.get(1)));
		assertEquals(3, shortestPaths.get(points.get(2)));
		assertEquals(-5, shortestPaths.get(points.get(3)));
		assertEquals(2, shortestPaths.get(points.get(4)));
		assertEquals(-9, shortestPaths.get(points.get(5)));
	}
}
