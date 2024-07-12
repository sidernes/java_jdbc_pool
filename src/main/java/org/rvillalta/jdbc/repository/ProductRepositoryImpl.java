package org.rvillalta.jdbc.repository;

import org.rvillalta.jdbc.model.Product;
import org.rvillalta.jdbc.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImpl implements  Repository<Product> {
    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getInstance();
    }
    @Override
    public List<Product> listAll() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM product";

        try (Statement stmt = getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getNString("name"));
                product.setPrice(rs.getBigDecimal("price"));
                product.setDateRegistered(rs.getTimestamp("date_registered"));
                products.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    @Override
    public Product getById(Long id) {
        Product product = null;
        String query = "SELECT * FROM product WHERE id = ?";

        try ( PreparedStatement pstmt = getConnection().prepareStatement(query)) {

            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getNString("name"));
                product.setPrice(rs.getBigDecimal("price"));
                product.setDateRegistered(rs.getTimestamp("date_registered"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return product;
    }

    @Override
    public void save(Product product) {
        String query = "INSERT INTO product (name, price, date_registered) VALUES (?, ?, NOW())";

        try (PreparedStatement pstmt = getConnection().prepareStatement(query)) {

            pstmt.setString(1, product.getName());
            pstmt.setBigDecimal(2, product.getPrice());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Product product) {

    }

    @Override
    public void delete(Long id) {

    }
}
