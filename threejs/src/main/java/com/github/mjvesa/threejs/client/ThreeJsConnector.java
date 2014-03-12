package com.github.mjvesa.threejs.client;

import com.github.mjvesa.threejs.ThreeJs;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.MouseEventDetailsBuilder;
import com.vaadin.client.communication.RpcProxy;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.AbstractComponentConnector;
import com.vaadin.shared.MouseEventDetails;
import com.vaadin.shared.ui.Connect;

// Connector binds client-side widget class to server-side component class
// Connector lives in the client and the @Connect annotation specifies the
// corresponding server-side component
@Connect(ThreeJs.class)
public class ThreeJsConnector extends AbstractComponentConnector {

    // ServerRpc is used to send events to server. Communication implementation
    // is automatically created here
    ThreeJsServerRpc rpc = RpcProxy.create(ThreeJsServerRpc.class, this);

    public ThreeJsConnector() {
        // To receive RPC events from server, we register ClientRpc
        // implementation
        registerRpc(ThreeJsClientRpc.class, new ThreeJsClientRpc() {

            @Override
            public void startAnimation() {
                getWidget().startAnimation();
            }

            @Override
            public void loadObj() {
                    String url = getResourceUrl("objUrl");
                    getWidget().loadObj(url);
                
            }

            @Override
            public void loadObjWithTexture() {
                String url = getResourceUrl("objUrl");
                String texUrl = getResourceUrl("texUrl");
                getWidget().loadObjWithTexture(url, texUrl);
            }

            @Override
            public void loadObjWithTextureAndPhong(int ambient, int color, int specular,
                    int shininess) {
                String url = getResourceUrl("objUrl");
                String texUrl = getResourceUrl("texUrl");
                getWidget().loadObjWithTextureAndPhong(url, texUrl, ambient, color, specular, shininess);
                
            }

            @Override
            public void addDirectionalLight(String id, int hexColor,
                    double intensity) {
                getWidget().addDirectionalLight(id, hexColor, intensity);
                
            }

            @Override
            public void setDirectionalLightPosition(String id, double x,
                    double y, double z) {
                getWidget().setDirectionalLightPosition(id, x, y, z);
                
            }
        });
    }

    // We must implement createWidget() to create correct type of widget
    @Override
    protected Widget createWidget() {
        return GWT.create(ThreeJsWidget.class);
    }

    // We must implement getWidget() to cast to correct type
    @Override
    public ThreeJsWidget getWidget() {
        return (ThreeJsWidget) super.getWidget();
    }

    // We must implement getState() to cast to correct type
    @Override
    public ThreeJsState getState() {
        return (ThreeJsState) super.getState();
    }

    // Whenever the state changes in the server-side, this method is called
    @Override
    public void onStateChanged(StateChangeEvent stateChangeEvent) {
        super.onStateChanged(stateChangeEvent);
        getWidget().init();
    }

}
