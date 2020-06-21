package com.company;

public class Team {
    private String teamName;
    private Competitor[] competitors;

    public Team(Competitor... competitors) {
        this.competitors = competitors;
    }

    public Competitor[] getCompetitors() {
        return competitors;
    }

}
