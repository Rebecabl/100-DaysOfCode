public class Student {
    private String name;
    private double grade1, grade2;

    public Student(String name, double grade1, double grade2) {
        this.name = name;
        this.grade1 = grade1;
        this.grade2 = grade2;
    }

    public String getName() {
        return name;
    }

    public double getAverage() {
        return (grade1 + grade2) / 2;
    }

    public String getStatus() {
        return getAverage() >= 6.0 ? "Aprovado" : "Reprovado";
    }

    @Override
    public String toString() {
        return name + "," + grade1 + "," + grade2;
    }
}
