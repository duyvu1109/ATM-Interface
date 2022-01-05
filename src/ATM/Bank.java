package ATM;

import java.util.ArrayList;
import java.util.Random;

public class Bank {
    private String name;
    private ArrayList<User> users;
    private ArrayList<Account> accounts;
    public Bank(String name) {
        this.name = name;
        this.users = new ArrayList<User>();
        this.accounts = new ArrayList<Account>();
    }
    public String getNewUserUUID() {
        String uuid;
        Random rd = new Random();
        int len = 6;
        boolean nonUnique = false;
        do {
            uuid = "";
            for (int i =0; i < len; i++) {
                uuid += ((Integer)rd.nextInt(10)).toString();
            }
            for (User u : this.users) {
                if (uuid.compareTo(u.getUUID()) == 0) {
                    nonUnique = true;
                    break;
                }
            }
        } while (nonUnique);

        return uuid;
    }
    public String getNewAccountUUID() {
        String uuid;
        Random rd = new Random();
        int len = 10;
        boolean nonUnique = false;
        do {
            uuid = "";
            for (int i =0; i < len; i++) {
                uuid += ((Integer)rd.nextInt(10)).toString();
            }
            for (Account a : this.accounts) {
                if (uuid.compareTo(a.getUUID()) == 0) {
                    nonUnique = true;
                    break;
                }
            }
        } while (nonUnique);

        return uuid;
    }
    public void addAccount(Account acc) {
        this.accounts.add(acc);
    }

    public User addUser(String firstName, String lastName, String pin) {
        User newUser = new User(firstName, lastName, pin, this);
        this.users.add(newUser);
        Account newAccount = new Account("Savings", newUser, this);
        newUser.addAccount(newAccount);
        this.addAccount(newAccount);

        return newUser;
    }
    public User userLogin(String userID, String pin) {
        for (User u : this.users) {
            if (u.getUUID().compareTo(userID) == 0 && u.validatePin(pin)) {
                return u;
            }
        }
        return null;
    }
    public String getName() {
        return this.name;
    }
}
