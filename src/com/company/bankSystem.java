package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class bankSystem {
    // Create a storage place for client data
    private final List<Client> clientList = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    public void chooseAction(){
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

    public void createClient(){
        System.out.println("Provide client's first name: ");
        String firstName = scanner.nextLine();
        System.out.println("Provide client's last name: ");
        String lastName = scanner.nextLine();
        System.out.println("Provide client's date of birth (DD/MM/YYYY): ");
        String dateOfBirth = scanner.nextLine();

        // Validate the information
        Validation validator = new Validation();
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

    public void createAccount(){
        // Pick new account information
        System.out.println("Select account type:\n1. Standard\n2. Savings");
        String accountOption = scanner.nextLine();
        if(!(accountOption.equals("1") || accountOption.equals("2")) && accountOption.length() != 1){
            System.out.println("You provided a wrong input (should be either 1 or 2), try again.");
            this.createAccount();
        }

        // Pick the client
        System.out.println("Provide client's account number: ");
        String accountNumber = scanner.nextLine();

        // Find the client and check if it exists
        Client foundClient = (Client) this.getClient(accountNumber);
        if (foundClient == null){
            System.out.println("Sorry, it seems like this account does not exist.");
            this.createAccount();
        }else{
            // Create a new account and add it to the client object
            if(accountOption.equals("1")){
                StandardAccount newAccount = new StandardAccount();
                foundClient.addNewAccount(newAccount);
            }else{
                SavingsAccount newAccount = new SavingsAccount();
                foundClient.addNewAccount(newAccount);
            }
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

    // Methods for looking up information in the collection
    public Object getClient(String accountNumber){
        return this.clientList.stream()
                .filter(client -> accountNumber.equals(client.getAccountNumber()))
                .findFirst()
                .orElse(null);
    }
}
