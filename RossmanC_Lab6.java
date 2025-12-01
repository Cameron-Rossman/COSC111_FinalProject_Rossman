
/**
This program simulates a simple ATM machine that allows users to deposit money, withdraw funds, and check their account 
balance. The balance is stored in cents to avoid rounding errors and formatted in dollars for display. The program uses
multiple methods to handle tasks like showing the menu, reading user choices, validating dollar inputs, and updating the 
balance. It includes input validation to prevent invalid or negative entries and blocks withdrawals that
 exceed the available balance. The program runs continuously in a loop until the user selects the Exit option.
 * @author Cameron Rossman
 * @version Fall 2025
 */
import java.util.Scanner;

public class RossmanC_Lab6 {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        int balanceCents = 0;
        System.out.println("Welcome to COSC ATM");
        System.out.println();   

        ///main method Use 
        while (true) {
            printMenu();
            int choice = readMenuChoice(kb);

            if (choice == 1) {
                int amount = readAmountCents(kb, "Enter deposit amount in dollars (e.g., 25.75): ");
                balanceCents = deposit(balanceCents, amount);
            } else if (choice == 2) {
                int amount = readAmountCents(kb, "Enter withdrawal amount in dollars (e.g., 10.00): ");
                balanceCents = withdraw(balanceCents, amount);
            } else if (choice == 3) {
                System.out.println("Current Balance: " + formatCents(balanceCents));
            } else if (choice == 4) {
                System.out.println("Goodbye!");
                break;
            }
            System.out.println();
        }

    }

    // Print Menu Method
    public static void printMenu() {
        System.out.println("1) Deposit");
        System.out.println("2) Withdraw");
        System.out.println("3) Check Balance");
        System.out.println("4) Exit");
        System.out.print("Choose 1-4: ");

    }

    /// Read Menu Method
    public static int readMenuChoice(Scanner kb) {
        int choice = 0;
        while (choice < 1 || choice > 4) {
            if (kb.hasNextInt()) {
                choice = kb.nextInt();
            } else {
                kb.nextLine();
            }
            if (choice < 1 || choice > 4) {
                System.out.println("Invalid Choice. Choose 1-4");
            }

        }
        return choice;
    }

    // Read the amount
    public static int readAmountCents(Scanner kb, String prompt) {
        double money = 0.0;
        while (true) {
            System.out.print(prompt);
            if (kb.hasNextDouble()) {
                money = kb.nextDouble();
                kb.nextLine();
                if (money > 0) {
                    break;
                } else {
                    System.out.println("Amount must be positive. Try again.");
                }
            } else {
                System.out.println("Invalid input. Enter a number like 25.75.");
                kb.nextLine();
            }
        }
        int amountCents = (int) Math.round(money * 100);
        return amountCents;
    }

    // format decimal places method
    public static String formatCents(int cents) {
        double dollars = cents / 100.0;
        return String.format("$%.2f", dollars);
    }

    // Deposit method
    public static int deposit(int balanceCents, int amountCents) {
        balanceCents += amountCents;
        System.out.println("Deposited " + formatCents(amountCents) + " New Balance: " + formatCents(balanceCents));
        return balanceCents;
    }

    // Withdraw method
    public static int withdraw(int balanceCents, int amountCents) {
        if (amountCents > balanceCents) {
            System.out.println("Insufficient funds. Balance remains: " + formatCents(balanceCents));
        } else {
            balanceCents -= amountCents;
            System.out.println("Withdrew " + formatCents(amountCents) + " New balance: " + formatCents(balanceCents));
        }
        return balanceCents;
    }
}

/* Test Code Sample
Welcome to COSC ATM

1) Deposit
2) Withdraw
3) Check Balance
4) Exit
Choose 1-4: 1
Enter deposit amount in dollars (e.g., 25.75): 200
Deposited $200.00 New Balance: $200.00

1) Deposit
2) Withdraw
3) Check Balance
4) Exit
Choose 1-4: 1
Enter deposit amount in dollars (e.g., 25.75): 234234
Deposited $234234.00 New Balance: $234434.00

1) Deposit
2) Withdraw
3) Check Balance
4) Exit
Choose 1-4: 1
Enter deposit amount in dollars (e.g., 25.75): asdfasdf
Invalid input. Enter a number like 25.75.
Enter deposit amount in dollars (e.g., 25.75): Invalid input. Enter a number like 25.75.
Enter deposit amount in dollars (e.g., 25.75): -2342
Amount must be positive. Try again.
Enter deposit amount in dollars (e.g., 25.75): 100
Deposited $100.00 New Balance: $234534.00

1) Deposit
2) Withdraw
3) Check Balance
4) Exit
Choose 1-4: 2
Enter withdrawal amount in dollars (e.g., 10.00): 300
Withdrew $300.00 New balance: $234234.00

1) Deposit
2) Withdraw
3) Check Balance
4) Exit
Choose 1-4: 2
Enter withdrawal amount in dollars (e.g., 10.00): asfdadfs
Invalid input. Enter a number like 25.75.
Enter withdrawal amount in dollars (e.g., 10.00): Invalid input. Enter a number like 25.75.
Enter withdrawal amount in dollars (e.g., 10.00): -400
Amount must be positive. Try again.
Enter withdrawal amount in dollars (e.g., 10.00): 100
Withdrew $100.00 New balance: $234134.00

1) Deposit
2) Withdraw
3) Check Balance
4) Exit
Choose 1-4: 3
Current Balance: $234134.00

1) Deposit
2) Withdraw
3) Check Balance
4) Exit
Choose 1-4: 2
Enter withdrawal amount in dollars (e.g., 10.00): 500
Insufficient funds. Balance remains: $0.00

1) Deposit
2) Withdraw
3) Check Balance
4) Exit
Choose 1-4: 2500
Invalid Choice. Choose 1-4
ASDFAFDS
Invalid Choice. Choose 1-4
Invalid Choice. Choose 1-4




1) Deposit
2) Withdraw
3) Check Balance
4) Exit
Choose 1-4: 4
Goodbye!
*/