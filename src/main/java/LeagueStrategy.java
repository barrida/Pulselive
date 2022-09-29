import java.util.Map;

/**
 * @author suleyman.yildirim
 */
public abstract class LeagueStrategy {

    /**
     * Abstract method for won, lost or drawn strategies.
     *
     * @param homeTeam            Home team
     * @param awayTeam            Away team
     * @param leagueTableEntryMap League table entry to be updated for each match
     */
    public abstract void updateLeagueTable(LeagueTableEntry homeTeam, LeagueTableEntry awayTeam, Map<String, LeagueTableEntry> leagueTableEntryMap);

    /**
     * Update league table entry for either won or lost scenario.
     *
     * @param winnerTeam          The team whose score is greater than the opponent team
     * @param losingTeam          The team whose score is less than the opponent team
     * @param leagueTableEntryMap League table entry to be updated for each match
     */
    public void updateWinnerAndLoser(LeagueTableEntry winnerTeam, LeagueTableEntry losingTeam, Map<String, LeagueTableEntry> leagueTableEntryMap) {
        //update winner team
        winnerTeam.updatePoints(Constants.WIN_POINTS);
        winnerTeam.updateWon();
        winnerTeam.updatePlayed();
        leagueTableEntryMap.put(winnerTeam.getTeamName(), winnerTeam);
        //update losing team
        losingTeam.updateLost();
        losingTeam.updatePlayed();
        leagueTableEntryMap.put(losingTeam.getTeamName(), losingTeam);
    }

    /**
     * Update GF, GA, and GD of the specified <code>match</code> for <code>homeTeam</code> and <code>awayTeam</code>.
     *
     * @param homeTeam Home team
     * @param awayTeam Away team
     * @param match    Match
     */
    public void updateGoals(LeagueTableEntry homeTeam, LeagueTableEntry awayTeam, Match match) {
        homeTeam.updateGoalsFor(match.getHomeScore());
        homeTeam.updateGoalsAgainst(match.getAwayScore());
        homeTeam.updateGoalDifference();
        awayTeam.updateGoalsFor(match.getAwayScore());
        awayTeam.updateGoalsAgainst(match.getHomeScore());
        awayTeam.updateGoalDifference();
    }
}
