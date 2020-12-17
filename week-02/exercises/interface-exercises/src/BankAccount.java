public class BankAccount implements MoneyStorage {


    private double balance;
    private String accountNumber;


    public BankAccount(double startingBalance) {
        this.balance = startingBalance;
//        this.accountNumber = accountNumber;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public String getDescription() {
        return String.format("Mortgage #%s", accountNumber);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    @Override
    public boolean deposit(double amount) {
        if (amount > 0.0) {
            balance += amount;
            return true;
        }
        return false;
    }

    @Override
    public void setBalance(double amount, double balance) {
        if (balance - amount < 0) {
            this.balance = balance - amount;
        } else {
            double result = (balance - amount);
            this.balance = result;
        }
    }

    @Override
    public double withdraw(double amount) {

        if ((balance - amount >= -25) && (balance - amount < 0)) {
            setBalance(amount,balance);
            return amount;
        } else if (amount > 0) {
            double result = (getBalance() - amount);
            balance = result;
//            setBalance(amount,balance);
            return result;
        }
        return 0;
    }
}


//    Add a new class to the project named BankAccount.
//        BankAccount must implement MoneyStorage.
//        Complete the implementation. Add fields, constructors, and getters as required. (Refer to Mortgage
//        for inspiration, but with a positive balance.)
//        Rules:
//        Deposits must be positive values.
//        Can overdraw up to -25.00 dollars, but no lower. (The balance is allowed to go negative.)