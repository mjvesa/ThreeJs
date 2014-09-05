package com.github.mjvesa.threejs.client;

import com.vaadin.server.ThemeResource;
import com.vaadin.shared.communication.ClientRpc;

public interface ThreeJsClientRpc extends ClientRpc {

    public void loadObj(String key);

    // TODO obj + mtl

    public void loadPdb(String key);

    void createDirectionalLight(String key, int hexColor, double intensity);

    public void setDirectionalLightPosition(String id, double x, double y, double z);

    public void startAnimation();

    public void createPhongMaterial(String key, int ambient, int color,
            int specular, int shininess);

    public void setMaterialToObj(String materialId, String objId);

    public void addObj(String id);

    public void addLight(String id);

    public void loadTexture(String id);

    
}    

