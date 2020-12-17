public class Vault implements MoneyStorage {
    private double balance;
    private String description;

    public Vault(double startingBalance) {
        this.balance = startingBalance;
    }

    public void setBalance(double balance, double amount) {
        if (amount > balance) {
            balance = 0;
        }
        this.balance = balance;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public String getDescription() {
        return description;
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
    public double withdraw(double amount) {
        // return entire balance if attempted withdrawal is greater than balance
        if (amount > getBalance()) {
            double result = getBalance();
            setBalance(balance, amount);
            return result;
        }
        double result = Math.min(amount, balance);
        balance -= result;
        return result;
    }
//    Add a new class to the project named Vault.
//    Vault must implement MoneyStorage.
//    Complete the implementation. Add fields, constructors, and getters as required.
//        (Refer to Wallet for inspiration.)
//    Rules:
//    Deposits must be positive values.
//    Cannot overdraw, but can return the remaining balance. For example, if the balance
//        is 45.0 and we withdraw(109.0)
//    the amount returned should be 45.0 and the balance should be 0.0 after the method call.


}
