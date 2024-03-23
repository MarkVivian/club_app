package org.example.projo;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject; // Import the JSON library

public class handleApi {
        JSONObject jsonResponse;
        public void getClubData(){
            try {
                String apiUrl = "http://localhost:8080/getChats";
                URL url = new URL(apiUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // Parse the JSON response
                jsonResponse = new JSONObject(response.toString());

                // Iterate over each key (club index) in the JSON object
                for (String key : jsonResponse.keySet()) {
                    JSONObject clubInfo = jsonResponse.getJSONObject(key);
                    String clubName = clubInfo.getString("club_name");
                    String clubDescription = clubInfo.getString("club_description");

                    System.out.println("Club Name: " + clubName);
                    System.out.println("Club Description: " + clubDescription);
                    System.out.println(); // Add an empty line for better readability
                }

                connection.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void storeMssg(String message){
            try {
                String apiUrl = "http://localhost:8080/storeMssg";
                URL url = new URL(apiUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // Parse the JSON response
                jsonResponse = new JSONObject(response.toString());

                // Iterate over each key (club index) in the JSON object
                for (String key : jsonResponse.keySet()) {
                    JSONObject clubInfo = jsonResponse.getJSONObject(key);
                    String clubName = clubInfo.getString("club_name");
                    String clubDescription = clubInfo.getString("club_description");

                    System.out.println("Club Name: " + clubName);
                    System.out.println("Club Description: " + clubDescription);
                    System.out.println(); // Add an empty line for better readability
                }

                connection.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
}
