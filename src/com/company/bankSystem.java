package com.company;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class bankSystem {
    // Create a storage place for client data
    private final List<Client> clientList = new ArrayList<>();
    private final List<StandardAccount> standardAccounts = new ArrayList<>();
    private final List<SavingsAccount> savingsAccounts = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    public void chooseAction() throws Exception {
        System.out.println("Welcome to the bank. Which action you want to perform?\n1. Create client\n2. Create account\n3. Get client info");
        String actionStr = scanner.nextLine();

        // Check if the input is a number
        try{
            int action = Integer.parseInt(actionStr);
            switch (action) {
                case 1 -> this.createClient();
                case 2 -> this.createAccount();
                case 3 -> this.showClientSummary();
            }
        }catch(NumberFormatException e){
            System.out.println("Please input a number.");
            this.chooseAction();
        }
    }

    public void createClient() throws Exception {
        System.out.println("Provide client's first name: ");
        String firstName = scanner.nextLine();
        System.out.println("Provide client's last name: ");
        String lastName = scanner.nextLine();
        System.out.println("Provide client's date of birth (DD/MM/YYYY): ");
        String dateOfBirth = scanner.nextLine();

        // Validate the information
        Validator validator = new Validator();
        Boolean validatedName = validator.validateName(firstName, lastName);
        Boolean validatedYear = validator.validateDateOfBirth(dateOfBirth);

        if(validatedName && validatedYear){
            Client newClient = new Client(firstName, lastName, dateOfBirth);
            this.clientList.add(newClient);
            String res = String.format("A new client with the name of %s got created.", firstName + lastName);
            System.out.println(res);
        }else{
            System.out.println("Please input correct information with given hints.");
            this.createClient();
        }

    }

    public void createAccount() throws Exception {
        // Pick new account information
        System.out.println("Select account type:\n1. Standard\n2. Savings");
        String accountOption = scanner.nextLine();
        if(!(accountOption.equals("1") || accountOption.equals("2")) && accountOption.length() != 1){
            System.out.println("You provided a wrong input (should be either 1 or 2), try again.");
            this.createAccount();
        }

        // Pick the client
        System.out.println("Provide client's id: ");
        String clientId = scanner.nextLine();

        // Find the client and check if it exists
        Client foundClient = (Client) this.getClient(clientId);
        if (foundClient == null){
            System.out.println("Sorry, it seems like this client does not exist.");
            this.createAccount();
        }

        // Create a new account and add it to the client object
        if(accountOption.equals("1")){
            StandardAccount newAccount = new StandardAccount(clientId);
            this.standardAccounts.add(newAccount);
        }else{
            SavingsAccount newAccount = new SavingsAccount(clientId);
            this.savingsAccounts.add(newAccount);
        }
    }

    public void showClientSummary(){
        System.out.println("Provide client's account number: ");
        String accountNumber = scanner.nextLine();
        // Get client by their account id
        System.out.println(Arrays.toString(this.clientList.toArray()));
        Client foundClient = (Client) this.getClient(accountNumber);

        if (foundClient == null){
            System.out.println("Sorry, it seems like this account does not exist.");
        }else{
            System.out.println(foundClient.clientSummary());
        }
    }

    // Transaction methods
    public void sendMoney(){
        // Get both account numbers
        System.out.println("Provide sender's account number: ");
        String accountNumberSender = scanner.nextLine();
        System.out.println("Provide receiver's account number: ");
        String accountNumberReceiver = scanner.nextLine();

        StandardAccount foundSender = (StandardAccount) this.getStandardAccount(accountNumberSender);
        StandardAccount foundReceiver = (StandardAccount) this.getStandardAccount(accountNumberReceiver);

        if(foundSender == null) {
            System.out.println("Sorry, it seems like the sender account does not exist.");
            this.sendMoney();
        }else if(foundReceiver == null){
            System.out.println("Sorry, it seems like the receiver account does not exist.");
            this.sendMoney();
        }

        // Ask for amount of money to transfer
        System.out.println("How much money do you want to transfer?");
        String amountStr = scanner.nextLine();
        // Check if the input is valid (just numbers)
        try{
            int amountInt = Integer.parseInt(amountStr);
            if(amountInt < 0){
                System.out.println("The amount of money must be positive.");
                this.sendMoney();
            }

            // Transform to a big decimal (the appropriate type for handling a currency)
            BigDecimal amount = new BigDecimal(amountStr);

            // Get the money from the sender
            foundSender.changeBalance(amount.negate());
            foundReceiver.changeBalance(amount);
            System.out.println("The money got successfuly transfered.");

        }catch(NumberFormatException e){
            System.out.println("The amount you provided consists of characters that are not numbers.");
            this.sendMoney();
        }
    }

    // Methods for looking up information in the collection
    public Object getClient(String id){
        return this.clientList.stream()
                .filter(client -> id.equals(client.getClientId()))
                .findFirst()
                .orElse(null);
    }

    public Object getStandardAccount(String accountNumber){
        return this.standardAccounts.stream()
                .filter(account -> accountNumber.equals(account.getAccountNumber()))
                .findFirst()
                .orElse(null);
    }
}
