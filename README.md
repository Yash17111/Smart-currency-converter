# 🚀 Project Overview

SmartCurrencyConverter is a console-based application that:

✔ Converts money between different currencies

✔ Uses real OOP structure (interfaces, inheritance, abstraction, polymorphism)

✔ Stores each conversion in a MySQL Database

✔ Fetches live exchange rates via REST API

✔ Demonstrates custom exceptions and error handling

---

### 🧩 Features Used (Complete Java Concepts)

**1. Object-Oriented Programming** * Interface → `ConverterService`

* Custom Exception → `InvalidCurrencyException`
* Abstraction → `RateProvider`
* Inheritance & Polymorphism → `APIRateProvider`

**2. JDBC Integration** Saves every conversion to MySQL using:

`conversion_history(amount, source, target, result, time)`

**3. API & Networking** Uses `HttpURLConnection` to fetch live data from ExchangeRate-API.

---

### 📂 Project Structure

```text
SmartCurrencyConverter
│
├── ConverterService (Interface)
├── InvalidCurrencyException (Custom Exception)
├── RateProvider (Abstract Class)
├── APIRateProvider (API Logic)
├── CurrencyConverter (Service Implementation)
├── DBHelper (Database Setup)
├── ConversionDAO (Database Operations)
└── SmartCurrencyConverter (Main Class)

```

---

### 🗃 Database Setup

The program creates the database automatically, but here is the schema for reference:

```sql
CREATE DATABASE IF NOT EXISTS converterdb;
USE converterdb;

CREATE TABLE IF NOT EXISTS conversion_history (
    id INT AUTO_INCREMENT PRIMARY KEY,
    amount DOUBLE,
    source VARCHAR(10),
    target VARCHAR(10),
    result DOUBLE,
    time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

```

---

### ▶️ How to Run

1. **Add MySQL JDBC Driver** to your project classpath.
2. **Compile the Java file:** `javac SmartCurrencyConverter.java`
3. **Run the program:** `java -cp ".;mysql-connector-j-x.x.x.jar" SmartCurrencyConverter

