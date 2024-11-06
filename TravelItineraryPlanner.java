import java.util.ArrayList;
import java.util.Scanner;

class Destination {
    String name;
    String date;
    String preference;
    double estimatedCostPerDay; // Estimated cost per day at the destination

    // Constructor to initialize destination details
    public Destination(String name, String date, String preference, double estimatedCostPerDay) {
        this.name = name;
        this.date = date;
        this.preference = preference;
        this.estimatedCostPerDay = estimatedCostPerDay;
    }
}

class WeatherService {
    // Mock-up weather details. In a real-world scenario, you would fetch real-time
    // data from an API.
    public String getWeather(String city) {
        // For demo purposes, returning a hardcoded weather message.
        return "Weather in " + city + ": 22°C, Partly Cloudy";
    }
}

class MapService {
    // Provide a simple map link. In reality, you would use Google Maps API to
    // generate map or directions.
    public String getMapLink(String city) {
        return "Google Maps Link: https://www.google.com/maps/search/" + city.replace(" ", "+");
    }
}

class BudgetCalculator {
    // Calculates total budget based on the number of days and estimated daily cost
    public double calculateTotalCost(double estimatedCostPerDay, int numDays) {
        return estimatedCostPerDay * numDays;
    }
}

public class TravelItineraryPlanner {
    private ArrayList<Destination> destinations = new ArrayList<>();
    private WeatherService weatherService = new WeatherService();
    private MapService mapService = new MapService();
    private BudgetCalculator budgetCalculator = new BudgetCalculator();
    private Scanner scanner = new Scanner(System.in);

    // Method to add a destination to the itinerary
    public void addDestination(String name, String date, String preference, double estimatedCostPerDay) {
        destinations.add(new Destination(name, date, preference, estimatedCostPerDay));
    }

    // Method to display the complete travel itinerary with weather, map, and
    // preferences
    public void showItinerary() {
        System.out.println("\n--- Travel Itinerary ---");
        for (Destination dest : destinations) {
            System.out.println("\nDestination: " + dest.name);
            System.out.println("Date: " + dest.date);
            System.out.println("Preference: " + dest.preference);
            System.out.println("Estimated Cost per Day: $" + dest.estimatedCostPerDay);
            System.out.println("Weather: " + weatherService.getWeather(dest.name));
            System.out.println("Map Link: " + mapService.getMapLink(dest.name));
        }
    }

    // Method to calculate and display the total budget for the trip
    public void showTotalBudget() {
        double totalBudget = 0;
        int totalDays = 0;

        for (Destination dest : destinations) {
            // Assuming user provides number of days for each destination
            System.out.print("Enter the number of days for visiting " + dest.name + ": ");
            int days = scanner.nextInt();
            totalDays += days;
            totalBudget += budgetCalculator.calculateTotalCost(dest.estimatedCostPerDay, days);
        }

        System.out.println("\nTotal budget for the trip: $" + totalBudget);
        System.out.println("Total days of travel: " + totalDays + " days");
    }

    // Main method to run the application
    public static void main(String[] args) {
        TravelItineraryPlanner planner = new TravelItineraryPlanner();

        // Input for number of destinations
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of destinations: ");
        int numDestinations = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Input destinations details
        for (int i = 0; i < numDestinations; i++) {
            System.out.print("\nEnter destination name: ");
            String name = scanner.nextLine();

            System.out.print("Enter travel date (e.g., 2024-12-20): ");
            String date = scanner.nextLine();

            System.out.print("Enter preferences (e.g., city tour, beach, hiking): ");
            String preference = scanner.nextLine();

            System.out.print("Enter estimated cost per day at this destination: ");
            double estimatedCostPerDay = scanner.nextDouble();
            scanner.nextLine(); // Consume newline

            planner.addDestination(name, date, preference, estimatedCostPerDay);
        }

        // Display itinerary and weather details
        planner.showItinerary();

        // Calculate and display total budget
        planner.showTotalBudget();

        scanner.close();
    }
}
