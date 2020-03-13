package workstations;

public class Computer {
    Researcher researcherUsing;
    Integer expiry;

    public Computer(Researcher researcherUsing, Integer expiry) {
        this.researcherUsing = researcherUsing;
        this.expiry = expiry;
    }

    public void setResearcherUsing(Researcher researcher) {
        this.researcherUsing = researcher;
    }
}
