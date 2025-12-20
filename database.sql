CREATE DATABASE IF NOT EXISTS converterdb;
USE converterdb;

CREATE TABLE IF NOT EXISTS conversion_history (
    id INT AUTO_INCREMENT PRIMARY KEY,
    amount DOUBLE,
    source VARCHAR(10),
    target VARCHAR(10),
    result DOUBLE,
    conversion_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
