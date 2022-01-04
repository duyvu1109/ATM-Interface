package ATM;

import java.util.ArrayList;
import java.util.Random;

public class Bank {
    private String name;
    private ArrayList<User> users;
    private ArrayList<Account> accounts;
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
}
