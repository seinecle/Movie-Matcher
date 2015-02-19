package userclasses;

import com.codename1.io.FileSystemStorage;
import com.codename1.io.Util;
import generated.StateMachineBase;
import com.codename1.ui.*;
import com.codename1.ui.events.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.util.Resources;
import java.io.IOException;

/**
 *
 * @author Your name here
 */
public class StateMachine extends StateMachineBase {

    float width;
    FileSystemStorage fs;
    String root;
    String path;

    public StateMachine(String resFile) {
        super(resFile);
        // do not modify, write code in initVars and initialize class members there,
        // the constructor might be invoked too late due to race conditions that might occur
    }

    /**
     * this method should be used to initialize variables instead of the
     * constructor/class scope to avoid race conditions
     *
     * @param res
     */
    @Override
    protected void initVars(Resources res) {
        Util.register("Movie", Movie.class);
        width = Display.getInstance().getDisplayWidth();
        width /= 2;

        fs = FileSystemStorage.getInstance();
        root = RootGetter.Get(fs);
        System.out.println("path root: " + root);
        fs.mkdir(root + "posters");
        path = root + "posters" + fs.getFileSystemSeparator();
        System.out.println("path with folder: " + path);

    }

    @Override
    protected void exitSecondForm(Form f) {
        List list = findList(f);
        PreferencesManager.preferenceSaver(list);

    }

    @Override
    protected void onMain_GoToRecommendationsAction(Component c, ActionEvent event) {
        GetMovieReleases.getNewReleases(path);
        loadOnePosterFromDisk(1, "forward");
    }

    @Override
    protected void beforeSecondForm(Form f) {
        PreferencesManager.preferenceLoaderToCheckBoxes(findList());
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
                    showForm(getFirstFormName(), this);
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

    @Override
    protected void onMain_Button1Action(Component c, ActionEvent event) {
        try {

            Container container = new Container();
            
            Label i = new Label();
            Image img = Image.createImage("/tmdb-logo.png");
            i.setIcon(img);

            TextArea lbl = new TextArea("This product uses the TMDb API but is not endorsed or certified by TMDb.");
            lbl.setEditable(false);
            lbl.setFocusable(false);
            lbl.setUIID("Label");
            
            container.addComponent(i);
            container.addComponent(lbl);


            Dialog.show("Attribution", container, null);
        } catch (IOException ex) {
            System.out.println("error in acknowl");
        }

    }
}
