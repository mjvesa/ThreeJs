package com.github.mjvesa.threejs.client;

import com.vaadin.server.ThemeResource;
import com.vaadin.shared.communication.ClientRpc;

// ClientRpc is used to pass events from server to client
// For sending information about the changes to component state, use State instead
public interface ThreeJsClientRpc extends ClientRpc {

    public void loadObj();

    public void loadObjWithTexture();

    public void loadObjWithTextureAndPhong(int ambient, int color, int specular, int shininess);

    public void addDirectionalLight(String id, int hexColor, double intensity);

    public void setDirectionalLightPosition(String id, double x, double y, double z);

    // TODO obj + mtl
    
    public void startAnimation();



}    

