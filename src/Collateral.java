public class Collateral {

    private String name;
    private String description;
    private int value;

    public Collateral(String name, String description, int value){
        this.setName(name);
        this.setDescription(description);
        this.setValue(value);
    }



    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }



}
