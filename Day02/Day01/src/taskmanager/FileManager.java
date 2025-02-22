package taskmanager;

import java.io.*;
import java.util.List;

public class FileManager {
    private static final String FILE_PATH = "tasks.txt";

    public static void saveTasks(List<Task> tasks) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            out.writeObject(tasks);
            System.out.println("Tarefas salvas com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao salvar tarefas: " + e.getMessage());
        }
    }

    public static List<Task> loadTasks() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            return (List<Task>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Nenhuma tarefa salva.");
            return null;
        }
    }
}
