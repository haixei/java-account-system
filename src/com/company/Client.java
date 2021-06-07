package com.company;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Client {
    private String firstName;
    private String lastName;
    private long dateCreated;
    private String dateOfBirth;
    private String Id;

    public Client(String firstName, String lastName, String dateOfBirth) throws Exception {
        // Pass the information and create the instance
        IdGenerator generator = new IdGenerator();
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.dateCreated = System.currentTimeMillis();
        this.Id = generator.generateClientId(firstName, lastName);
    }

    // Let the client change its name
    public void changeLastName(String newName){
        this.lastName = newName;
    }

    public void changeFirstName(String newName){
        this.firstName = newName;
    }

    // Give client's summary
    public String clientSummary(){
        return String.format("First name: %s\nLast name: %s\nDate of birth: %s", this.firstName, this.lastName,
                             this.dateOfBirth);
    }

    public String getClientId(){
        return this.Id;
    }
}
