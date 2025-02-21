package taskmanager;

import java.util.ArrayList;
import java.util.List;

public class Database {
    private static List<Task> tasks = new ArrayList<>();

    public static List<Task> getTasks() {
        return tasks;
    }

    public static void addTask(Task task) {
        tasks.add(task);
    }
}
