# Task 06 – Hospital Emergency Triage

Run `javac Triage.java` and `java Triage`. Lower severity number means more critical. For equal severity, the smaller arrival counter is served first (FIFO). The program uses PriorityQueue with a comparator over severity and arrival sequence.

**Complexity:** PriorityQueue insertion and poll are O(log n); peek is O(1). Printing a PriorityQueue directly does not guarantee sorted treatment order; poll a copy to display the ordered queue. A severity-5 patient can starve if more urgent cases keep arriving; an ageing rule can gradually improve priority after a waiting threshold.
