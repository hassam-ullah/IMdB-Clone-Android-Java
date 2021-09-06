package com.project.imdb_clone_app;

public class Model_Achievement {
    private String actor;
    private String award;
    private String year;

    public Model_Achievement() {
    }

    public Model_Achievement(String actor, String award, String year) {
        this.actor = actor;
        this.award = award;
        this.year = year;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
