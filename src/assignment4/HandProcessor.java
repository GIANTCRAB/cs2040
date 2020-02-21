package assignment4;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author WOO HUIREN ( A0202242B )
 */
public class HandProcessor {
    private final List<Hand> handList = new ArrayList<>();
    private final int syllableCount;

    public HandProcessor(int playerCount, int syllableCount) {
        // start index at 1, not 0
        IntStream.rangeClosed(1, playerCount).mapToObj(Player::new).forEach(player -> {
            handList.add(new Hand(player));
        });

        this.syllableCount = syllableCount;
    }

    public Player runSimulation() {
        var position = 0;
        final var positionToIncrement = syllableCount - 1;
        // get last hand standing
        while (handList.size() > 1) {
            // calculate next position
            position += positionToIncrement;
            // if position exceeds size, re-position for index
            position = position % handList.size();
            // additional position to increment (based on action)
            position += this.processHand(position);
        }

        // Return winner
        return handList.get(0).getPlayer();
    }

    /**
     * @param position current position for processing
     * @return additional increment
     */
    private int processHand(int position) {
        final var hand = handList.get(position);
        final var player = hand.getPlayer();
        switch (hand.getHandState()) {
            case OKAY:
                // Split into 2
                // Change states to split
                hand.setHandState(HandState.SPLIT);
                // Add another hand with a state of SPLIT
                handList.add(position, new Hand(player, HandState.SPLIT));
                break;
            case SPLIT:
                // Change state to spill
                hand.setHandState(HandState.SPILL);
                return 1;
            case SPILL:
                // Remove hand
                handList.remove(hand);
                break;
        }

        return 0;
    }
}
