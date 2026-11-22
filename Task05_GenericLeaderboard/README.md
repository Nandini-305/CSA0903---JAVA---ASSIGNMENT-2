# Task 05 – Generic Sports Leaderboard

Run `javac LeaderboardDemo.java` and `java LeaderboardDemo`. The generic Leaderboard accepts a Comparator, stores any item type, and returns a sorted copy without changing its internal insertion order. Team ranking is points descending, goal difference descending, then name ascending.

**Why Comparator:** Ranking rules can vary between teams, players and colleges. A separate Comparator makes the ranking policy reusable without forcing one natural order on the Team class.
