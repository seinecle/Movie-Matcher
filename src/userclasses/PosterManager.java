/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userclasses;

import com.codename1.io.FileSystemStorage;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import java.io.IOException;

/**
 *
 * @author LEVALLOIS
 */
public class PosterManager {

    StateMachine stateMachine;
    FileSystemStorage fs;
    String path;
    float width;

    public PosterManager(StateMachine stateMachineParam) {
        stateMachine = stateMachineParam;
        fs = stateMachine.fs;
        path = stateMachine.path;
        width = stateMachine.width;
    }

    public void loadOnePosterFromDisk(int currentPoster, String direction) {
        try {
            String[] listFiles = fs.listFiles(path);
            if (listFiles.length == 0) {
                return;
            }

            if (currentPoster >= listFiles.length) {
                currentPoster = 0;
            }
            if (currentPoster < 0) {
                currentPoster = listFiles.length - 1;
            }
            String pathFile = path + listFiles[currentPoster];

            if (!fs.exists(pathFile)) {
                return;
            }

            Form f = new Form();

            addSwipeBack(f, currentPoster - 1);
            addSwipeForward(f, currentPoster + 1);

            Command back = new Command("Back") {
                @Override
                public void actionPerformed(ActionEvent ev) {
                    // notice that when showing a previous form it is best to use showBack() so the 
                    // transition runs in reverse
                    stateMachine.showForm("Main", this);
                }
            };
            f.setBackCommand(back);

            BorderLayout borderLayout = new BorderLayout();
            f.setLayout(borderLayout);
            Label label = new Label();
            Image i = Image.createImage(pathFile);
            i = i.scaled(400, -1);
            label.getUnselectedStyle().setBgImage(i);
            Button button = new Button("Menu");
            button.setCommand(back);
            f.addComponent(BorderLayout.NORTH, button);
            f.addComponent(BorderLayout.CENTER, label);
            if (direction.equals("forward")) {
                f.show();
            }
            if (direction.equals("back")) {
                f.showBack();
            }
        } catch (IOException ex) {
        }

    }

    /**
     * add code so we can reverse swipe to go back. calculate min width and if
     * swipe starts on left and goes across centre, then assume it is a swipe
     *
     * @param c
     */
    void addSwipeBack(final Form c, final int posterToLoad) {

        c.addPointerDraggedListener(new ActionListener() {
            boolean triggered = false;

            int startx;
            int starty;

            @Override
            public void actionPerformed(ActionEvent evt) {
                int x = evt.getX();
                int y = evt.getY();

                if (!triggered) {
                    if (x < width) {
                        triggered = true;
                        startx = x;
                        starty = y;

                        c.addPointerReleasedListener(new ActionListener() {

                            @Override
                            public void actionPerformed(ActionEvent evt) {
                                int x = evt.getX();
                                int y = evt.getY();
                                triggered = false;
                                c.removePointerReleasedListener(this);

                                if (x > width) {
                                    // finished
                                    loadOnePosterFromDisk(posterToLoad, "back");
                                }
                            }
                        });
                    }
                }
            }
        });
    }

    void addSwipeForward(final Form c, final int posterToLoad) {

        c.addOrientationListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
            }

        });

        c.addPointerDraggedListener(new ActionListener() {
            boolean triggered = false;

            int startx;
            int starty;

            @Override
            public void actionPerformed(ActionEvent evt) {
                int x = evt.getX();
                int y = evt.getY();

                if (!triggered) {
                    if (x > width) {
                        triggered = true;
                        startx = x;
                        starty = y;

                        c.addPointerReleasedListener(new ActionListener() {

                            @Override
                            public void actionPerformed(ActionEvent evt) {
                                int x = evt.getX();
                                int y = evt.getY();
                                triggered = false;
                                c.removePointerReleasedListener(this);

                                if (Math.abs(x - startx) > Math.abs(y - starty)) {
                                    if (x < width) {
                                        // finished
                                        loadOnePosterFromDisk(posterToLoad, "forward");
                                    }
                                }
                            }
                        });
                    }
                }
            }
        });
    }

}
