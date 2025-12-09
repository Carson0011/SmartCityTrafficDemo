package smartcity;

import java.util.*;

public class Graph {
    private Map<Node, List<Edge>> adjacencyList = new HashMap<>();

    public void addNode(Node node) {
        adjacencyList.putIfAbsent(node, new ArrayList<>());
    }

    public void addEdge(Node source, Node target, double weight) {
        adjacencyList.get(source).add(new Edge(source, target, weight));
    }

    public List<Edge> getEdges(Node node) {
        return adjacencyList.getOrDefault(node, new ArrayList<>());
    }

    public Set<Node> getNodes() {
        return adjacencyList.keySet();
    }
}
