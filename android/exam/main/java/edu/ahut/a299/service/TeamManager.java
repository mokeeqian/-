package edu.ahut.a299.service;

import edu.ahut.a299.entity.Team;

/**
 * 业务类
 */
public class TeamManager {

    /**
     * 点击一次按钮，只加分一分
     * @param team
     */
    public static void addScore(Team team) {
        team.setTeamScore( team.getTeamScore() + 1 );
    }

    /**
     * 比较两球队的分数
     * @param left
     * @param right
     * @return
     */
    public static int compareScore(Team left, Team right) {
        if ( left.getTeamScore() > right.getTeamScore() )
            return 1;
        else if ( left.getTeamScore() < right.getTeamScore())
            return -1;
        else
            return 0;
    }

}
