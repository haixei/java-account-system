package com.company;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Validation {
    public Boolean validateDateOfBirth(String dateOfBirth){
        // First check the length, this would catch the most errors up front
        // We check if the string is long enough and formatted with three "/" characters
        long countOccurrences = dateOfBirth.chars().filter(ch -> ch =='/').count();
        if (dateOfBirth.length() != 10 && countOccurrences != 3){
            System.out.println("Inappropriate birth date format, please pass it the following way: DD/MM/YYYY");
            return false;
        }

        // Check if the date doesn't contain any character that is not a number
        try {
            // The year must be 1940 at max and 2000 at least
            int year = Integer.parseInt(dateOfBirth.substring(6, 10));
            if(year < 1940 || year > 2000){
                System.out.println("Please parse a date between years 1940 and 2000.");
            }
            // Validate that the date existed
            DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);
            try{
                sdf.parse(dateOfBirth);
                return true;
            }catch(ParseException e){
                System.out.println("This date does not exist.");
                return false;
            }
        } catch(NumberFormatException e){
            System.out.println("Your date contains characters that are not numbers, please correct it.");
            return false;
        }
    }

    public Boolean validateName(String firstName, String lastName){
        if(firstName.length() < 3 && lastName.length() < 3){
            System.out.println("Both first and last name should have at least 2 characters.");
            return false;
        }
        return true;
    }
}
