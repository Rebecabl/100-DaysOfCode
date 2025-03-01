import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GuessingGame {
    private JFrame frame;
    private JTextField guessField;
    private JLabel messageLabel;
    private JButton guessButton, resetButton;
    private int secretNumber, attempts;

    public GuessingGame() {
        frame = new JFrame("Jogo de Adivinhação");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new FlowLayout());

        messageLabel = new JLabel("Adivinhe um número entre 1 e 100:");
        guessField = new JTextField(5);
        guessButton = new JButton("Tentar");
        resetButton = new JButton("Reiniciar");
        resetButton.setEnabled(false);  // Só ativa quando o jogo termina

        frame.add(messageLabel);
        frame.add(guessField);
        frame.add(guessButton);
        frame.add(resetButton);

        generateSecretNumber();

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int guess = Integer.parseInt(guessField.getText());
                    attempts++;
                    if (guess == secretNumber) {
                        messageLabel.setText("Parabéns! Acertou em " + attempts + " tentativas!");
                        guessButton.setEnabled(false);
                        resetButton.setEnabled(true);
                    } else if (guess < secretNumber) {
                        messageLabel.setText("Tente um número maior!");
                    } else {
                        messageLabel.setText("Tente um número menor!");
                    }
                } catch (NumberFormatException ex) {
                    messageLabel.setText("Digite um número válido!");
                }
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateSecretNumber();
                messageLabel.setText("Adivinhe um número entre 1 e 100:");
                guessButton.setEnabled(true);
                resetButton.setEnabled(false);
                guessField.setText("");
            }
        });

        frame.setVisible(true);
    }

    private void generateSecretNumber() {
        Random random = new Random();
        secretNumber = random.nextInt(100) + 1;  // Número entre 1 e 100
        attempts = 0;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GuessingGame::new);
    }
}
