package org.example.projo;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.*;
import javafx.scene.shape.Circle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;

import java.util.Arrays;



public class Controller {
    @FXML


    public VBox clubList;

    public VBox messageSide;


    public static VBox theRealText;
    public static TextField newMessages;
    public HBox middleTitle;
    public VBox sideBar;
    public HBox searchBar;


    public void initialize() {
        Circle search = new Circle(10);
        Image searchIcon = new Image("file:/C:/Users/User/projects/javaaa/Projo/images_used/search_icon/icons8-search-100.png");
        ImagePattern searchProfile = new ImagePattern(searchIcon, 0, 0, 1, 1, true);
        search.setFill(searchProfile);
        search.setCursor(Cursor.HAND);

        searchBar.getChildren().add(search);

        Circle chats = new Circle();
        chats.setRadius(20);
        chats.setOnMouseClicked(e -> onStatusClick());
        Image chatsIcon = new Image("file:/C:/Users/User/projects/javaaa/Projo/images_used/chat_icon/icons8-chat-100.png");
        ImagePattern chatsProfile = new ImagePattern(chatsIcon, 0, 0, 1, 1, true);
        chats.setFill(chatsProfile);
        chats.setCursor(Cursor.HAND);



        Circle statuses = new Circle();
        statuses.setRadius(20);
        statuses.setOnMouseClicked(e -> onChatClick());
        Image statusIcon = new Image("file:/C:/Users/User/projects/javaaa/Projo/images_used/status_icon/icons8-status-100.png");
        ImagePattern statusProfile = new ImagePattern(statusIcon, 0, 0, 1, 1, true);
        statuses.setFill(statusProfile);
        statuses.setCursor(Cursor.HAND);


        Region sideSpacer = new Region();
        sideSpacer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        sideSpacer.setPrefWidth(Region.USE_COMPUTED_SIZE);

        Circle settings = new Circle();
        settings.setRadius(20);
        Image settingsIcon = new Image("file:/C:/Users/User/projects/javaaa/Projo/images_used/settings_icon/icons8-settings-100.png");
        ImagePattern settingsProfile = new ImagePattern(settingsIcon, 0, 0, 1, 1, true);
        settings.setFill(settingsProfile);
        settings.setCursor(Cursor.HAND);


        Circle profile = new Circle();
        profile.setRadius(20);
        profile.setOnMouseClicked(e -> Profile.display());
        Image profileIcon = new Image("file:/C:/Users/User/projects/javaaa/Projo/images_used/user_icon.jpg");
        ImagePattern profileProfile = new ImagePattern(profileIcon, 0, 0, 1, 1, true);
        profile.setFill(profileProfile);
        profile.setCursor(Cursor.HAND);


        sideBar.getChildren().addAll(chats, statuses, sideSpacer, settings, profile);
        sideBar.setSpacing(10);
        VBox.setVgrow(sideSpacer, Priority.ALWAYS);
    }



