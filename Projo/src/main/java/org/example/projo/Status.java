package org.example.projo;

public class Status {

    public static String name;
    public static  String time;

    public Status(){
        this.name = "";
        this.time = "";
    }

    public Status(String name, String time){
        this.name = name;
        this.time = time;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        Status.name = name;
    }

    public static String getTime() {
        return time;
    }

    public static void setTime(String time) {
        Status.time = time;
    }
}
