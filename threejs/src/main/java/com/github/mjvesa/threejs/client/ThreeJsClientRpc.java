package com.github.mjvesa.threejs.client;

import com.vaadin.server.ThemeResource;
import com.vaadin.shared.communication.ClientRpc;

public interface ThreeJsClientRpc extends ClientRpc {

    public void loadObj(String key);

//    public void loadObjWithTexture();
//
//    public void loadObjWithTextureAndPhong(int ambient, int color, int specular, int shininess);
//
//    public void loadObjWithPhong(int ambient, int color, int specular,
//            int shininess);

    void createDirectionalLight(String key, int hexColor, double intensity);

    public void setDirectionalLightPosition(String id, double x, double y, double z);

    // TODO obj + mtl
    
    public void startAnimation();

    public void createPhongMaterial(String key, int ambient, int color,
            int specular, int shininess);

    public void setMaterialToObj(String materialId, String objId);

    public void addObj(String id);

    public void addLight(String id);

    public void loadTexture(String id);
    
}    

