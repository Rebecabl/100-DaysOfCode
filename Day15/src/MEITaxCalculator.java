import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

public class MEITaxCalculator {
    private JFrame frame;
    private JTextField revenueField;
    private JLabel resultLabel;
    private JButton calculateButton, saveButton;
    private JComboBox<String> categoryBox;

    public MEITaxCalculator() {
        frame = new JFrame("Calculadora de Impostos MEI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 250);
        frame.setLayout(new FlowLayout());

        JLabel revenueLabel = new JLabel("Faturamento Mensal (R$):");
        revenueField = new JTextField(10);

        JLabel categoryLabel = new JLabel("Tipo de MEI:");
        String[] categories = {"Comércio", "Indústria", "Serviço"};
        categoryBox = new JComboBox<>(categories);

        resultLabel = new JLabel("Imposto DAS: ---");

        calculateButton = new JButton("Calcular");
        saveButton = new JButton("Salvar Relatório");
        saveButton.setEnabled(false);

        frame.add(revenueLabel);
        frame.add(revenueField);
        frame.add(categoryLabel);
        frame.add(categoryBox);
        frame.add(calculateButton);
        frame.add(saveButton);
        frame.add(resultLabel);

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateTaxes();
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveReport();
            }
        });

        frame.setVisible(true);
    }

    private void calculateTaxes() {
        try {
            double inss = 66.00;  // Contribuição fixa para INSS
            double iss = 0, icms = 0;

            String category = categoryBox.getSelectedItem().toString();
            if (category.equals("Serviço")) {
                iss = 5.00;
            } else if (category.equals("Comércio")) {
                icms = 1.00;
            } else if (category.equals("Indústria")) {
                icms = 1.00;
            }

            double totalTax = inss + iss + icms;

            DecimalFormat df = new DecimalFormat("#,##0.00");
            resultLabel.setText("Imposto DAS: R$ " + df.format(totalTax));
            saveButton.setEnabled(true);
        } catch (NumberFormatException ex) {
            resultLabel.setText("Erro! Digite um valor válido.");
        }
    }

    private void saveReport() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("relatorio.txt", true))) {
            writer.write("Faturamento: R$ " + revenueField.getText() + "\n");
            writer.write(resultLabel.getText() + "\n");
            writer.write("---------------------------------\n");
            JOptionPane.showMessageDialog(frame, "Relatório salvo com sucesso!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Erro ao salvar relatório.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MEITaxCalculator::new);
    }
}
