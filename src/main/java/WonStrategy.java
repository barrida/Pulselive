
import java.util.Map;

/**
 * @author suleyman.yildirim
 */
public class WonStrategy extends LeagueStrategy {

    public void updateLeagueTable(LeagueTableEntry winnerTeam, LeagueTableEntry losingTeam, Map<String, LeagueTableEntry> leagueTableEntryMap) {
        super.updateWinnerAndLoser(winnerTeam, losingTeam, leagueTableEntryMap);
    }
}
