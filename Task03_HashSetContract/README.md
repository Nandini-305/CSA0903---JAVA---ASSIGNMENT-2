# Task 03 – hashCode and equals Contract

Run `javac Attendance.java` and `java Attendance`. The program demonstrates a broken Student class (equals without hashCode), then a corrected immutable class. It includes tests for duplicate scans and a mutation warning.

**Important:** Equal objects must return the same hash code. Hash-based collections use the hash to select a bucket and equals to distinguish values within that bucket. Do not mutate fields used by equals/hashCode while an object is stored in a HashSet or used as a HashMap key.
