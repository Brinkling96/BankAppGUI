public abstract class Stock {
    protected String name;
    protected Integer value;
    protected Integer shares;

    public Stock(String name, Integer value, Integer shares) {
        this.name = name;
        this.value = value;
        this.shares = shares;
    }

    public String getName() {
        return name;
    }

    public Integer getValue() {
        return value;
    }

    public Integer getShares() {
        return shares;
    }
}