import java.sql.*;
import java.util.*;
import java.io.*;
import java.net.*;

interface ConverterService {
    double convert(double amount, String from, String to) throws InvalidCurrencyException;
}

class InvalidCurrencyException extends Exception {
    public InvalidCurrencyException(String msg) { super(msg); }
}

abstract class RateProvider {
    protected Map<String, Double> rates = new HashMap<>();
    public abstract void loadRates();
    public double getRate(String c) throws InvalidCurrencyException {
        if (!rates.containsKey(c)) throw new InvalidCurrencyException("Invalid Currency: " + c);
        return rates.get(c);
    }
}

class APIRateProvider extends RateProvider {
    private final String API_KEY = "d64f4cdeaef576050832cc84";
    @Override public void loadRates() {}
    @Override
    public double getRate(String c) throws InvalidCurrencyException {
        try {
            String urlString = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/pair/USD/" + c;
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) response.append(line);
            reader.close();
            String json = response.toString();
            int startIndex = json.indexOf("conversion_rate\":") + 17;
            int endIndex = json.indexOf(",", startIndex);
            if (endIndex == -1) endIndex = json.indexOf("}", startIndex);
            return Double.parseDouble(json.substring(startIndex, endIndex));
        } catch (Exception e) {
            throw new InvalidCurrencyException("Real-time rate unavailable for: " + c);
        }
    }
}

class CurrencyConverter implements ConverterService {
    private RateProvider provider;
    public CurrencyConverter(RateProvider p) { this.provider = p; provider.loadRates(); }
    @Override
    public double convert(double amount, String from, String to) throws InvalidCurrencyException {
        return (amount / provider.getRate(from)) * provider.getRate(to);
    }
}

class DBHelper {
    private static final String BASE_URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_NAME = "converterdb";
    public static String password = "";

    public static void initializeSystem() {
        try (Connection con = DriverManager.getConnection(BASE_URL, "root", password);
             Statement stmt = con.createStatement()) {
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DB_NAME);
            stmt.executeUpdate("USE " + DB_NAME);
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS conversion_history (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, amount DOUBLE, source VARCHAR(10), " +
                    "target VARCHAR(10), result DOUBLE, time TIMESTAMP DEFAULT CURRENT_TIMESTAMP)");
            System.out.println("✔ Database system initialized.");
        } catch (SQLException e) {
            System.out.println("⚠ Database Setup Warning: " + e.getMessage());
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(BASE_URL + DB_NAME, "root", password);
    }
}

class ConversionDAO {
    public void save(double amt, String f, String t, double res) {
        try (Connection con = DBHelper.getConnection()) {
            String q = "INSERT INTO conversion_history(amount, source, target, result) VALUES(?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(q);
            ps.setDouble(1, amt); ps.setString(2, f); ps.setString(3, t); ps.setDouble(4, res);
            ps.executeUpdate();
        } catch (Exception ignored) {}
    }
}

public class SmartCurrencyConverter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter MySQL Root Password: ");
        DBHelper.password = sc.nextLine();
        DBHelper.initializeSystem();

        ConverterService converter = new CurrencyConverter(new APIRateProvider());
        ConversionDAO dao = new ConversionDAO();

        while (true) {
            System.out.print("\nEnter amount (0 to exit): ");
            double amount = sc.nextDouble();
            if (amount == 0) break;
            System.out.print("From (e.g. USD): "); String from = sc.next().toUpperCase();
            System.out.print("To (e.g. INR): "); String to = sc.next().toUpperCase();

            try {
                double result = converter.convert(amount, from, to);
                System.out.printf("\nResult: %.2f %s = %.2f %s\n", amount, from, result, to);
                dao.save(amount, from, to, result);
            } catch (InvalidCurrencyException e) { System.out.println(e.getMessage()); }
        }
        sc.close();
    }
}
