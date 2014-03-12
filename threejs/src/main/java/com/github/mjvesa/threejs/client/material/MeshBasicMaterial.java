package com.github.mjvesa.threejs.client.material;

import com.github.mjvesa.threejs.client.material.Material;
import com.github.mjvesa.threejs.client.texture.Texture;

public class MeshBasicMaterial extends Material {

    protected MeshBasicMaterial() {
    }

    public final static native MeshBasicMaterial getInstance() /*-{
		return new $wnd.THREE.MeshBasicMaterial();
    }-*/;
    

}
