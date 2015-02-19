/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userclasses;

import com.codename1.io.FileSystemStorage;

/**
 *
 * @author LEVALLOIS
 */
public class RootGetter {

    public static String Get(FileSystemStorage fs) {
        String root = null;
        String[] ch = fs.getRoots();
        int nbRoots = ch.length;

        for (int k = 0; k < nbRoots; k++) {
            int typeStorage = fs.getRootType(ch[k]);
            System.out.println("type Storage: " + String.valueOf(typeStorage));
            if (fs.getRootType(ch[k]) == FileSystemStorage.ROOT_TYPE_MAINSTORAGE) {
                root = ch[k];
                if (!root.endsWith("/")) {
                    root += "/";
                }
                break;
            }
        }
        if (root == null) {
            System.out.println("MAIN STORAGE NOT FOUND. DEFAULTING TO WHATEVER THERE IS");
            root = ch[0];
        }

        root = fs.getAppHomePath();
        if (!root.endsWith("/")) {
            root += "/";
        }
        return root;
    }

}
