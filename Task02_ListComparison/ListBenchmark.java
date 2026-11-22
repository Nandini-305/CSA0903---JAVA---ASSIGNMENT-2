import java.util.*;

public class ListBenchmark {
    private static volatile long sink;

    private static long time(Runnable action) {
        long start = System.nanoTime();
        action.run();
        return System.nanoTime() - start;
    }

    private static List<Integer> filled(List<Integer> list, int n) {
        for (int i = 0; i < n; i++) list.add(i);
        return list;
    }

    public static void main(String[] args) {
        int[] sizes = {1000, 5000, 10000};
        System.out.println("Q2 – ArrayList vs LinkedList (nanoseconds; environment dependent)");
        for (int n : sizes) {
            System.out.println("\\nPlaylist size: " + n);
            List<Integer> a = filled(new ArrayList<>(), n);
            List<Integer> l = filled(new LinkedList<>(), n);
            final int repeats = 300;

            long aGet = time(() -> { long s = 0; for (int i = 0; i < repeats; i++) s += a.get(n / 2); sink = s; });
            long lGet = time(() -> { long s = 0; for (int i = 0; i < repeats; i++) s += l.get(n / 2); sink = s; });
            System.out.printf("get middle x%d: ArrayList=%d ns, LinkedList=%d ns%n", repeats, aGet, lGet);

            long aIter = time(() -> { long s = 0; for (int x : a) s += x; sink = s; });
            long lIter = time(() -> { long s = 0; for (int x : l) s += x; sink = s; });
            System.out.printf("full iteration: ArrayList=%d ns, LinkedList=%d ns%n", aIter, lIter);

            long aFront = time(() -> { List<Integer> x = new ArrayList<>(a); for (int i = 0; i < 100; i++) x.add(0, -i); });
            long lFront = time(() -> { List<Integer> x = new LinkedList<>(l); for (int i = 0; i < 100; i++) x.add(0, -i); });
            System.out.printf("insert front x100: ArrayList=%d ns, LinkedList=%d ns%n", aFront, lFront);

            long aMiddle = time(() -> { List<Integer> x = new ArrayList<>(a); for (int i = 0; i < 100; i++) x.add(x.size()/2, -i); });
            long lMiddle = time(() -> { List<Integer> x = new LinkedList<>(l); for (int i = 0; i < 100; i++) x.add(x.size()/2, -i); });
            System.out.printf("insert middle x100: ArrayList=%d ns, LinkedList=%d ns%n", aMiddle, lMiddle);

            long aEnd = time(() -> { List<Integer> x = new ArrayList<>(); for (int i = 0; i < n; i++) x.add(i); });
            long lEnd = time(() -> { List<Integer> x = new LinkedList<>(); for (int i = 0; i < n; i++) x.add(i); });
            System.out.printf("append %d: ArrayList=%d ns, LinkedList=%d ns%n", n, aEnd, lEnd);
        }

        Deque<String> upNext = new ArrayDeque<>();
        upNext.addLast("Song B");
        upNext.addFirst("Song A");
        upNext.addLast("Song C");
        System.out.println("\\nArrayDeque Up Next: " + upNext);
        System.out.println("Play next: " + upNext.removeFirst());
        System.out.println("Remaining: " + upNext);
    }
}
