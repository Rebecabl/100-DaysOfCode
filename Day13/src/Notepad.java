import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Notepad {
    private JFrame frame;
    private JTextArea textArea;
    private JFileChooser fileChooser;

    public Notepad() {
        frame = new JFrame("Bloco de Notas Simples");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        textArea = new JTextArea();
        fileChooser = new JFileChooser();

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Arquivo");
        JMenuItem newItem = new JMenuItem("Novo");
        JMenuItem openItem = new JMenuItem("Abrir");
        JMenuItem saveItem = new JMenuItem("Salvar");
        JMenuItem exitItem = new JMenuItem("Sair");

        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);

        frame.setJMenuBar(menuBar);
        frame.add(new JScrollPane(textArea), BorderLayout.CENTER);

        // Ações dos botões
        newItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
            }
        });

        openItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnValue = fileChooser.showOpenDialog(frame);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                        textArea.read(br, null);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(frame, "Erro ao abrir arquivo!");
                    }
                }
            }
        });

        saveItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnValue = fileChooser.showSaveDialog(frame);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                        textArea.write(bw);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(frame, "Erro ao salvar arquivo!");
                    }
                }
            }
        });

        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Notepad::new);
    }
}
