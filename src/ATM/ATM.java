package ATM;

import java.util.ArrayList;
import java.util.Scanner;

public class ATM {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Bank bank = new Bank("HCMUT BANK");
        User em1 = bank.addUser("Vu", "Duy", "1109");
        Account acc1 = new Account("Checking", em1, bank);
        em1.addAccount(acc1);
        bank.addAccount(acc1);

        User curUser;
        while (true) {
            // stay in login until success
            curUser = ATM.mainMenuPrompt(bank, sc);
            // stay in main menu until quit
            ATM.printUserMenu(curUser, sc);
        }
    }
    public static User mainMenuPrompt(Bank theBank, Scanner sc) {
        String userID, pin;
        User authUser;

        do {
            System.out.printf("\n\nWelcome to %s \n\n", theBank.getName());
            System.out.println("Enter user ID: ");
            userID = sc.nextLine();
            System.out.println("Enter pin: ");
            pin = sc.nextLine();

            authUser = theBank.userLogin(userID, pin);
            if (authUser == null) {
                System.out.println("Incorrect, please try again.");
            }
        } while (authUser == null);
        return authUser;
    }
    public static void printUserMenu(User theUser, Scanner sc) {
        theUser.printAccountSummary();
        int choice;
        do {
            System.out.printf("Welcome %s, what would you like to do?\n", theUser.getFirstName());
            System.out.println("1. Show account transaction history");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.println();
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            if (choice < 1 || choice > 5) {
                System.out.println("Invalid choice, please choose 1-5");
            }
        } while (choice < 1 || choice > 5);

        switch (choice) {
            case 1:
                ATM.showTransactionHistory(theUser, sc);
                break;
            case 2:
                ATM.withdrawFunc(theUser, sc);
                break;
            case 3:
                ATM.depositFunc(theUser, sc);
                break;
            case 4:
                ATM.transferFunc(theUser, sc);
                break;
            case 5:
                // gobble up
                sc.nextLine();
                break;
        }
        if (choice != 5) {
            ATM.printUserMenu(theUser, sc);
        }
    }
    public static void showTransactionHistory(User theUser, Scanner sc) {
        int theAcc;
        do {
            System.out.printf("Enter 1-%d of the account\nwhose transactions you want to see: ", theUser.numAccounts());
            theAcc = sc.nextInt();
            if (theAcc < 0 || theAcc >= theUser.numAccounts()) {
                System.out.println("Invalid Account, please try again.");
            }
        } while (theAcc < 0 || theAcc >= theUser.numAccounts());
        theUser.printAccountTransactionHistory(theAcc);
    }
    public static void transferFunc(User theUser, Scanner sc) {
        int fromAcc, toAcc;
        double amount, accBalance;
        // from account
        do {
            System.out.printf("Enter the number 1-%d of the account \nto transfer from: ", theUser.numAccounts());
            fromAcc = sc.nextInt()-1;
            if (fromAcc < 0 || fromAcc >= theUser.numAccounts()) {
                System.out.println("Invalid Account, please try again.");
            }
        } while (fromAcc < 0 || fromAcc >= theUser.numAccounts());
        accBalance = theUser.getAccBalance(fromAcc);
        // to account
        do {
            System.out.printf("Enter the number 1-%d of the account \nto transfer to: ", theUser.numAccounts());
            toAcc = sc.nextInt()-1;
            if (toAcc < 0 || toAcc >= theUser.numAccounts()) {
                System.out.println("Invalid Account, please try again.");
            }
        } while (toAcc < 0 || toAcc >= theUser.numAccounts());
        // get amount to transfer
        do {
            System.out.printf("Enter the amount you want to transfer (max: $%.02f): $", accBalance);
            amount = sc.nextDouble();
            if (amount < 0) {
                System.out.println("Amount must be greater than 0");
            } else if (amount > accBalance) {
                System.out.printf("Amount must not be greater than balance of $%.02f.", accBalance);
            }
        } while (amount < 0 || amount > accBalance);
        // do transfer
        theUser.addAccountTransaction(fromAcc, -amount, String.format("Transfer to account %s", theUser.getAccUUID(toAcc)));
        theUser.addAccountTransaction(toAcc, amount, String.format("Transfer from account %s", theUser.getAccUUID(fromAcc)));
    }
    public static void withdrawFunc(User theUser, Scanner sc) {
        int fromAcc;
        double amount, accBalance;
        String memo;
        // from account
        do {
            System.out.printf("Enter the number 1-%d of the account \nto withdraw from: ", theUser.numAccounts());
            fromAcc = sc.nextInt()-1;
            if (fromAcc < 0 || fromAcc >= theUser.numAccounts()) {
                System.out.println("Invalid Account, please try again.");
            }
        } while (fromAcc < 0 || fromAcc >= theUser.numAccounts());
        accBalance = theUser.getAccBalance(fromAcc);
        // get amount to transfer
        do {
            System.out.printf("Enter the amount you want to withdraw (max: $%.02f): $", accBalance);
            amount = sc.nextDouble();
            if (amount < 0) {
                System.out.println("Amount must be greater than 0");
            } else if (amount > accBalance) {
                System.out.printf("Amount must not be greater than balance of $%.02f.", accBalance);
            }
        } while (amount < 0 || amount > accBalance);
        // gobble up
        sc.nextLine();
        // get a memo
        System.out.println("Enter a memo: ");
        memo = sc.nextLine();
        // do withdraw
        theUser.addAccountTransaction(fromAcc, -amount, memo);
    }
    public static void depositFunc(User theUser, Scanner sc) {
        int toAcc;
        double amount, accBalance;
        String memo;
        // from account
        do {
            System.out.printf("Enter the number 1-%d of the account \nto deposit in: ", theUser.numAccounts());
            toAcc = sc.nextInt()-1;
            if (toAcc < 0 || toAcc >= theUser.numAccounts()) {
                System.out.println("Invalid Account, please try again.");
            }
        } while (toAcc < 0 || toAcc >= theUser.numAccounts());
        accBalance = theUser.getAccBalance(toAcc);
        // get amount to transfer
        do {
            System.out.printf("Enter the amount you want to transfer (max: $%.02f): $", accBalance);
            amount = sc.nextDouble();
            if (amount < 0) {
                System.out.println("Amount must be greater than 0");
            }
        } while (amount < 0);
        // gobble up
        sc.nextLine();
        // get a memo
        System.out.println("Enter a memo: ");
        memo = sc.nextLine();
        // do withdraw
        theUser.addAccountTransaction(toAcc, amount, memo);
    }
}
