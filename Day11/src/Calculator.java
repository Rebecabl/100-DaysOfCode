import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Calculator {
    private JFrame frame;
    private JTextField inputField;
    private JTextArea historyArea;
    private List<String> history;
    private DecimalFormat df = new DecimalFormat("#.#####");

    public Calculator() {
        history = new ArrayList<>();

        frame = new JFrame("Calculadora Científica");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setLayout(new FlowLayout());

        inputField = new JTextField(20);
        JButton addButton = new JButton("+");
        JButton subButton = new JButton("-");
        JButton mulButton = new JButton("*");
        JButton divButton = new JButton("/");
        JButton sqrtButton = new JButton("√");
        JButton powButton = new JButton("^");
        JButton sinButton = new JButton("sin");
        JButton cosButton = new JButton("cos");
        JButton tanButton = new JButton("tan");
        JButton clearButton = new JButton("C");
        JButton historyButton = new JButton("Histórico");
        historyArea = new JTextArea(10, 30);
        historyArea.setEditable(false);

        frame.add(inputField);
        frame.add(addButton);
        frame.add(subButton);
        frame.add(mulButton);
        frame.add(divButton);
        frame.add(sqrtButton);
        frame.add(powButton);
        frame.add(sinButton);
        frame.add(cosButton);
        frame.add(tanButton);
        frame.add(clearButton);
        frame.add(historyButton);
        frame.add(new JScrollPane(historyArea));

        addButton.addActionListener(e -> performOperation("+"));
        subButton.addActionListener(e -> performOperation("-"));
        mulButton.addActionListener(e -> performOperation("*"));
        divButton.addActionListener(e -> performOperation("/"));
        sqrtButton.addActionListener(e -> performSingleOperation("√"));
        powButton.addActionListener(e -> performOperation("^"));
        sinButton.addActionListener(e -> performSingleOperation("sin"));
        cosButton.addActionListener(e -> performSingleOperation("cos"));
        tanButton.addActionListener(e -> performSingleOperation("tan"));
        clearButton.addActionListener(e -> inputField.setText(""));
        historyButton.addActionListener(e -> showHistory());

        frame.setVisible(true);
    }

    private void performOperation(String operator) {
        try {
            String input = inputField.getText().trim();
            String[] values = input.split("\\s+"); // Divide por espaços múltiplos

            if (values.length != 2) {
                inputField.setText("Erro! Digite: num1 espaço num2");
                return;
            }

            double num1 = Double.parseDouble(values[0]);
            double num2 = Double.parseDouble(values[1]);
            double result = 0;

            switch (operator) {
                case "+": result = num1 + num2; break;
                case "-": result = num1 - num2; break;
                case "*": result = num1 * num2; break;
                case "/":
                    if (num2 == 0) {
                        inputField.setText("Erro! Divisão por zero.");
                        return;
                    }
                    result = num1 / num2;
                    break;
                case "^": result = Math.pow(num1, num2); break;
                default:
                    inputField.setText("Erro! Operação inválida.");
                    return;
            }

            String operation = num1 + " " + operator + " " + num2 + " = " + df.format(result);
            history.add(operation);
            inputField.setText(df.format(result));

        } catch (NumberFormatException ex) {
            inputField.setText("Erro! Digite números válidos.");
        }
    }

    private void performSingleOperation(String operator) {
        try {
            String input = inputField.getText().trim();
            if (input.isEmpty()) {
                inputField.setText("Erro! Digite um número.");
                return;
            }

            double num = Double.parseDouble(input);
            double result = 0;

            switch (operator) {
                case "√":
                    if (num < 0) {
                        inputField.setText("Erro! Raiz negativa.");
                        return;
                    }
                    result = Math.sqrt(num);
                    break;
                case "sin": result = Math.sin(Math.toRadians(num)); break;
                case "cos": result = Math.cos(Math.toRadians(num)); break;
                case "tan": result = Math.tan(Math.toRadians(num)); break;
                default:
                    inputField.setText("Erro! Operação inválida.");
                    return;
            }

            String operation = operator + "(" + num + ") = " + df.format(result);
            history.add(operation);
            inputField.setText(df.format(result));

        } catch (NumberFormatException ex) {
            inputField.setText("Erro! Digite um número válido.");
        }
    }

    private void showHistory() {
        historyArea.setText("Histórico:\n");
        for (String record : history) {
            historyArea.append(record + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Calculator::new);
    }
}
