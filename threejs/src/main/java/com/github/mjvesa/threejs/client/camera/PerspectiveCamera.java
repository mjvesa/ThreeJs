package com.github.mjvesa.threejs.client.camera;

import com.google.gwt.dom.client.Element;

public class PerspectiveCamera extends Camera {
    
    protected PerspectiveCamera() {}

    public final static native Camera getInstance(Element parent, double aspectRatio) /*-{
        return new $wnd.THREE.PerspectiveCamera(75, aspectRatio, 0.1, 1000)
    }-*/;

}
