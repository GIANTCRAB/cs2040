package homeassign1b;

public class RelayRunner {
    private final String name;
    private final Float firstLegTime;
    private final Float secondLegTime;

    public RelayRunner(String name, Float firstLegTime, Float secondLegTime) {
        this.name = name;
        this.firstLegTime = firstLegTime;
        this.secondLegTime = secondLegTime;
    }

    public String getName() {
        return name;
    }

    public Float getFirstLegTime() {
        return firstLegTime;
    }

    public Float getSecondLegTime() {
        return secondLegTime;
    }
}
