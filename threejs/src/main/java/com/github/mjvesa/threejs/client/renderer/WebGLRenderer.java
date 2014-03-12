package com.github.mjvesa.threejs.client.renderer;


public class WebGLRenderer extends Renderer {
    
    protected WebGLRenderer() {}
    
    public final static native WebGLRenderer getInstance() /*-{
        return new $wnd.THREE.WebGLRenderer();
    }-*/;
}
