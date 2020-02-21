package assignment4;

/**
 * @author WOO HUIREN ( A0202242B )
 */
public class Player {
    private int id;

    public Player(int id) {
        this.setId(id);
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "" + id;
    }
}
