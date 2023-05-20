package org.administratum;

import java.util.ArrayList;
import java.util.Arrays;

public class Show {
    private String name;
    private String date;
    private String time;
    private String[][] seats;
    private ArrayList<Customer> customers;
    private double price;
    private final String[] seatNo = {"A","B","C"};
    public Show(){
    }

    public Show(String name, String date, String time, double price) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.price = price;
        this.seats = new String[][]{{"o","o","o"},{"o","o","o"},{"o","o","o"}};
        customers = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(ArrayList<Customer> customers) {
        this.customers = customers;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String[][] getSeats() {
        return seats;
    }

    public void setSeats(String[][] seats) {
        this.seats = seats;
    }

    public void viewSeats(){
        for(int i=0;i<seats.length;i++){
            for(int j=0;j<seats[0].length;j++){
                System.out.print(seatNo[i]+(j+1)+"["+seats[i][j]+"] ");
            }
            System.out.println();
        }
    }

    @Override
    public String toString() {
        return "Show{" +
                "name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", price=" + price +
                '}';
    }
}
