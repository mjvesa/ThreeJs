package com.github.mjvesa.threejs.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import com.github.mjvesa.threejs.client.renderer.Renderer;
import com.github.mjvesa.threejs.client.renderer.WebGLRenderer;
import com.github.mjvesa.threejs.client.scene.Scene;
import com.github.mjvesa.threejs.client.texture.Texture;
import com.github.mjvesa.threejs.client.util.ImageUtils;
import com.google.gwt.animation.client.AnimationScheduler;
import com.google.gwt.animation.client.AnimationScheduler.AnimationCallback;
import com.google.gwt.animation.client.AnimationScheduler.AnimationHandle;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.Widget;

public class ThreeJsWidget extends Widget {

    private static final Mesh OBJ_ADD_REQUESTED = Mesh.getInstance();

    private HashMap<String, Light> lights = new HashMap<String, Light>();
    private HashMap<String, Mesh> objects = new HashMap<String, Mesh>();
    private HashMap<String, Material> materials = new HashMap<String, Material>();
    private HashMap<String, List<Material>> objMaterials = new HashMap<String, List<Material>>();

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

    public void loadObj(final String id, String url) {
        Obj.loadObj(url, new OnObjLoad() {
            @Override
            public void onLoad(Obj obj) {
                Mesh mesh = objects.get(id);
                objects.put(id, obj);
                addPendingMaterialsToMesh(id);
                if (mesh == OBJ_ADD_REQUESTED) {
                    addObj(id);
                }
            }

            private void addPendingMaterialsToMesh(String id) {

                List<Material> materials = objMaterials.get(id);
                if (materials != null) {
                    Mesh mesh = objects.get(id);
                    for (Material material : materials) {
                        mesh.setMaterial(material);
                    }
                }
            }
        });
    }

    public void loadObjWithMtl(final String id, String url) {
        // TODO add the mtl, or sumthin.
        Obj.loadObj(url, new OnObjLoad() {
            @Override
            public void onLoad(Obj obj) {
                objects.put(id, obj);
            }
        });
    }

    public void setMaterialToObject(String materialid, String objid) {
        objects.get(objid).setMaterial(materials.get(objid));
    }

    public void addObj(String id) {
        Mesh mesh = objects.get(id);

        if (mesh != null) {
            if (mesh != OBJ_ADD_REQUESTED) {
                scene.addObject(objects.get(id));
            }
        } else {
            objects.put(id, OBJ_ADD_REQUESTED);
        }
    }

    // // Materials ////

    public void createPhongMaterial(String id, int ambient, int color,
            int specular, int shininess) {

        MeshPhongMaterial material = MeshPhongMaterial.getInstance(ambient,
                color, specular, shininess);
        materials.put(id, material);
    }

    public void createBasicMaterial(String id) {
        materials.put(id, MeshBasicMaterial.getInstance());
    }

    public void loadTextureToMaterial(final String id, String url) {

        ImageUtils.loadTexture(url, new ImageUtils.OnTextureLoad() {
            @Override
            public void onLoad(Texture texture) {
                Material material = materials.get(id);
                material.setTexture(texture);
            }
        });
    }

    // // Lights ////

    public void createDirectionalLight(String id, int hexColor,
            double intensity) {
        DirectionalLight light = DirectionalLight.getInstance(0xffffff, 0.5);
        lights.put(id, light);
    }

    public void addLight(String id) {
        scene.addLight(lights.get(id));
    }

    public void setDirectionalLightPosition(String id, double x, double y,
            double z) {
        DirectionalLight light = (DirectionalLight) lights.get(id);
        if (light != null) {
            light.setPosition(x, y, z);
        }
    }

    public void setMaterialToObj(String materialId, String objId) {

        Mesh obj = objects.get(objId);
        if (obj == null || obj == OBJ_ADD_REQUESTED) {
            addPendingMaterialToObj(materialId, objId);
        } else {
            obj.setMaterial(materials.get(materialId));
        }
    }

    private void addPendingMaterialToObj(String materialId, String objId) {

        List<Material> lst = objMaterials.get(objId);
        if (lst == null) {
            lst = new ArrayList<Material>();
        }

        lst.add(materials.get(materialId));
    }

}