import java.util.*;

public class Attendance {
    static class BrokenStudent {
        String regNo, name;
        BrokenStudent(String regNo, String name) { this.regNo = regNo; this.name = name; }
        @Override public boolean equals(Object o) {
            return o instanceof BrokenStudent && regNo.equals(((BrokenStudent)o).regNo);
        }
        // Deliberately missing hashCode() for the experiment.
    }

    static final class Student {
        private final String regNo;
        private final String name;
        Student(String regNo, String name) { this.regNo = regNo; this.name = name; }
        @Override public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Student)) return false;
            Student other = (Student) o;
            return Objects.equals(regNo, other.regNo);
        }
        @Override public int hashCode() { return Objects.hash(regNo); }
        @Override public String toString() { return regNo + " " + name; }
    }

    public static void main(String[] args) {
        System.out.println("Q3 – hashCode/equals contract");
        Set<BrokenStudent> broken = new HashSet<>();
        broken.add(new BrokenStudent("192311001", "Arun"));
        broken.add(new BrokenStudent("192311002", "Divya"));
        broken.add(new BrokenStudent("192311001", "Arun"));
        System.out.println("Broken class set size (may be 3): " + broken.size());

        Set<Student> corrected = new HashSet<>();
        corrected.add(new Student("192311001", "Arun"));
        corrected.add(new Student("192311002", "Divya"));
        corrected.add(new Student("192311001", "Arun"));
        System.out.println("Corrected class unique students: " + corrected.size());
        System.out.println("Corrected set: " + corrected);

        Set<Student> secondTest = new HashSet<>();
        secondTest.add(new Student("REG100", "A"));
        secondTest.add(new Student("REG101", "B"));
        secondTest.add(new Student("REG100", "Different display name"));
        System.out.println("Second test (same regNo): " + secondTest.size());

        System.out.println("Alternative: TreeSet with Comparator.comparing(s -> s.regNo), "
                + "or Map<String, Student> keyed by regNo.");
    }
}
