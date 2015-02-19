/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userclasses;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author LEVALLOIS
 */
public class GetMovieInfo {

    public static Movie getMovieInfo(String id) {
        Movie movie = null;
        try {
            movie = new Movie();
            movie.id = id;

            ConnectionRequest request = ConnectionRequestBuilder.requestBuilder("https://api.themoviedb.org/3/movie/" + id, true);

            JSONParser jp = new JSONParser();
            Map<String, Object> m = jp.parseJSON(new InputStreamReader(new ByteArrayInputStream(request.getResponseData())));
//            System.out.println("" + m);
            ArrayList<Map<String, String>> genres = (ArrayList<Map<String, String>>) m.get("genres");
            int genreNumber = 1;
            for (Map<String, String> keyValues : genres) {
                for (Map.Entry<String, String> entry : keyValues.entrySet()) {
//                    System.out.println(entry.getKey() + ": " + String.valueOf(entry.getValue()));
                    if (entry.getKey().equals("name")) {
                        if (genreNumber == 1) {
                            movie.genre1 = String.valueOf(entry.getValue());
                        }
                        if (genreNumber == 2) {
                            movie.genre2 = String.valueOf(entry.getValue());
                        }
                        if (genreNumber == 3) {
                            movie.genre3 = String.valueOf(entry.getValue());
                        }
                    }
                }
                genreNumber++;
            }
            movie.title = (String) m.get("original_title");
            movie.overview = (String) m.get("overview");


        } catch (IOException ex) {
            System.out.println("ERROR!!");
        }
        return movie;

    }
}
