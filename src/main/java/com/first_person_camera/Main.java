package com.first_person_camera;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.robot.Robot;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Screen;
import javafx.stage.Stage;

import static javafx.scene.input.KeyCode.*;

public class Main extends Application{

    public static Group group = new Group();
    public static double[] sceneSize = {1280, 720};
    Subscene3D subscene3D;
    Stage stage;

    double hSensitivity = 0.75;
    double vSensitivity = 0.02;
    double movingSpeed = 1.0;

    KeyCode keyExit = ESCAPE;

    public static void main(String[] args){launch();}

    @Override
    public void start(Stage stage){
        this.stage = stage;
        try{
            Scene scene = createScene();

            stage.setTitle("First Person Camera");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public Scene createScene(){
        //subscene
        Group3D group3D = new Group3D();
        subscene3D = new Subscene3D(group3D);
        group.getChildren().add(subscene3D);

        //label
        Label name = new Label("Press esc to Exit");
        group.getChildren().add(name);

        //scene
        Scene scene = new Scene(group, sceneSize[0], sceneSize[1], true);

        //Event Listeners
        //Key Pressed Command
        scene.setOnKeyPressed(e -> {
            Walk(e.getCode());
            keyCommand(e.getCode());
        });

        //Camera Rotate By Mouse Move
        scene.setOnMouseMoved(e -> onMouseMove());

        //Hide Cursor
        scene.setOnMouseEntered(e -> scene.setCursor(Cursor.NONE));

        return scene;
    }

    private void onMouseMove(){
        Robot r = new Robot();
        double newX = r.getMouseX();
        double newY = r.getMouseY();
        double centerX = getScreenCenterX();
        double centerY = getScreenCenterY();

        //Consume Mouse Move From Robot
        if(Math.round(newX) == Math.round(centerX) &&
                Math.round(newY) == Math.round(centerY)){
            return;
        }

        //Horizontal Rotate
        if(newX > centerX){
            cameraRotate('r', newX - centerX);
        } else if(centerX > newX){
            cameraRotate('l', centerX - newX);
        }

        //Vertical Rotate
        if(newY > centerY){
            cameraRotate('d', newY - centerY);
        } else if(centerY > newY){
            cameraRotate('u', centerY - newY);
        }

        r.mouseMove(centerX, centerY);
    }

    private void cameraRotate(char direction, double distance){
        PerspectiveCamera camera = getCamera();
        Rotate r = (Rotate) camera.getTransforms().get(2);
        Affine a = (Affine) camera.getTransforms().get(4);

        switch (direction){
            case 'u':
                a.setMyz(a.getMyz() - vSensitivity*distance);
                break;

            case 'd':
                a.setMyz(a.getMyz() + vSensitivity*distance);
                break;

            case 'l':
                if(r.getAngle() <=-360){
                    r.setAngle(r.getAngle() + 360);
                }
                r.setAngle(r.getAngle() - hSensitivity*distance);
                break;

            case 'r':
                if(r.getAngle() >=360){
                    r.setAngle(r.getAngle() - 360);
                }
                r.setAngle(r.getAngle() + hSensitivity*distance);
                break;

            default:
                System.out.println("Unknown Input");
                break;
        }

    }

    private void Walk(KeyCode code){
        PerspectiveCamera camera = getCamera();
        Rotate r = (Rotate)camera.getTransforms().get(2);
        Translate t = (Translate) camera.getTransforms().get(0);

        double xScale = 0;
        double zScale = 0;
        double pi = Math.PI;

        switch (code){
            case W:
                zScale = Math.cos(r.getAngle()*pi/180);
                xScale = Math.sin(r.getAngle()*pi/180);
                break;
            case S:
                zScale = Math.cos(r.getAngle()*pi/180) * -1;
                xScale = Math.sin(r.getAngle()*pi/180) * -1;
                break;
            case A:
                zScale = Math.cos((r.getAngle()-90)*pi/180);
                xScale = Math.sin((r.getAngle()-90)*pi/180);
                break;
            case D:
                zScale = Math.cos((r.getAngle()+90)*pi/180);
                xScale = Math.sin((r.getAngle()+90)*pi/180);
                break;
        }

        //update camera location
        t.setX(t.getX() + xScale * movingSpeed);
        t.setZ(t.getZ() + zScale * movingSpeed);
    }

    private void keyCommand(KeyCode code){
        //Key to Exit Stage
        if(!code.equals(keyExit)){return;}
                stage.close();
    }

    private double getScreenCenterX(){
        Rectangle2D screen = Screen.getPrimary().getBounds();
        return screen.getWidth()/2;
    }

    private double getScreenCenterY(){
        Rectangle2D screen = Screen.getPrimary().getBounds();
        return screen.getHeight()/2;
    }

    private PerspectiveCamera getCamera(){
        return (PerspectiveCamera) subscene3D.getCamera();
    }
}
