import java.util.HashMap; 
import java.util.Map; 
import java.util.Scanner; 
 
class BankAccount { 
    private String accountNumber; 
    private String name; 
    private String mobileNumber; 
    private String emailId; 
    private String password; 
    private double balance; 
 
    public BankAccount(String accountNumber, String name, String mobileNumber, String emailId, String password, double balance) { 
        this.accountNumber = accountNumber; 
        this.name = name; 
        this.mobileNumber = mobileNumber; 
        this.emailId = emailId; 
        this.password = password; 
        this.balance = balance; 
    } 
 
    public String getAccountNumber() { 
        return accountNumber; 
    } 
 
    public String getName() { 
        return name; 
    } 
 
    public String getMobileNumber() { 
        return mobileNumber; 
    } 
 
    public String getEmailId() { 
        return emailId; 
    } 
 
    public String getPassword() { 
        return password; 
    } 
 
    public double getBalance() { 
        return balance; 
    } 
 
    public void deposit(double amount) { 
        balance += amount; 
    } 
 
    public void withdraw(double amount) { 
        if (balance >= amount) { 
            balance -= amount; 
        } else { 
            System.out.println("Insufficient balance"); 
        } 
    } 
} 
 
public class BankingInformationSystem { 
    private Map<String, BankAccount> accounts; 
    private Scanner scanner; 
 
    public BankingInformationSystem() { 
        accounts = new HashMap<>(); 
        scanner = new Scanner(System.in); 
    } 
 
    public void createAccount() { 
        System.out.print("Enter name: "); 
        String name = scanner.next(); 
        System.out.print("Enter mobile number: "); 
        String mobileNumber = scanner.next(); 
        System.out.print("Enter email ID: "); 
        scanner.nextLine(); // Consume newline left-over 
        String emailId = scanner.nextLine(); 
        System.out.print("Enter password: "); 
        String password = scanner.next(); 
        System.out.print("Enter initial balance: "); 
        double balance = scanner.nextDouble(); 
 
        String accountNumber = "ACC" + (accounts.size() + 1); 
        BankAccount account = new BankAccount(accountNumber, name, mobileNumber, emailId, password, balance); 
        accounts.put(accountNumber, account); 
        System.out.println("Account created successfully. Your account number is " + accountNumber); 
    } 
 
    public void login() { 
        System.out.print("Enter account number: "); 
        String accountNumber = scanner.next(); 
        System.out.print("Enter password: "); 
        String password = scanner.next(); 
 
        BankAccount account = accounts.get(accountNumber); 
        if (account != null && account.getPassword().equals(password)) { 
            System.out.println("Login successful"); 
            accountMenu(account); 
        } else { 
            System.out.println("Invalid account number or password"); 
        } 
    } 
 
    public void transferAmount(BankAccount account) { 
        System.out.print("Enter recipient's account number: "); 
        String recipientAccountNumber = scanner.next(); 
        BankAccount recipientAccount = accounts.get(recipientAccountNumber); 
 
        if (recipientAccount != null) { 
            System.out.print("Enter amount to transfer: "); 
            double transferAmount = scanner.nextDouble(); 
 
            if (account.getBalance() >= transferAmount) { 
                account.withdraw(transferAmount); 
                recipientAccount.deposit(transferAmount); 
                System.out.println("Transfer successful"); 
            } else { 
                System.out.println("Insufficient balance"); 
            } 
        } else { 
            System.out.println("Recipient account not found");
} 
    } 
    public void accountMenu(BankAccount account) { 
        while (true) { 
            System.out.println("1. Check balance"); 
            System.out.println("2. Deposit"); 
            System.out.println("3. Withdraw"); 
            System.out.println("4. Transfer amount"); 
            System.out.println("5. Exit"); 
 
            System.out.print("Choose an option: "); 
            int option = scanner.nextInt(); 
 
            switch (option) { 
                case 1: 
                    System.out.println("Current balance: " + account.getBalance()); 
                    break; 
                case 2: 
                    System.out.print("Enter amount to deposit: "); 
                    double deposit = scanner.nextDouble(); 
                    account.deposit(deposit); 
                    break; 
                case 3: 
                    System.out.print("Enter amount to withdraw: "); 
                    double withdrawal = scanner.nextDouble(); 
                    account.withdraw(withdrawal); 
                    break; 
                case 4: 
                    transferAmount(account); 
                    break; 
                case 5: 
                    return; 
                default: 
                    System.out.println("Invalid option"); 
            } 
        } 
    } 
 
    public void run() { 
        while (true) { 
            System.out.println("1. Create account"); 
            System.out.println("2. Login"); 
            System.out.println("3. Exit"); 
 
            System.out.print("Choose an option: "); 
            int option = scanner.nextInt(); 
 
            switch (option) { 
                case 1: 
                    createAccount(); 
                    break; 
                case 2: 
                    login(); 
                    break; 
                case 3: 
                    System.exit(0); 
                    break; 
                default: 
                    System.out.println("Invalid option"); 
            } 
        } 
    } 
 
    public static void main(String[] args) { 
        BankingInformationSystem bankingSystem = new BankingInformationSystem(); 
        bankingSystem.run(); 
    } 
}