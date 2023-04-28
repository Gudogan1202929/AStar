import java.util.ArrayList;


public class Vertex implements Comparable<Vertex> {
    private City city;
    private ArrayList<Edge> Adjacents;
    private boolean enqueued;
    private boolean enqueued2;

    public double getHeuristic() {
        return heuristic;
    }

    public void setHeuristic(double heuristic) {
        this.heuristic = heuristic;
    }

    private double heuristic;

    public boolean isEnqueued2() {
        return enqueued2;
    }

    public void setEnqueued2(boolean enqueued2) {
        this.enqueued2 = enqueued2;
    }

    private int heapIndex = -1;

    public int getHeapIndex() {
        return heapIndex;
    }

    public void setHeapIndex(int heapIndex) {
        this.heapIndex = heapIndex;
    }

    public boolean isEnqueued() {
        return enqueued;
    }

    public void setEnqueued(boolean enqueued) {
        this.enqueued = enqueued;
    }

    public boolean isKnown() {
        return known;
    }

    public void setKnown(boolean known) {
        this.known = known;
    }

    public Vertex getPrev() {
        return prev;
    }

    public void setPrev(Vertex prev) {
        this.prev = prev;
    }

    private boolean known;

    public boolean isKnown2() {
        return known2;
    }

    public void setKnown2(boolean known2) {
        this.known2 = known2;
    }

    private boolean known2;
    private Vertex prev;


    private double dis;

    public double getDis() {
        return dis;
    }

    public void setDis(double dis) {
        this.dis = dis;
    }

    public Vertex(City city) {
        this.city = city;
        Adjacents = new ArrayList<>();
    }

    public void addAdjacent(Vertex vertex, double distance) {
        Adjacents.add(new Edge(vertex, distance));
    }


    public City getCity() {
        return city;
    }

    public ArrayList<Edge> getAdjacents() {
        return Adjacents;
    }


    public void setCity(City city) {
        this.city = city;
    }

    public void setAdjacents(ArrayList<Edge> Adjacents) {
        this.Adjacents = Adjacents;
    }


    @Override
    public int compareTo(Vertex o) {
        if (this.dis + this.heuristic > o.getDis() + o.heuristic)
            return 1;
        else if (this.dis + this.heuristic == o.getDis() + o.heuristic)
            return 0;
        else
            return -1;

    }
}