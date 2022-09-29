import java.util.Map;

/**
 * @author suleyman.yildirim
 */
public class DrawnStrategy extends LeagueStrategy {

    /**
     * Update league table entry for either drawn scenario.
     *
     * @param homeTeam            Home team
     * @param awayTeam            Away team
     * @param leagueTableEntryMap League table entry to be updated for each match
     */
    public void updateLeagueTable(LeagueTableEntry homeTeam, LeagueTableEntry awayTeam, Map<String, LeagueTableEntry> leagueTableEntryMap) {
        // update winnerTeam team
        homeTeam.updatePoints(Constants.DRAWN_POINTS);
        homeTeam.updateDrawn();
        homeTeam.updatePlayed();
        leagueTableEntryMap.put(homeTeam.getTeamName(), homeTeam);
        //update losing team
        awayTeam.updatePoints(Constants.DRAWN_POINTS);
        awayTeam.updateDrawn();
        awayTeam.updatePlayed();
        leagueTableEntryMap.put(awayTeam.getTeamName(), awayTeam);
    }
}
