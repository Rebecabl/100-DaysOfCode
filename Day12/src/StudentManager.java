import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentManager {
    private final String FILE_NAME = "alunos.txt";
    private List<Student> students;

    public StudentManager() {
        students = new ArrayList<>();
        loadStudents();
    }

    private void loadStudents() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                students.add(new Student(data[0], Double.parseDouble(data[1]), Double.parseDouble(data[2])));
            }
        } catch (IOException e) {
            System.out.println("Nenhum aluno encontrado. Criando novo arquivo.");
        }
    }

    private void saveStudents() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Student student : students) {
                bw.write(student.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar alunos.");
        }
    }

    public void addStudent(String name, double grade1, double grade2) {
        students.add(new Student(name, grade1, grade2));
        saveStudents();
    }

    public List<Student> listStudents() {
        return students;
    }
}
