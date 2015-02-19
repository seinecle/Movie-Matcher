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
public class GetMoviePictures {

    public static Movie getMoviePictures(Movie movie) {
        try {

            ConnectionRequest request = ConnectionRequestBuilder.requestBuilder("https://api.themoviedb.org/3/movie/" + movie.id + "/images", true);

            JSONParser jp = new JSONParser();
            Map<String, Object> m = jp.parseJSON(new InputStreamReader(new ByteArrayInputStream(request.getResponseData())));
//            System.out.println("" + m);
            ArrayList<Map<String, String>> posters = (ArrayList<Map<String, String>>) m.get("posters");
            boolean posterFound = false;
            for (Map<String, String> keyValues : posters) {
                for (Map.Entry<String, String> entry : keyValues.entrySet()) {
//                    System.out.println(entry.getKey() + ": " + String.valueOf(entry.getValue()));
                    if (entry.getKey().equals("file_path")) {
                        movie.posterFilePath = "http://image.tmdb.org/t/p/w342" + String.valueOf(entry.getValue());
//                        System.out.println("poster of " + movie.title + ": " + movie.posterFilePath);
                        posterFound = true;
                        break;
                    }
                }
                if (posterFound) {
                    break;
                }
            }

        } catch (IOException ex) {
            System.out.println("ERROR!!");
        }
        if (movie.posterFilePath == null) {
//            System.out.println("poster of " + movie.title + " NOT FOUND");
        }
        return movie;
    }
}