    public void onStatusClick() {
        //setting middle space to show clubs
        clubList.getChildren().clear();
        middleTitle.getChildren().clear();
        Clubs[] Parties = {
                new Clubs("Science and Tech", "We have a meeting scheduled at 6pm tomorrow"),
                new Clubs("Big boys", "We have a meeting scheduled at 6pm tomorrow"),
                new Clubs("Great Engineers", "We have a meeting scheduled at 6pm tomorrow"),
                new Clubs("Teaching peers club", "We have a meeting scheduled at 6pm tomorrow"),
                new Clubs("Economics and business", "We have a meeting scheduled at 6pm tomorrow")
        };



        Label clubsLabel = new Label("Clubs");
        clubsLabel.setTextFill(Color.WHITE);
        clubsLabel.setFont(new Font("SansSerif Bold", 23.0));
        Region space = new Region();
        Circle circle = new Circle(15.0);
        Image chatsIcon = new Image("file:/C:/Users/User/projects/javaaa/Projo/images_used/chat_icon/icons8-chat-100.png");
        ImagePattern chatsProfile = new ImagePattern(chatsIcon, 0, 0, 1, 1, true);
        circle.setFill(chatsProfile);
        middleTitle.getChildren().addAll(clubsLabel,space,circle);
        middleTitle.setHgrow(space, Priority.ALWAYS);
        middleTitle.setPadding(new Insets(5.0));


        Arrays.stream(Parties)
                .map(Club -> {
                    HBox hBox = new HBox();
                    Label clubName = new Label(Clubs.getName());
                    Label lastMessage = new Label(Clubs.getMessage());
                    clubName.setTextFill(Color.ALICEBLUE);
                    lastMessage.setTextFill(Color.ALICEBLUE);
                    Circle profiler = new Circle();
                    profiler.setRadius(20);
                    profiler.setFill(new LinearGradient(
                            0.0, 0.0, 1.0, 0.0, true, CycleMethod.NO_CYCLE,
                            new Stop(0.0, new Color(0.1, 0.2, 0.22, 1.0)),
                            new Stop(0.5, new Color(0.18, 0.31, 0.35, 1.0)),
                            new Stop(1.0, new Color(0.22, 0.41, 0.49, 1.0))));
                    VBox right = new VBox();
                    right.getChildren().addAll(clubName, lastMessage);
                    hBox.getChildren().addAll(profiler, right);
                    hBox.setSpacing(20);
                    right.setSpacing(2);
                    hBox.setCursor(Cursor.HAND);

                    return hBox;
                }).forEach(
                        hBox -> {
                            clubList.getChildren().add(hBox);
                        }
                );


        //Setting the messages side to show messages
        messageSide.getChildren().clear();

        VBox messageSides = new VBox(5.0); // Create main VBox with 5px spacing
        messageSides.setPadding(new Insets(5.0)); // Set padding for all sides

        // Club Name Label
        Label clubNameLabel = new Label("Club Name Goes Here");
        clubNameLabel.setTextFill(Color.WHITE);
        clubNameLabel.setFont(new Font("System Bold", 21.0));
        HBox.setMargin(clubNameLabel, new Insets(5.0, 5.0, 5.0, 5.0));
        clubNameLabel.setPadding(new Insets(5.0, 5.0, 5.0, 5.0));
        HBox clubNameHBox = new HBox(clubNameLabel);

        // Separator
        Separator separator = new Separator();
        separator.setMinHeight(0.0);
        separator.setOpacity(0.18);
        separator.setPrefWidth(0.0);

        // Message List VBox
        theRealText = new VBox(10.0); // Sub-VBox for messages with 10px spacing
        theRealText.setPrefHeight(200.0);
        theRealText.setPrefWidth(100.0);
        theRealText.setFocusTraversable(true);

        // Sample Message HBox
        HBox messageHBox = new HBox(10.0);
        messageHBox.setPadding(new Insets(5.0, 5.0, 5.0, 5.0));
        VBox.setMargin(messageHBox, new Insets(5.0, 5.0, 5.0, 5.0));

        Circle profilePicture = new Circle(21.0, Color.DODGERBLUE);
        profilePicture.setStroke(Color.BLACK);
        profilePicture.setStrokeType(StrokeType.INSIDE);
        profilePicture.setStrokeWidth(0.0);

        Label messageLabel = new Label("This is a message from the club to you");
        messageLabel.setTextFill(Color.WHITE);
        messageLabel.setFont(new Font(11.0));
        VBox.setMargin(messageLabel, new Insets(5.0, 5.0, 5.0, 5.0));

        VBox messageVBox = new VBox(messageLabel);


        Region spacer = new Region();


        messageHBox.getChildren().addAll(profilePicture, messageVBox, spacer);
        messageHBox.setHgrow(spacer, Priority.ALWAYS);
        messageHBox.setHgrow(messageVBox, Priority.ALWAYS);

        theRealText.getChildren().add(messageHBox);  // Add sample message

        // Input and Send Button HBox
        HBox inputHBox = new HBox(20.0);
        inputHBox.setPadding(new Insets(5.0, 5.0, 5.0, 5.0));

        newMessages = new TextField();
        newMessages.setPromptText("New message ...");
        newMessages.setStyle("-fx-background-color: #666666; -fx-background-radius: 10;");



        Button sendButton = new Button("Send");
        sendButton.setTextFill(Color.WHITE);
        sendButton.setOnAction(event -> onSenderClicked()); // Replace with your logic
        sendButton.setStyle("-fx-background-color: #666666; -fx-background-radius: 10;");

        inputHBox.getChildren().addAll(newMessages, sendButton);
        inputHBox.setHgrow(newMessages, Priority.ALWAYS);

        Region newRegion = new Region();

        // Add all elements to main VBox
        messageSides.getChildren().addAll(clubNameHBox, separator, theRealText, newRegion, inputHBox);
        messageSides.setVgrow(newRegion, Priority.ALWAYS);

        messageSide.getChildren().add(messageSides);
        messageSide.setVgrow(messageSides, Priority.ALWAYS);


    }

