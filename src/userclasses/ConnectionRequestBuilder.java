/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userclasses;

import com.codename1.components.InfiniteProgress;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;

/**
 *
 * @author LEVALLOIS
 */
public class ConnectionRequestBuilder {

    public static ConnectionRequest requestBuilder(String url, boolean blockUI) {
        ConnectionRequest request = new ConnectionRequest();
        request.setUrl(url);
        request.setPost(false);

        request.addArgument("api_key", "57fee46762701c4ccc511560715875be");

        if (blockUI) {
        }
        NetworkManager.getInstance().addToQueueAndWait(request);

        return request;
    }

}
