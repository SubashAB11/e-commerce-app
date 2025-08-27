\c product_db;

CREATE TABLE category (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    description TEXT
);

CREATE TABLE product (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    description TEXT,
    available_quantity DOUBLE PRECISION,
    price DECIMAL(10,2),
    category_id INT REFERENCES category(id)
);

-- sample categories
INSERT INTO category (name, description) VALUES
('Electronics', 'Electronic gadgets and devices'),
('Books', 'All kinds of books'),
('Clothing', 'Apparel and fashion items'),
('Home & Kitchen', 'Furniture and kitchen essentials'),
('Sports', 'Sports equipment'),
('Beauty', 'Cosmetics and beauty products'),
('Toys', 'Toys and games'),
('Automotive', 'Car accessories'),
('Grocery', 'Daily groceries'),
('Health', 'Healthcare products');

-- sample products (50)
INSERT INTO product (name, description, available_quantity, price, category_id)
SELECT
    'Product ' || g,
    'Description for product ' || g,
    (10 + g),
    (10 + g) * 5,
    (g % 10) + 1
FROM generate_series(1,50) g;
