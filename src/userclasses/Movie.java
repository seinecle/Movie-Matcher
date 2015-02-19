/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userclasses;

import com.codename1.io.Externalizable;
import com.codename1.io.Util;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 *
 * @author LEVALLOIS
 */
public class Movie implements Externalizable {

    String title;
    String genre1;
    String genre2;
    String genre3;
    String genre4;
    int year;
    String id;
    String overview;
    String posterFilePath;

    public Movie() {
    }


    public int getVersion() {
        return 1;
    }

    public void externalize(DataOutputStream out) throws IOException {
        Util.writeUTF(title, out);
        Util.writeUTF(genre1, out);
        out.writeInt(year);
    }

    public void internalize(int version, DataInputStream in) throws IOException {
        title = Util.readUTF(in);
        genre1 = Util.readUTF(in);
        year = in.readInt();
    }

    public String getObjectId() {
        return "Movie";
    }

}
