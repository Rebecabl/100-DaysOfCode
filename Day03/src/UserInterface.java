package TSearch;

import javax.swing.*;
import java.awt.*;

public class UserInterface {
    private TaskManager taskManager;
    private DefaultListModel<String> taskListModel;
    private JList<String> taskList;

    public UserInterface() {
        taskManager = new TaskManager();
        JFrame frame = new JFrame("TaskMaster Pro");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        frame.add(new JScrollPane(taskList), BorderLayout.CENTER);

        JPanel panel = new JPanel();
        JButton btnAdd = new JButton("Adicionar");
        JButton btnRemove = new JButton("Remover");
        JButton btnConcluir = new JButton("Concluir");
        JButton btnBuscar = new JButton("Buscar");

        panel.add(btnAdd);
        panel.add(btnRemove);
        panel.add(btnConcluir);
        panel.add(btnBuscar);

        frame.add(panel, BorderLayout.SOUTH);
        frame.setVisible(true);

        btnAdd.addActionListener(e -> adicionarTarefa());
        btnRemove.addActionListener(e -> removerTarefa());
        btnConcluir.addActionListener(e -> concluirTarefa());
        btnBuscar.addActionListener(e -> buscarTarefa());
    }

    private void adicionarTarefa() {
        String titulo = JOptionPane.showInputDialog("Título da Tarefa:");
        String descricao = JOptionPane.showInputDialog("Descrição:");
        String prioridade = JOptionPane.showInputDialog("Prioridade (Alta/Média/Baixa):");
        String prazo = JOptionPane.showInputDialog("Data de Vencimento (dd/MM/yyyy):");

        Task task = new Task(titulo, descricao, prioridade, prazo);
        taskManager.adicionarTarefa(task);
        taskListModel.addElement(task.toString());
    }

    private void removerTarefa() {
        int index = taskList.getSelectedIndex();
        if (index != -1) {
            taskManager.getTarefas().remove(index);
            taskListModel.remove(index);
        } else {
            JOptionPane.showMessageDialog(null, "Selecione uma tarefa para remover.");
        }
    }

    private void concluirTarefa() {
        int index = taskList.getSelectedIndex();
        if (index != -1) {
            Task tarefa = taskManager.getTarefas().get(index);
            tarefa.concluir();
            taskListModel.set(index, tarefa.toString());
        } else {
            JOptionPane.showMessageDialog(null, "Selecione uma tarefa para concluir.");
        }
    }

    private void buscarTarefa() {
        String termo = JOptionPane.showInputDialog("Digite o nome ou status da tarefa:");

        if (termo != null && !termo.trim().isEmpty()) {
            taskListModel.clear(); // Limpa a lista exibida na interface

            for (Task task : taskManager.getTarefas()) {
                if (task.getTitulo().toLowerCase().contains(termo.toLowerCase()) ||
                        (task.isConcluida() ? "concluída" : "pendente").contains(termo.toLowerCase())) {
                    taskListModel.addElement(task.toString());
                }
            }
        }
    }
}
