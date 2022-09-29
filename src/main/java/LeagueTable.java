import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LeagueTable {

    private final List<Match> matches;

    public LeagueTable(final List<Match> matches) {
        this.matches = matches;
    }

    /**
     * Takes a list of completed matches and produce a sorted list of <code>LeagueTableEntry</code> objects.
     *
     * @return sorted list of <code>LeagueTableEntry</code> objects
     */
    public List<LeagueTableEntry> getTableEntries() {
        return getLeagueTableEntries(matches).values().stream()
                .sorted(Comparator.comparing(LeagueTableEntry::getPoints).reversed()
                        .thenComparing(LeagueTableEntry::getGoalDifference, Comparator.reverseOrder())
                        .thenComparing(LeagueTableEntry::getGoalsFor, Comparator.reverseOrder())
                        .thenComparing(LeagueTableEntry::getTeamName)).collect(Collectors.toList());
    }

    /**
     * Creates a map of <code>LeagueTableEntry</code> for each team, based on the <code>matches</code> played.
     *
     * @param matches The total number of matches played
     * @return Map of league table entries for each team
     */
    private Map<String, LeagueTableEntry> getLeagueTableEntries(List<Match> matches) {
        Map<String, LeagueTableEntry> leagueTableEntryMap = new HashMap<>();
        for (Match match : matches) {
            LeagueTableEntry homeTeam = LeagueFactory.getTeam(leagueTableEntryMap, match.getHomeTeam());
            LeagueTableEntry awayTeam = LeagueFactory.getTeam(leagueTableEntryMap, match.getAwayTeam());
            LeagueStrategy leagueStrategy = LeagueFactory.getLeagueStrategyByScore(match.getHomeScore(), match.getAwayScore());
            leagueStrategy.updateLeagueTable(homeTeam, awayTeam, leagueTableEntryMap);
            leagueStrategy.updateGoals(homeTeam, awayTeam, match);
        }
        return leagueTableEntryMap;
    }
}
