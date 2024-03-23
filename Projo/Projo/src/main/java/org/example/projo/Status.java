package org.example.projo;

public class Status {

    private  String name;
    private  String time;

    private String image;

    public Status(){
        this.name = "";
        this.time = "";
        this.image = "";
    }


    public  String getImage() {
        return image;
    }



    public Status(String name, String time, String image){
        this.name = name;
        this.time = time;
        this.image = image;
    }

    public String getName() {
        return name;
    }



    public  String getTime() {
        return time;
    }



    public static Status[] manyStatus() {
        return new Status[]{
                new Status("Science and Technology", "9:10 am", "file:images_used/user_icon.jpg"),
                new Status("Chuka Baddies", "9:10 am", "file:images_used/current_logo.jpg"),
                new Status("Programmers", "9:10 am", "file:images_used/devdev.jpeg")
        };
    }


}
