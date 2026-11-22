# Task 07 – Diagnose, Redesign and Validate

This project demonstrates a measurable redesign of repeated linear medicine-code searches. The original implementation scans an ArrayList for every lookup (O(n)); the redesigned implementation builds a HashMap index once and performs average O(1) lookups. It uses the same generated records and query sequence for both approaches.

Run `javac RedesignComparison.java` then `java RedesignComparison`. The program reports construction time and lookup time for multiple data sizes. The HashMap requires additional memory and preprocessing; for very small datasets or rare lookups, a simple list can remain appropriate.

For your report, use the output from your own computer, explain the measured difference, and include a class diagram or pseudocode if required.
