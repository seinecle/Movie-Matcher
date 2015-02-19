/**
 * This class contains generated code from the Codename One Designer, DO NOT MODIFY!
 * This class is designed for subclassing that way the code generator can overwrite it
 * anytime without erasing your changes which should exist in a subclass!
 * For details about this file and how it works please read this blog post:
 * http://codenameone.blogspot.com/2010/10/ui-builder-class-how-to-actually-use.html
*/
package generated;

import com.codename1.ui.*;
import com.codename1.ui.util.*;
import com.codename1.ui.plaf.*;
import java.util.Hashtable;
import com.codename1.ui.events.*;

public abstract class StateMachineBase extends UIBuilder {
    private Container aboutToShowThisContainer;
    /**
     * this method should be used to initialize variables instead of
     * the constructor/class scope to avoid race conditions
     */
    /**
    * @deprecated use the version that accepts a resource as an argument instead
    
**/
    protected void initVars() {}

    protected void initVars(Resources res) {}

    public StateMachineBase(Resources res, String resPath, boolean loadTheme) {
        startApp(res, resPath, loadTheme);
    }

    public Container startApp(Resources res, String resPath, boolean loadTheme) {
        initVars();
        UIBuilder.registerCustomComponent("Button", com.codename1.ui.Button.class);
        UIBuilder.registerCustomComponent("Form", com.codename1.ui.Form.class);
        UIBuilder.registerCustomComponent("CheckBox", com.codename1.ui.CheckBox.class);
        UIBuilder.registerCustomComponent("Label", com.codename1.ui.Label.class);
        UIBuilder.registerCustomComponent("List", com.codename1.ui.List.class);
        UIBuilder.registerCustomComponent("Container", com.codename1.ui.Container.class);
        if(loadTheme) {
            if(res == null) {
                try {
                    if(resPath.endsWith(".res")) {
                        res = Resources.open(resPath);
                        System.out.println("Warning: you should construct the state machine without the .res extension to allow theme overlays");
                    } else {
                        res = Resources.openLayered(resPath);
                    }
                } catch(java.io.IOException err) { err.printStackTrace(); }
            }
            initTheme(res);
        }
        if(res != null) {
            setResourceFilePath(resPath);
            setResourceFile(res);
            initVars(res);
            return showForm(getFirstFormName(), null);
        } else {
            Form f = (Form)createContainer(resPath, getFirstFormName());
            initVars(fetchResourceFile());
            beforeShow(f);
            f.show();
            postShow(f);
            return f;
        }
    }

    protected String getFirstFormName() {
        return "Main";
    }

    public Container createWidget(Resources res, String resPath, boolean loadTheme) {
        initVars();
        UIBuilder.registerCustomComponent("Button", com.codename1.ui.Button.class);
        UIBuilder.registerCustomComponent("Form", com.codename1.ui.Form.class);
        UIBuilder.registerCustomComponent("CheckBox", com.codename1.ui.CheckBox.class);
        UIBuilder.registerCustomComponent("Label", com.codename1.ui.Label.class);
        UIBuilder.registerCustomComponent("List", com.codename1.ui.List.class);
        UIBuilder.registerCustomComponent("Container", com.codename1.ui.Container.class);
        if(loadTheme) {
            if(res == null) {
                try {
                    res = Resources.openLayered(resPath);
                } catch(java.io.IOException err) { err.printStackTrace(); }
            }
            initTheme(res);
        }
        return createContainer(resPath, "Main");
    }

    protected void initTheme(Resources res) {
            String[] themes = res.getThemeResourceNames();
            if(themes != null && themes.length > 0) {
                UIManager.getInstance().setThemeProps(res.getTheme(themes[0]));
            }
    }

    public StateMachineBase() {
    }

    public StateMachineBase(String resPath) {
        this(null, resPath, true);
    }

    public StateMachineBase(Resources res) {
        this(res, null, true);
    }

    public StateMachineBase(String resPath, boolean loadTheme) {
        this(null, resPath, loadTheme);
    }

    public StateMachineBase(Resources res, boolean loadTheme) {
        this(res, null, loadTheme);
    }

    public com.codename1.ui.Container findMyListRenderer(Component root) {
        return (com.codename1.ui.Container)findByName("MyListRenderer", root);
    }

