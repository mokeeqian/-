package edu.ahut.a299.entity;

import android.widget.ImageView;


/**
 * 球队实体类
 */
public class Team {
    private String teamName;
    private ImageView teamLogo;
    private int teamScore;

    public Team(String teamName, ImageView teamLogo, int teamScore) {
        this.teamName = teamName;
        this.teamLogo = teamLogo;
        this.teamScore = teamScore;
    }

    public Team() {
        this.teamScore = 0;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public ImageView getTeamLogo() {
        return teamLogo;
    }

    public void setTeamLogo(ImageView teamLogo) {
        this.teamLogo = teamLogo;
    }

    public int getTeamScore() {
        return teamScore;
    }

    public void setTeamScore(int teamScore) {
        this.teamScore = teamScore;
    }

    @Override
    public String toString() {
        return "Team{" +
                "teamName='" + teamName + '\'' +
                ", teamLogo=" + teamLogo +
                ", teamScore=" + teamScore +
                '}';
    }
}
