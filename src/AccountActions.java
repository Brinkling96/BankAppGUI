/******************************************************************************
 * Class: AccountActions.java
 * Author: Sarah Shahinpour, Sean Brady, Shuaike Zhou, 
 *******************************************************************************/

//defines all necessary functions of an account
public interface AccountActions {
    public boolean deposit(int amount, String currency);
    public boolean withdraw(int amount, String currency);
}
