import java.util.List;

public class TransactionManager {
    private List<Transaction> transactions;

    public TransactionManager() {
    }

    public TransactionManager(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        TransactionFileManager.saveTransactions(transactions);
        System.out.println("Transaction added successfully!");
    }

    public List<Transaction> getAllTransactions() {
        return transactions;
    }

    public Transaction getSpecificTransactionById(String transactionID) {
        List<Transaction> transactions = getAllTransactions();
        for (Transaction transaction : transactions) {
            if (transaction.getTransactionID().equals(transactionID)) {
                return transaction;
            }
        }
        return null; // Transaction not found
    }

    public void updateTransaction(Transaction oldTransaction, Transaction newTransaction, String field) {
        boolean found = false;
        for (Transaction transaction : transactions) {
            if (transaction.getTransactionID().equals(oldTransaction.getTransactionID())) {
                if (field.equals("TransactionType")) {
                    transaction.setTransactionType(newTransaction.getTransactionType());
                } else if (field.equals("Category")) {
                    transaction.setCategory(newTransaction.getCategory());
                } else if (field.equals("Date")) {
                    transaction.setDate(newTransaction.getDate());
                } else if (field.equals("Amount")) {
                    transaction.setAmount(newTransaction.getAmount());
                } else {
                    transaction.setDescription(newTransaction.getDescription());
                }
                TransactionFileManager.saveTransactions(transactions);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("ID not found: " + oldTransaction.getTransactionID());
        }
    }

    // Delete operation: Delete an existing category
    public void deleteTransaction(Transaction transaction) {
        Transaction transactionToDelete = transactions.stream()
                .filter(c -> c.getTransactionID().equals(transaction.getTransactionID()))
                .findFirst()
                .orElse(null);
        transactions.remove(transactionToDelete);
        TransactionFileManager.saveTransactions(transactions);
        transactions = TransactionFileManager.loadTransactions();
        System.out.println("Transaction deleted succesfully!");
    }
}