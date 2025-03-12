import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Stopwatch {
    private JFrame frame;
    private JLabel timeLabel;
    private JButton startButton, pauseButton, resetButton;
    private Timer timer;
    private int elapsedTime = 0; // Tempo em milissegundos
    private boolean running = false;

    public Stopwatch() {
        frame = new JFrame("Cronômetro");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new FlowLayout());

        timeLabel = new JLabel(formatTime(elapsedTime));
        timeLabel.setFont(new Font("Arial", Font.BOLD, 24));

        startButton = new JButton("Iniciar");
        pauseButton = new JButton("Pausar");
        resetButton = new JButton("Resetar");

        frame.add(timeLabel);
        frame.add(startButton);
        frame.add(pauseButton);
        frame.add(resetButton);

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                elapsedTime += 1000;
                timeLabel.setText(formatTime(elapsedTime));
            }
        });

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startTimer();
            }
        });

        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pauseTimer();
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetTimer();
            }
        });

        frame.setVisible(true);
    }

    private void startTimer() {
        if (!running) {
            timer.start();
            running = true;
        }
    }

    private void pauseTimer() {
        timer.stop();
        running = false;
    }

    private void resetTimer() {
        timer.stop();
        elapsedTime = 0;
        timeLabel.setText(formatTime(elapsedTime));
        running = false;
    }

    private String formatTime(int milliseconds) {
        int seconds = (milliseconds / 1000) % 60;
        int minutes = (milliseconds / 1000) / 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Stopwatch::new);
    }
}
