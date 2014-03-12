package com.github.mjvesa.threejs.client.material;

import com.github.mjvesa.threejs.client.material.Material;
import com.github.mjvesa.threejs.client.texture.Texture;

public class MeshPhongMaterial extends Material {

    protected MeshPhongMaterial() {
    }

    public final static native MeshPhongMaterial getInstance(
            int ambientValue, int colorValue, int specularValue,
            int shininessValue) /*-{
		return new $wnd.THREE.MeshPhongMaterial({
			ambient : ambientValue,
			color : colorValue,
			specular : specularValue,
			shininess : shininessValue
		});
    }-*/;
    

}
