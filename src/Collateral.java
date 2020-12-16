/******************************************************************************
 * Class: Collateral.java
 * Author: Sarah Shahinpour, Sean Brady, Shuaike Zhou, 
 *******************************************************************************/

//An item of a particular value needed when someone wants to take out a loan
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

    public String toString() {
        String out = "";
        out += name + ",";
        out += description + ",";
        out += String.valueOf(value) + ",";
        return out;
    }

}
