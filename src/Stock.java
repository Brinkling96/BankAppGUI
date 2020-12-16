//Contains the necessary attributes for any stock in and stock market
public abstract class Stock {
    protected String name;
    protected Integer value;
    protected Integer shares;
    protected String type;

    public Stock(String name, Integer value, Integer shares) {
        this.name = name;
        this.value = value;
        this.shares = shares;
        this.type = "";
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

    public void setValue(Integer value) {
        this.value = value;
    }

    public void setShares(Integer shares) {
        this.shares = shares;
    }

    public String toString() {
        String out = "";
        out += name + ",";
        out += String.valueOf(value) + ",";
        out += String.valueOf(shares) + ",";
        out += type + ",";
        return out;
    }
}
