package com.github.mjvesa.threejs.client.object;

import com.github.mjvesa.threejs.client.material.Material;
import com.github.mjvesa.threejs.client.scene.Scene;

public class Obj extends Mesh {

    
    public static interface OnObjLoad {
        public void onLoad(Obj obj);
    }
    
    protected Obj() {
    }

    public final static native void loadObj(String url, OnObjLoad onLoad) /*-{

		var loader = new $wnd.THREE.OBJLoader();
		var that = this;
		loader.load(url, function(object) {
			onLoad.@com.github.mjvesa.threejs.client.object.Obj.OnObjLoad::onLoad(Lcom/github/mjvesa/threejs/client/object/Obj;)(object);
		});
    }-*/;

}
