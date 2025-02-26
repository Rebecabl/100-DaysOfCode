import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserSystem {
    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextArea userListArea;
    private final String FILE_NAME = "users.txt";

    public UserSystem() {
        frame = new JFrame("Sistema de Cadastro de Usuários");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new FlowLayout());

        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);
        JButton registerButton = new JButton("Registrar");
        JButton loginButton = new JButton("Login");
        JButton listButton = new JButton("Listar Usuários");
        JButton deleteButton = new JButton("Excluir Usuário");
        userListArea = new JTextArea(10, 30);
        userListArea.setEditable(false);

        frame.add(new JLabel("Usuário:"));
        frame.add(usernameField);
        frame.add(new JLabel("Senha:"));
        frame.add(passwordField);
        frame.add(registerButton);
        frame.add(loginButton);
        frame.add(listButton);
        frame.add(deleteButton);
        frame.add(new JScrollPane(userListArea));

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser(usernameField.getText(), new String(passwordField.getPassword()));
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                authenticateUser(usernameField.getText(), new String(passwordField.getPassword()));
            }
        });

        listButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listUsers();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteUser(usernameField.getText());
            }
        });

        frame.setVisible(true);
    }

    private void registerUser(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Preencha todos os campos!");
            return;
        }

        if (userExists(username)) {
            JOptionPane.showMessageDialog(frame, "Usuário já existe!");
            return;
        }

        try (FileWriter fw = new FileWriter(FILE_NAME, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(username + "," + password);
            bw.newLine();
            JOptionPane.showMessageDialog(frame, "Usuário cadastrado com sucesso!");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "Erro ao salvar usuário!");
        }
    }

    private void authenticateUser(String username, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] credentials = line.split(",");
                if (credentials[0].equals(username) && credentials[1].equals(password)) {
                    JOptionPane.showMessageDialog(frame, "Login bem-sucedido!");
                    return;
                }
            }
            JOptionPane.showMessageDialog(frame, "Usuário ou senha incorretos!");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "Erro ao autenticar usuário!");
        }
    }

    private void listUsers() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            userListArea.setText("");
            String line;
            while ((line = br.readLine()) != null) {
                userListArea.append(line.split(",")[0] + "\n");
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "Erro ao listar usuários!");
        }
    }

    private void deleteUser(String username) {
        List<String> users = new ArrayList<>();
        boolean found = false;

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.startsWith(username + ",")) {
                    users.add(line);
                } else {
                    found = true;
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "Erro ao ler usuários!");
            return;
        }

        if (found) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
                for (String user : users) {
                    bw.write(user);
                    bw.newLine();
                }
                JOptionPane.showMessageDialog(frame, "Usuário removido!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(frame, "Erro ao salvar alterações!");
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Usuário não encontrado!");
        }
    }

    private boolean userExists(String username) {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith(username + ",")) {
                    return true;
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "Erro ao verificar usuário!");
        }
        return false;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(UserSystem::new);
    }
}
