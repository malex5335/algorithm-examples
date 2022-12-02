package de.riagade.algorithms.graph;

import java.util.*;

public class BellmanFord {
	private final List<Point> points;

	public BellmanFord(List<Point> points) {
		this.points = points;
	}

	public Map<Point, Integer> calculateShortestPaths(Point start) {
		if(!points.contains(start))
			throw new IllegalArgumentException("Start point is not part of the graph");
		var shortestPaths = new HashMap<Point, Integer>();
		shortestPaths.put(start, 0);
		var hashBefore = 0;
		while (hashBefore != shortestPaths.hashCode()) {
			hashBefore = shortestPaths.hashCode();
			points.forEach(point -> point.getEdges().forEach((edge, edgeDistance) -> {
				var currentShortestPath = shortestPaths.getOrDefault(edge, Integer.MAX_VALUE);
				var newShortestPath = shortestPaths.get(point) + edgeDistance;
				if (newShortestPath < currentShortestPath) {
					shortestPaths.put(edge, newShortestPath);
				}
			}));
		}
		return shortestPaths;
	}

	public static class Point {
		private final Map<Point, Integer> edges = new HashMap<>();

		public void addEdge(Point point, int distance) {
			edges.put(point, distance);
		}

		public Map<Point, Integer> getEdges() {
			return edges;
		}
	}
}
