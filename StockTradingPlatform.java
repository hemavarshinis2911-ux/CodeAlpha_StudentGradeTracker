
import java.util.ArrayList;
import java.util.Scanner;

class Stock {
    String name;
    double price;

    Stock(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

class Transaction {
    String type;
    String stockName;
    int quantity;
    double amount;

    Transaction(String type, String stockName,
                int quantity, double amount) {
        this.type = type;
        this.stockName = stockName;
        this.quantity = quantity;
        this.amount = amount;
    }
}

class User {
    double balance = 10000;
    ArrayList<String> portfolio = new ArrayList<>();
    ArrayList<Transaction> history = new ArrayList<>();
}

public class StockTradingPlatform {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Stock[] stocks = {
            new Stock("TCS", 3500),
            new Stock("INFOSYS", 1800),
            new Stock("RELIANCE", 2900)
        };

        User user = new User();

        int choice;

        do {
            System.out.println("\n===== STOCK TRADING PLATFORM =====");
            System.out.println("1. View Stocks");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Transaction History");
            System.out.println("6. Check Balance");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.println("\nAvailable Stocks:");
                    for (int i = 0; i < stocks.length; i++) {
                        System.out.println((i + 1) + ". "
                            + stocks[i].name + " - Rs."
                            + stocks[i].price);
                    }
                    break;

                case 2:
                    System.out.print("Enter stock number (1-3): ");
                    int buyChoice = sc.nextInt();

                    if (buyChoice < 1 || buyChoice > stocks.length) {
                        System.out.println("Invalid stock number!");
                        break;
                    }

                    System.out.print("Enter quantity: ");
                    int buyQty = sc.nextInt();

                    if (buyQty <= 0) {
                        System.out.println("Quantity must be positive!");
                        break;
                    }

                    Stock buyStock = stocks[buyChoice - 1];
                    double buyAmount = buyStock.price * buyQty;

                    if (buyAmount <= user.balance) {
                        user.balance -= buyAmount;

                        user.portfolio.add(
                            buyStock.name + " - " + buyQty + " shares"
                        );

                        user.history.add(new Transaction(
                            "BUY", buyStock.name, buyQty, buyAmount
                        ));

                        System.out.println("Stock purchased successfully!");
                    } else {
                        System.out.println("Insufficient balance!");
                    }
                    break;

                case 3:
                    System.out.print("Enter stock number (1-3): ");
                    int sellChoice = sc.nextInt();

                    if (sellChoice < 1 || sellChoice > stocks.length) {
                        System.out.println("Invalid stock number!");
                        break;
                    }

                    System.out.print("Enter quantity to sell: ");
                    int sellQty = sc.nextInt();

                    if (sellQty <= 0) {
                        System.out.println("Quantity must be positive!");
                        break;
                    }

                    Stock sellStock = stocks[sellChoice - 1];
                    int owned = 0;

                    for (String item : user.portfolio) {
                        if (item.startsWith(sellStock.name + " - ")) {
                            owned += Integer.parseInt(
                                item.split(" - ")[1].split(" ")[0]
                            );
                        }
                    }

                    if (sellQty <= owned) {
                        int remaining = sellQty;

                        for (int i = 0;
                             i < user.portfolio.size() && remaining > 0;) {

                            String item = user.portfolio.get(i);

                            if (item.startsWith(sellStock.name + " - ")) {
                                int qty = Integer.parseInt(
                                    item.split(" - ")[1].split(" ")[0]
                                );

                                if (qty <= remaining) {
                                    remaining -= qty;
                                    user.portfolio.remove(i);
                                } else {
                                    user.portfolio.set(i,
                                        sellStock.name + " - "
                                        + (qty - remaining) + " shares");
                                    remaining = 0;
                                }
                            } else {
                                i++;
                            }
                        }

                        double sellAmount = sellStock.price * sellQty;
                        user.balance += sellAmount;

                        user.history.add(new Transaction(
                            "SELL", sellStock.name, sellQty, sellAmount
                        ));

                        System.out.println("Stock sold successfully!");
                    } else {
                        System.out.println("You do not own enough shares!");
                    }
                    break;

                case 4:
                    System.out.println("\nYour Portfolio:");
                    if (user.portfolio.isEmpty()) {
                        System.out.println("No stocks owned.");
                    } else {
                        for (String item : user.portfolio) {
                            System.out.println(item);
                        }
                    }
                    break;

                case 5:
                    System.out.println("\nTransaction History:");
                    if (user.history.isEmpty()) {
                        System.out.println("No transactions yet.");
                    } else {
                        for (Transaction t : user.history) {
                            System.out.println(t.type + " "
                                + t.stockName + " | Quantity: "
                                + t.quantity + " | Amount: Rs."
                                + t.amount);
                        }
                    }
                    break;

                case 6:
                    System.out.printf("Available Balance: Rs.%.2f%n",
                        user.balance);
                    break;

                case 7:
                    System.out.println("Thank you for using the platform!");
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
            }

        } while (choice != 7);

        sc.close();
    }
}
