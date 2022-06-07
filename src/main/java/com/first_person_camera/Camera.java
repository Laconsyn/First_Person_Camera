package com.first_person_camera;

import javafx.scene.PerspectiveCamera;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

public class Camera extends PerspectiveCamera {
    int farClip = 100;

        public Camera(){
            super(true);

            setNearClip(0.1);
            setFarClip(farClip);

            getTransforms().add(0, new Translate(0,-2,0));
            getTransforms().add(1, new Rotate(0, Rotate.X_AXIS));
            getTransforms().add(2, new Rotate(0,Rotate.Y_AXIS));
            getTransforms().add(3, new Rotate(0,Rotate.Z_AXIS));
            getTransforms().add(4, new Affine());
        }
}
