package com.github.mjvesa.threejs.sprite;

import com.github.mjvesa.threejs.client.material.Material;
import com.google.gwt.core.client.JavaScriptObject;

public class Sprite extends JavaScriptObject {

    
    public static native Sprite getInstance(Material material) /*-{
        return $wnd.THREE.Sprite(material);
    }-*/;
    
    public native void setPosition(double x, double y, double z) /*-{
        
    }-*/;
}
