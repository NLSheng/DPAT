import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TransactionFileManager {
    private static final String FILE_PATH = "transaction.txt";

    public TransactionFileManager() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("An error occurred while creating the file.");
                e.printStackTrace();
            }
        }
    }

    public static List<Transaction> loadTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 6);
                if (parts.length == 6) {
                    // Parse TransactionType from string
                    Transaction.TransactionType transactionType = Transaction.TransactionType.valueOf(parts[1].trim());
                    Category category = new Category(null, parts[2].trim()); // Assuming Category class has a
                                                                             // constructor that accepts category name
                    double amount = Double.parseDouble(parts[4].trim());
                    Transaction transaction = new Transaction(parts[0].trim(), transactionType, category,
                            parts[3].trim(), amount, parts[5].trim());
                    transactions.add(transaction);
                }
            }
            System.out.println("Transactions loaded from file: " + FILE_PATH);
        } catch (IOException e) {
            System.out.println("An error occurred while reading from the file.");
            e.printStackTrace();
        }
        return transactions;
    }

    public static void saveTransactions(List<Transaction> transactions) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (int i = 0; i < transactions.size(); i++) {
                Transaction transaction = transactions.get(i);
                writer.write((i + 1) + "," + transaction.getTransactionType() + "," + transaction.getCategory() + ","
                        + transaction.getDate() + "," + transaction.getAmount() + "," + transaction.getDescription());
                writer.newLine();
            }
            System.out.println("Transactions saved to file: " + FILE_PATH);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }
}