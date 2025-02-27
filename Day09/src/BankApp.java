import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BankApp {
    private JFrame frame;
    private JTextField accountField, ownerField, amountField, transferToField;
    private JTextArea accountList;
    private BankManager bankManager;

    public BankApp() {
        bankManager = new BankManager();

        frame = new JFrame("Banco Simples");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new FlowLayout());

        accountField = new JTextField(10);
        ownerField = new JTextField(10);
        amountField = new JTextField(7);
        transferToField = new JTextField(10);
        JButton createButton = new JButton("Criar Conta");
        JButton depositButton = new JButton("Depositar");
        JButton withdrawButton = new JButton("Sacar");
        JButton transferButton = new JButton("Transferir");
        JButton listButton = new JButton("Listar Contas");
        accountList = new JTextArea(15, 40);
        accountList.setEditable(false);

        frame.add(new JLabel("Número da Conta:"));
        frame.add(accountField);
        frame.add(new JLabel("Dono:"));
        frame.add(ownerField);
        frame.add(new JLabel("Valor:"));
        frame.add(amountField);
        frame.add(createButton);
        frame.add(depositButton);
        frame.add(withdrawButton);
        frame.add(new JLabel("Transferir Para:"));
        frame.add(transferToField);
        frame.add(transferButton);
        frame.add(listButton);
        frame.add(new JScrollPane(accountList));

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String accNum = accountField.getText();
                String owner = ownerField.getText();
                double initialBalance = Double.parseDouble(amountField.getText());
                bankManager.createAccount(accNum, owner, initialBalance);
                JOptionPane.showMessageDialog(frame, "Conta criada!");
            }
        });

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String accNum = accountField.getText();
                double amount = Double.parseDouble(amountField.getText());
                if (bankManager.deposit(accNum, amount)) {
                    JOptionPane.showMessageDialog(frame, "Depósito realizado!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Conta não encontrada!");
                }
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String accNum = accountField.getText();
                double amount = Double.parseDouble(amountField.getText());
                if (bankManager.withdraw(accNum, amount)) {
                    JOptionPane.showMessageDialog(frame, "Saque realizado!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Saldo insuficiente ou conta não encontrada!");
                }
            }
        });

        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fromAcc = accountField.getText();
                String toAcc = transferToField.getText();
                double amount = Double.parseDouble(amountField.getText());
                if (bankManager.transfer(fromAcc, toAcc, amount)) {
                    JOptionPane.showMessageDialog(frame, "Transferência realizada!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Erro na transferência!");
                }
            }
        });

        listButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accountList.setText("");
                for (Account acc : bankManager.listAccounts()) {
                    accountList.append(acc.getAccountNumber() + " - " + acc.getOwner() + " - R$ " + acc.getBalance() + "\n");
                }
            }
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BankApp::new);
    }
}
