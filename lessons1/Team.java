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
    public void showResults() {
        System.out.println("В результате сошли с дистанции: ");
        for (Competitor competitor : competitors) {
            competitor.info();
        }
    }
}
