package ATM;

import java.util.ArrayList;

public class Account {
    private String name, uuid;
    private double balance;
    private User holder;
    private ArrayList<Transaction> transactions;
}
