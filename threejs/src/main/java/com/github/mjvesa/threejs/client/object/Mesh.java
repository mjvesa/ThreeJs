package com.github.mjvesa.threejs.client.object;

import com.github.mjvesa.threejs.client.material.Material;
import com.google.gwt.core.client.JavaScriptObject;

public class Mesh extends BaseObject {

    protected Mesh() {
    }

    public static final native Mesh getInstance() /*-{
        return {};
     }-*/;
                

    // public final native setGeometry()

    public final native void setMaterial(Material material) /*-{
		this.traverse(function(child) {
			if (child instanceof $wnd.THREE.Mesh) {
				child.material = material;
			}
		});
    }-*/;

}
