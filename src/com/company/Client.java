package com.company;
import java.util.ArrayList;
import java.util.List;

public class Client {
    private String firstName;
    private String lastName;
    private long dateCreated;
    private List<Object> accounts;
    private int age;
    private String accountNumber;

    public Client(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.dateCreated = System.currentTimeMillis();
        this.accounts = new ArrayList<Object>();
        this.accountNumber = generateAccountNumber(firstName, lastName);
    }

    // For now this generates enough of a unique sequence for us but
    // in case of creating more instances it would be important to store the
    // numbers and make sure that they are always unique, for example creating
    // the number based on the last one created like [0, 0, 0, 0] --> [0, 0, 0, 1]
    private String generateAccountNumber(String firstName, String lastName){
        // Generate 10 random two digit numbers
        List<Integer> numbers = new ArrayList<Integer>();
        for (int i = 0; i <= 10; i += 1){
            int random = (int)(Math.random() * 50 + 1);
            numbers.add(random);
        }
        String accountNumber = String.join("", (CharSequence) numbers);
        accountNumber = accountNumber + firstName.charAt(0) + lastName.charAt(0);
        return accountNumber;
    }
}
