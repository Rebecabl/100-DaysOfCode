package TaskSorter;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class UserInterface {
    private TaskManager taskManager;
    private DefaultListModel<String> taskListModel;
    private JList<String> taskList;
    private JComboBox<String> categoryFilter;
    private JButton themeToggleButton;
    private boolean isDarkMode = false;
    private JPanel panel;
    private JFrame frame;

    public UserInterface() {
        taskManager = new TaskManager();
        frame = new JFrame("TaskMaster Pro");
        frame.setSize(550, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Lista de tarefas
        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        JScrollPane scrollPane = new JScrollPane(taskList);

        frame.add(scrollPane, BorderLayout.CENTER);

        // Criando painel para os botões
        panel = new JPanel(new GridLayout(2, 3, 5, 5));

        // Criando botões
        JButton btnAdd = new JButton("Adicionar");
        JButton btnRemove = new JButton("Remover");
        JButton btnConcluir = new JButton("Concluir");
        JButton btnBuscar = new JButton("Buscar");

        // Filtro de categoria
        String[] categorias = {"Todas", "Trabalho", "Estudos", "Pessoal"};
        categoryFilter = new JComboBox<>(categorias);
        categoryFilter.addActionListener(e -> filtrarPorCategoria());

        // Botão para alternar tema
        themeToggleButton = new JButton("Modo Escuro");
        themeToggleButton.addActionListener(e -> alternarTema());

        // Adicionando botões ao painel
        panel.add(btnAdd);
        panel.add(btnRemove);
        panel.add(btnConcluir);
        panel.add(btnBuscar);
        panel.add(categoryFilter);
        panel.add(themeToggleButton);

        frame.add(panel, BorderLayout.SOUTH);
        frame.setVisible(true);

        // Adicionando Listeners
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

        String[] categorias = {"Trabalho", "Estudos", "Pessoal"};
        String categoria = (String) JOptionPane.showInputDialog(null, "Escolha a categoria:",
                "Categoria", JOptionPane.QUESTION_MESSAGE,
                null, categorias, categorias[0]);

        if (titulo != null && categoria != null) {
            Task task = new Task(titulo, descricao, prioridade, prazo, categoria);
            taskManager.adicionarTarefa(task);
            atualizarLista();
        }
    }

    private void removerTarefa() {
        int index = taskList.getSelectedIndex();
        if (index != -1) {
            taskManager.getTarefas().remove(index);
            atualizarLista();
        } else {
            JOptionPane.showMessageDialog(null, "Selecione uma tarefa para remover.");
        }
    }

    private void concluirTarefa() {
        int index = taskList.getSelectedIndex();
        if (index != -1) {
            Task tarefa = taskManager.getTarefas().get(index);
            tarefa.concluir();
            atualizarLista();
        } else {
            JOptionPane.showMessageDialog(null, "Selecione uma tarefa para concluir.");
        }
    }

    private void buscarTarefa() {
        String termo = JOptionPane.showInputDialog("Digite o nome ou status da tarefa:");
        if (termo != null && !termo.trim().isEmpty()) {
            List<Task> filtradas = taskManager.getTarefas().stream()
                    .filter(task -> task.getTitulo().toLowerCase().contains(termo.toLowerCase()) ||
                            task.getCategoria().toLowerCase().contains(termo.toLowerCase()))
                    .collect(Collectors.toList());
            atualizarLista(filtradas);
        }
    }

    private void filtrarPorCategoria() {
        String categoriaSelecionada = (String) categoryFilter.getSelectedItem();
        if (categoriaSelecionada.equals("Todas")) {
            atualizarLista();
        } else {
            List<Task> filtradas = taskManager.getTarefas().stream()
                    .filter(task -> task.getCategoria().equals(categoriaSelecionada))
                    .collect(Collectors.toList());
            atualizarLista(filtradas);
        }
    }

    private void atualizarLista() {
        taskListModel.clear();
        for (Task task : taskManager.getTarefas()) {
            taskListModel.addElement(task.toString());
        }
    }

    private void atualizarLista(List<Task> tarefas) {
        taskListModel.clear();
        for (Task task : tarefas) {
            taskListModel.addElement(task.toString());
        }
    }

    private void alternarTema() {
        if (isDarkMode) {
            // Voltar para o tema claro
            frame.getContentPane().setBackground(Color.WHITE);
            panel.setBackground(Color.LIGHT_GRAY);
            taskList.setBackground(Color.WHITE);
            taskList.setForeground(Color.BLACK);
            themeToggleButton.setText("Modo Escuro");
        } else {
            // Ativar o tema escuro
            frame.getContentPane().setBackground(Color.DARK_GRAY);
            panel.setBackground(Color.BLACK);
            taskList.setBackground(Color.BLACK);
            taskList.setForeground(Color.WHITE);
            themeToggleButton.setText("Modo Claro");
        }
        isDarkMode = !isDarkMode;
        frame.repaint(); // Reforça a atualização da interface
    }
}
