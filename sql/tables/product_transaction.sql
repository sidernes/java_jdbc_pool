CREATE TABLE product_transaction (
    transaction_id INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT,
    quantity INT,
    transaction_type ENUM('INCOME', 'OUTCOME'),
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    description VARCHAR(255),
    FOREIGN KEY (product_id) REFERENCES product(id)
);