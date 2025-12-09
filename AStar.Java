package smartcity;

import java.util.*;

public class AStar {
    public static Map<Node, Double> computeShortestPaths(Graph graph, Node start, Node goal) {
        Map<Node, Double> gScore = new HashMap<>();
        Map<Node, Double> fScore = new HashMap<>();
        PriorityQueue<NodeDistance> openSet = new PriorityQueue<>(Comparator.comparingDouble(nd -> nd.distance));

        for (Node node : graph.getNodes()) {
            gScore.put(node, Double.POSITIVE_INFINITY);
            fScore.put(node, Double.POSITIVE_INFINITY);
        }
        gScore.put(start, 0.0);
        fScore.put(start, heuristic(start, goal));
        openSet.add(new NodeDistance(start, fScore.get(start)));

        while (!openSet.isEmpty()) {
            Node current = openSet.poll().node;
            if (current.equals(goal)) break;

            for (Edge edge : graph.getEdges(current)) {
                double tentativeG = gScore.get(current) + edge.getWeight();
                if (tentativeG < gScore.get(edge.getTarget())) {
                    gScore.put(edge.getTarget(), tentativeG);
                    fScore.put(edge.getTarget(), tentativeG + heuristic(edge.getTarget(), goal));
                    openSet.add(new NodeDistance(edge.getTarget(), fScore.get(edge.getTarget())));
                }
            }
        }

        return gScore;
    }

    private static double heuristic(Node a, Node b) {
        return Math.hypot(a.getX() - b.getX(), a.getY() - b.getY());
    }

    static class NodeDistance {
        Node node;
        double distance;
        NodeDistance(Node node, double distance) { this.node = node; this.distance = distance; }
    }
}
