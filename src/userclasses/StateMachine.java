package userclasses;

import com.codename1.io.FileSystemStorage;
import com.codename1.io.Util;
import generated.StateMachineBase;
import com.codename1.ui.*;
import com.codename1.ui.events.*;
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

        PosterManager posterManager = new PosterManager(this);
        posterManager.loadOnePosterFromDisk(1, "forward");
    }

    @Override
    protected void beforeSecondForm(Form f) {
        PreferencesManager.preferenceLoaderToCheckBoxes(findList());
    }

    @Override
    protected void onMain_AcknowledgementsAction(Component c, ActionEvent event) {
        try {
            ShowDialogAcknowledgement.showIt();
        } catch (IOException ex) {
        }

    }

    @Override
    protected void onMain_MapButtonAction(Component c, ActionEvent event) {
        MapMaker mapMaker = new MapMaker(this);
        mapMaker.showMap();
    }

    @Override
    protected void postMain(Form f) {
        findShareButton().setTextToShare("This is a test");

        System.out.println();
        System.out.println("That's it man! Go and build something awesome with Scribe! :)");
    }
}
