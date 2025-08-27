\c customer_db;

CREATE TABLE customer (
    id VARCHAR(255) PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    street VARCHAR(255),
    house_number VARCHAR(50),
    zip_code VARCHAR(20)
);

-- sample data
INSERT INTO customer (id, first_name, last_name, email, street, house_number, zip_code) VALUES
('CUST001', 'John', 'Doe', 'john@example.com', 'Main St', '12A', '12345'),
('CUST002', 'Alice', 'Smith', 'alice@example.com', 'Oak St', '5B', '54321'),
('CUST003', 'Bob', 'Johnson', 'bob@example.com', 'Pine St', '44C', '67890'),
('CUST004', 'Charlie', 'Brown', 'charlie@example.com', 'Maple St', '77D', '13579'),
('CUST005', 'David', 'Lee', 'david@example.com', 'Cedar St', '2E', '24680'),
('CUST006', 'Eve', 'Adams', 'eve@example.com', 'Birch St', '10F', '11223'),
('CUST007', 'Frank', 'White', 'frank@example.com', 'Elm St', '99G', '33445'),
('CUST008', 'Grace', 'Hall', 'grace@example.com', 'Walnut St', '20H', '55667'),
('CUST009', 'Hank', 'Miller', 'hank@example.com', 'Poplar St', '3I', '77889'),
('CUST010', 'Ivy', 'Green', 'ivy@example.com', 'Ash St', '15J', '99000');
