/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userclasses;

import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.events.ActionEvent;
import java.io.IOException;

/**
 *
 * @author LEVALLOIS
 */
public class ShowDialogAcknowledgement {

    public static void showIt() throws IOException {
        Container container = new Container();

        Label i = new Label();
        //image has to be next to the src folder next to the .res file, and nowhere else, or it won't work.
        Image img = Image.createImage("/tmdb-logo.png");
        i.setIcon(img);

        TextArea lbl = new TextArea("This product uses the TMDb API but is not endorsed or certified by TMDb.");
        lbl.setEditable(false);
        lbl.setFocusable(false);
        lbl.setUIID("Label");

        container.addComponent(i);
        container.addComponent(lbl);

        Command close = new Command("Ok") {
            @Override
            public void actionPerformed(ActionEvent ev) {
            }
        };
        Command[] commands = new Command[1];
        commands[0] = (close);

        Dialog.show("Attribution", container, commands);
    }
}
