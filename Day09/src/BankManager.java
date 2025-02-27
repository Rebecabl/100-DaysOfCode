import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BankManager {
    private final String FILE_NAME = "contas.txt";
    private List<Account> accounts;

    public BankManager() {
        accounts = new ArrayList<>();
        loadAccounts();
    }

    private void loadAccounts() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                accounts.add(new Account(data[0], data[1], Double.parseDouble(data[2])));
            }
        } catch (IOException e) {
            System.out.println("Nenhuma conta encontrada. Criando novo banco.");
        }
    }

    private void saveAccounts() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Account acc : accounts) {
                bw.write(acc.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar contas.");
        }
    }

    public void createAccount(String accountNumber, String owner, double initialBalance) {
        accounts.add(new Account(accountNumber, owner, initialBalance));
        saveAccounts();
    }

    public List<Account> listAccounts() {
        return accounts;
    }

    public Account findAccount(String accountNumber) {
        for (Account acc : accounts) {
            if (acc.getAccountNumber().equalsIgnoreCase(accountNumber)) {
                return acc;
            }
        }
        return null;
    }

    public boolean deposit(String accountNumber, double amount) {
        Account acc = findAccount(accountNumber);
        if (acc != null) {
            acc.deposit(amount);
            saveAccounts();
            return true;
        }
        return false;
    }

    public boolean withdraw(String accountNumber, double amount) {
        Account acc = findAccount(accountNumber);
        if (acc != null && acc.withdraw(amount)) {
            saveAccounts();
            return true;
        }
        return false;
    }

    public boolean transfer(String fromAccount, String toAccount, double amount) {
        Account accFrom = findAccount(fromAccount);
        Account accTo = findAccount(toAccount);
        if (accFrom != null && accTo != null && accFrom.transfer(accTo, amount)) {
            saveAccounts();
            return true;
        }
        return false;
    }
}
