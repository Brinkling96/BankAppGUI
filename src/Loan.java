public class Loan{

    public static double INTEREST_RATE = 0.1;
    public static double COLLATERAL_ORIGINIAL_VALUE = 0.1;
    private int originalValue;
    private int principal;
    // interest is in decimal form, should be < 1
    private double interest;
    private Collateral collateral;


    public Loan(int originalValue, double interest, Collateral collateral){
        this.setOriginalValue(originalValue);
        this.setPrincipal(originalValue);
        this.setInterest(interest);
        this.setCollateral(collateral);
    }

    public void chargeInterest(int days){
        int interestGained = (int)(this.getPrincipal()*Math.pow((1+(this.getInterest())), days));
        this.setPrincipal(this.getPrincipal() + interestGained);
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
