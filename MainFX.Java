package smartcity;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import java.util.Map;

public class MainFX extends Application {

    @Override
    public void start(Stage primaryStage) {
        Graph cityGraph = new Graph();

        // Create nodes
        Node A = new Node("A", 50, 50);
        Node B = new Node("B", 200, 50);
        Node C = new Node("C", 50, 200);
        Node D = new Node("D", 200, 200);

        cityGraph.addNode(A); cityGraph.addNode(B);
        cityGraph.addNode(C); cityGraph.addNode(D);

        // Add edges
        cityGraph.addEdge(A, B, 5);
        cityGraph.addEdge(A, C, 10);
        cityGraph.addEdge(B, C, 3);
        cityGraph.addEdge(B, D, 7);
        cityGraph.addEdge(C, D, 1);

        // Traffic simulation
        TrafficSimulator simulator = new TrafficSimulator(cityGraph);
        simulator.updateTraffic();

        // Compute shortest path from A to D using Dijkstra
        Map<Node, Double> distances = Dijkstra.computeShortestPaths(cityGraph, A);

        // Create JavaFX Pane
        Pane pane = new Pane();

        // Draw edges
        for (Node node : cityGraph.getNodes()) {
            for (Edge edge : cityGraph.getEdges(node)) {
                Line line = new Line(
                        edge.getSource().getX(), edge.getSource().getY(),
                        edge.getTarget().getX(), edge.getTarget().getY()
                );
                line.setStroke(Color.GRAY);
                line.setStrokeWidth(2);
                pane.getChildren().add(line);
            }
        }

        // Highlight shortest path from A to D (manually for demo)
        Line shortestLine = new Line(A.getX(), A.getY(), C.getX(), C.getY());
        shortestLine.setStroke(Color.RED);
        shortestLine.setStrokeWidth(3);
        pane.getChildren().add(shortestLine);

        Line shortestLine2 = new Line(C.getX(), C.getY(), D.getX(), D.getY());
        shortestLine2.setStroke(Color.RED);
        shortestLine2.setStrokeWidth(3);
        pane.getChildren().add(shortestLine2);

        // Draw nodes
        for (Node node : cityGraph.getNodes()) {
            Circle circle = new Circle(node.getX(), node.getY(), 15, Color.LIGHTBLUE);
            circle.setStroke(Color.BLACK);
            pane.getChildren().add(circle);
        }

        // Create scene and show
        Scene scene = new Scene(pane, 300, 300);
        primaryStage.setTitle("Smart City Traffic Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
