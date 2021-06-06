package com.company;
import java.util.Scanner;

// This is a basic implementation of a bank account system
// One can create a client and an account for that client, saving or a standard one
public class Main {

    public static void main(String[] args){
        // Test creating a new account
	    Account newAccount = createAccount();
	    float bal = newAccount.changeBalance(10);
	    System.out.println(bal);

        // Test a bank system
        bankSystem bankSystem = new bankSystem();
        bankSystem.chooseAction();
    }

    public static Account createAccount(){
        return new StandardAccount();
    }
}
