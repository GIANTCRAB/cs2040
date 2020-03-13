package workstations;

public class ResearcherEvent {
    final Integer eventTime;
    final EventTypes eventType;

    public ResearcherEvent(Integer eventTime, EventTypes eventType) {
        this.eventTime = eventTime;
        this.eventType = eventType;
    }
}
