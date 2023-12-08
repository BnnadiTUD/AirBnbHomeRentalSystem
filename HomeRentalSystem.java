import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class HomeRentalSystem {
    private List<Home> homes;
    private List<Customer> customers;
    private List<Rental> rentals;

    public HomeRentalSystem() {
        homes = new ArrayList<>();
        customers = new ArrayList<>();
        rentals = new ArrayList<>();
    }

    public void addHome(Home home) {
        homes.add(home);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void rentHome(Home home, Customer customer, int days) {
        if (home.isAvailable()) {
            home.rent();
            rentals.add(new Rental(home, customer, days));

        } else {
            System.out.println("Home is not available for rent.");
        }
    }

    public void returnHome(Home home) {
        home.returnHome();
        Rental rentalToRemove = null;
        for (Rental rental : rentals) {
            if (rental.getHome() == home) {
                rentalToRemove = rental;
                break;
            }
        }
        if (rentalToRemove != null) {
            rentals.remove(rentalToRemove);

        } else {
            System.out.println("Home was not rented.");
        }
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("===== Home Rental System =====");
            System.out.println("1. Rent a Home");
            System.out.println("2. Return a Home");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 1) {
                System.out.println("\n== Rent a Home ==\n");
                System.out.print("Enter your name: ");
                String customerName = scanner.nextLine();

                System.out.println("\nAvailable Homes:");
                for (Home home : homes) {
                    if (home.isAvailable()) {
                        System.out.println(home.getHomeId() + " - " + home.getCity() + " " + home.getType());
                    }
                }

                System.out.print("\nEnter the home ID you want to rent: ");
                String homeId = scanner.nextLine();

                System.out.print("Enter the number of days for rental: ");
                int rentalDays = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                Customer c = new Customer("CUS" + (customers.size() + 1), customerName);
                addCustomer(c);

                Home selectedHome = null;
                for (Home home : homes) {
                    if (home.getHomeId().equals(homeId) && home.isAvailable()) {
                        selectedHome = home;
                        break;
                    }
                }

                if (selectedHome != null) {
                    double totalPrice = selectedHome.calculatePrice(rentalDays);
                    System.out.println("\n== Rental Information ==\n");
                    System.out.println("Customer ID: " + c.getCustomerId());
                    System.out.println("Customer Name: " + c.getName());
                    System.out.println("Home: " + selectedHome.getCity() + " " + selectedHome.getType());
                    System.out.println("Rental Days: " + rentalDays);
                    System.out.printf("Total Price: $%.2f%n", totalPrice);

                    System.out.print("\nConfirm rental (Y/N): ");
                    String confirm = scanner.nextLine();

                    if (confirm.equalsIgnoreCase("Y")) {
                        rentHome(selectedHome, c, rentalDays);
                        System.out.println("\nHome rented successfully.");
                    } else {
                        System.out.println("\nRental canceled.");
                    }
                } else {
                    System.out.println("\nInvalid home selection or home not available for rent.");
                }
            } else if (choice == 2) {
                System.out.println("\n== Return a Home ==\n");
                System.out.print("Enter the home ID you want to return: ");
                String homeId = scanner.nextLine();

                Home homeToReturn = null;
                for (Home home : homes) {
                    if (home.getHomeId().equals(homeId) && !home.isAvailable()) {
                        homeToReturn = home;
                        break;
                    }
                }

                if (homeToReturn != null) {
                    Customer customer = null;
                    for (Rental rental : rentals) {
                        if (rental.getHome() == homeToReturn) {
                            customer = rental.getCustomer();
                            break;
                        }
                    }

                    if (customer != null) {
                        returnHome(homeToReturn);
                        System.out.println("Home returned successfully by " + customer.getName());
                    } else {
                        System.out.println("Home was not rented or rental information is missing.");
                    }
                } else {
                    System.out.println("Invalid home ID or home is not rented.");
                }
            } else if (choice == 3) {
                break;
            } else {
                System.out.println("Invalid choice. Please enter a valid option.");
            }
        }

        System.out.println("\nThank you for using the Home Rental System!");
    }
    public static void main(String[] args) {
        HomeRentalSystem rentalSystem = new HomeRentalSystem();

        Home home1 = new Home("H001", "Cork", "PentHouse", 90.0); // Different base price per day for each car
        Home home2 = new Home("H002", "Dublin", "Apartment", 140.0);
        Home home3 = new Home("H003", "Wexford", "Villa", 100.0);
        rentalSystem.addHome(home1);
        rentalSystem.addHome(home2);
        rentalSystem.addHome(home3);

        rentalSystem.menu();
    }
}

