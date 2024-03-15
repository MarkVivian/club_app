package org.example.frontend;

public class Clubs {

    public static String name;
    public static String message;
    public static String imagePath;

    public Clubs() {
        this.name = "";
        this.message = "";
        this.imagePath = "";
    }

    public Clubs (String name , String message, String imagePath ) {

        this.name = name;
        this.message = message;
        this.imagePath = imagePath;

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

    public static String getImagePath() {
        return imagePath;
    }

    public static void setImagePath(String imagePath) {
        Clubs.imagePath = imagePath;
    }


}
