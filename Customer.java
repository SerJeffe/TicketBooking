package org.administratum;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private List<String> bookedSeats;
    private double totalAmount;

    public Customer(String name, List<String> bookedSeats, double totalAmount) {
        this.name = name;
        this.bookedSeats = bookedSeats;
        this.totalAmount = totalAmount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getBookedSeats() {
        return bookedSeats;
    }

    public void setBookedSeats(ArrayList<String> bookedSeats) {
        this.bookedSeats = bookedSeats;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", bookedSeats=" + bookedSeats +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