    //function to create new message side
    public void onSenderClicked() {
        if (!newMessages.getText().isEmpty()) {
            HBox textMessage = new HBox();
            textMessage.setPrefHeight(Region.USE_COMPUTED_SIZE);
            textMessage.setPrefWidth(Region.USE_COMPUTED_SIZE);
            Label theText = new Label(newMessages.getText());
            theText.setTextFill(Color.ALICEBLUE);
            Circle myProfile = new Circle();
            VBox labelSide = new VBox();
            labelSide.setPrefHeight(Region.USE_COMPUTED_SIZE);
            labelSide.setPrefWidth(Region.USE_COMPUTED_SIZE);
            labelSide.setAlignment(Pos.CENTER);
            labelSide.getChildren().add(theText);
            myProfile.setRadius(20);
            myProfile.setFill(new LinearGradient(
                    0.0, 0.0, 1.0, 0.0, true, CycleMethod.NO_CYCLE,
                    new Stop(0.0, new Color(0.1, 0.2, 0.22, 1.0)),
                    new Stop(0.5, new Color(0.18, 0.31, 0.35, 1.0)),
                    new Stop(1.0, new Color(0.22, 0.41, 0.49, 1.0))));
            textMessage.setSpacing(15);
            Region spacer = new Region();
            spacer.setPrefHeight(Region.USE_COMPUTED_SIZE);
            spacer.setPrefWidth(Region.USE_COMPUTED_SIZE);
            textMessage.getChildren().addAll(spacer, labelSide, myProfile);
            HBox.setHgrow(spacer, Priority.ALWAYS);
            textMessage.setSpacing(10);
            textMessage.setPadding(new Insets(5, 5, 5, 5));
            HBox.setMargin(textMessage, new Insets(5, 5, 5, 5));

            theRealText.getChildren().add(textMessage);
            newMessages.clear();

        }
    }

    public void onChatClick(){

        middleTitle.getChildren().clear();
        Label clubsLabel = new Label("Status");
        clubsLabel.setTextFill(Color.WHITE);
        clubsLabel.setFont(new Font("SansSerif Bold", 23.0));
        Region space = new Region();
        Circle circle = new Circle(15.0);
        Image statusIcon = new Image("file:/C:/Users/User/projects/javaaa/Projo/images_used/status_icon/icons8-status-100.png");
        ImagePattern statusProfile = new ImagePattern(statusIcon, 0, 0, 1, 1, true);
        circle.setFill(statusProfile);
        middleTitle.getChildren().addAll(clubsLabel,space,circle);
        HBox.setHgrow(space, Priority.ALWAYS);
        middleTitle.setPadding(new Insets(5.0));


        clubList.getChildren().clear();
        Status[] allStatus = {
                new Status("Science club", "9:10 am"),
                new Status("Science club", "9:10 am"),
                new Status("Science club", "9:10 am")
        };

        Arrays.stream(allStatus).
                map(status -> {
                    HBox hBox = new HBox();
                    Label clubName = new Label(Status.getName());
                    Label lastMessage = new Label(Status.getTime());
                    clubName.setTextFill(Color.ALICEBLUE);
                    lastMessage.setTextFill(Color.ALICEBLUE);
                    Image profPic = new Image("file:/C:/Users/User/projects/javaaa/Projo/images_used/user_icon.jpg");
                    ImagePattern profPattern = new ImagePattern(profPic, 0,0,1,1, true);
                    Circle profiler = new Circle();
                    profiler.setRadius(20);
                    profiler.setFill(profPattern);
                    VBox right = new VBox();
                    right.getChildren().addAll(clubName, lastMessage);
                    hBox.getChildren().addAll(profiler, right);
                    hBox.setSpacing(20);
                    right.setSpacing(2);
                    hBox.setCursor(Cursor.HAND);

                    return hBox;
                }).forEach(
                        hBox -> {
                            clubList.getChildren().add(hBox);
                        }
                );


        messageSide.getChildren().clear();
        VBox messageSider = new VBox();
        messageSider.setId("messageSide");
        messageSider.setPadding(new Insets(5, 5, 5, 5));
        HBox titleBox = new HBox();
        titleBox.setAlignment(Pos.TOP_CENTER);
        Label titleLabel = new Label("Who's Status");
        titleLabel.setTextFill(Color .WHITE);
        titleLabel.setFont(new Font("System Bold", 21.0));
        titleBox.getChildren().add(titleLabel);
        Separator separator = new Separator();
        separator.setPrefWidth(200);
        separator.setOpacity(0.14);
        separator.setStyle("-fx-background-color: #666666;");
        HBox innerVBox = new HBox();
        innerVBox.setMaxHeight(600);
        innerVBox.setMinHeight(300);
        innerVBox.setPrefWidth(Region.USE_COMPUTED_SIZE);
        innerVBox.setStyle("-fx-background-color: grey; -fx-background-radius: 20;");

        ImageView statusImage = new ImageView("file:/C:/Users/User/projects/javaaa/Projo/images/belligol.jpg");
        statusImage.setFitHeight(500);
        statusImage.setFitWidth(600);

        statusImage.preserveRatioProperty();
        innerVBox.getChildren().add(statusImage);
        innerVBox.setAlignment(Pos.CENTER);
        HBox.setHgrow(statusImage, Priority.ALWAYS);
        messageSider.getChildren().addAll(titleBox, separator, innerVBox);
        messageSider.setSpacing(20);
        VBox.setVgrow(innerVBox, Priority.ALWAYS);
        HBox.setHgrow(messageSider, Priority.ALWAYS);

        messageSide.getChildren().add(messageSider);
    }




}