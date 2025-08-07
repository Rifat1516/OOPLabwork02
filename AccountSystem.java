import java.util.*;
class Account {
    private String id;
    private String name;
    private int balance;

    public Account(String id, String name) {
        this.id = id;
        this.name = name;
        this.balance = 0;
    }
    public Account(String id, String name, int balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }
    public String getID() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getBalance() {
        return balance;
    }
    public int credit(int amount) {
        balance += amount;
        return balance;
    }
    public int debit(int amount) {
        if (amount <= balance) {
            balance -= amount;
        }else{
            System.out.println("Amount exceeded balance");
        }
        return balance;
    }
    public int transferTo(Account another, int amount) {
        if (amount <= balance){
            balance -=amount;
            another.balance +=amount;
        } else{
            System.out.println("Amount exceeded balance");
        }
        return balance;
    }
    public String toString(){
        return "Account[id=" + id + ",name=" + name + ",balance=" + balance + "]";
    }
}
public class AccountSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Account> accounts = new ArrayList<>();

        accounts.add(new Account("A1", "Alice"));
        accounts.add(new Account("A2", "Bob", 1000));
        accounts.add(new Account("A3", "Charlie", 500));

        while (true) {
            System.out.println("\n1. Create New Account");
            System.out.println("2. Credit");
            System.out.println("3. Debit");
            System.out.println("4. Transfer");
            System.out.println("5. Show All Accounts");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            if (choice == 1) {
                System.out.print("Enter ID: ");
                String id = sc.next();
                System.out.print("Enter Name: ");
                String name = sc.next();
                System.out.print("Enter Balance (or -1 for default 0): ");
                int bal = sc.nextInt();
                if (bal == -1)
                    accounts.add(new Account(id, name));
                else
                    accounts.add(new Account(id, name, bal));
                System.out.println("Account created.");
            } else if (choice == 2) {
                System.out.print("Enter Account ID: ");
                String id = sc.next();
                System.out.print("Enter amount: ");
                int amt = sc.nextInt();
                Account acc = findAccount(accounts, id);
                if (acc != null) {
                    acc.credit(amt);
                    System.out.println("Updated balance: " + acc.getBalance());
                } else {
                    System.out.println("Account not found.");
                }
            }else if (choice == 3) {
                System.out.print("Enter Account ID: ");
                String id = sc.next();
                System.out.print("Enter amount: ");
                int amt = sc.nextInt();
                Account acc = findAccount(accounts, id);
                if (acc != null) {
                    acc.debit(amt);
                    System.out.println("Updated balance: " + acc.getBalance());
                } else {
                    System.out.println("Account not found.");
                }
            } else if (choice == 4) {
                System.out.print("Enter Source ID: ");
                String srcId = sc.next();
                System.out.print("Enter Destination ID: ");
                String destId = sc.next();
                System.out.print("Enter amount: ");
                int amt = sc.nextInt();
                Account src = findAccount(accounts, srcId);
                Account dest = findAccount(accounts, destId);
                if (src != null && dest != null) {
                    src.transferTo(dest, amt);
                    System.out.println("Transfer complete.");
                } else {
                    System.out.println("One or both accounts not found.");
                }
            }else if (choice == 5) {
                for (Account a : accounts) {
                    System.out.println(a);
                }
            } else if (choice == 6) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Invalid option.");
            }
        }
        sc.close();
    }
    public static Account findAccount(ArrayList<Account> list, String id) {
        for (Account a : list) {
            if (a.getID().equals(id)) {
                return a;
            }
        }
        return null;
    }
}
