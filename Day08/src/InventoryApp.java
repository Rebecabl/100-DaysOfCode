import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InventoryApp {
    private JFrame frame;
    private JTextField nameField, quantityField, priceField;
    private JTextArea inventoryList;
    private InventoryManager inventoryManager;

    public InventoryApp() {
        inventoryManager = new InventoryManager();

        frame = new JFrame("Gerenciamento de Estoque");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new FlowLayout());

        nameField = new JTextField(15);
        quantityField = new JTextField(5);
        priceField = new JTextField(7);
        JButton addButton = new JButton("Adicionar");
        JButton listButton = new JButton("Listar");
        JButton updateButton = new JButton("Atualizar");
        JButton deleteButton = new JButton("Remover");
        inventoryList = new JTextArea(15, 40);
        inventoryList.setEditable(false);

        frame.add(new JLabel("Nome:"));
        frame.add(nameField);
        frame.add(new JLabel("Quantidade:"));
        frame.add(quantityField);
        frame.add(new JLabel("Preço:"));
        frame.add(priceField);
        frame.add(addButton);
        frame.add(updateButton);
        frame.add(deleteButton);
        frame.add(listButton);
        frame.add(new JScrollPane(inventoryList));

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                int quantity = Integer.parseInt(quantityField.getText());
                double price = Double.parseDouble(priceField.getText());
                inventoryManager.addProduct(name, quantity, price);
                JOptionPane.showMessageDialog(frame, "Produto adicionado!");
            }
        });

        listButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inventoryList.setText("");
                for (Product p : inventoryManager.listProducts()) {
                    inventoryList.append(p.getName() + " - " + p.getQuantity() + " unidades - R$ " + p.getPrice() + "\n");
                }
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                int quantity = Integer.parseInt(quantityField.getText());
                double price = Double.parseDouble(priceField.getText());
                if (inventoryManager.updateProduct(name, quantity, price)) {
                    JOptionPane.showMessageDialog(frame, "Produto atualizado!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Produto não encontrado!");
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                if (inventoryManager.deleteProduct(name)) {
                    JOptionPane.showMessageDialog(frame, "Produto removido!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Produto não encontrado!");
                }
            }
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(InventoryApp::new);
    }
}
