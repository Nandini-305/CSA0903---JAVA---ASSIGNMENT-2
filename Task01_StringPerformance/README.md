# Task 01 – Building Text Under Load

Run `javac TicketBenchmark.java` then `java TicketBenchmark`. It compares repeated String concatenation, StringBuilder and StringBuffer for three workloads. It prints elapsed time and an estimated number of intermediate concatenation results. The estimates are explanatory approximations, not JVM allocation-profiler measurements.

**Concepts:** String is immutable; StringBuilder is mutable and not synchronized; StringBuffer is mutable and synchronized. For a ticket built by one thread, StringBuilder is normally suitable. For a shared log accessed by multiple threads, use a synchronized design (for example, a single log-writer or synchronized StringBuffer) rather than sharing an unsynchronized StringBuilder.
