import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;

public class Main {
    public static void main(String[] args) {
        try{
            // Create an HTTP server that listens on port 8080
            HttpServer server = HttpServer.create(new InetSocketAddress(8081), 0);

            server.createContext("/getMssg", new api_handler()::getMssg);
            server.createContext("/storeMssg", new api_handler()::storeMssg);
            server.createContext("/getChats", new api_handler()::getChats);
            server.createContext("/addUsers", new api_handler()::addUsers);
            server.createContext("/getUsers", new api_handler()::getUsers);

            server.start();
            System.out.println("server is running on port 8081");
        }catch (IOException e){
            System.out.println("im obviously an error");
            e.printStackTrace();
        }
    }
}