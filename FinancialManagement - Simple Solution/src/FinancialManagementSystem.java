public class FinancialManagementSystem {
    public static void main(String[] args) {
        TransactionManager transactionManager = new TransactionManager();
        CategoryManager categoryManager = new CategoryManager();
        Dashboard dashboard = new Dashboard(transactionManager, categoryManager);
        dashboard.displayMainMenu();
    }
}