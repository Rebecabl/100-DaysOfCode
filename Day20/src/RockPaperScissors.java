import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class RockPaperScissors {
    private JFrame frame;
    private JButton rockButton, paperButton, scissorsButton, resetButton;
    private JLabel resultLabel, scoreLabel;
    private int playerScore = 0, computerScore = 0;
    private final String[] choices = {"Pedra", "Papel", "Tesoura"};

    public RockPaperScissors() {
        frame = new JFrame("Pedra, Papel e Tesoura");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new FlowLayout());

        rockButton = new JButton("Pedra 🪨");
        paperButton = new JButton("Papel 📄");
        scissorsButton = new JButton("Tesoura ✂");
        resetButton = new JButton("Reiniciar");

        resultLabel = new JLabel("Escolha uma opção para jogar!");
        scoreLabel = new JLabel("Placar: Jogador 0 - 0 Computador");

        frame.add(rockButton);
        frame.add(paperButton);
        frame.add(scissorsButton);
        frame.add(resultLabel);
        frame.add(scoreLabel);
        frame.add(resetButton);

        rockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playGame("Pedra");
            }
        });

        paperButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playGame("Papel");
            }
        });

        scissorsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playGame("Tesoura");
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });

        frame.setVisible(true);
    }

    private void playGame(String playerChoice) {
        Random rand = new Random();
        String computerChoice = choices[rand.nextInt(3)];
        String result;

        if (playerChoice.equals(computerChoice)) {
            result = "Empate! Ambos escolheram " + playerChoice;
        } else if (
                (playerChoice.equals("Pedra") && computerChoice.equals("Tesoura")) ||
                        (playerChoice.equals("Tesoura") && computerChoice.equals("Papel")) ||
                        (playerChoice.equals("Papel") && computerChoice.equals("Pedra"))
        ) {
            playerScore++;
            result = "Você venceu! " + playerChoice + " vence " + computerChoice;
        } else {
            computerScore++;
            result = "Você perdeu! " + computerChoice + " vence " + playerChoice;
        }

        resultLabel.setText(result);
        scoreLabel.setText("Placar: Jogador " + playerScore + " - " + computerScore + " Computador");
    }

    private void resetGame() {
        playerScore = 0;
        computerScore = 0;
        resultLabel.setText("Escolha uma opção para jogar!");
        scoreLabel.setText("Placar: Jogador 0 - 0 Computador");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RockPaperScissors::new);
    }
}
