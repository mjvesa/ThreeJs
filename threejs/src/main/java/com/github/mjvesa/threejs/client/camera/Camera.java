package com.github.mjvesa.threejs.client.camera;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;

public class Camera extends JavaScriptObject {
    
    protected Camera () {}
    
    
    public final native void setPosition(double x, double y, double z) /*-{
        this.position.x = x;
        this.position.y = y;
        this.position.z = z;
    }-*/;
    

    public final native void setRotation(double x, double y, double z) /*-{
        this.rotation.x = x;
        this.rotation.y = y;
        this.rotation.z = z;
    }-*/;

}
