import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class CurrencyConverter {
    private JFrame frame;
    private JTextField amountField;
    private JComboBox<String> fromCurrency, toCurrency;
    private JLabel resultLabel;
    private Map<String, Double> exchangeRates;

    public CurrencyConverter() {
        frame = new JFrame("Conversor de Moedas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new FlowLayout());

        JLabel amountLabel = new JLabel("Valor:");
        amountField = new JTextField(10);

        JLabel fromLabel = new JLabel("De:");
        fromCurrency = new JComboBox<>(new String[]{"Real (BRL)", "Dólar (USD)", "Euro (EUR)", "Libra (GBP)"});

        JLabel toLabel = new JLabel("Para:");
        toCurrency = new JComboBox<>(new String[]{"Real (BRL)", "Dólar (USD)", "Euro (EUR)", "Libra (GBP)"});

        JButton convertButton = new JButton("Converter");
        resultLabel = new JLabel("Resultado: ---");

        frame.add(amountLabel);
        frame.add(amountField);
        frame.add(fromLabel);
        frame.add(fromCurrency);
        frame.add(toLabel);
        frame.add(toCurrency);
        frame.add(convertButton);
        frame.add(resultLabel);

        initializeExchangeRates();

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertCurrency();
            }
        });

        frame.setVisible(true);
    }

    private void initializeExchangeRates() {
        exchangeRates = new HashMap<>();
        exchangeRates.put("BRL-USD", 0.20);
        exchangeRates.put("BRL-EUR", 0.18);
        exchangeRates.put("BRL-GBP", 0.15);
        exchangeRates.put("USD-BRL", 5.0);
        exchangeRates.put("USD-EUR", 0.90);
        exchangeRates.put("USD-GBP", 0.75);
        exchangeRates.put("EUR-BRL", 5.5);
        exchangeRates.put("EUR-USD", 1.11);
        exchangeRates.put("EUR-GBP", 0.85);
        exchangeRates.put("GBP-BRL", 6.5);
        exchangeRates.put("GBP-USD", 1.33);
        exchangeRates.put("GBP-EUR", 1.18);
    }

    private void convertCurrency() {
        try {
            double amount = Double.parseDouble(amountField.getText().replace(",", "."));
            String from = getCurrencyCode(fromCurrency.getSelectedItem().toString());
            String to = getCurrencyCode(toCurrency.getSelectedItem().toString());

            if (from.equals(to)) {
                resultLabel.setText("Resultado: " + amount + " " + to);
                return;
            }

            String key = from + "-" + to;
            if (exchangeRates.containsKey(key)) {
                double convertedAmount = amount * exchangeRates.get(key);
                resultLabel.setText("Resultado: " + String.format("%.2f", convertedAmount) + " " + to);
            } else {
                resultLabel.setText("Erro na conversão.");
            }
        } catch (NumberFormatException ex) {
            resultLabel.setText("Digite um valor válido.");
        }
    }

    private String getCurrencyCode(String currency) {
        if (currency.contains("BRL")) return "BRL";
        if (currency.contains("USD")) return "USD";
        if (currency.contains("EUR")) return "EUR";
        if (currency.contains("GBP")) return "GBP";
        return "";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CurrencyConverter::new);
    }
}
