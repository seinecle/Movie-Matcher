/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userclasses;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.Progress;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.JSONParser;
import com.codename1.io.Util;
import com.codename1.ui.Dialog;
import com.codename1.util.StringUtil;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author LEVALLOIS
 */
public class GetMovieReleases {

    static FileSystemStorage fs;
    static Dialog dialog;

    public static void getNewReleases(String path) {
        try {

            fs = FileSystemStorage.getInstance();
            String[] files = fs.listFiles(path);

            // delete all files from the folder;
            if (files != null) {
                for (String file : files) {
                    // if the file date is older than a week, delete it
//                if (System.currentTimeMillis() - 604800000 > fs.getLastModified(file)) {
                    fs.deleteRetry(file, 2);
//                }
                }
            }
            
            System.out.println("about to call movie releases");
            InfiniteProgress prog = new InfiniteProgress();
            dialog = prog.showInifiniteBlocking();

            ConnectionRequest request = ConnectionRequestBuilder.requestBuilder("https://api.themoviedb.org/3/movie/now_playing", true);

            JSONParser jp = new JSONParser();
            Map<String, Object> m = jp.parseJSON(new InputStreamReader(new ByteArrayInputStream(request.getResponseData())));
            
            System.out.println("movie releases received");

//            System.out.println("" + m);
            ArrayList<Map<String, String>> list = (ArrayList<Map<String, String>>) m.get("results");
            int counterMovies = 1;
            for (Map<String, String> keyValues : list) {
                if (counterMovies > 10) {
                    break;
                }
//                System.out.println("Movie " + counterMovies);
                for (Map.Entry<String, String> entry : keyValues.entrySet()) {
//                    System.out.println(entry.getKey() + ": " + String.valueOf(entry.getValue()));
                    if (entry.getKey().equals("id")) {
                        String value = String.valueOf(entry.getValue());
                        java.util.List<String> tokens = StringUtil.tokenize(value, ".");
                        String id = tokens.get(0);
                        System.out.println("id: " + id);

                        Movie movie = GetMovieInfo.getMovieInfo(id);
                        System.out.println("one movie info downloaded: " + id);

                        if (!PreferencesManager.isMovieFromAPreferredGenre(movie)) {
                            break;
                        }

                        movie = GetMoviePictures.getMoviePictures(movie);
                        System.out.println("one movie pic path found: " + id);

                        if (movie.posterFilePath == null) {
                            break;
                        }

                        String movieFileName = String.valueOf(counterMovies) + movie.id + ".jpg";

                        String pathFile = path + movieFileName;

                        Util.downloadUrlToFileSystemInBackground(movie.posterFilePath, pathFile);
                        System.out.println("one movie info + pic downloaded: " + pathFile);
                        counterMovies++;

                    }
                }
            }
            dialog.dispose();

        } catch (IOException ex) {
            dialog.dispose();
            System.out.println("ERROR!!");
        }

    }

}
