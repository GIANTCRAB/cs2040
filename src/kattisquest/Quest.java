package kattisquest;

public class Quest {
    private final Integer energy;
    private final Integer gold;

    public Quest(Integer energy, Integer gold) {
        this.energy = energy;
        this.gold = gold;
    }

    public Integer getEnergy() {
        return energy;
    }

    public Integer getGold() {
        return gold;
    }
}
