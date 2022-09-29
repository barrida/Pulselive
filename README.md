# Introduction

The purpose of this exercise is to demonstrate your ability to use 
Java to build a dynamic football league table generator.

#Task

Consider a league table for football. Each team plays a number of matches and the results
of each match build the table. Given the code attached as a starting point build
the LeagueTable class that can take a list of completed matches and produce a sorted 
list of LeagueTableEntry objects.

The sorting rules for entries in the league table should be
* Sort by total points (descending)
* Then by goal difference (descending)
* Then by goals scored (descending)
* Then by team name (in alphabetical order)
A win is worth three points to the winning team. A draw is worth one point to each team.

Your code will be run through a series of JUnit tests to validate the implementation so it is important 
that method signatures are not changed. You will also be assessed on code quality and clarity.

In undertaking this task, please consider the following:
* You should be submitting production quality code
* Future reuse and extension of code
* Any documentation / notes on build

# Design Decisions

In order to process each match for won, lost, and drawn scenarios, I used Replace Conditional with Polymorphism and Factory Method patterns. For each scenario, I added a separate strategy that updates the league table for each match. However, rule design pattern can also be applied to this problem. I have an article on that: https://proxify.io/articles/rule-design-pattern-for-nested-if-else-statements.

``` 
private Map<String, LeagueTableEntry> getLeagueTableEntries(List<Match> matches) {
        Map<String, LeagueTableEntry> leagueTableEntryMap = new HashMap<>();
        for (Match match : matches) {
            LeagueTableEntry homeTeam = LeagueFactory.getTeam(leagueTableEntryMap, match.getHomeTeam());
            LeagueTableEntry awayTeam = LeagueFactory.getTeam(leagueTableEntryMap, match.getAwayTeam());
            LeagueStrategy leagueStrategy = LeagueFactory.getLeagueStrategyByScore(match);
            leagueStrategy.updateLeagueTable(homeTeam, awayTeam, leagueTableEntryMap);
            leagueStrategy.updateGoals(homeTeam, awayTeam, match);
        }
        return leagueTableEntryMap;
    }
```

Once we get the LeagueTableEntry map for each team, we produce a sorted list of LeagueTableEntry using the Comparator API:

```
public List<LeagueTableEntry> getTableEntries() {
    return getLeagueTableEntries(matches).values().stream()
    .sorted(Comparator.comparing(LeagueTableEntry::getPoints).reversed()
    .thenComparing(LeagueTableEntry::getGoalDifference, Comparator.reverseOrder())
    .thenComparing(LeagueTableEntry::getGoalsFor, Comparator.reverseOrder())
    .thenComparing(LeagueTableEntry::getTeamName)).collect(Collectors.toList());
}
```

Some benefits of this approach are as follows: 

1. You can update existing strategies without modifying the client code (Tell-Don’t-Ask principle)
2. If you need to add a new scoring variant, you just need to do is add a new strategy without touching the existing code (Open/Closed Principle). 
3. Get rid of if-else conditionals such as below:

```
for (Match match : matches) {
    if (match.getHomeScore() > match.getAwayScore()) {
      // won scenario
    } else if (match.getHomeScore() < match.getAwayScore()) {
      // lost scenario
    } else {
      // drawn scenario
    }
}
```

# Unit Tests and Test Data

Run all unit test using `mvn test` in command line. You can also use IntelliJ's Maven plugin and double click on `test`.

I got the test data from https://www.bbc.co.uk/sport/football/premier-league/scores-fixtures/2022-08 between August 5th, 2022 and August 30th, 2022. Then, I validated the league table as it stood on August 30th, 2022 via following websites:

- https://www.twtd.co.uk/league-tables/competition:premier-league/daterange/fromdate:2022-Aug-04/todate:2022-Aug-31/type:home-and-away  
- https://www.bbc.co.uk/sport/football/62641728


