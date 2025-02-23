package TSearch;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private List<Task> tarefas;

    public TaskManager() {
        this.tarefas = new ArrayList<>();
    }

    public void adicionarTarefa(Task task) {
        tarefas.add(task);
    }

    public void removerTarefa(Task task) {
        tarefas.remove(task);
    }

    public List<Task> getTarefas() {
        return tarefas;
    }
}
