package com.first_person_camera;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.transform.Translate;

public class Group3D extends Group {
    public Group3D(){
        //floor
        for(int x=-20;x<20;x++){
            for(int z=-20;z<20;z++){
                Block b = new Block(Color.YELLOW);
                b.getTransforms().setAll(new Translate(x,0,z));
                getChildren().add(b);
            }
        }

        //wall in front
        for(int x=-20;x<20;x++){
            for(int y=0;y>-10;y--){
                Block c = new Block(Color.SKYBLUE);
                c.getTransforms().setAll(new Translate(x,y,20));
                getChildren().add(c);
            }
        }
        //wall behind
        for(int x=-20;x<20;x++){
            for(int y=0;y>-10;y--){
                Block c = new Block(Color.ORANGE);
                c.getTransforms().setAll(new Translate(x,y,-20));
                getChildren().add(c);
            }
        }

        //wall at left
        for(int z=-20;z<20;z++){
            for(int y=0;y>-10;y--){
                Block c = new Block(Color.VIOLET);
                c.getTransforms().setAll(new Translate(20,y,z));
                getChildren().add(c);
            }
        }

        //wall at right
        for(int z=-20;z<20;z++){
            for(int y=0;y>-10;y--){
                Block c = new Block(Color.DARKGREEN);
                c.getTransforms().setAll(new Translate(-20,y,z));
                getChildren().add(c);
            }
        }

        //roof
        for(int x=-20;x<20;x++){
            for(int z=-20;z<20;z++){
                Block b = new Block(Color.SILVER);
                b.getTransforms().setAll(new Translate(x,-10,z));
                getChildren().add(b);
            }
        }

    }

}
