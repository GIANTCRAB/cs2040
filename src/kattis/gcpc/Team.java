package kattis.gcpc;

public class Team {
    private final Integer id;
    private Integer score;
    private Integer penalty;

    Team(Integer id) {
        this.id = id;
        this.score = 0;
        this.penalty = 0;
    }

    public Team incrementScore() {
        this.score++;
        return this;
    }

    public Integer getScore() {
        return this.score;
    }

    public Team addPenalty(Integer penalty) {
        this.penalty += penalty;
        return this;
    }

    public Integer getPenalty() {
        return this.penalty;
    }
}
