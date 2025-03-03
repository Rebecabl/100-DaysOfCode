import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentApp {
    private JFrame frame;
    private JTextField nameField, grade1Field, grade2Field;
    private JTextArea studentList;
    private StudentManager studentManager;

    public StudentApp() {
        studentManager = new StudentManager();

        frame = new JFrame("Gerenciador de Notas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new FlowLayout());

        nameField = new JTextField(15);
        grade1Field = new JTextField(5);
        grade2Field = new JTextField(5);
        JButton addButton = new JButton("Adicionar Aluno");
        JButton listButton = new JButton("Listar Alunos");
        studentList = new JTextArea(15, 40);
        studentList.setEditable(false);

        frame.add(new JLabel("Nome:"));
        frame.add(nameField);
        frame.add(new JLabel("Nota 1:"));
        frame.add(grade1Field);
        frame.add(new JLabel("Nota 2:"));
        frame.add(grade2Field);
        frame.add(addButton);
        frame.add(listButton);
        frame.add(new JScrollPane(studentList));

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = nameField.getText();
                    double grade1 = Double.parseDouble(grade1Field.getText());
                    double grade2 = Double.parseDouble(grade2Field.getText());
                    studentManager.addStudent(name, grade1, grade2);
                    JOptionPane.showMessageDialog(frame, "Aluno adicionado!");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Erro! Digite notas válidas.");
                }
            }
        });

        listButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentList.setText("");
                for (Student student : studentManager.listStudents()) {
                    studentList.append(student.getName() + " - Média: " + student.getAverage() + " - " + student.getStatus() + "\n");
                }
            }
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StudentApp::new);
    }
}
