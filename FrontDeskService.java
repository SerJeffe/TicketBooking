package org.administratum;

import java.lang.reflect.Array;
import java.util.*;

public class FrontDeskService {
    private final Scanner sc;
    public FrontDeskService(){
        sc = new Scanner(System.in);
    }

    public void frontDeskLogIn(ArrayList<User> users, ArrayList<Show> shows){
        User validUser;
        do{
            System.out.print("Enter Username:");
            String username = sc.nextLine();
            System.out.print("Enter Password:");
            String password = sc.nextLine();
            validUser = accountVerify(username,password,users);
            if(validUser==null)
                System.out.println("Incorrect username or password!\n");
        }while(validUser==null);
        System.out.println("--------------------------------------------");
        System.out.println("Welcome "+validUser.getUsername());
        mainMenu(shows);
    }

    public User accountVerify(String username, String password, ArrayList<User> users){
        User validUser = null;
        for(User user: users){
            if(user.getUsername().equals(username) && user.getPassword().equals(password)){
                validUser = user;
                break;
            }
        }
        return validUser;
    }

    public void mainMenu(ArrayList<Show> shows){
        String choice;
        Show showFound=null;
        do{
            System.out.println("1. View seats");
            System.out.println("2. Book seats");
            System.out.println("3. Check Booking");
            int option = Integer.parseInt(sc.nextLine());
            switch (option) {
                case 1 -> {
                    System.out.print("Enter show date:");
                    String date = sc.nextLine();
                    System.out.print("Enter show time:");
                    String time = sc.nextLine();
                    showFound = findShow(date, time, shows);
                    if (showFound == null) {
                        System.out.println("no show found for given date and time!");
                        break;
                    }
                    showFound.viewSeats();
                }
                case 2 -> {
                    if (showFound == null) {
                        System.out.println("Please select a show from 'View seats' option");
                        break;
                    }
                    System.out.println("Booking seats for " + showFound.getName() + "!");
                    System.out.println("1 Continue");
                    System.out.println("2 Go Back");
                    int back = Integer.parseInt(sc.nextLine());
                    if (back != 1)
                        break;
                    System.out.print("Enter Customer name:");
                    String customerName = sc.nextLine();
                    showFound.viewSeats();
                    System.out.print("Enter seat nos to book (enter seat number seperated by ','):");
                    String inputSeat = sc.nextLine();
                    String[] arraySeat = inputSeat.split(",");
                    double totalAmount = showFound.getPrice() * arraySeat.length;
                    Customer customer = new Customer(customerName, Arrays.asList(arraySeat), totalAmount);
                    System.out.println("Booking seats "+Arrays.toString(arraySeat)+", Total amount: "+totalAmount);
                    System.out.println("Continue transaction? (y/n)");
                    String transactionComplete = sc.nextLine();
                    if(!transactionComplete.equals("y"))
                        break;
                    boolean success =  updateShow(showFound,arraySeat,customer);
                    if(success) {
                        System.out.println("Thank you! " + customerName + ". You seats have been successfully booked!");
                        break;
                    }
                    System.out.println("oops! all or some of the following seats might already be booked. Please check and book empty seats! "+Arrays.toString(arraySeat));
                }
                case 3 -> {
                    System.out.print("Enter Customer name:");
                    String name = sc.nextLine();
                    enquireBooking(name,shows);
                }
                default -> {
                }
            }
            System.out.println("Do you wish to continue? (y/n)");
            choice = sc.nextLine();
        }while (choice.trim().equals("y"));
    }

    public Show findShow(String date, String time,ArrayList<Show> shows) {
        Show showFound = null;
        for(Show show: shows){
            if(show.getDate().equals(date) && show.getTime().equals(time)){
                showFound = show;
                break;
            }
        }
        return showFound;
    }

    public boolean updateShow(Show show,String[] seats,Customer customer){
        boolean success = false;
        String[][] seatsFound = show.getSeats();
        for(String seat: seats)
            switch (seat.substring(0,1)) {
                case "A" -> {
                    if (seatsFound[0][Integer.parseInt(seat.substring(1)) - 1].equals("x"))
                        return success;
                    seatsFound[0][Integer.parseInt(seat.substring(1)) - 1] = "x";
                }
                case "B" -> {
                    if (seatsFound[1][Integer.parseInt(seat.substring(1)) - 1].equals("x"))
                        return success;
                    seatsFound[1][Integer.parseInt(seat.substring(1)) - 1] = "x";
                }
                case "C" -> {
                    if (seatsFound[2][Integer.parseInt(seat.substring(1)) - 1].equals("x"))
                        return success;
                    seatsFound[2][Integer.parseInt(seat.substring(1)) - 1] = "x";
                }
            }
        success = true;
        ArrayList<Customer> updatedCustomers = show.getCustomers();
        updatedCustomers.add(customer);
        show.setCustomers(updatedCustomers);
        return success;
    }

    public void enquireBooking(String name, ArrayList<Show> shows){
        Show showFound = null;
        Customer customerFound = null;
        for(Show show: shows){
            for(Customer customer: show.getCustomers()){
                if(customer.getName().equals(name)){
                    customerFound = customer;
                    break;
                }
            }
            if(customerFound!=null){
                showFound = show;
                break;
            }
        }
        if(showFound==null){
            System.out.println("No booking found for this customer :(");
            return;
        }
        System.out.println(customerFound.getName()+" has booked seats "+customerFound.getBookedSeats()+" for the show "+showFound.getName()+"!");
    }
}
