package com.github.mjvesa.threejs.client.controls;

import com.google.gwt.core.client.JavaScriptObject;

public class TrackballControls extends Controls {

    protected TrackballControls() {
    }

    public final static native TrackballControls getInstance(JavaScriptObject camera) /*-{
		return new $wnd.THREE.TrackballControls(camera);

    }-*/;


}
