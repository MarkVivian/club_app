package org.example.projo;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Clubs {

    private String namee;
    private String message;

    private String logo;

    public Clubs(){
        this.namee = "";
        this.message = "";
        this.logo = "";
    }




    public Clubs(String name, String message, String logo){
        this.namee = name;
        this.message = message;
        this.logo = logo;
    }

    public  String getName() {
        return namee;
    }



    public  String getMessage() {
        return message;
    }


    public String getLogo() {
        return logo;
    }

    public static Clubs[] manyClubs (){
        handleApi ha = new handleApi();
        ha.getClubData();
        JSONObject jsonResponse = ha.jsonResponse;
        List<Clubs> clubsList = new ArrayList<>();
        String[] images = {
                "file:images_used/devdev.jpeg",
                "file:images_used/bomb.jpg",
                "file:images_used/mbisha.jpg",
                "file:images_used/mboto.jpg",
                "file:images_used/user_icon.jpg"
        };

        // Loop through each key in the jsonResponse
        for (int i = 0; i < jsonResponse.length(); i++) {
            JSONObject clubJson = jsonResponse.getJSONObject(String.valueOf(i));
            String clubName = clubJson.getString("club_name");
            String clubDescription = clubJson.getString("club_description");

            // Use the image from the images array, or a default if there aren't enough images
            String image = images.length > i ? images[i] : "file:images_used/bomb.jpg";

            // Create a new Clubs object with the name and description
            clubsList.add(new Clubs(clubName, clubDescription, image));
        }

        // Convert list to array
        return clubsList.toArray(new Clubs[0]);
    }
}
