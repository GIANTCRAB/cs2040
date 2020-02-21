package assignment4;

/**
 * @author WOO HUIREN ( A0202242B )
 */
public class Hand {
    private HandState handState;
    private Player player;

    public Hand(Player player) {
        this(player, HandState.OKAY);
    }

    public Hand(Player player, HandState handState) {
        this.setPlayer(player);
        this.setHandState(handState);
    }

    public HandState getHandState() {
        return handState;
    }

    public void setHandState(HandState handState) {
        this.handState = handState;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
