public class Loan {
    
    private int originalValue;
    private int principal;
    private double interest;
    private Collateral collateral;


    public Loan(int originalValue, double interest, Collateral collateral){
        this.setOriginalValue(originalValue);
        this.setPrincipal(originalValue);
        this.setInterest(interest);
        this.setCollateral(collateral);
    }

    // Getters and setters
    public int getOriginalValue() {
        return originalValue;
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

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public Collateral getCollateral() {
        return collateral;
    }

    public void setCollateral(Collateral collateral) {
        this.collateral = collateral;
    }
}
