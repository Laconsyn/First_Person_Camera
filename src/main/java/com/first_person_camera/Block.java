package com.first_person_camera;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

public class Block extends Box {
    Color hoverColor = Color.RED;

    public Block(Color color){
        super(1,1,1);
        setColor(color);

        //Hover animation
        setOnMouseEntered(e -> setColor(hoverColor));
        setOnMouseExited(e -> setColor(color));
    }

    private void setColor(Color color){
        setMaterial(new PhongMaterial(color));
    }

}
