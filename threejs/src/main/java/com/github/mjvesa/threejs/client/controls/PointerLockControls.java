package com.github.mjvesa.threejs.client.controls;

import com.github.mjvesa.threejs.client.camera.Camera;
import com.google.gwt.core.client.JavaScriptObject;

public class PointerLockControls extends Controls {

    protected PointerLockControls() {
    }

    public final static native PointerLockControls getInstance(Camera camera) /*-{
		return new $wnd.THREE.PointerLockControls(camera);

    }-*/;


}
