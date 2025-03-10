import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;

public class PasswordGenerator {
    private JFrame frame;
    private JTextField passwordField;
    private JSpinner lengthSpinner;
    private JCheckBox numbersBox, upperBox, lowerBox, specialBox;
    private JButton generateButton, copyButton;
    private final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private final String NUMBERS = "0123456789";
    private final String SPECIAL = "!@#$%^&*()-_=+<>?";

    public PasswordGenerator() {
        frame = new JFrame("Gerador de Senhas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new FlowLayout());

        JLabel lengthLabel = new JLabel("Tamanho:");
        lengthSpinner = new JSpinner(new SpinnerNumberModel(12, 6, 32, 1));

        numbersBox = new JCheckBox("Números", true);
        upperBox = new JCheckBox("Letras Maiúsculas", true);
        lowerBox = new JCheckBox("Letras Minúsculas", true);
        specialBox = new JCheckBox("Caracteres Especiais", true);

        generateButton = new JButton("Gerar Senha");
        copyButton = new JButton("Copiar");
        passwordField = new JTextField(20);
        passwordField.setEditable(false);

        frame.add(lengthLabel);
        frame.add(lengthSpinner);
        frame.add(numbersBox);
        frame.add(upperBox);
        frame.add(lowerBox);
        frame.add(specialBox);
        frame.add(generateButton);
        frame.add(copyButton);
        frame.add(passwordField);

        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generatePassword();
            }
        });

        copyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                copyToClipboard();
            }
        });

        frame.setVisible(true);
    }

    private void generatePassword() {
        int length = (int) lengthSpinner.getValue();
        StringBuilder password = new StringBuilder();
        SecureRandom random = new SecureRandom();
        String charPool = "";

        if (upperBox.isSelected()) charPool += UPPERCASE;
        if (lowerBox.isSelected()) charPool += LOWERCASE;
        if (numbersBox.isSelected()) charPool += NUMBERS;
        if (specialBox.isSelected()) charPool += SPECIAL;

        if (charPool.isEmpty()) {
            passwordField.setText("Selecione pelo menos uma opção!");
            return;
        }

        for (int i = 0; i < length; i++) {
            password.append(charPool.charAt(random.nextInt(charPool.length())));
        }

        passwordField.setText(password.toString());
    }

    private void copyToClipboard() {
        String password = passwordField.getText();
        if (!password.isEmpty()) {
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new java.awt.datatransfer.StringSelection(password), null);
            JOptionPane.showMessageDialog(frame, "Senha copiada!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PasswordGenerator::new);
    }
}
