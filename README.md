🚀 Project Overview: Smart Currency Converter
SmartCurrencyConverter is a professional-grade console application that demonstrates the integration of real-world web services with local data persistence.

✔ Real-Time Data: Fetches live market rates via REST API.
✔ Advanced OOP: Implements interfaces, inheritance, abstraction, and polymorphism.
✔ Dynamic JDBC: Automatically creates and manages a MySQL Database.
✔ Secure Design: Handles sensitive credentials via runtime user input.
✔ Error Resilience: Uses custom exceptions to manage API and user input failures.
 


🧩 Core Java Concepts Implemented1. 
1.Object-Oriented Programming (OOP)
->Interface: ConverterService (Defines the conversion contract).
->Custom Exception: InvalidCurrencyException (Handles domain-specific errors).
->Abstraction: RateProvider (Abstract base for fetching data).
->Inheritance & Polymorphism: APIRateProvider extends RateProvider, allowing the CurrencyConverter to work with any provider type.

2. Networking & API IntegrationREST API
->Consumption: Uses HttpURLConnection to parse real-time JSON data from ExchangeRate-API.
->Data Parsing: Manual string manipulation and index tracking to extract values from JSON strings.

3. JDBC & Data PersistenceAutomated 
->Setup: DBHelper uses SQL CREATE IF NOT EXISTS commands to set up the environment without manual SQL execution.
->DAO Pattern: ConversionDAO abstracts the database logic from the main application flow.





🛠 Technologies Used
  Component        Technology
->Language         Java 8+
->Database         MySQL
->Connectivity     JDBC(Java Database Connectivity)
->API Service      ExchangeRate-API (v6)
->Data Format      JSON




📂 Project Structure
SmartCurrencyConverter
    │
    ├── ConverterService (Interface)
    ├── InvalidCurrencyException (Custom Exception)
    ├── RateProvider (Abstract Class)
    ├── APIRateProvider (API Logic - Inheritance)
    ├── CurrencyConverter (Service Implementation)
    ├── DBHelper (Connection & Auto-Schema Setup)
    ├── ConversionDAO (Database Operations)
    └── SmartCurrencyConverter (Main Entry Point)




🗃 Database Architecture
The application is "Self-Healing." While it creates the table automatically, the schema used is:

SQLCREATE DATABASE IF NOT EXISTS converterdb;
USE converterdb;

CREATE TABLE IF NOT EXISTS conversion_history (
    id INT AUTO_INCREMENT PRIMARY KEY,
    amount DOUBLE,
    source VARCHAR(10),
    target VARCHAR(10),
    result DOUBLE,
    time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);




▶️ How to Run
1.Ensure MySQL Server is running on your machine.

2.Add the MySQL JDBC Driver (Connector/J) to your project classpath.

3.Compile:
javac SmartCurrencyConverter.java

4.Run:
java -cp ".;mysql-connector-j-x.x.x.jar" SmartCurrencyConverter

5.Enter your MySQL root password when prompted.
🧪 Sample Output
Enter MySQL Root Password: ****
✔ Database system initialized.

Enter amount (0 to exit): 100
From (e.g. USD): USD
To (e.g. INR): INR

Result: 100.00 USD = 8345.50 INR
