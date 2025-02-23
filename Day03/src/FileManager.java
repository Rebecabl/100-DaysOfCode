package TSearch;

import java.io.*;
import java.util.List;

public class FileManager {
    private static final String FILE_NAME = "tasks.txt";

    public static void salvarTarefas(List<Task> tarefas) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(tarefas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Task> carregarTarefas() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (List<Task>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }
}
