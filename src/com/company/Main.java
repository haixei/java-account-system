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

        // When everything is alright, we create a new client
        Client newClient = new Client("Kate", "Smith", "02/03/1980");
        System.out.println(newClient.clientSummary());

        // Test a bank system
        bankSystem bankSystem = new bankSystem();
        bankSystem.chooseAction();
    }

    public static Account createAccount(){
        return new StandardAccount();
    }
}
