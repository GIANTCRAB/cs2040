package workstations;

public class ResearcherEvent implements EventTime {
    final private Integer eventStart;
    final private Integer eventEnd;
    final EventTypes eventType;
    final Researcher researcher;

    public ResearcherEvent(Integer eventStart, Integer eventEnd, EventTypes eventType, Researcher researcher) {
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
        this.eventType = eventType;
        this.researcher = researcher;
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
