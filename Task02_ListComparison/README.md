# Task 02 – List and Deque Comparison

Run `javac ListBenchmark.java` and `java ListBenchmark`. It tests ArrayList and LinkedList at three sizes for append, front/middle insertion and removal, indexed access, and iteration. It also demonstrates ArrayDeque for a double-ended Up Next queue.

**Interpretation:** ArrayList offers fast indexed access and efficient append (amortized), but front/middle insertion/removal shifts elements. LinkedList has constant-time insertion/removal once positioned, but locating an index is linear and each node has overhead. ArrayDeque is usually a strong choice for adding/removing at both ends.
