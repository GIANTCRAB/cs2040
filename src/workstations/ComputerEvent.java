package workstations;

public class ComputerEvent implements EventTime {
    final private Integer eventStart;
    final private Integer eventEnd;
    final Computer computer;

    public ComputerEvent(Integer eventStart, Integer eventEnd, Computer computer) {
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
        this.computer = computer;
    }

    @Override
    public Integer getEventStart() {
        return this.eventStart;
    }

    @Override
    public Integer getEventEnd() {
        return this.eventEnd;
    }
}
