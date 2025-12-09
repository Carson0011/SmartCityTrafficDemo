package smartcity;

import java.util.Random;

public class TrafficSimulator {
    private Graph graph;
    private Random rand = new Random();

    public TrafficSimulator(Graph graph) { this.graph = graph; }

    public void updateTraffic() {
        for (Node node : graph.getNodes()) {
            for (Edge edge : graph.getEdges(node)) {
                double congestionFactor = 1 + rand.nextDouble(); // 1x to 2x weight
                edge.setWeight(edge.getWeight() * congestionFactor);
            }
        }
    }
}
