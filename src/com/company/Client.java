package com.company;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Client {
    private String firstName;
    private String lastName;
    private long dateCreated;
    private List<Object> accounts;
    private String dateOfBirth;
    private String accountNumber;

    public Client(String firstName, String lastName, String dateOfBirth){
        // Pass the information and create the instance
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.dateCreated = System.currentTimeMillis();
        this.accounts = new ArrayList<>();
        this.accountNumber = generateAccountNumber(firstName, lastName);
    }

    // For now this generates enough of a unique sequence for us but
    // in case of creating more instances it would be important to store the
    // numbers and make sure that they are always unique, for example creating
    // the number based on the last one created like [0, 0, 0, 0] --> [0, 0, 0, 1]
    private String generateAccountNumber(String firstName, String lastName){
        // Generate 10 random two digit numbers
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i <= 10; i += 1){
            int random = (int)(Math.random() * 50 + 1);
            numbers.add(random);
        }
        String accountNumber = numbers.toString();
        accountNumber = accountNumber + firstName.charAt(0) + lastName.charAt(0);
        return accountNumber;
    }

    // Let the client change its name
    public void changeLastName(String newName){
        this.lastName = newName;
    }

    public void changeFirstName(String newName){
        this.firstName = newName;
    }

    // Allow for adding new accounts
    public void addNewAccount(Object newAccount){
        this.accounts.add(newAccount);
    }

    // Give client's summary
    public String clientSummary(){
        return String.format("First name: %s\nLast name: %s\nDate of birth: %s", this.firstName, this.lastName,
                             this.dateOfBirth);
    }
}
