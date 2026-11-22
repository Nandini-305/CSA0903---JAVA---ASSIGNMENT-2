# Task 04 – Pharmacy Inventory Map Comparison

Run `javac PharmacyInventory.java` and `java PharmacyInventory`. The same medicine records are loaded into HashMap, TreeMap and LinkedHashMap. The program measures lookups and report generation and displays each map's iteration order.

**Decision:** HashMap is suitable for fast average lookup without order guarantees. TreeMap maintains sorted key order. LinkedHashMap preserves insertion order. If both sorted and insertion-order reports are required, maintain two views or sort/copy when generating the less-natural report.
