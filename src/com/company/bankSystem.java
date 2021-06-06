package com.company;

import java.util.Scanner;

public class bankSystem {
    public void chooseAction(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the bank. Which action you want to perform?\n1. Create client\n2. Create account\n3. Get client info");
        String actionStr = scanner.nextLine();

        // Check if the input is a number
        try{
            int action = Integer.parseInt(actionStr);
            switch (action){
                case 1:
                    this.createClient();
                    break;
                case 2:
                    this.createAccount();
                    break;
                case 3:
                    this.getClient();
                    break;
            }
        }catch(NumberFormatException e){
            System.out.println("Please input a number.");
            this.chooseAction();
        }
    }
    public void createClient(){
        System.out.println("Create new client.");
    }

    public void createAccount(){

    }

    public void getClient(){

    }
}
