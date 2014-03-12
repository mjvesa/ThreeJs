package com.github.mjvesa.threejs.client.math;

import com.github.mjvesa.threejs.client.camera.Camera;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;

public class Color extends JavaScriptObject {

    protected Color() {}
    
    public final static native Color getInstance(int r, int g, int b) /*-{
        return new $wnd.THREE.Color(r, b, g);
    }-*/;
    

    public  final native void setRGB(int r, int g, int b) /*-{
        this.setRGB(r, g, b);
    }-*/;

}