    public com.codename1.ui.Container findMyListRenderer() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("MyListRenderer", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("MyListRenderer", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findButton(Component root) {
        return (com.codename1.ui.Button)findByName("Button", root);
    }

    public com.codename1.ui.Button findButton() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("Button", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("Button", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findGenre(Component root) {
        return (com.codename1.ui.Label)findByName("Genre", root);
    }

    public com.codename1.ui.Label findGenre() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("Genre", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("Genre", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.CheckBox findCheckBox(Component root) {
        return (com.codename1.ui.CheckBox)findByName("CheckBox", root);
    }

    public com.codename1.ui.CheckBox findCheckBox() {
        com.codename1.ui.CheckBox cmp = (com.codename1.ui.CheckBox)findByName("CheckBox", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.CheckBox)findByName("CheckBox", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findButton1(Component root) {
        return (com.codename1.ui.Button)findByName("Button1", root);
    }

    public com.codename1.ui.Button findButton1() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("Button1", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("Button1", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.List findList(Component root) {
        return (com.codename1.ui.List)findByName("List", root);
    }

    public com.codename1.ui.List findList() {
        com.codename1.ui.List cmp = (com.codename1.ui.List)findByName("List", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.List)findByName("List", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findGoToRecommendations(Component root) {
        return (com.codename1.ui.Button)findByName("GoToRecommendations", root);
    }

    public com.codename1.ui.Button findGoToRecommendations() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("GoToRecommendations", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("GoToRecommendations", aboutToShowThisContainer);
        }
        return cmp;
    }

    public static final int COMMAND_MainMyMoviePreferences = 1;

    protected boolean onMainMyMoviePreferences() {
        return false;
    }

    protected void processCommand(ActionEvent ev, Command cmd) {
        switch(cmd.getId()) {
            case COMMAND_MainMyMoviePreferences:
                if(onMainMyMoviePreferences()) {
                    ev.consume();
                    return;
                }
                break;

        }
        if(ev.getComponent() != null) {
            handleComponentAction(ev.getComponent(), ev);
        }
    }

    protected void exitForm(Form f) {
        if("MyListRenderer".equals(f.getName())) {
            exitMyListRenderer(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Main".equals(f.getName())) {
            exitMain(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("SecondForm".equals(f.getName())) {
            exitSecondForm(f);
            aboutToShowThisContainer = null;
            return;
        }

            return;
    }


    protected void exitMyListRenderer(Form f) {
    }


    protected void exitMain(Form f) {
    }


    protected void exitSecondForm(Form f) {
    }

    protected void beforeShow(Form f) {
    aboutToShowThisContainer = f;
        if("MyListRenderer".equals(f.getName())) {
            beforeMyListRenderer(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Main".equals(f.getName())) {
            beforeMain(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("SecondForm".equals(f.getName())) {
            beforeSecondForm(f);
            aboutToShowThisContainer = null;
            return;
        }

            return;
    }


    protected void beforeMyListRenderer(Form f) {
    }


    protected void beforeMain(Form f) {
    }


    protected void beforeSecondForm(Form f) {
    }

    protected void beforeShowContainer(Container c) {
        aboutToShowThisContainer = c;
        if("MyListRenderer".equals(c.getName())) {
            beforeContainerMyListRenderer(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Main".equals(c.getName())) {
            beforeContainerMain(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("SecondForm".equals(c.getName())) {
            beforeContainerSecondForm(c);
            aboutToShowThisContainer = null;
            return;
        }

            return;
    }


    protected void beforeContainerMyListRenderer(Container c) {
    }


    protected void beforeContainerMain(Container c) {
    }


    protected void beforeContainerSecondForm(Container c) {
    }

    protected void postShow(Form f) {
        if("MyListRenderer".equals(f.getName())) {
            postMyListRenderer(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Main".equals(f.getName())) {
            postMain(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("SecondForm".equals(f.getName())) {
            postSecondForm(f);
            aboutToShowThisContainer = null;
            return;
        }

            return;
    }


    protected void postMyListRenderer(Form f) {
    }


    protected void postMain(Form f) {
    }


    protected void postSecondForm(Form f) {
    }

    protected void postShowContainer(Container c) {
        if("MyListRenderer".equals(c.getName())) {
            postContainerMyListRenderer(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Main".equals(c.getName())) {
            postContainerMain(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("SecondForm".equals(c.getName())) {
            postContainerSecondForm(c);
            aboutToShowThisContainer = null;
            return;
        }

            return;
    }


    protected void postContainerMyListRenderer(Container c) {
    }


    protected void postContainerMain(Container c) {
    }


    protected void postContainerSecondForm(Container c) {
    }

    protected void onCreateRoot(String rootName) {
        if("MyListRenderer".equals(rootName)) {
            onCreateMyListRenderer();
            aboutToShowThisContainer = null;
            return;
        }

        if("Main".equals(rootName)) {
            onCreateMain();
            aboutToShowThisContainer = null;
            return;
        }

        if("SecondForm".equals(rootName)) {
            onCreateSecondForm();
            aboutToShowThisContainer = null;
            return;
        }

            return;
    }


    protected void onCreateMyListRenderer() {
    }


    protected void onCreateMain() {
    }


    protected void onCreateSecondForm() {
    }

    protected Hashtable getFormState(Form f) {
        Hashtable h = super.getFormState(f);
        if("MyListRenderer".equals(f.getName())) {
            getStateMyListRenderer(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("Main".equals(f.getName())) {
            getStateMain(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("SecondForm".equals(f.getName())) {
            getStateSecondForm(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

            return h;
    }


    protected void getStateMyListRenderer(Form f, Hashtable h) {
    }


    protected void getStateMain(Form f, Hashtable h) {
    }


    protected void getStateSecondForm(Form f, Hashtable h) {
    }

    protected void setFormState(Form f, Hashtable state) {
        super.setFormState(f, state);
        if("MyListRenderer".equals(f.getName())) {
            setStateMyListRenderer(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("Main".equals(f.getName())) {
            setStateMain(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("SecondForm".equals(f.getName())) {
            setStateSecondForm(f, state);
            aboutToShowThisContainer = null;
            return;
        }

            return;
    }


    protected void setStateMyListRenderer(Form f, Hashtable state) {
    }


    protected void setStateMain(Form f, Hashtable state) {
    }


    protected void setStateSecondForm(Form f, Hashtable state) {
    }

    protected boolean setListModel(List cmp) {
        String listName = cmp.getName();
        if("List".equals(listName)) {
            return initListModelList(cmp);
        }
        return super.setListModel(cmp);
    }

    protected boolean initListModelList(List cmp) {
        return false;
    }

    protected void handleComponentAction(Component c, ActionEvent event) {
        Container rootContainerAncestor = getRootAncestor(c);
        if(rootContainerAncestor == null) return;
        String rootContainerName = rootContainerAncestor.getName();
        Container leadParentContainer = c.getParent().getLeadParent();
        if(leadParentContainer != null && leadParentContainer.getClass() != Container.class) {
            c = c.getParent().getLeadParent();
        }
        if(rootContainerName == null) return;
        if(rootContainerName.equals("MyListRenderer")) {
            if("CheckBox".equals(c.getName())) {
                onMyListRenderer_CheckBoxAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("Main")) {
            if("Button".equals(c.getName())) {
                onMain_ButtonAction(c, event);
                return;
            }
            if("GoToRecommendations".equals(c.getName())) {
                onMain_GoToRecommendationsAction(c, event);
                return;
            }
            if("Button1".equals(c.getName())) {
                onMain_Button1Action(c, event);
                return;
            }
        }
        if(rootContainerName.equals("SecondForm")) {
            if("CheckBox".equals(c.getName())) {
                onSecondForm_CheckBoxAction(c, event);
                return;
            }
            if("List".equals(c.getName())) {
                onSecondForm_ListAction(c, event);
                return;
            }
        }
    }

      protected void onMyListRenderer_CheckBoxAction(Component c, ActionEvent event) {
      }

      protected void onMain_ButtonAction(Component c, ActionEvent event) {
      }

      protected void onMain_GoToRecommendationsAction(Component c, ActionEvent event) {
      }

      protected void onMain_Button1Action(Component c, ActionEvent event) {
      }

      protected void onSecondForm_CheckBoxAction(Component c, ActionEvent event) {
      }

      protected void onSecondForm_ListAction(Component c, ActionEvent event) {
      }

}
