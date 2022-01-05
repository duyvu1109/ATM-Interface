package ATM;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.security.MessageDigest;

public class User {
    private String firstName, lastName, uuid;
    private byte pinHash[];
    private ArrayList<Account> accounts;
    public User(String firstName, String lastName, String pin, Bank bank) {
        this.firstName = firstName;
        this.lastName = lastName;
         // security
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            this.pinHash = md.digest(pin.getBytes());
        } catch (NoSuchAlgorithmException e) {
            System.err.println("error, caught NoSuchAlgorithmException");
            e.printStackTrace();
            System.exit(1);
        }
        // get a new, unique ID for user
        this.uuid = bank.getNewUserUUID();
        // create new list account
        this.accounts = new ArrayList<Account>();
        // log message
        System.out.printf("New user %s with ID %s created.", this.firstName + " " + this.lastName, this.uuid);
    }
    public void addAccount(Account acc) {
        this.accounts.add(acc);
    }
    public String getUUID() {
        return this.uuid;
    }
    public boolean validatePin(String pin) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return MessageDigest.isEqual(md.digest(pin.getBytes()), this.pinHash);
        } catch (NoSuchAlgorithmException e) {
            System.err.println("error, caught NoSuchAlgorithmException");
            e.printStackTrace();
            System.exit(1);
        }
        return false;
    }
    public String getFirstName() {
        return this.firstName;
    }
    public void printAccountSummary() {
        System.out.printf("\n\n%s's accounts summary\n", this.firstName);
        for (int i = 0; i < this.accounts.size(); i++) {
            System.out.printf("%d. %s\n", i+1, this.accounts.get(i).getSummaryLine());
        }
    }
    public int numAccounts() {
        return this.accounts.size();
    }
    public void printAccountTransactionHistory(int indexOfAccount) {
        this.accounts.get(indexOfAccount).printTransactionHistory();
    }
    public double getAccBalance(int indexOfAccount) {
        return this.accounts.get(indexOfAccount).getBalance();
    }
    public String getAccUUID(int indexOfAccount) {
        return this.accounts.get(indexOfAccount).getUUID();
    }
    public void addAccountTransaction(int indexOfAccount, double amount, String memo) {
        this.accounts.get(indexOfAccount).addTransaction(amount, memo);
    }
}
