package com.github.mjvesa.threejs.client;

import java.util.HashMap;

import com.github.mjvesa.threejs.client.camera.Camera;
import com.github.mjvesa.threejs.client.camera.PerspectiveCamera;
import com.github.mjvesa.threejs.client.controls.TrackballControls;
import com.github.mjvesa.threejs.client.light.DirectionalLight;
import com.github.mjvesa.threejs.client.light.Light;
import com.github.mjvesa.threejs.client.material.Material;
import com.github.mjvesa.threejs.client.material.MeshBasicMaterial;
import com.github.mjvesa.threejs.client.material.MeshPhongMaterial;
import com.github.mjvesa.threejs.client.object.Mesh;
import com.github.mjvesa.threejs.client.object.Obj;
import com.github.mjvesa.threejs.client.object.Obj.OnObjLoad;
import com.github.mjvesa.threejs.client.renderer.CanvasRenderer;
import com.github.mjvesa.threejs.client.renderer.Renderer;
import com.github.mjvesa.threejs.client.renderer.WebGLRenderer;
import com.github.mjvesa.threejs.client.scene.Scene;
import com.github.mjvesa.threejs.client.texture.Texture;
import com.github.mjvesa.threejs.client.util.ImageUtils;
import com.google.gwt.animation.client.AnimationScheduler;
import com.google.gwt.animation.client.AnimationScheduler.AnimationCallback;
import com.google.gwt.animation.client.AnimationScheduler.AnimationHandle;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.Widget;

public class ThreeJsWidget extends Widget {

    private HashMap<String, Light> lights = new HashMap<String, Light>();
    private Camera camera;
    private Renderer renderer;
    private Scene scene;
    private TrackballControls controls;
    private Element root;

    public ThreeJsWidget() {
        root = Document.get().createDivElement();
        setElement(root);
        setStyleName("threejs");

    }

    public void init() {
        scene = Scene.getInstance();
        camera = PerspectiveCamera.getInstance(root, root.getOffsetWidth()
                / root.getOffsetHeight());
        camera.setPosition(0, 0, 2);
        
        renderer = WebGLRenderer.getInstance();
        renderer.setSize(root.getOffsetWidth(), root.getOffsetHeight());
        root.appendChild(renderer.getDOMElement());
        controls = TrackballControls.getInstance(camera);
    }

    public void startAnimation() {
        AnimationHandle animation = AnimationScheduler.get()
                .requestAnimationFrame(new AnimationCallback() {
                    @Override
                    public void execute(double timestamp) {
                        controls.update();
                        renderer.render(scene, camera);
                        AnimationScheduler.get().requestAnimationFrame(this);
                    }

                });

    }

    public void loadObj(String url) {
        Obj.loadObj(url, new OnObjLoad() {

            @Override
            public void onLoad(Obj obj) {
                scene.addObject(obj);
            }
        });

    }

    public void loadObjWithTexture(String url, final String texUrl) {

        final MeshBasicMaterial material = MeshBasicMaterial.getInstance();
        loadObjWithMaterial(url, texUrl, material);
    }

    public void loadObjWithTextureAndPhong(String url, final String texUrl, int ambient, int color,
            int specular, int shininess) {

        final MeshPhongMaterial material = MeshPhongMaterial.getInstance(ambient, color, specular, shininess);
        loadObjWithMaterial(url, texUrl, material);
        
    }
    
    private void loadObjWithMaterial(String url, final String texUrl, final Material material) {


        Obj.loadObj(url, new OnObjLoad() {

            @Override
            public void onLoad(final Obj obj) {

                ImageUtils.loadTexture(texUrl,
                        new ImageUtils.OnTextureLoad() {
                            @Override
                            public void onLoad(Texture texture) {
                                material.setTexture(texture);
                                obj.setMaterial(material);
                                scene.addObject(obj);

                            }
                        });
            };

        });
    }
    
    public void addDirectionalLight(String id, int hexColor, double intensity) {
        DirectionalLight light = DirectionalLight.getInstance(0xffffff, 0.5);
        scene.addLight(light);
        lights.put(id, light);
        
    }
    
    public void setDirectionalLightPosition(String id, double x, double y, double z) {
            DirectionalLight light = (DirectionalLight) lights.get(id);
            if (light != null) {
                light.setPosition(x, y, z);
            }
    }

}