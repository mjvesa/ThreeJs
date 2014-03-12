package com.github.mjvesa.threejs.client.light;

import com.github.mjvesa.threejs.client.camera.Camera;
import com.google.gwt.dom.client.Element;

public class DirectionalLight extends Light {
    
    protected DirectionalLight() {}
    
    public final static native DirectionalLight getInstance(int hexColor, double intensity) /*-{
        return new $wnd.THREE.DirectionalLight(hexColor, intensity);
    }-*/;
    
    public final native void setPosition(double x, double y, double z) /*-{
        this.position.set(x, y, z);
    }-*/;
}
