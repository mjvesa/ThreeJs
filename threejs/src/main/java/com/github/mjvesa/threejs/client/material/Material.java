package com.github.mjvesa.threejs.client.material;

import com.github.mjvesa.threejs.client.texture.Texture;
import com.google.gwt.core.client.JavaScriptObject;

public class Material extends JavaScriptObject {

    protected Material() {
    }

    public final native void setTexture(Texture texture) /*-{
                this.map = texture;
                this.map.needsUpdate = true;
                this.needsUpdate = true;
    }-*/;


}
