package smartcity;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Graph cityGraph = new Graph();

        // Nodes
        Node A = new Node("A", 0, 0);
        Node B = new Node("B", 1, 1);
        Node C = new Node("C", 2, 0);
        Node D = new Node("D", 3, 1);

        cityGraph.addNode(A); cityGraph.addNode(B);
        cityGraph.addNode(C); cityGraph.addNode(D);

        // Edges
        cityGraph.addEdge(A, B, 5);
        cityGraph.addEdge(A, C, 10);
        cityGraph.addEdge(B, C, 3);
        cityGraph.addEdge(B, D, 7);
        cityGraph.addEdge(C, D, 1);

        // Simulate traffic
        TrafficSimulator simulator = new TrafficSimulator(cityGraph);
        simulator.updateTraffic();

        // Dijkstra
        System.out.println("=== Dijkstra Shortest Paths from A ===");
        Map<Node, Double> distances = Dijkstra.computeShortestPaths(cityGraph, A);
        distances.forEach((node, dist) -> System.out.println("Distance to " + node + ": " + dist));

        // A*
        System.out.println("\n=== A* Shortest Path from A to D ===");
        Map<Node, Double> aStarDistances = AStar.computeShortestPaths(cityGraph, A, D);
        aStarDistances.forEach((node, dist) -> System.out.println("Distance to " + node + ": " + dist));
    }
}
