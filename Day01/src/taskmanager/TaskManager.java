package taskmanager;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private List<Task> tasks;
    private int taskIdCounter;

    public TaskManager() {
        this.tasks = new ArrayList<>();
        this.taskIdCounter = 1;
    }

    public void addTask(String title, String description) {
        Task newTask = new Task(taskIdCounter++, title, description);
        tasks.add(newTask);
        System.out.println("Tarefa adicionada com sucesso!");
    }

    public void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("Nenhuma tarefa cadastrada.");
        } else {
            for (Task task : tasks) {
                System.out.println(task);
            }
        }
    }

    public void completeTask(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.markAsCompleted();
                System.out.println("Tarefa marcada como concluída.");
                return;
            }
        }
        System.out.println("Tarefa não encontrada.");
    }

    public void deleteTask(int id) {
        tasks.removeIf(task -> task.getId() == id);
        System.out.println("Tarefa removida.");
    }
}

