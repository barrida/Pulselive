import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static constants.TestConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

/**
 * @author suleyman.yildirim
 */
class LeagueTableTest {

    private List<Match> matches;
    private List<LeagueTableEntry> expectedLeagueTableEntries;
    private LeagueTable leagueTable;

    @BeforeEach
    void setUp() {
        matches = new ArrayList<>(List.of(
                new Match(CRYSTAL_PALACE, ARSENAL, 0, 2),
                new Match(EVERTON, CHELSEA, 0, 1),
                new Match(TOTTENHAM_HOTSPUR, SOUTHAMPTON, 4, 1),
                new Match(NEWCASTLE_UNITED, NOTTINGHAM_FOREST, 2, 0),
                new Match(LEADS_UNITED, WOLVERHAMPTON_WANDERERS, 2, 1),
                new Match(AFC_BOURNEMOUTH, ASTON_VILLA, 2, 0),
                new Match(FULHAM, LIVERPOOL, 2, 2),
                new Match(WEST_HAM_UNITED, MANCHESTER_CITY, 0, 2),
                new Match(MANCHESTER_UNITED, BRIGHTON_HOVE_ALBION, 1, 2),
                new Match(LEICESTER_CITY, BRENTFORD, 2, 2),
                new Match(BRENTFORD, MANCHESTER_UNITED, 4, 0),
                new Match(WOLVERHAMPTON_WANDERERS, FULHAM, 0, 0),
                new Match(SOUTHAMPTON, LEADS_UNITED, 2, 2),
                new Match(MANCHESTER_CITY, AFC_BOURNEMOUTH, 4, 0),
                new Match(BRIGHTON_HOVE_ALBION, NEWCASTLE_UNITED, 0, 0),
                new Match(ARSENAL, LEICESTER_CITY, 4, 2),
                new Match(ASTON_VILLA, EVERTON, 2, 1),
                new Match(CHELSEA, TOTTENHAM_HOTSPUR, 2, 2),
                new Match(NOTTINGHAM_FOREST, WEST_HAM_UNITED, 1, 0),
                new Match(LIVERPOOL, CRYSTAL_PALACE, 1, 1),
                new Match(AFC_BOURNEMOUTH, ARSENAL, 0, 3),
                new Match(LEICESTER_CITY, SOUTHAMPTON, 1, 2),
                new Match(FULHAM, BRENTFORD, 3, 2),
                new Match(EVERTON, NOTTINGHAM_FOREST, 1, 1),
                new Match(CRYSTAL_PALACE, ASTON_VILLA, 3, 1),
                new Match(TOTTENHAM_HOTSPUR, WOLVERHAMPTON_WANDERERS, 1, 0),
                new Match(NEWCASTLE_UNITED, MANCHESTER_CITY, 3, 3),
                new Match(WEST_HAM_UNITED, BRIGHTON_HOVE_ALBION, 0, 2),
                new Match(LEADS_UNITED, CHELSEA, 3, 0),
                new Match(MANCHESTER_UNITED, LIVERPOOL, 2, 1),
                new Match(ARSENAL, FULHAM, 2, 1),
                new Match(MANCHESTER_CITY, CRYSTAL_PALACE, 4, 2),
                new Match(LIVERPOOL, AFC_BOURNEMOUTH, 9, 0),
                new Match(CHELSEA, LEICESTER_CITY, 2, 1),
                new Match(BRIGHTON_HOVE_ALBION, LEADS_UNITED, 1, 0),
                new Match(BRENTFORD, EVERTON, 1, 1),
                new Match(SOUTHAMPTON, MANCHESTER_UNITED, 0, 1),
                new Match(NOTTINGHAM_FOREST, TOTTENHAM_HOTSPUR, 0, 2),
                new Match(WOLVERHAMPTON_WANDERERS, NEWCASTLE_UNITED, 1, 1),
                new Match(ASTON_VILLA, WEST_HAM_UNITED, 0, 1),
                new Match(LEADS_UNITED, EVERTON, 1, 1),
                new Match(SOUTHAMPTON, CHELSEA, 2, 1),
                new Match(FULHAM, BRIGHTON_HOVE_ALBION, 2, 1),
                new Match(CRYSTAL_PALACE, BRENTFORD, 1, 1)
        ));

        expectedLeagueTableEntries = new ArrayList<>(List.of(
                new LeagueTableEntry(ARSENAL, 4, 4, 0, 0, 11, 3, 8, 12),
                new LeagueTableEntry(MANCHESTER_CITY, 4, 3, 1, 0, 13, 5, 8, 10),
                new LeagueTableEntry(TOTTENHAM_HOTSPUR, 4, 3, 1, 0, 9, 3, 6, 10),
                new LeagueTableEntry(BRIGHTON_HOVE_ALBION, 5, 3, 1, 1, 6, 3, 3, 10),
                new LeagueTableEntry(LEADS_UNITED, 5, 2, 2, 1, 8, 5, 3, 8),
                new LeagueTableEntry(FULHAM, 5, 2, 2, 1, 8, 7, 1, 8),
                new LeagueTableEntry(SOUTHAMPTON, 5, 2, 1, 2, 7, 9, -2, 7),
                new LeagueTableEntry(CHELSEA, 5, 2, 1, 2, 6, 8, -2, 7),
                new LeagueTableEntry(BRENTFORD, 5, 1, 3, 1, 10, 7, 3, 6),
                new LeagueTableEntry(NEWCASTLE_UNITED, 4, 1, 3, 0, 6, 4, 2, 6),
                new LeagueTableEntry(MANCHESTER_UNITED, 4, 2, 0, 2, 4, 7, -3, 6),
                new LeagueTableEntry(LIVERPOOL, 4, 1, 2, 1, 13, 5, 8, 5),
                new LeagueTableEntry(CRYSTAL_PALACE, 5, 1, 2, 2, 7, 9, -2, 5),
                new LeagueTableEntry(NOTTINGHAM_FOREST, 4, 1, 1, 2, 2, 5, -3, 4),
                new LeagueTableEntry(EVERTON, 5, 0, 3, 2, 4, 6, -2, 3),
                new LeagueTableEntry(ASTON_VILLA, 4, 1, 0, 3, 3, 7, -4, 3),
                new LeagueTableEntry(WEST_HAM_UNITED, 4, 1, 0, 3, 1, 5, -4, 3),
                new LeagueTableEntry(AFC_BOURNEMOUTH, 4, 1, 0, 3, 2, 16, -14, 3),
                new LeagueTableEntry(WOLVERHAMPTON_WANDERERS, 4, 0, 2, 2, 2, 4, -2, 2),
                new LeagueTableEntry(LEICESTER_CITY, 4, 0, 1, 3, 6, 10, -4, 1)
        ));
    }

