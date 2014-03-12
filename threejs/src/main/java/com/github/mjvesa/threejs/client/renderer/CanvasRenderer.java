package com.github.mjvesa.threejs.client.renderer;

public class CanvasRenderer extends Renderer {

    protected CanvasRenderer() {
    }

    public final static native CanvasRenderer getInstance() /*-{
		return new $wnd.THREE.CanvasRenderer();
    }-*/;

}
