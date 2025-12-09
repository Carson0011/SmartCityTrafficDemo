package smartcity;

public class Node {
    private String id;
    private double x, y;

    public Node(String id, double x, double y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public String getId() { return id; }
    public double getX() { return x; }
    public double getY() { return y; }

    @Override
    public String toString() { return id; }
}
