import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

public class InstallmentSimulator {
    private JFrame frame;
    private JTextField totalAmountField;
    private JComboBox<Integer> installmentBox;
    private JLabel resultLabel, totalLabel;
    private JButton calculateButton, saveButton;

    public InstallmentSimulator() {
        frame = new JFrame("Simulador de Parcelamento");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new FlowLayout());

        JLabel totalLabelTxt = new JLabel("Valor da Compra (R$):");
        totalAmountField = new JTextField(10);

        JLabel installmentLabel = new JLabel("Número de Parcelas:");
        Integer[] options = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        installmentBox = new JComboBox<>(options);

        calculateButton = new JButton("Calcular");
        saveButton = new JButton("Salvar Relatório");
        saveButton.setEnabled(false);

        resultLabel = new JLabel("Valor da Parcela: ---");
        totalLabel = new JLabel("Total a Pagar: ---");

        frame.add(totalLabelTxt);
        frame.add(totalAmountField);
        frame.add(installmentLabel);
        frame.add(installmentBox);
        frame.add(calculateButton);
        frame.add(saveButton);
        frame.add(resultLabel);
        frame.add(totalLabel);

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateInstallments();
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

    private void calculateInstallments() {
        try {
            double totalAmount = Double.parseDouble(totalAmountField.getText().replace(",", "."));
            int installments = (int) installmentBox.getSelectedItem();
            double interestRate = (installments > 6) ? 0.05 : 0.00; // 5% de juros para mais de 6 parcelas

            double totalWithInterest = totalAmount * Math.pow(1 + interestRate, installments > 6 ? installments - 6 : 0);
            double installmentValue = totalWithInterest / installments;

            DecimalFormat df = new DecimalFormat("#,##0.00");
            resultLabel.setText("Valor da Parcela: R$ " + df.format(installmentValue));
            totalLabel.setText("Total a Pagar: R$ " + df.format(totalWithInterest));
            saveButton.setEnabled(true);
        } catch (NumberFormatException ex) {
            resultLabel.setText("Erro! Digite valores válidos.");
            totalLabel.setText("---");
        }
    }

    private void saveReport() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("relatorio_parcelas.txt", true))) {
            writer.write("=== Simulação de Parcelamento ===\n");
            writer.write("Valor da Compra: R$ " + totalAmountField.getText() + "\n");
            writer.write("Número de Parcelas: " + installmentBox.getSelectedItem() + "\n");
            writer.write(resultLabel.getText() + "\n");
            writer.write(totalLabel.getText() + "\n");
            writer.write("-------------------------\n");
            JOptionPane.showMessageDialog(frame, "Relatório salvo com sucesso!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Erro ao salvar relatório.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(InstallmentSimulator::new);
    }
}
