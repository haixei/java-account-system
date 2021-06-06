package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class bankSystem {
    // Create a storage place for client data
    private List<Client> clientList = new ArrayList<>();
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
                case 3 -> this.getClient();
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

    }

    public void getClient(){
        System.out.println("Provide client's account number: ");
        String accountNumber = scanner.nextLine();
        // Get client by their account id
        System.out.println(Arrays.toString(this.clientList.toArray()));
        Client foundClient = this.clientList.stream()
                .filter(client -> accountNumber.equals(client.getAccountNumber()))
                .findFirst()
                .orElse(null);

        if (foundClient == null){
            System.out.println("Sorry, it seems like this account does not exist.");
        }else{
            System.out.println(foundClient.clientSummary());
        }
    }
}
