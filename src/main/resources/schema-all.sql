DROP TABLE orders if EXISTS;

CREATE TABLE orders (
 id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
 customerName VARCHAR(100),
 amount DECIMAL(10, 2)
);
