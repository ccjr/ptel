package mobi.ccjr.ptel.model;

public class Balance {
    private String balance;
    private String expiry;

    public Balance(String balance, String expiry) {
        this.balance = balance;
        this.expiry = expiry;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }
}
