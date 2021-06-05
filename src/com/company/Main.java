package com.company;
// This is a basic implementation of a bank account system
// One can create a client and an account for that client, saving or a standard one
public class Main {

    public static void main(String[] args) {
	    Account newAccount = createAccount();
	    float bal = newAccount.changeBalance(10);
	    System.out.println(bal);
        System.out.println(newAccount.getBalance());
    }

    public static Account createAccount(){
        return new StandardAccount();
    }
}
