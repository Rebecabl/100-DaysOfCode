import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryManager {
    private final String FILE_NAME = "estoque.txt";
    private List<Product> products;

    public InventoryManager() {
        products = new ArrayList<>();
        loadProducts();
    }

    private void loadProducts() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                products.add(new Product(data[0], Integer.parseInt(data[1]), Double.parseDouble(data[2])));
            }
        } catch (IOException e) {
            System.out.println("Arquivo não encontrado. Criando novo estoque.");
        }
    }

    private void saveProducts() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Product p : products) {
                bw.write(p.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar estoque.");
        }
    }

    public void addProduct(String name, int quantity, double price) {
        products.add(new Product(name, quantity, price));
        saveProducts();
    }

    public List<Product> listProducts() {
        return products;
    }

    public boolean updateProduct(String name, int newQuantity, double newPrice) {
        for (Product p : products) {
            if (p.getName().equalsIgnoreCase(name)) {
                p.setQuantity(newQuantity);
                p.setPrice(newPrice);
                saveProducts();
                return true;
            }
        }
        return false;
    }

    public boolean deleteProduct(String name) {
        boolean removed = products.removeIf(p -> p.getName().equalsIgnoreCase(name));
        if (removed) {
            saveProducts();
        }
        return removed;
    }
}
