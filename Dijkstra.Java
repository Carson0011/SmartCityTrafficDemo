package smartcity;

import java.util.*;

public class Dijkstra {
    public static Map<Node, Double> computeShortestPaths(Graph graph, Node start) {
        Map<Node, Double> distances = new HashMap<>();
        PriorityQueue<NodeDistance> pq = new PriorityQueue<>(Comparator.comparingDouble(nd -> nd.distance));

        for (Node node : graph.getNodes()) distances.put(node, Double.POSITIVE_INFINITY);
        distances.put(start, 0.0);
        pq.add(new NodeDistance(start, 0));

        while (!pq.isEmpty()) {
            NodeDistance current = pq.poll();
            for (Edge edge : graph.getEdges(current.node)) {
                double newDist = distances.get(current.node) + edge.getWeight();
                if (newDist < distances.get(edge.getTarget())) {
                    distances.put(edge.getTarget(), newDist);
                    pq.add(new NodeDistance(edge.getTarget(), newDist));
                }
            }
        }
        return distances;
    }

    static class NodeDistance {
        Node node;
        double distance;
        NodeDistance(Node node, double distance) { this.node = node; this.distance = distance; }
    }
}
