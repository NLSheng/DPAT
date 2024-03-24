import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CategoryFileManager {

    private static final String FILE_PATH = "category.txt";
    
    public CategoryFileManager() {
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

    public static List<Category> loadCategories() {
        List<Category> categories = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 2);
                if (parts.length == 2) {
                    Category category = new Category(parts[0].trim(), parts[1].trim());
                    categories.add(category);
                }
            }
            System.out.println("Categories loaded from file: " + FILE_PATH);
        } catch (IOException e) {
            System.out.println("An error occurred while reading from the file.");
            e.printStackTrace();
        }
        return categories;
    }

    public static void saveCategories(List<Category> categories) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (int i = 0; i < categories.size(); i++) {
                Category category = categories.get(i);
                writer.write((i + 1) + "," + category.getCategoryName());
                writer.newLine();
            }
            System.out.println("Categories saved to file: " + FILE_PATH);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }
}
