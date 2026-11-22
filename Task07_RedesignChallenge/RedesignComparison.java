import java.util.*;

public class RedesignComparison {
    static class Medicine {
        final String code;
        Medicine(String code) { this.code = code; }
    }

    static List<Medicine> createData(int n) {
        List<Medicine> data = new ArrayList<>();
        for (int i=0;i<n;i++) data.add(new Medicine(String.format("MED%06d", i)));
        return data;
    }

    static Medicine linearFind(List<Medicine> data, String code) {
        for (Medicine m : data) if (m.code.equals(code)) return m;
        return null;
    }

    public static void main(String[] args) {
        int[] sizes = {1000, 10000, 50000};
        volatileSink = 0;
        System.out.println("Q7 – Original linear search vs redesigned indexed search");
        for (int n : sizes) {
            List<Medicine> data = createData(n);
            long buildStart = System.nanoTime();
            Map<String, Medicine> index = new HashMap<>();
            for (Medicine m : data) index.put(m.code, m);
            long buildTime = System.nanoTime() - buildStart;

            int queries = 20000;
            String[] keys = new String[queries];
            for (int i=0;i<queries;i++)
                keys[i] = String.format("MED%06d", (i * 37) % n);

            long start = System.nanoTime();
            int found1 = 0;
            for (String key : keys) if (linearFind(data, key) != null) found1++;
            long linearTime = System.nanoTime() - start;

            start = System.nanoTime();
            int found2 = 0;
            for (String key : keys) if (index.get(key) != null) found2++;
            long indexedTime = System.nanoTime() - start;
            volatileSink = found1 + found2;

            System.out.printf("n=%d, queries=%d%n", n, queries);
            System.out.printf("Original ArrayList linear search: %d ns, found=%d%n", linearTime, found1);
            System.out.printf("Redesign HashMap lookup: %d ns, found=%d, index build=%d ns%n",
                    indexedTime, found2, buildTime);
            System.out.println("Trade-off: HashMap uses extra memory and one-time index construction.");
        }
    }
    private static volatile int volatileSink;
}
