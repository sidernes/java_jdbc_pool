-- Crear la tabla product con la columna category_id
CREATE TABLE product (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(45) DEFAULT NULL,
    price DECIMAL(10,2) DEFAULT NULL,
    date_registered DATETIME DEFAULT NULL,
    category_id INT,
    CONSTRAINT fk_category FOREIGN KEY (category_id) REFERENCES product_category(category_id)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;