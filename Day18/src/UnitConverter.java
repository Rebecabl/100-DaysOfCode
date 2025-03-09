import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UnitConverter {
    private JFrame frame;
    private JTextField inputField;
    private JComboBox<String> unitBox;
    private JLabel resultLabel;
    private JButton convertButton;

    public UnitConverter() {
        frame = new JFrame("Conversor de Unidades");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 250);
        frame.setLayout(new FlowLayout());

        JLabel inputLabel = new JLabel("Valor:");
        inputField = new JTextField(10);

        JLabel unitLabel = new JLabel("Converter para:");
        String[] units = {"Km → Milhas", "Milhas → Km", "Celsius → Fahrenheit", "Fahrenheit → Celsius", "Kg → Libras", "Libras → Kg"};
        unitBox = new JComboBox<>(units);

        convertButton = new JButton("Converter");
        resultLabel = new JLabel("Resultado: ---");

        frame.add(inputLabel);
        frame.add(inputField);
        frame.add(unitLabel);
        frame.add(unitBox);
        frame.add(convertButton);
        frame.add(resultLabel);

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convert();
            }
        });

        frame.setVisible(true);
    }

    private void convert() {
        try {
            double value = Double.parseDouble(inputField.getText().replace(",", "."));
            String selectedUnit = (String) unitBox.getSelectedItem();
            double result = 0;

            switch (selectedUnit) {
                case "Km → Milhas":
                    result = value * 0.621371;
                    break;
                case "Milhas → Km":
                    result = value / 0.621371;
                    break;
                case "Celsius → Fahrenheit":
                    result = (value * 9 / 5) + 32;
                    break;
                case "Fahrenheit → Celsius":
                    result = (value - 32) * 5 / 9;
                    break;
                case "Kg → Libras":
                    result = value * 2.20462;
                    break;
                case "Libras → Kg":
                    result = value / 2.20462;
                    break;
            }

            resultLabel.setText("Resultado: " + String.format("%.2f", result));
        } catch (NumberFormatException ex) {
            resultLabel.setText("Erro! Digite um valor válido.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(UnitConverter::new);
    }
}
