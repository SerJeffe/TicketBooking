package org.administratum;

import java.util.ArrayList;

public class FrontDesk {
    static ArrayList<User> users;
    static ArrayList<Show> shows;
    static FrontDeskService frontDeskService;
    static {
        frontDeskService = new FrontDeskService();
        users = new ArrayList<>();
        //initializing user lists
        String[] username = {"Abesh", "Sumit", "Ishan"};
        String[] password = {"A", "S", "I"};
        for (int i = 0; i < username.length; i++) {
            users.add(new User(username[i], password[i]));
        }
        shows = new ArrayList<>();
        //initializing show lists
        String[] date = {"22/05/2023", "23/05/2023", "24/05/2023"};
        String[] time = {"11:00", "18:00", "20:00"};
        String[] name = {"Guardians of the Galaxy Vol 2", "Suzume", "Abesh the hawsi"};
        double[] price = {250.50,300,1450};
        for (int i = 0; i < name.length; i++) {
            shows.add(new Show(name[i], date[i], time[i], price[i]));
        }
    }

    public static void main(String[] args){
        FrontDeskService frontDeskService = new FrontDeskService();
        frontDeskService.frontDeskLogIn(users,shows);
    }
}
