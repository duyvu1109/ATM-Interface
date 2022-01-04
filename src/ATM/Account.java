package ATM;

import java.util.ArrayList;

public class Account {
    private String name, uuid;
    private double balance;
    private User holder;
    private ArrayList<Transaction> transactions;
    public Account(String name, User holder, Bank bank) {
        this.name = name;
        this.holder = holder;
        // get new account UUID
        this.uuid = bank.getNewAccountUUID();
        this.transactions = new ArrayList<Transaction>();
        holder.addAccount(this);
        bank.addAccount(this);
    }
}
