//Contains the necessary attributes for a stock on the NYSE
public class NYSE_Stock extends Stock{
    public NYSE_Stock(String name, Integer value, Integer shares) {
        super(name, value, shares);
        this.type = "nyse";
    }

    public NYSE_Stock(String name, String value, String shares) {
        super(name, Integer.parseInt(value), Integer.parseInt(shares));
        this.type = "nyse";
    }
}
