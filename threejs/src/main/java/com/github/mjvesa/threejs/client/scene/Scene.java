package com.github.mjvesa.threejs.client.scene;

import com.github.mjvesa.threejs.client.light.Light;
import com.github.mjvesa.threejs.client.object.BaseObject;
import com.google.gwt.core.client.JavaScriptObject;

public class Scene extends JavaScriptObject {
    
    protected Scene() {}
    
    public final native static Scene getInstance()  /*-{
        var scene = new $wnd.THREE.Scene();
        return scene;
    }-*/;
    
    
    public final native void addLight(Light light)  /*-{
        this.add(light);
    }-*/;

    public final native void addObject(BaseObject object)  /*-{
        this.add(object);
    }-*/;

    // TODO

}
