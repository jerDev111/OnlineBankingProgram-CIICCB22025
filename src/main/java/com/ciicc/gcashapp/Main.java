package com.ciicc.gcashapp;
import java.util.Scanner;



public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //class objects
        UserAuthentication r1 = new UserAuthentication();
        UserAuthentication r2 = new UserAuthentication();
        CheckBalance r3 = new CheckBalance();
        CashIn r4 = new CashIn();
        CashTransfer r5 = new CashTransfer();
        Transactions r6 = new Transactions();


        boolean on = true; // loop first
        while (on) {
            System.out.println("\n==== Welcome To GcashApp ====");
            System.out.println("are you new[1]?, or have existing account[2].?\n");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit\n");
            System.out.print("input [1][2][3]:");

            int choice1 = scanner.nextInt();
            scanner.nextLine();

            switch (choice1) {
                case 1:
                    System.out.println("\n====REGISTER ACCOUNT==== ");

                    System.out.print("Enter your name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter your email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter your phone number: ");
                     String number = scanner.nextLine();
                    System.out.print("Enter your PIN: ");
                    int pin = scanner.nextInt();

                    r1.Registration(name, email, number, pin);//insert each to method
                    break;

                case 2:
                    System.out.println("\n====LOGIN ACCOUNT====");

                    System.out.print("Enter your name: ");
                    String loginName = scanner.nextLine();
                    System.out.print("Enter your PIN: ");

                    int loginPIN = scanner.nextInt();
                    boolean isAccess = r2.Login( loginName, loginPIN);




                  boolean on2 = true;// loop 2nd
                  while (on2) {
                    if (isAccess) {
                        System.out.println("\n==== Welcome user, " + loginName+" ====");
                        System.out.println("1. Check Balance");
                        System.out.println("2. CashIn");
                        System.out.println("3. Transfer Money");
                        System.out.println("4. View all transaction");
                        System.out.println("5. Change PIN");
                        System.out.println("6. Logout\n");
                        System.out.print("please select :");

                        int choice2 = scanner.nextInt();//new switch statement
                        scanner.nextLine();

                        switch (choice2) {
                            case 1:
                                System.out.print("Enter your User ID to check balance: ");
                                int ID = scanner.nextInt(); // Get user ID input

                                double Balance = r3.checkBalance(ID);// Call checkBalance method
                                System.out.println("\nYour (User ID: " + ID + ") current balance is: " +Balance);
                                break;

                            case 2:
                                System.out.print("Enter your User ID: ");
                                int userID = scanner.nextInt();

                                System.out.print("Enter amount to cash in: ");
                                double amount = scanner.nextDouble(); // Get cash-in amount

                                r4.cashIn(userID, amount);
                                break;

                            case 3:
                                System.out.print("Enter your User ID: ");
                                int senderID = scanner.nextInt(); // Get senders ID

                                System.out.print("Enter recipient's User ID: ");
                                int receiverID = scanner.nextInt(); // Get receivers ID

                                System.out.print("Enter amount to transfer: ");
                                double cash = scanner.nextDouble(); // Get transfer amount

                                r5.cashTransfer(senderID, receiverID, cash); // Call cashTransfer method
                                break;

                            case 4:
                                System.out.print("Enter your User ID to view transactions: ");
                                int transactionID = scanner.nextInt();
                                r6.viewUserAll(transactionID); // Call viewUserAll method
                                break;

                            case 5:
                                System.out.print("\nEnter your name: ");
                                String usedName = scanner.nextLine();
                                System.out.print("Enter your current PIN: ");
                                int currentPIN = scanner.nextInt();
                                System.out.print("Enter your new PIN: ");
                                int newPIN = scanner.nextInt();
                                scanner.nextLine();

                                r2.changePIN(usedName, currentPIN, newPIN); // Change PIN
                                break;

                            case 6:
                                on2 = false;
                                System.out.println("logging out..");
                                break;
                        }

                    } else {
                        System.out.println("Invalid login- Please try again..");
                        break;
                    }
                }
                  break;




                case 3:
                    on = false;
                    System.out.println("Exiting Gcash..");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }


        scanner.close();
    }
}



