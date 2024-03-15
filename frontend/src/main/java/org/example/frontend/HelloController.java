package org.example.frontend;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Font;

import java.util.Objects;

public class HelloController {


    public Circle chatCircle;
    public Circle statusCircle;
    public Circle settingsCircle;
    public Circle profileCircle;
    public Circle appCircle;

    public void initialize(){

            Image chatsIcon = new Image("file:C:/Users/User/Githubs/club_app/frontend/images_used/chat_icon/icons8-chat-100.png");
            ImagePattern chatsProfile = new ImagePattern(chatsIcon, 0, 0, 1, 1, true);
            Image statusIcon = new Image("file:C:/Users/User/Githubs/club_app/frontend/images_used/status_icon/icons8-status-100.png");
            ImagePattern statusProfile = new ImagePattern(statusIcon, 0, 0, 1, 1, true);
            Image settingsIcon = new Image("file:C:/Users/User/Githubs/club_app/frontend/images_used/settings_icon/icons8-settings-100.png");
            ImagePattern settingsProfile = new ImagePattern(settingsIcon, 0, 0, 1, 1, true);
            Image profileIcon = new Image("file:C:/Users/User/Githubs/club_app/frontend/images_used/user_icon.jpg");
            ImagePattern profileProfile = new ImagePattern(profileIcon, 0, 0, 1, 1, true);
            Image appIcon = new Image("file:C:/Users/User/Githubs/club_app/frontend/images_used/current_logo.jpg");
            ImagePattern appProfile = new ImagePattern(appIcon, 0, 0, 1, 1, true);

            chatCircle.setFill(chatsProfile);
            statusCircle.setFill(statusProfile);
            settingsCircle.setFill(settingsProfile);
            profileCircle.setFill(profileProfile);
            appCircle.setFill(appProfile);
        }



}