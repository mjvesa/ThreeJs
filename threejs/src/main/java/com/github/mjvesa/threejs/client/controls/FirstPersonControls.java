package com.github.mjvesa.threejs.client.controls;

import com.github.mjvesa.threejs.client.camera.Camera;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;

public class FirstPersonControls extends Controls {

    protected FirstPersonControls() {
    }

    public final static native FirstPersonControls getInstance(Camera camera, Element element) /*-{
                var controls = new $wnd.THREE.FirstPersonControls(camera, element); 
		return controls; 
    }-*/;


}
