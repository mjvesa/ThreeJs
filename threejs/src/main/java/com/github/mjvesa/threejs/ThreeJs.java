package com.github.mjvesa.threejs;

import com.github.mjvesa.threejs.client.ThreeJsClientRpc;
import com.github.mjvesa.threejs.client.ThreeJsServerRpc;
import com.github.mjvesa.threejs.client.ThreeJsState;
import com.vaadin.annotations.JavaScript;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.MouseEventDetails;

@JavaScript({ "three.min.js", "OBJLoader.js", "TrackballControls.js" })
public class ThreeJs extends com.vaadin.ui.AbstractComponent {

    private static final String TEX_URL_KEY = "texUrl";
    private static final String OBJ_URL_KEY = "objUrl";

    private int clickCount = 0;

    private ThreeJsServerRpc rpc = new ThreeJsServerRpc() {

    };

    public ThreeJs() {

        registerRpc(rpc);
    }

    @Override
    public ThreeJsState getState() {
        return (ThreeJsState) super.getState();
    }

    public void loadObj(String key, Resource objRes) {
        setResource(OBJ_URL_KEY, objRes);
        getRpc().loadObj(key);
    }
    
    public void startAnimation() {
        getRpc().startAnimation();
    }
    
    private ThreeJsClientRpc getRpc() {
        return  getRpcProxy(ThreeJsClientRpc.class);
    }

    public void setDirectionalLightPosition(String id, double x, double y, double z) {
        getRpc().setDirectionalLightPosition(id, x, y, z);
    }

    public void createPhongMaterial(String key, int ambient, int color, int specular, int shininess) {
        getRpc().createPhongMaterial(key, ambient, color, specular, shininess);
    }

    public void setMaterialToObj(String materialId, String objId) {
        getRpc().setMaterialToObj(materialId, objId);
        
    }

    public void addObj(String id) {
        getRpc().addObj(id);
        
    }

    public void createDirectionalLight(String id, int hexColor, double intensity) {
        getRpc().createDirectionalLight(id, hexColor, intensity);
        
    }

    public void addLight(String id) {
        getRpc().addLight(id);
        
    }

    public void loadTextureToMaterial(ThemeResource themeResource,
            String id) {
        setResource(TEX_URL_KEY, themeResource);
        getRpc().loadTexture(id);
        
    }

}
