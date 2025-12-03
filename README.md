🚀 Project Overview

SmartCurrencyConverter is a console-based application that:

✔ Converts money between different currencies
✔ Uses real OOP structure (interfaces, inheritance, abstraction, polymorphism)
✔ Stores each conversion in a MySQL Database
✔ Logs conversions in a background thread
✔ Maintains in-memory history using Collections
✔ Demonstrates custom exceptions and error handling

This makes it a perfect academic + practical Java project.

🧩 Features Used (Complete Java Concepts)
1. Object-Oriented Programming

Interface → ConverterService

Custom Exception → InvalidCurrencyException

Abstraction → RateProvider

Inheritance & Polymorphism → StaticRateProvider, CurrencyConverter

2. Collections & Generics

Uses List<ConversionRecord> to store conversion history

3. JDBC Integration

Saves every conversion to MySQL using:

conversion_history(amount, source, target, result)

4. Multithreading

A logger thread prints all conversion logs in the background:

new LoggerThread(history).start();

5. Exception Handling

Gracefully handles invalid currency codes using custom exceptions.

🛠 Technologies Used
Component	Technology
Language	Java
Database	MySQL
Driver	JDBC
Threading	Java Threads
Collections	ArrayList / Map
📂 Project Structure
SmartCurrencyConverter
│
├── ConverterService (Interface)
├── InvalidCurrencyException (Custom Exception)
├── RateProvider (Abstract Class)
├── StaticRateProvider (Child Class)
├── CurrencyConverter (Implements Interface)
├── ConversionRecord (Model Class)
├── DBHelper (Database Connection)
├── ConversionDAO (Database Operations)
├── LoggerThread (Multithreading)
└── SmartCurrencyConverter (Main Class)

🗃 Database Setup

Create the database before running the application:

CREATE DATABASE converterdb;

USE converterdb;

CREATE TABLE conversion_history (
    id INT AUTO_INCREMENT PRIMARY KEY,
    amount DOUBLE,
    source VARCHAR(10),
    target VARCHAR(10),
    result DOUBLE
);


Update your MySQL username and password in DBHelper:

private static final String URL = "jdbc:mysql://localhost:3306/converterdb";
private static final String USER = "root";
private static final String PASS = "your_password";

▶️ How to Run

Install MySQL and create the required table

Add MySQL JDBC Driver to your project classpath

Compile the Java file:

javac SmartCurrencyConverter.java


Run the program:

java SmartCurrencyConverter

🧪 Sample Output
===== SMART CURRENCY CONVERTER =====

Enter amount (or 0 to exit): 100
From Currency: USD
To Currency: INR

100 USD = 8300.0 INR
✔ Conversion Saved to Database!
--- Background Logging Thread Started ---
Log: 100 USD -> 8300.0 INR
--- Logging Completed ---

⭐ Why This Project is Excellent for College Submission

✔ Covers ALL major Java concepts
✔ Implements JDBC + Threads, which most projects miss
✔ Clean architecture with OOP + Abstraction
✔ Beginner-friendly but still industry-style
✔ Can be easily extended using APIs for real-time currency rates

📌 Future Enhancements

Add GUI using JavaFX or Swing

Use live API rates (Fixer, CurrencyLayer, etc.)

Export history to CSV or PDF

Add user accounts + login system
