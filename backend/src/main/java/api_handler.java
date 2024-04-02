import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject; // Import the JSON library

public class api_handler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();
        if (path.equals("/getMssg")) {
            getMssg(exchange);
        } else if (path.equals("/storeMssg")) {
            storeMssg(exchange);
        } else {
            // Handle other endpoints or return an error
            String response = "Invalid endpoint";
            sendResponse(exchange, response);
        }
    }

    private void sendResponse(HttpExchange exchange, String response) throws IOException {
        exchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    public void getMssg(HttpExchange exchange) throws IOException {
        if ("POST".equals(exchange.getRequestMethod())) {
            // Read the request body (if present)
            InputStream is = exchange.getRequestBody();

            String requestBody = new String(is.readAllBytes(), StandardCharsets.UTF_8);

            database_connection db = new database_connection();

            // Parse the JSON data (if valid)
            try {
                JSONObject jsonBody = new JSONObject(requestBody);
                String club_ID = jsonBody.getString("club_ID");// Example: Extract the "message" field

                db.readFromDatabase(
                        "message_info",
                        new String[]{"club_ID", "message_text"},
                        Integer.parseInt(club_ID),
                        "specific"

                );
                // Access the data map using the getter method.
                Map<Integer, Map<String, Object>> dataMap = db.getDataMap();

                // Convert dataMap to JSON
                ObjectMapper objectMapper = new ObjectMapper();
                String json = objectMapper.writeValueAsString(dataMap);

                // Now 'json' contains the dataMap in JSON format
                System.out.println("JSON representation of dataMap:\n" + json);

                // Send the JSON response to the client (customize this part)
                sendResponse(exchange, json);

                System.out.println("We have received a GET connection from a client");

            } catch (Exception e) {
                // Invalid JSON format
                String errorResponse = "Error: Invalid JSON format in request body";
                sendResponse(exchange, errorResponse);
            }
        } else {
            // Method not allowed
            exchange.sendResponseHeaders(405, -1); // 405 Method Not Allowed
        }
    }

    public void getChats(HttpExchange exchange) throws IOException {
        if ("GET".equals(exchange.getRequestMethod())) {

            try{
                database_connection db = new database_connection();
                db.readFromDatabase(
                        "club_info",
                        new String[]{"club_ID", "club_name","club_description"},
                        1, // not important since its in normal mode
                        "normal"
                );

                // Access the data map using the getter method.
                Map<Integer, Map<String, Object>> dataMap = db.getDataMap();

                // Convert dataMap to JSON
                ObjectMapper objectMapper = new ObjectMapper();
                String json = objectMapper.writeValueAsString(dataMap);

                // Now 'json' contains the dataMap in JSON format
                System.out.println("JSON representation of dataMap:\n" + json);

                // Send the JSON response to the client (customize this part)
                sendResponse(exchange, json);

                System.out.println("We have received a GET connection from a client");

            }catch(Exception e){
                e.printStackTrace();
            }

        } else {
            // Method not allowed
            exchange.sendResponseHeaders(405, -1); // 405 Method Not Allowed
        }
    }

    public void storeMssg(HttpExchange exchange) throws IOException {
        if ("POST".equals(exchange.getRequestMethod())) {
            InputStream is = exchange.getRequestBody();
            System.out.println("we have received a post connection from a client");
            String requestBody = new String(is.readAllBytes(), StandardCharsets.UTF_8);

            database_connection db = new database_connection();
            // Parse the JSON data
            try {


                JSONObject jsonBody = new JSONObject(requestBody);

                HashMap<String, Object> extractedData = new HashMap<>();
                extractedData.put("student_ID", jsonBody.getString("student_ID"));
                extractedData.put("message_text", jsonBody.getString("message_text"));
                extractedData.put("club_ID", jsonBody.getString("club_ID"));
                extractedData.put("timestamp", jsonBody.getString("timestamp"));

                db.writeToDatabase(
                        "message_info",
                        "add",
                        "doesn't_matter",
                        1,
                        extractedData
                        );

                // Process the extracted data as needed
                String response = "stored post request with the message: " + extractedData.get("message_text");
                sendResponse(exchange, response);
            } catch (Exception e) {
                // Invalid JSON format
                String errorResponse = "Error: Invalid JSON format in request body";
                sendResponse(exchange, errorResponse);
            }

        } else {
            // Method not allowed
            exchange.sendResponseHeaders(405, -1); // 405 Method Not Allowed
        }
    }

    public void addUsers(HttpExchange exchange) throws IOException{
        if ("POST".equals(exchange.getRequestMethod())) {
            InputStream is = exchange.getRequestBody();
            System.out.println("we have received a post connection from a client");
            String requestBody = new String(is.readAllBytes(), StandardCharsets.UTF_8);

            database_connection db = new database_connection();
            // Parse the JSON data
            try {


                JSONObject jsonBody = new JSONObject(requestBody);

                HashMap<String, Object> extractedData = new HashMap<>();
                extractedData.put("student_name", jsonBody.getString("student_name"));
                extractedData.put("student_password", jsonBody.getString("student_password"));

                db.writeToDatabase(
                        "student_info",
                        "add",
                        "doesn't_matter",
                        1,
                        extractedData
                );

                // Process the extracted data as needed
                String response = "stored post request with the message: " + extractedData.get("student_name");
                sendResponse(exchange, response);
            } catch (Exception e) {
                // Invalid JSON format
                String errorResponse = "Error: Invalid JSON format in request body";
                sendResponse(exchange, errorResponse);
            }

        } else {
            // Method not allowed
            exchange.sendResponseHeaders(405, -1); // 405 Method Not Allowed
        }
    }

    public void getUsers(HttpExchange httpExchange) throws IOException{
        if ("GET".equals(httpExchange.getRequestMethod())) {

            try{
                database_connection db = new database_connection();
                db.readFromDatabase(
                        "student_info",
                        new String[]{"student_ID", "student_name","student_password"},
                        1, // not important since its in normal mode
                        "normal"
                );

                // Access the data map using the getter method.
                Map<Integer, Map<String, Object>> dataMap = db.getDataMap();

                // Convert dataMap to JSON
                ObjectMapper objectMapper = new ObjectMapper();
                String json = objectMapper.writeValueAsString(dataMap);

                // Now 'json' contains the dataMap in JSON format
                System.out.println("JSON representation of dataMap:\n" + json);

                // Send the JSON response to the client (customize this part)
                sendResponse(httpExchange, json);

                System.out.println("We have received a GET connection from a client");

            }catch(Exception e){
                e.printStackTrace();
            }

        } else {
            // Method not allowed
            httpExchange.sendResponseHeaders(405, -1); // 405 Method Not Allowed
        }
    }
}
