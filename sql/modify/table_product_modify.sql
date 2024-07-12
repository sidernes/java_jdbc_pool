--add column category_id to product table
ALTER TABLE product
ADD COLUMN category_id INT;

--add foreign key to product table
ALTER TABLE product
ADD CONSTRAINT fk_category
FOREIGN KEY (category_id)
REFERENCES product_category(category_id);