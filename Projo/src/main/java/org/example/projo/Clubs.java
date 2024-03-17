package org.example.projo;

public class Clubs {

    public static String name;
    public static String message;

    public Clubs(){
        this.name = "";
        this.message = "";
    }
    public Clubs(String name, String message){
        this.name = name;
        this.message = message;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        Clubs.name = name;
    }

    public static String getMessage() {
        return message;
    }

    public static void setMessage(String message) {
        Clubs.message = message;
    }
}
