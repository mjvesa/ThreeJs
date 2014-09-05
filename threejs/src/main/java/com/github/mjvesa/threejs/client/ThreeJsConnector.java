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
            public void loadObj(String key) {
                    String url = getResourceUrl("objUrl");
                    getWidget().loadObj(key, url);
                
            }
            
            @Override
            public void loadPdb(String key) {
                String url = getResourceUrl("objUrl");
                getWidget().loadPdb(key, url);
            }


            @Override
            public void createDirectionalLight(String key, int hexColor,
                    double intensity) {
                getWidget().createDirectionalLight(key, hexColor, intensity);
                
            }

            @Override
            public void setDirectionalLightPosition(String id, double x,
                    double y, double z) {
                getWidget().setDirectionalLightPosition(id, x, y, z);
                
            }

            @Override
            public void createPhongMaterial(String key, int ambient,
                    int color, int specular, int shininess) {
                getWidget().createPhongMaterial(key, ambient, color, specular, shininess);
            }

            @Override
            public void setMaterialToObj(String materialId, String objId) {
                getWidget().setMaterialToObj(materialId, objId);
            }

            @Override
            public void addObj(String id) {
                getWidget().addObj(id);
            }

            @Override
            public void addLight(String id) {
                getWidget().addLight(id);
            }

            @Override
            public void loadTexture(String id) {
                String url = getResourceUrl("texUrl");
                getWidget().loadTextureToMaterial(url, id);
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
