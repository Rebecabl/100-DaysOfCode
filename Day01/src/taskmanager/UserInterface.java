package taskmanager;

import java.util.Scanner;

public class UserInterface {
    private TaskManager taskManager;
    private Scanner scanner;

    public UserInterface() {
        this.taskManager = new TaskManager();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("\n1. Adicionar Tarefa");
            System.out.println("2. Listar Tarefas");
            System.out.println("3. Concluir Tarefa");
            System.out.println("4. Remover Tarefa");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addTask();
                case 2 -> taskManager.listTasks();
                case 3 -> completeTask();
                case 4 -> removeTask();
                case 5 -> {
                    System.out.println("Encerrando o programa...");
                    return;
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private void addTask() {
        System.out.print("Título: ");
        String title = scanner.nextLine();
        System.out.print("Descrição: ");
        String description = scanner.nextLine();
        taskManager.addTask(title, description);
    }

    private void completeTask() {
        System.out.print("ID da tarefa a concluir: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        taskManager.completeTask(id);
    }

    private void removeTask() {
        System.out.print("ID da tarefa a remover: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        taskManager.deleteTask(id);
    }
}
