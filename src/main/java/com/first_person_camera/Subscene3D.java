package com.first_person_camera;

import javafx.scene.SceneAntialiasing;
import javafx.scene.Group;
import javafx.scene.SubScene;

public class Subscene3D extends SubScene {
    static Camera camera = new Camera();

    public Subscene3D(Group group3D) {
        super(group3D, Main.sceneSize[0], Main.sceneSize[1], true, SceneAntialiasing.BALANCED);

        //camera
        setCamera(camera);
    }
}
