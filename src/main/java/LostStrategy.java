
import java.util.Map;

/**
 * @author suleyman.yildirim
 */
public class LostStrategy extends LeagueStrategy {

    public void updateLeagueTable(LeagueTableEntry winnerTeam, LeagueTableEntry losingTeam, Map<String, LeagueTableEntry> leagueTableEntryMap) {
       super.updateWinnerAndLoser(losingTeam, winnerTeam, leagueTableEntryMap);
    }
}
