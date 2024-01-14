package nl.tudelft.sem.template.review.services;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

@Service
public class CommunicationServiceImpl {
    private final String userMicroUrl = "http://localhost:8080";

    private final String bookMicroUrl = "http://localhost:8081";

    public CommunicationServiceImpl() {
    }
    public boolean getResponse(String server, Long id, boolean admin){
        String url;
        StringBuilder response = new StringBuilder();
        int responseCode;
        if(Objects.equals(server, bookMicroUrl)){
            url = server + "/book/getById/" + id;
        }else{
            url = server + "/check/role/" + id;
        }
        try {
            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

            // Set the request method
            connection.setRequestMethod("GET");

            responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

            } else {
                System.out.println("Failed with status code " + responseCode);
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if(Objects.equals(server,userMicroUrl)){
            if(admin)
                return response.toString().toLowerCase().contains("admin");
            else
                return response.toString().toLowerCase().contains("role");
        }else {
            return response.toString().toLowerCase().contains("genre");
        }
    }

    public boolean isAdmin(Long userId) {
        //TODO make http request to endpoint for admin
        return getResponse(userMicroUrl,userId,true);
        //return true;
    }


    public boolean existsBook(Long bookId) {
        //TODO make http request to endpoint for book
        return getResponse(bookMicroUrl,bookId,false);
        //return true;
    }

    public boolean existsUser(Long userId) {
        //TODO make http request to endpoint for user
        return getResponse(userMicroUrl,userId,false);
        //return true;
    }


}