    @Test
    void shouldGetEqualLeagueTableEntries() {
        leagueTable = new LeagueTable(matches);
        var actualLeagueTableEntries = leagueTable.getTableEntries();
        assertEquals(expectedLeagueTableEntries, actualLeagueTableEntries);
        assertLeagueTableEntries(expectedLeagueTableEntries, actualLeagueTableEntries);
    }

    @Test
    void shouldGetWrongLeagueTableEntriesWhenTheFirstMatchIsModified() {
        matches.remove(matches.get(0));
        matches.add(new Match(CRYSTAL_PALACE, ARSENAL, 3, 1));
        leagueTable = new LeagueTable(matches);
        assertNotSame(expectedLeagueTableEntries, leagueTable.getTableEntries());
    }

    private void assertLeagueTableEntries(List<LeagueTableEntry> expectedLeagueTableEntries, List<LeagueTableEntry> actualLeagueTableEntries) {
        for (var i = 0; i < expectedLeagueTableEntries.size(); i++) {
            assertEquals(expectedLeagueTableEntries.get(i).getTeamName(), actualLeagueTableEntries.get(i).getTeamName());
            assertEquals(expectedLeagueTableEntries.get(i).getPlayed(), actualLeagueTableEntries.get(i).getPlayed());
            assertEquals(expectedLeagueTableEntries.get(i).getWon(), actualLeagueTableEntries.get(i).getWon());
            assertEquals(expectedLeagueTableEntries.get(i).getDrawn(), actualLeagueTableEntries.get(i).getDrawn());
            assertEquals(expectedLeagueTableEntries.get(i).getLost(), actualLeagueTableEntries.get(i).getLost());
            assertEquals(expectedLeagueTableEntries.get(i).getGoalsFor(), actualLeagueTableEntries.get(i).getGoalsFor());
            assertEquals(expectedLeagueTableEntries.get(i).getGoalsAgainst(), actualLeagueTableEntries.get(i).getGoalsAgainst());
            assertEquals(expectedLeagueTableEntries.get(i).getGoalDifference(), actualLeagueTableEntries.get(i).getGoalDifference());
            assertEquals(expectedLeagueTableEntries.get(i).getPoints(), actualLeagueTableEntries.get(i).getPoints());
        }
    }


}