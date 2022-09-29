import java.util.Map;

/**
 * @author suleyman.yildirim
 */
public class LeagueFactory {

    /**
     * Gets the corresponding strategy for won, lost, or drawn scenarios.
     *
     * @param homeScore Home score of the match
     * @param awayScore Away score of the match
     * @return <code>strategy.LeagueStrategy</code> object
     */
    public static LeagueStrategy getLeagueStrategyByScore(int homeScore, int awayScore) {
        if (homeScore > awayScore)
            return new WonStrategy();
        else if (homeScore < awayScore)
            return new LostStrategy();
        else
            return new DrawnStrategy();
    }

    /**
     * Get the corresponding league table entry to be updated for <code>teamName</code>
     *
     * @param leagueTableEntries League table entry to be updated for each match
     * @param teamName           The team name
     * @return <code>LeagueTableEntry</code> object
     */
    public static LeagueTableEntry getTeam(Map<String, LeagueTableEntry> leagueTableEntries, String teamName) {
        leagueTableEntries.putIfAbsent(teamName, new LeagueTableEntry(teamName));
        return leagueTableEntries.get(teamName);
    }
}
