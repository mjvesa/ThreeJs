package com.github.mjvesa.threejs.client.renderer;

import com.github.mjvesa.threejs.client.camera.Camera;
import com.github.mjvesa.threejs.client.scene.Scene;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;

public class Renderer extends JavaScriptObject {
    
    protected Renderer() {}
    
    public final native void render(Scene scene, Camera camera) /*-{
        this.render(scene, camera);
    }-*/;
    
    public final native void setSize(int width, int height) /*-{
        this.setSize(width, height);
    }-*/;
    
    public final native Element getDOMElement() /*-{
        return this.domElement;
    }-*/; 
}
