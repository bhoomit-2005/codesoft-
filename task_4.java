import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CurrencyConverter {

    // Replace with your actual API key
    private static final String API_KEY = "YOUR_API_KEY";
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("🌍 Currency Converter");

        System.out.print("Enter base currency (e.g., USD): ");
        String baseCurrency = scanner.nextLine().toUpperCase();

        System.out.print("Enter target currency (e.g., EUR): ");
        String targetCurrency = scanner.nextLine().toUpperCase();

        System.out.print("Enter amount to convert: ");
        double amount = scanner.nextDouble();

        try {
            double exchangeRate = fetchExchangeRate(baseCurrency, targetCurrency);
            if (exchangeRate == -1) {
                System.out.println("❌ Failed to fetch exchange rate. Please check currency codes.");
                return;
            }

            double convertedAmount = amount * exchangeRate;
            System.out.printf("✅ %.2f %s = %.2f %s\n", amount, baseCurrency, convertedAmount, targetCurrency);
        } catch (Exception e) {
            System.out.println("❌ Error occurred: " + e.getMessage());
        }
    }

    // Fetch exchange rate from API
    private static double fetchExchangeRate(String baseCurrency, String targetCurrency) throws Exception {
        String urlStr = API_URL + baseCurrency;

        URL url = new URL(urlStr);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();

        if (request.getResponseCode() != 200) {
            return -1;
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        StringBuilder jsonBuilder = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            jsonBuilder.append(line);
        }

        JSONObject json = new JSONObject(jsonBuilder.toString());
        JSONObject rates = json.getJSONObject("conversion_rates");

        return rates.getDouble(targetCurrency);
    }
}
