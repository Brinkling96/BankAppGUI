//Type of account that handles loans
public class LoanAccount extends Account {

    // interest is in decimal form, should be < 1
    public static double INTEREST_RATE = 0.1;
    public static double COLLATERAL_ORIGINAL_VALUE = 0.1;
    private int originalValue;
    private int principal;
    private Collateral collateral;

    public LoanAccount (int originalValue, User user, Bank bank, Collateral collateral) {
        super(originalValue, user, bank);
        this.setOriginalValue(originalValue);
        this.setPrincipal(originalValue);
        this.setCollateral(collateral);
    }

    public LoanAccount(String accountID, String originalValue, String principal, Collateral collateral) {
        super(accountID, principal);
        this.setOriginalValue(Integer.parseInt(originalValue));
        this.setPrincipal(Integer.parseInt(originalValue));
        this.setCollateral(collateral);
    }

    public void chargeInterest(int days){
        int interestGained = (int)(this.getPrincipal()*Math.pow((1+(INTEREST_RATE)), days));
        this.setPrincipal(this.getPrincipal() + interestGained);
    }

    // Getters and setters
    public int getOriginalValue() {
        return this.getOriginalValue();
    }

    public void setOriginalValue(int originalValue) {
        this.originalValue = originalValue;
    }

    public int getPrincipal() {
        return principal;
    }

    public void setPrincipal(int principal) {
        this.principal = principal;
    }

    public Collateral getCollateral() {
        return collateral;
    }

    public void setCollateral(Collateral collateral) {
        this.collateral = collateral;
    }

    @Override
    public boolean deposit(int amount, String currency) {
        System.out.println(bank);
        switch (currency) {
            case "usd" -> this.setPrincipal(this.getPrincipal() - amount);
            case "yen" -> this.setPrincipal((int) (this.getPrincipal() - amount / bank.getYenConversionRate()));
            case "euro" -> this.setPrincipal((int) (this.getPrincipal() - amount / bank.getEuroConversionRate()));
            default -> {
                System.out.println("Currency not supported.");
                return false;
            }
        }

        bank.createTransaction(this, "pay loan", amount, currency);
        return true;
    }

    @Override
    public String toString() {
        String out = "";
        out += this.accountID + ",";
        out += String.valueOf(originalValue) + ",";
        out += String.valueOf(principal) + ",";
        out += collateral.toString();
        out += "\n";
        return out;
    }
}
