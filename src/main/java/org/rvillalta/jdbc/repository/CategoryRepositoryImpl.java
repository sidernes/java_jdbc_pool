package org.rvillalta.jdbc.repository;

import org.rvillalta.jdbc.model.Category;
import org.rvillalta.jdbc.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepositoryImpl implements Repository<Category> {

    @Override
    public List<Category> listAll() {
        List<Category> categories = new ArrayList<>();
        String query = "SELECT * FROM product_category";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Category category = new Category();
                category.setCategoryId(rs.getInt("category_id"));
                category.setCategoryName(rs.getString("category_name"));

                categories.add(category);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categories;
    }

    @Override
    public Category getById(Long id) {
        Category category = null;
        String query = "SELECT * FROM product_category WHERE category_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                category = new Category();
                category.setCategoryId(rs.getInt("category_id"));
                category.setCategoryName(rs.getString("category_name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return category;
    }

    @Override
    public void save(Category category) {
        String query = "INSERT INTO product_category (category_name) VALUES (?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, category.getCategoryName());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Category category) {
        String query = "UPDATE product_category SET category_name = ? WHERE category_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, category.getCategoryName());
            pstmt.setLong(2, category.getCategoryId());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        String query = "DELETE FROM product_category WHERE category_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setLong(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}