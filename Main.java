package book;

import java.util.Scanner;
import java.math.BigDecimal;
import java.math.RoundingMode;

// --------------------- BankAccount Class ---------------------
class BankAccount {
    String holderName;
    int accNumber;
    BigDecimal balance;
    String email;
    String phoneNumber;

    BankAccount(String name, int accNumber, double initialBalance, String email, String phone) {
        this.holderName = name;
        this.accNumber = accNumber;
        this.balance = BigDecimal.valueOf(initialBalance);
        this.email = email;
        this.phoneNumber = phone;
    }

    void deposit(double amount) {
        if (amount > 0) {
            balance = balance.add(BigDecimal.valueOf(amount));
            System.out.printf("₹%.2f deposited successfully.%n", amount);
        } else {
            System.out.println("Deposit amount must be positive!");
        }
    }

    void withdraw(double amount) {
        BigDecimal amt = BigDecimal.valueOf(amount);
        if (amount > 0 && balance.compareTo(amt) >= 0) {
            balance = balance.subtract(amt);
            System.out.printf("₹%.2f withdrawn successfully.%n", amount);
        } else {
            System.out.println("Invalid withdrawal amount or insufficient funds.");
        }
    }

    void accountInfo() {
        System.out.println("----- Account Details -----");
        System.out.println("Name       : " + holderName);
        System.out.println("Acc Number : " + accNumber);
        System.out.println("Balance    : ₹" + balance.setScale(2, RoundingMode.HALF_UP).toPlainString());
        System.out.println("Email      : " + email);
        System.out.println("Phone      : " + phoneNumber);
        System.out.println("---------------------------");
    }

    void updateContactDetails(String email, String phone) {
        this.email = email;
        this.phoneNumber = phone;
        System.out.println("Contact details updated successfully!");
    }
}

// --------------------- UserInterface Class ---------------------
class UserInterface {
    static Scanner sc = new Scanner(System.in);
    static BankAccount[] accounts = new BankAccount[100];
    static int accountCount = 0;
    static int nextAccountNumber = 1001; // Auto-increment account numbers

    static void createAccount() {
        if (accountCount >= accounts.length) {
            System.out.println("Sorry, cannot create more accounts. Capacity full!");
            return;
        }

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        double balance;
        while (true) {
            System.out.print("Enter Initial Balance (must be positive): ");
            balance = sc.nextDouble();
            sc.nextLine();
            if (balance > 0) break;
            System.out.println("Initial balance must be greater than 0.");
        }

        System.out.print("Enter Email: ");
        String email = sc.nextLine();
        System.out.print("Enter Phone: ");
        String phone = sc.nextLine();

        BankAccount acc = new BankAccount(name, nextAccountNumber++, balance, email, phone);
        accounts[accountCount++] = acc;
        System.out.println("Account created successfully! Your Account Number is " + acc.accNumber);
    }

    static BankAccount findAccount(int accNo) {
        for (int i = 0; i < accountCount; i++) {
            if (accounts[i].accNumber == accNo) {
                return accounts[i];
            }
        }
        return null;
    }

    static void performDeposit() {
        System.out.print("Enter Account Number: ");
        int accNo = sc.nextInt();
        System.out.print("Enter Amount to Deposit: ");
        double amt = sc.nextDouble();
        sc.nextLine();
        BankAccount acc = findAccount(accNo);
        if (acc != null) acc.deposit(amt);
        else System.out.println("Account not found!");
    }

    static void performWithdrawal() {
        System.out.print("Enter Account Number: ");
        int accNo = sc.nextInt();
        System.out.print("Enter Amount to Withdraw: ");
        double amt = sc.nextDouble();
        sc.nextLine();
        BankAccount acc = findAccount(accNo);
        if (acc != null) acc.withdraw(amt);
        else System.out.println("Account not found!");
    }

    static void showAccountDetails() {
        System.out.print("Enter Account Number: ");
        int accNo = sc.nextInt();
        sc.nextLine();
        BankAccount acc = findAccount(accNo);
        if (acc != null) acc.accountInfo();
        else System.out.println("Account not found!");
    }

    static void updateContact() {
        System.out.print("Enter Account Number: ");
        int accNo = sc.nextInt();
        sc.nextLine();
        BankAccount acc = findAccount(accNo);
        if (acc != null) {
            System.out.print("Enter New Email: ");
            String email = sc.nextLine();
            System.out.print("Enter New Phone: ");
            String phone = sc.nextLine();
            acc.updateContactDetails(email, phone);
        } else {
            System.out.println("Account not found!");
        }
    }

    static void mainMenu() {
        while (true) {
            System.out.println("\n====== Banking Menu ======");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Show Account Details");
            System.out.println("5. Update Contact");
            System.out.println("6. Exit");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();
            sc.nextLine();//MadeBySanjeev
            switch (choice) {
                case 1: createAccount(); break;
                case 2: performDeposit(); break;
                case 3: performWithdrawal(); break;
                case 4: showAccountDetails(); break;
                case 5: updateContact(); break;
                case 6: System.out.println("Goodbye!"); return;
                default: System.out.println("Invalid choice!");
            }
        }
    }

    public static void main(String[] args) {
        mainMenu();
    }
}
