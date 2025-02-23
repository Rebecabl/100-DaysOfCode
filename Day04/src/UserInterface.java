package TaskManagerDark;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class UserInterface {
    private TaskManager taskManager;
    private DefaultListModel<String> taskListModel;
    private JList<String> taskList;
    private JFrame frame;
    private boolean isDarkMode;
    private JButton btnTheme;

    public UserInterface() {
        taskManager = new TaskManager();
        frame = new JFrame("TaskMaster Pro");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        frame.add(new JScrollPane(taskList), BorderLayout.CENTER);

        // 🛠 CORREÇÃO: Alteramos para GridLayout para garantir que os botões fiquem visíveis
        JPanel panel = new JPanel(new GridLayout(2, 3, 5, 5)); // 2 Linhas, 3 colunas, espaçamento de 5px
        JButton btnAdd = new JButton("Adicionar");
        JButton btnRemove = new JButton("Remover");
        JButton btnConcluir = new JButton("Concluir");
        JButton btnBuscar = new JButton("Buscar");
        btnTheme = new JButton("Alternar Tema"); // ✅ Adicionando botão de tema

        // Adicionando botões ao painel
        panel.add(btnAdd);
        panel.add(btnRemove);
        panel.add(btnConcluir);
        panel.add(btnBuscar);
        panel.add(btnTheme); // ✅ Agora realmente aparece!

        frame.add(panel, BorderLayout.SOUTH);

        // Carregar o tema salvo do usuário
        carregarTemaSalvo();

        btnAdd.addActionListener(e -> adicionarTarefa());
        btnRemove.addActionListener(e -> removerTarefa());
        btnConcluir.addActionListener(e -> concluirTarefa());
        btnBuscar.addActionListener(e -> buscarTarefa());
        btnTheme.addActionListener(e -> alternarTema()); // ✅ Agora o botão funciona!

        frame.setVisible(true);
    }

    private void adicionarTarefa() {
        String titulo = JOptionPane.showInputDialog("Título da Tarefa:");
        String descricao = JOptionPane.showInputDialog("Descrição:");
        String prioridade = JOptionPane.showInputDialog("Prioridade (Alta/Média/Baixa):");
        String prazo = JOptionPane.showInputDialog("Data de Vencimento (dd/MM/yyyy):");

        if (titulo != null && descricao != null && prioridade != null && prazo != null) {
            Task task = new Task(titulo, descricao, prioridade, prazo);
            taskManager.adicionarTarefa(task);
            taskListModel.addElement(task.toString());
        }
    }

    private void removerTarefa() {
        int index = taskList.getSelectedIndex();
        if (index != -1) {
            taskManager.getTarefas().remove(index);
            taskListModel.remove(index);
        } else {
            JOptionPane.showMessageDialog(frame, "Selecione uma tarefa para remover.");
        }
    }

    private void concluirTarefa() {
        int index = taskList.getSelectedIndex();
        if (index != -1) {
            Task tarefa = taskManager.getTarefas().get(index);
            tarefa.concluir();
            taskListModel.set(index, tarefa.toString());
        } else {
            JOptionPane.showMessageDialog(frame, "Selecione uma tarefa para concluir.");
        }
    }

    private void buscarTarefa() {
        String termo = JOptionPane.showInputDialog("Digite o nome ou status da tarefa:");

        if (termo != null && !termo.trim().isEmpty()) {
            taskListModel.clear();

            for (Task task : taskManager.getTarefas()) {
                if (task.getTitulo().toLowerCase().contains(termo.toLowerCase()) ||
                        (task.isConcluida() ? "concluída" : "pendente").contains(termo.toLowerCase())) {
                    taskListModel.addElement(task.toString());
                }
            }
        }
    }

    private void alternarTema() {
        isDarkMode = !isDarkMode;
        aplicarTema();
        salvarTema();
    }

    private void aplicarTema() {
        SwingUtilities.invokeLater(() -> {
            if (isDarkMode) {
                frame.getContentPane().setBackground(Color.DARK_GRAY);
                taskList.setBackground(Color.BLACK);
                taskList.setForeground(Color.WHITE);
                btnTheme.setText("Modo Claro"); // Atualiza o texto do botão
            } else {
                frame.getContentPane().setBackground(Color.LIGHT_GRAY);
                taskList.setBackground(Color.WHITE);
                taskList.setForeground(Color.BLACK);
                btnTheme.setText("Modo Escuro"); // Atualiza o texto do botão
            }
            frame.repaint();
        });
    }

    private void salvarTema() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("theme_config.txt"))) {
            writer.write(isDarkMode ? "dark" : "light");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void carregarTemaSalvo() {
        try (BufferedReader reader = new BufferedReader(new FileReader("theme_config.txt"))) {
            String linha = reader.readLine();
            isDarkMode = "dark".equals(linha);
        } catch (IOException e) {
            isDarkMode = false;
        }
        aplicarTema();
    }
}
