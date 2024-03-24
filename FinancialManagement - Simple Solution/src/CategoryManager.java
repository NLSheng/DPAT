import java.util.List;

public class CategoryManager {
    private List<Category> categories;

    public CategoryManager() {
    }

    public CategoryManager(List<Category> categories) {
        this.categories = categories;
    }

    // Create operation: Add a new category
    public void addCategory(Category category) {
        if (!categories.contains(category)) {
            category.setCategoryID((String.valueOf(categories.size() + 1)));
            categories.add(category);
            CategoryFileManager.saveCategories(categories);
            System.out.println("Category added: " + category);
        } else {
            System.out.println("Category already exists: " + category);
        }
    }

    // Read operation: Retrieve all categories
    public List<Category> getAllCategories() {
        return categories;
    }

    // Update operation: Update an existing category
    public void updateCategory(Category oldCategory, Category updatedCategory) {
        boolean found = false;
        for (Category category : categories) {
            if (category.getCategoryID().equals(oldCategory.getCategoryID())) {
                // Update the category name
                category.setCategoryName(updatedCategory.getCategoryName());
                // Optionally, update other attributes if needed
                // category.setOtherAttribute(updatedCategory.getOtherAttribute());
                CategoryFileManager.saveCategories(categories);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("ID not found: " + oldCategory.getCategoryID());
        }
    }

    // Delete operation: Delete an existing category
    public void deleteCategory(Category category) {
        Category categoryToDelete = categories.stream()
                .filter(c -> c.getCategoryID().equals(category.getCategoryID()))
                .findFirst()
                .orElse(null);
        categories.remove(categoryToDelete);
        CategoryFileManager.saveCategories(categories);
        categories = CategoryFileManager.loadCategories();
        System.out.println("Category deleted: " + categoryToDelete);
    }
}