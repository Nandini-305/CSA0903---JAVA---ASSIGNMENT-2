import java.util.*;

public class PharmacyInventory {
    static class Medicine {
        final String code, name;
        final int stock;
        Medicine(String code, String name, int stock) { this.code=code; this.name=name; this.stock=stock; }
        @Override public String toString() { return code + " " + name + " stock=" + stock; }
    }

    public static void main(String[] args) {
        int[] sizes = {1000, 5000, 10000};
        System.out.println("Q4 – Map comparison");
        for (int n : sizes) {
            Map<String, Medicine> hash = new HashMap<>();
            Map<String, Medicine> tree = new TreeMap<>();
            Map<String, Medicine> linked = new LinkedHashMap<>();
            for (int i=0; i<n; i++) {
                String code = String.format("MED%05d", i);
                Medicine m = new Medicine(code, "Medicine-" + i, 100 + i);
                hash.put(code,m); tree.put(code,m); linked.put(code,m);
            }
            String target = String.format("MED%05d", n/2);
            int lookups = 100000;
            long start = System.nanoTime();
            for (int i=0;i<lookups;i++) hash.get(target);
            long hashTime = System.nanoTime()-start;
            start = System.nanoTime();
            for (int i=0;i<lookups;i++) tree.get(target);
            long treeTime = System.nanoTime()-start;
            start = System.nanoTime();
            for (int i=0;i<lookups;i++) linked.get(target);
            long linkedTime = System.nanoTime()-start;
            System.out.printf("n=%d, %d lookups (ns): HashMap=%d TreeMap=%d LinkedHashMap=%d%n",
                    n, lookups, hashTime, treeTime, linkedTime);
            start = System.nanoTime(); for (Medicine m : tree.values()) { /* report */ }
            long sortedReport = System.nanoTime()-start;
            start = System.nanoTime(); for (Medicine m : linked.values()) { /* report */ }
            long insertionReport = System.nanoTime()-start;
            System.out.printf("report iteration (ns): sorted TreeMap=%d, insertion LinkedHashMap=%d%n",
                    sortedReport, insertionReport);
        }

        Map<String, Medicine> inventory = new LinkedHashMap<>();
        inventory.put("M003", new Medicine("M003","Paracetamol",50));
        inventory.put("M001", new Medicine("M001","Cetirizine",30));
        inventory.put("M002", new Medicine("M002","Vitamin C",80));
        System.out.println("\\nInsertion order report: " + inventory.values());
        Map<String, Medicine> sorted = new TreeMap<>(inventory);
        System.out.println("Code order report: " + sorted.values());
        System.out.println("Hashtable is generally not preferred in new code; use modern Map implementations "
                + "and explicit synchronization/concurrent collections when required.");
    }
}
