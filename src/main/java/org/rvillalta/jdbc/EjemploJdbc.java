package org.rvillalta.jdbc;

import java.math.BigDecimal;
import java.util.Scanner;

import java.util.List;
import org.rvillalta.jdbc.model.Product;
import org.rvillalta.jdbc.model.Category;
import org.rvillalta.jdbc.repository.ProductRepositoryImpl;
import org.rvillalta.jdbc.repository.Repository;
import org.rvillalta.jdbc.repository.CategoryRepositoryImpl;

public class EjemploJdbc {
    private static final Repository<Product> productRepository = new ProductRepositoryImpl();
    private static final Repository<Category> categoryRepository = new CategoryRepositoryImpl();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int option;
        do {
            System.out.println("\n--- Inventory Management System ---");
            System.out.println("1. Create new category");
            System.out.println("2. List all categories");
            System.out.println("3. Get category by ID");
            System.out.println("4. Update category");
            System.out.println("5. Delete category");
            System.out.println("6. Create new product");
            System.out.println("7. List all products");
            System.out.println("8. Get product by ID");
            System.out.println("9. Update product");
            System.out.println("10. Delete product");
            System.out.println("11. Exit");
            System.out.print("Enter your option: ");
            option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1:
                    createCategory();
                    break;
                case 2:
                    listAllCategories();
                    break;
                case 3:
                    getCategoryById();
                    break;
                case 4:
                    updateCategory();
                    break;
                case 5:
                    deleteCategory();
                    break;
                case 6:
                    createProduct();
                    break;
                case 7:
                    listAllProducts();
                    break;
                case 8:
                    getProductById();
                    break;
                case 9:
                    updateProduct();
                    break;
                case 10:
                    deleteProduct();
                    break;
                case 11:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (option != 11);

        scanner.close();
    }

    private static void createCategory() {
        System.out.print("Enter category name: ");
        String name = scanner.nextLine();
        Category category = new Category();
        category.setCategoryName(name);
        categoryRepository.save(category);
        System.out.println("Category created successfully.");
    }

    private static void listAllCategories() {
        List<Category> categories = categoryRepository.listAll();
        for (Category category : categories) {
            System.out.printf("Category ID: %d, Name: %s%n",
                    category.getCategoryId(), category.getCategoryName());
        }
    }

    private static void getCategoryById() {
        System.out.print("Enter category ID: ");
        Long id = Long.parseLong(scanner.nextLine());
        Category category = categoryRepository.getById(id);
        if (category != null) {
            System.out.printf("Category ID: %d, Name: %s%n",
                    category.getCategoryId(), category.getCategoryName());
        } else {
            System.out.println("Category not found.");
        }
    }

    private static void updateCategory() {
        System.out.print("Enter category ID: ");
        Long id = Long.parseLong(scanner.nextLine());
        Category category = categoryRepository.getById(id);
        if (category != null) {
            System.out.print("Enter new category name: ");
            String name = scanner.nextLine();
            category.setCategoryName(name);
            categoryRepository.update(category);
            System.out.println("Category updated successfully.");
        } else {
            System.out.println("Category not found.");
        }
    }

    private static void deleteCategory() {
        System.out.print("Enter category ID: ");
        Long id = Long.parseLong(scanner.nextLine());
        categoryRepository.delete(id);
        System.out.println("Category deleted successfully.");
    }

    private static void createProduct() {
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter product price: ");
        BigDecimal price = new BigDecimal(scanner.nextLine());
        System.out.print("Enter category ID: ");
        int categoryId = Integer.parseInt(scanner.nextLine());

        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setCategoryId(categoryId);
        productRepository.save(product);
        System.out.println("Product created successfully.");
    }

    private static void listAllProducts() {
        List<Product> products = productRepository.listAll();
        for (Product product : products) {
            System.out.printf("ID: %d, Name: %s, Price: %.2f, Date: %s, Category ID: %d%n",
                    product.getId(), product.getName(), product.getPrice(), product.getDateRegistered(), product.getCategoryId());
        }
    }

    private static void getProductById() {
        System.out.print("Enter product ID: ");
        Long id = Long.parseLong(scanner.nextLine());
        Product product = productRepository.getById(id);
        if (product != null) {
            System.out.printf("Product with ID %d: Name: %s, Price: %.2f, Category ID: %d%n",
                    product.getId(), product.getName(), product.getPrice(), product.getCategoryId());
        } else {
            System.out.println("Product not found.");
        }
    }

    private static void updateProduct() {
        System.out.print("Enter product ID: ");
        Long id = Long.parseLong(scanner.nextLine());
        Product product = productRepository.getById(id);
        if (product != null) {
            System.out.print("Enter new product name: ");
            String name = scanner.nextLine();
            System.out.print("Enter new product price: ");
            BigDecimal price = new BigDecimal(scanner.nextLine());
            System.out.print("Enter new category ID: ");
            int categoryId = Integer.parseInt(scanner.nextLine());

            product.setName(name);
            product.setPrice(price);
            product.setCategoryId(categoryId);
            productRepository.update(product);
            System.out.println("Product updated successfully.");
        } else {
            System.out.println("Product not found.");
        }
    }

    private static void deleteProduct() {
        System.out.print("Enter product ID: ");
        Long id = Long.parseLong(scanner.nextLine());
        productRepository.delete(id);
        System.out.println("Product deleted successfully.");
    }
}