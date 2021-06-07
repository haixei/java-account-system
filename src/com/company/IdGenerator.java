package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IdGenerator {
    private final List<String> clientIds = new ArrayList<>();
    private final List<String> standardAccountIds = new ArrayList<>();
    private final List<String> savingsAccountIds = new ArrayList<>();

    public String generateClientId(String firstName, String lastName) throws Exception {
        String lastCreated = this.clientIds.get(this.clientIds.size() - 1);
        return this.generateNumberString(lastCreated) + firstName.charAt(0) + lastName.charAt(0);
    }

    public String generateAccountId(String type) throws Exception {
        // No need to check the type because it comes in as a predefined value so it's always one of the two possible ones
        // Get the last number created
        String lastCreated;
        if(type.equals("ST")){
            lastCreated = this.standardAccountIds.get(this.standardAccountIds.size() - 1);
        }else{
            lastCreated = this.savingsAccountIds.get(this.savingsAccountIds.size() - 1);
        }

        // If there is no prev Id, start from nothing
        if(lastCreated == null){
            lastCreated = "0000000000";
        }

        // Return a new account number (String of 10 unique numbers + account type [ST/SA])
        return this.generateNumberString(lastCreated) + type;
    }

    private String generateNumberString(String lastNumber) throws Exception {
        // Transform the string to a number
        int lastNumberInt = 0;
        try{
            lastNumberInt = Integer.parseInt(lastNumber);
        }catch(NumberFormatException e){
            System.out.println("Last number string can't be parsed to a number.");
        }
        // Add one
        lastNumberInt += 1;
        // Add zeros in front if the number is too small

        lastNumber = String.valueOf(lastNumberInt);
        int lastNumberLen = lastNumber.length();
        if(lastNumberLen != 10){
            String zeros = String.join("", Collections.nCopies(10 - lastNumberLen, "0"));
            lastNumber = zeros + lastNumber;
        }

        // If the list is full, throw an error
        if(lastNumberInt > 999999999){
            throw new Exception("Number exceeded the maximum value.");
        }

        return lastNumber;
    }
}
