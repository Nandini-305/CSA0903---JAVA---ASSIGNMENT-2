public class TicketBenchmark {
    private static volatile int blackHole;

    private static String buildWithPlus(int fields) {
        String result = "";
        for (int i = 0; i < fields; i++) result = result + "FIELD" + i + "|";
        return result;
    }

    private static String buildWithBuilder(int fields) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < fields; i++) result.append("FIELD").append(i).append('|');
        return result.toString();
    }

    private static String buildWithBuffer(int fields) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < fields; i++) result.append("FIELD").append(i).append('|');
        return result.toString();
    }

    private static long measure(String label, int tickets, int fields, int method, int repetitions) {
        long total = 0;
        for (int r = 0; r < repetitions; r++) {
            long start = System.nanoTime();
            int checksum = 0;
            for (int t = 0; t < tickets; t++) {
                String value = method == 1 ? buildWithPlus(fields)
                        : method == 2 ? buildWithBuilder(fields) : buildWithBuffer(fields);
                checksum += value.length();
            }
            total += System.nanoTime() - start;
            blackHole = checksum;
        }
        long average = total / repetitions;
        String name = method == 1 ? "String +" : method == 2 ? "StringBuilder" : "StringBuffer";
        System.out.printf("%-14s tickets=%-6d fields=%-4d average=%d ns%n",
                name, tickets, fields, average);
        if (method == 1) {
            System.out.println("  Estimated intermediate concatenation results: about "
                    + (long) tickets * fields);
        } else {
            System.out.println("  Estimated builder/buffer objects: about " + tickets
                    + " mutable objects plus final String results");
        }
        return average;
    }

    public static void main(String[] args) {
        int[][] workloads = {{1, 5}, {10000, 50}, {500, 200}};
        int repetitions = 3;
        System.out.println("Q1 – Ticket text construction benchmark");
        System.out.println("Times are machine/JVM dependent; averages over " + repetitions + " runs.");
        for (int i = 0; i < workloads.length; i++) {
            int tickets = workloads[i][0], fields = workloads[i][1];
            System.out.println("\\nWorkload " + (i + 1) + ": " + tickets + " ticket(s), "
                    + fields + " fields each");
            // Warm-up
            buildWithPlus(Math.min(fields, 20));
            buildWithBuilder(Math.min(fields, 20));
            buildWithBuffer(Math.min(fields, 20));
            for (int method = 1; method <= 3; method++)
                measure("", tickets, fields, method, repetitions);
        }
    }
}
