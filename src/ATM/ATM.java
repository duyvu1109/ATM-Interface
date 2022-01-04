package ATM;

import java.util.ArrayList;
import java.util.Scanner;

public class ATM {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Bank bank = new Bank("HCMUT BANK");
        User em1 = new User("Vu", "Duy", "1234", bank);
        Account acc1 = new Account("Checking", em1, bank);
    }
}
