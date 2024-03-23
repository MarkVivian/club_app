package org.example.projo;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.*;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Profile {

    static Stage window;

    static Circle profpic;
    static HBox name;
    static HBox about;
    static VBox phone;


    public static void display(){
        window = new Stage();
        window.setTitle("Profile window");
        window.initModality(Modality.APPLICATION_MODAL);
        VBox layout = new VBox();
        layout.setStyle("-fx-background-color: #666666; ");
        profpic = new Circle();
        profpic.setRadius(100);
        Image profileIcon = new Image("file:images_used/belligol.jpg");
        ImagePattern profileProfile = new ImagePattern(profileIcon, 0, 0, 1, 1, true);
        profpic.setFill(profileProfile);
        Label myName  = new Label("Brian Kaleli");
        myName.setFont(new Font("System Bold", 17));
        myName.setTextFill(Color.LIGHTBLUE);
        Label myABout = new Label("About: Calma");
        myABout.setTextFill(Color.WHITE);
        Label myPhone = new Label("Phone Number:");
        myPhone.setFont(new Font("System Bold", 17));
        myPhone.setTextFill(Color.LIGHTBLUE);
        Label number = new Label("+254 720128694");
        name = new HBox();
        name.getChildren().addAll(myName);
        about = new HBox();
        about.getChildren().add(myABout);
        phone = new VBox();
        phone.setSpacing(10);
        phone.getChildren().addAll(myPhone, number);
        layout.getChildren().addAll(profpic, name, about,phone);
        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(20);
        layout.setPadding(new Insets(20, 20, 20 , 20));
        layout.setPrefHeight(Region.USE_COMPUTED_SIZE);
        layout.setPrefWidth(Region.USE_COMPUTED_SIZE);
        layout.setMinWidth(300);
        layout.setMinHeight(350);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();

    }
}
