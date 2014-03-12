package com.github.mjvesa.threejs.client.util;

import com.github.mjvesa.threejs.client.texture.Texture;

public class ImageUtils {

    public static interface OnTextureLoad {
        public void onLoad(Texture texture);
    }

    public static final native void loadTexture(String url,
            OnTextureLoad onLoadListener) /*-{
		$wnd.THREE.ImageUtils
				.loadTexture(
						url,
						$wnd.THREE.UVMapping(),
						function(texture) {
							onLoadListener.@com.github.mjvesa.threejs.client.util.ImageUtils.OnTextureLoad::onLoad(Lcom/github/mjvesa/threejs/client/texture/Texture;)(texture);
						});

    }-*/;

}
