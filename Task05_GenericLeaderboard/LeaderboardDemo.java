import java.util.*;

public class LeaderboardDemo {
    static class Team {
        final String name; final int points; final int goalDiff;
        Team(String name, int points, int goalDiff) {
            this.name=name; this.points=points; this.goalDiff=goalDiff;
        }
        @Override public String toString() { return name + "(" + points + "," + goalDiff + ")"; }
    }

    static class Leaderboard<T> {
        private final List<T> items = new ArrayList<>();
        private final Comparator<? super T> order;
        Leaderboard(Comparator<? super T> order) { this.order = order; }
        void add(T item) { items.add(item); }
        List<T> ranking() {
            List<T> copy = new ArrayList<>(items);
            copy.sort(order);
            return copy;
        }
        List<T> originalOrder() { return new ArrayList<>(items); }
        void addAll(List<? extends T> moreItems) { items.addAll(moreItems); }
    }

    public static void main(String[] args) {
        Comparator<Team> byRank = Comparator
                .comparingInt((Team t) -> t.points).reversed()
                .thenComparing(Comparator.comparingInt((Team t) -> t.goalDiff).reversed())
                .thenComparing(t -> t.name);

        Leaderboard<Team> board = new Leaderboard<>(byRank);
        board.add(new Team("Panthers", 9, 4));
        board.add(new Team("Eagles", 9, 6));
        board.add(new Team("Cobras", 6, 1));
        board.add(new Team("Bisons", 9, 4));
        System.out.println("Ranking: " + board.ranking());
        System.out.println("Original insertion order: " + board.originalOrder());

        List<Team> extra = Arrays.asList(new Team("Falcons", 12, 3), new Team("Tigers", 6, 2));
        board.addAll(extra);
        System.out.println("Ranking after adding another list: " + board.ranking());
    }
}
