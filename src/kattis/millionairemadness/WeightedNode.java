package kattis.millionairemadness;

/**
 * @author WOO HUIREN ( A0202242B )
 */
class WeightedNode<T> {
    private final T posY;
    private final T posX;
    private final Integer cost;

    WeightedNode(T positionY, T positionX, Integer cost) {
        this.posY = positionY;
        this.posX = positionX;
        this.cost = cost;
    }

    public Integer getCost() {
        return cost;
    }

    public T getPosY() {
        return posY;
    }

    public T getPosX() {
        return posX;
    }
}
