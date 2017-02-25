package io.github.sumukhshiv.mdbsocials;

import java.util.ArrayList;

/**
 * Created by sumukhshivakumar on 2/24/17.
 */

public class Social {
    String nameOfEvent;
    String date;
    String description;
    String image;
    String emailOfHost;
//    String timeStamp;
    int numberIntersted;
    ArrayList<String> peopleInterested;

    public Social(String nameOfEvent, String date, String description, String image, String emailOfHost, int numberIntersted, ArrayList<String> peopleInterested) {
        this.nameOfEvent = nameOfEvent;
        this.date = date;
        this.description = description;
        this.image = image;
        this.emailOfHost = emailOfHost;
//        this.timeStamp = timeStamp;
        this.numberIntersted = numberIntersted;
        this.peopleInterested = peopleInterested;
    }
}
