package workstations;

public class ComputerEvent implements EventTime {
    final Integer eventTime;
    final EventTypes eventType;
    final Computer computer;

    public ComputerEvent(Integer eventTime, EventTypes eventType, Computer computer) {
        this.eventTime = eventTime;
        this.eventType = eventType;
        this.computer = computer;
    }

    @Override
    public Integer getEventTime() {
        return this.eventTime;
    }

    @Override
    public EventTypes getEventType() {
        return this.eventType;
    }
}
