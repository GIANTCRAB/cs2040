package workstations;

public class ComputerEvent implements EventTime {
    final private Integer eventStart;
    final private Integer eventEnd;
    final EventTypes eventType;
    final Computer computer;

    public ComputerEvent(Integer eventStart, Integer eventEnd, EventTypes eventType, Computer computer) {
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
        this.eventType = eventType;
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

    @Override
    public EventTypes getEventType() {
        return this.eventType;
    }
}
