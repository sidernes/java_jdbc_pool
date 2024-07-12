package org.rvillalta.jdbc;

import org.rvillalta.jdbc.model.Product;
import org.rvillalta.jdbc.repository.ProductRepositoryImpl;
import org.rvillalta.jdbc.repository.Repository;
import org.rvillalta.jdbc.util.DatabaseConnection;

import java.math.BigDecimal;
import java.sql.*;

public class EjemploJdbc {
    public static void main (String[] arg){
        try (
                Connection conn = DatabaseConnection.getInstance()
        ) {
            Repository<Product> repository =  new ProductRepositoryImpl();
            // Create product
//            Product newProduct = new Product();
//            newProduct.setName("Advanced Mountain Bike");
//            newProduct.setPrice(new BigDecimal("999.99"));
//            repository.save(newProduct);

            //Get all Products
            repository.listAll().forEach(
                    p -> System.out.printf("Name: %s, registered: %s%n", p.getName(), p.getDateRegistered())
            );

            //Get Product by ID
            Product productById = repository.getById(1L);
            if (productById != null) {
                System.out.printf("--> Product with ID 1: Name: %s, Price: %.2f%n",
                        productById.getName(), productById.getPrice());
            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

// Basic implement
//        try (
//                Connection conn = DatabaseConnection.getInstance();
//                Statement stmt = conn.createStatement();
//                ResultSet result = stmt.executeQuery("SELECT * FROM product")
//        ) {
//
//            while(result.next()){
//                String name = result.getNString("name");
//                Timestamp registered = result.getTimestamp("date_registered");
//                System.out.printf("Name: %s, registered: %s%n", name, registered);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    }
}
