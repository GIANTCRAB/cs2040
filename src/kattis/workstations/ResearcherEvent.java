package kattis.workstations;

public class ResearcherEvent implements EventTime {
    final private Integer eventStart;
    final private Integer eventEnd;
    final Researcher researcher;

    public ResearcherEvent(Integer eventStart, Integer eventEnd, Researcher researcher) {
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
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
}
