package org.rvillalta.jdbc.repository;

import org.rvillalta.jdbc.model.ProductTransaction;
import org.rvillalta.jdbc.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductTransactionRepositoryImpl implements Repository<ProductTransaction> {

    @Override
    public List<ProductTransaction> listAll() {
        List<ProductTransaction> transactions = new ArrayList<>();
        String query = "SELECT * FROM product_transaction";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                ProductTransaction transaction = new ProductTransaction();
                transaction.setTransactionId(rs.getInt("transaction_id"));
                transaction.setProductId(rs.getInt("product_id"));
                transaction.setQuantity(rs.getInt("quantity"));
                transaction.setTransactionType(rs.getString("transaction_type"));
                transaction.setTransactionDate(rs.getTimestamp("transaction_date"));
                transaction.setDescription(rs.getString("description"));

                transactions.add(transaction);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transactions;
    }

    @Override
    public ProductTransaction getById(Long id) {
        ProductTransaction transaction = null;
        String query = "SELECT * FROM product_transaction WHERE transaction_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                transaction = new ProductTransaction();
                transaction.setTransactionId(rs.getInt("transaction_id"));
                transaction.setProductId(rs.getInt("product_id"));
                transaction.setQuantity(rs.getInt("quantity"));
                transaction.setTransactionType(rs.getString("transaction_type"));
                transaction.setTransactionDate(rs.getTimestamp("transaction_date"));
                transaction.setDescription(rs.getString("description"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transaction;
    }

    @Override
    public void save(ProductTransaction transaction) {
        String query = "INSERT INTO product_transaction (product_id, quantity, transaction_type, transaction_date, description) VALUES (?, ?, ?, NOW(), ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, transaction.getProductId());
            pstmt.setInt(2, transaction.getQuantity());
            pstmt.setString(3, transaction.getTransactionType());
            pstmt.setString(4, transaction.getDescription());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(ProductTransaction transaction) {
        String query = "UPDATE product_transaction SET product_id = ?, quantity = ?, transaction_type = ?, transaction_date = NOW(), description = ? WHERE transaction_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, transaction.getProductId());
            pstmt.setInt(2, transaction.getQuantity());
            pstmt.setString(3, transaction.getTransactionType());
            pstmt.setString(4, transaction.getDescription());
            pstmt.setInt(5, transaction.getTransactionId());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        String query = "DELETE FROM product_transaction WHERE transaction_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setLong(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}