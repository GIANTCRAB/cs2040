package lostmap;

public class Edge {
    private final Integer v;
    private final Integer w;
    private final Integer distance;

    public Edge(Integer v, Integer w, Integer distance) {
        this.v = v;
        this.w = w;
        this.distance = distance;
    }

    public Integer getV() {
        return v;
    }

    public Integer getW() {
        return w;
    }

    public Integer getDistance() {
        return distance;
    }
}
