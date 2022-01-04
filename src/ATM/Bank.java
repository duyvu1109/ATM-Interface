package ATM;

import java.util.ArrayList;

public class Bank {
    private String name;
    private ArrayList<User> users;
    private ArrayList<Account> accounts;
    public String getNewUserUUID() {
        return "";
    }
    public String getNewAccountUUID() {
        return "";
    }
    public void addAccount(Account acc) {
        this.accounts.add(acc);
    }
}
