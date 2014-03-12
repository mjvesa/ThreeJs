package com.github.mjvesa.threejs.client.light;

import com.github.mjvesa.threejs.client.math.Color;
import com.google.gwt.core.client.JavaScriptObject;

public class Light extends JavaScriptObject {
    
    protected Light() {}
    
    public final native void setColor(Color color) /*-{
        this.color = color;
    }-*/;

}
