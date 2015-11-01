/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userclasses;

import com.codename1.io.Storage;
import com.codename1.ui.List;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

/**
 *
 * @author LEVALLOIS
 */
public class PreferencesManager {

    private static Vector<String> preferredGenresGlobal;

    public static void preferenceSaver(List l) {
        Storage.getInstance().clearStorage();
        preferredGenresGlobal = new Vector();
        for (int i = 0; i < l.getModel().getSize(); i++) {
            Hashtable comp = (Hashtable) l.getModel().getItemAt(i);
            Iterator<Object> it = comp.entrySet().iterator();
            boolean keepGenre = false;
            String currentGenre = null;
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                System.out.println("key: " + entry.getKey().toString());
                System.out.println("value: " + entry.getValue().toString());
                if (entry.getKey().toString().equals("CheckBox") && entry.getValue().toString().equals("true")) {
                    keepGenre = true;
                }
                if (entry.getKey().toString().equals("Genre")) {
                    currentGenre = entry.getValue().toString();
                }
                if (keepGenre & currentGenre != null) {
                    preferredGenresGlobal.add(currentGenre);
                    System.out.println("added preferred Genre in the vector: " + entry.getValue().toString());
                    currentGenre = null;
                    keepGenre = false;
                }
            }
        }
        boolean verification = Storage.getInstance().writeObject("preferredGenres", preferredGenresGlobal);
        System.out.println("verification: yes, the vevtor of preferrences was saved correctly: " + verification);
        if (!preferredGenresGlobal.isEmpty()) {
            System.out.println("data saved: " + preferredGenresGlobal.toString());
        } else {
            System.out.println("the vector of preferences was empty");
        }
    }

    public static Vector<String> preferenceLoaderToCheckBoxes(List l) {
        Vector<String> preferredGenres = (Vector<String>) Storage.getInstance().readObject("preferredGenres");
        if (preferredGenres == null) {
            System.out.println("returning from preference loader because preferredGenres is null");
            return null;
//            System.out.println("first element in preferredGenres loaded from storage: " + preferredGenres.firstElement());
        }
        if (preferredGenres.isEmpty()) {
            System.out.println("returning from preference loader because preferredGenres is empty");
            return null;
//            System.out.println("first element in preferredGenres loaded from storage: " + preferredGenres.firstElement());
        }
        for (int i = 0; i < l.getModel().getSize(); i++) {
            Hashtable comp = (Hashtable) l.getModel().getItemAt(i);
            Iterator<Object> it = comp.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
//                System.out.println("key: " + entry.getKey().toString());
//                System.out.println("value: " + entry.getValue().toString());
                if (entry.getKey().toString().equals("Genre")) {
                    for (String preferredGenre : preferredGenres) {
                        if (preferredGenre.equals(entry.getValue().toString())) {
                            Iterator<Object> it2 = comp.entrySet().iterator();
                            while (it2.hasNext()) {
                                Map.Entry entry2 = (Map.Entry) it2.next();
                                if (entry2.getKey().toString().equals("CheckBox")) {
                                    entry2.setValue("true");
                                }
                            }
                        }
                    }
                }
            }
        }
        l.getComponentForm().revalidate();
        System.out.println("returning from preferredGenresLoader with a full vector");
        return preferredGenres;
    }

    public static Vector<String> preferenceLoaderToVector() {
        Vector<String> preferredGenres = (Vector<String>) Storage.getInstance().readObject("preferredGenres");
        if (preferredGenres == null) {
            System.out.println("returning from preference loader to vector because preferredGenres is null");
            return null;
//            System.out.println("first element in preferredGenres loaded from storage: " + preferredGenres.firstElement());
        }
        if (preferredGenres.isEmpty()) {
            System.out.println("returning from preference loader to vector because preferredGenres is empty");
            return null;
//            System.out.println("first element in preferredGenres loaded from storage: " + preferredGenres.firstElement());
        }
        System.out.println("returning from preferredGenresLoader with a full vector");
        return preferredGenres;
    }

    public static boolean isMovieFromAPreferredGenre(Movie movie) {

        preferredGenresGlobal = preferenceLoaderToVector();

        System.out.println("1. entering the isFromAPreferredGenre method");
        if (preferredGenresGlobal == null) {
            System.out.println("LEAVING the isFromAPreferredGenre method because VECTOR IS NULL");
            return false;
        }

        Set<String> setPreferredGenres = new HashSet(preferredGenresGlobal);

        if (setPreferredGenres.contains(movie.genre1)) {
            System.out.println("2. returning from isMovieFromAPreferredGenre with a FALSE");
            return true;
        }
        if (setPreferredGenres.contains(movie.genre2)) {
            System.out.println("3. returning from isMovieFromAPreferredGenre with a FALSE");
            return true;
        }
        if (setPreferredGenres.contains(movie.genre3)) {
            System.out.println("4. returning from isMovieFromAPreferredGenre with a FALSE");
            return true;
        }

        System.out.println("5. returning from isMovieFromAPreferredGenre with a FALSE");

        return false;

    }
}
