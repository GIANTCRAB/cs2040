package workstations;

public class ResearcherEvent implements EventTime {
    final Integer eventTime;
    final EventTypes eventType;
    final Researcher researcher;

    public ResearcherEvent(Integer eventTime, EventTypes eventType, Researcher researcher) {
        this.eventTime = eventTime;
        this.eventType = eventType;
        this.researcher = researcher;
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