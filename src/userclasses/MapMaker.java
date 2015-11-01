/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userclasses;

import com.codename1.location.Location;
import com.codename1.location.LocationManager;
import com.codename1.maps.Coord;
import com.codename1.maps.MapComponent;
import com.codename1.maps.layers.PointLayer;
import com.codename1.maps.layers.PointsLayer;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import java.io.IOException;

/**
 *
 * @author LEVALLOIS
 */
public class MapMaker {

    StateMachine stateMachine;

    public MapMaker(StateMachine stateMachineParam) {
        this.stateMachine = stateMachineParam;
        System.out.println("well it should work");
        Button b = stateMachine.findButton();

        if (b == null) {
            System.out.println("b is null");
        } else {
            System.out.println("b is good!");
        }

    }

    void showMap() {
        Form map = new Form("Map");
        map.setLayout(new BorderLayout());
        map.setScrollable(false);
        final MapComponent mc = new MapComponent();
        try {
//get the current location from the Location API
            Location loc = LocationManager.getLocationManager().getCurrentLocation();
            Coord lastLocation = new Coord(loc.getLatitude(), loc.getLongitude());
            Image i = Image.createImage("/blue_pin_small.png");
            PointsLayer pl = new PointsLayer();
            pl.setPointIcon(i);
            PointLayer p = new PointLayer(lastLocation, "You Are Here", i);
            p.setDisplayName(true);
            pl.addPoint(p);
            mc.addLayer(pl);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        mc.zoomToLayers();
        map.addComponent(BorderLayout.CENTER, mc);
        Command back = new Command("Back") {
            @Override
            public void actionPerformed(ActionEvent ev) {
                // notice that when showing a previous form it is best to use showBack() so the 
                // transition runs in reverse
                stateMachine.showForm("Main", null);
            }
        };
        map.addCommand(back);
        map.setBackCommand(back);
        map.show();
    }

}
