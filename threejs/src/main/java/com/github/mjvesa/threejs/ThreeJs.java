package com.github.mjvesa.threejs;

import com.github.mjvesa.threejs.client.ThreeJsClientRpc;
import com.github.mjvesa.threejs.client.ThreeJsServerRpc;
import com.github.mjvesa.threejs.client.ThreeJsState;
import com.vaadin.annotations.JavaScript;
import com.vaadin.server.Resource;
import com.vaadin.shared.MouseEventDetails;

// This is the server-side UI component that provides public API 
// for Three
@JavaScript({ "three.min.js", "OBJLoader.js", "TrackballControls.js" })
public class ThreeJs extends com.vaadin.ui.AbstractComponent {

    private static final String TEX_URL_KEY = "texUrl";
    private static final String OBJ_URL_KEY = "objUrl";

    private int clickCount = 0;

    // To process events from the client, we implement ServerRpc
    private ThreeJsServerRpc rpc = new ThreeJsServerRpc() {

    };

    public ThreeJs() {

        // To receive events from the client, we register ServerRpc
        registerRpc(rpc);
    }

    // We must override getState() to cast the state to ThreeState
    @Override
    public ThreeJsState getState() {
        return (ThreeJsState) super.getState();
    }

    public void loadObj(Resource objRes) {
        setResource(OBJ_URL_KEY, objRes);
        getRpc().loadObj();
    }

    public void loadObj(Resource objRes, Resource texRes) {
        setResource(OBJ_URL_KEY, objRes);
        setResource(TEX_URL_KEY, texRes);
        getRpc().loadObjWithTexture();
    }
    
    public void loadObj(Resource objRes, Resource texRes, int ambient, int color, int specular, int shininess) {
        setResource(OBJ_URL_KEY, objRes);
        setResource(TEX_URL_KEY, texRes);
        getRpc().loadObjWithTextureAndPhong(ambient, color, specular, shininess);
    }
    
    public void startAnimation() {
        getRpc().startAnimation();
    }
    
    private ThreeJsClientRpc getRpc() {
        return  getRpcProxy(ThreeJsClientRpc.class);
    }

    public void addDirectionalLight(String id, int hexColor, double intensity) {
        getRpc().addDirectionalLight(id, hexColor, intensity);
        
    }

    public void setDirectionalLightPosition(String id, double x, double y, double z) {
        getRpc().setDirectionalLightPosition(id, x, y, z);
    }


}
