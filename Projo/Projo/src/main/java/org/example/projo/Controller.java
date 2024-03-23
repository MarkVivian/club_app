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

import java.lang.reflect.Array;
import java.util.Arrays;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;


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
        Image searchIcon = new Image("file:images_used/search_icon/icons8-search-100.png");
        ImagePattern searchProfile = new ImagePattern(searchIcon, 0, 0, 1, 1, true);
        search.setFill(searchProfile);
        search.setCursor(Cursor.HAND);

        searchBar.getChildren().add(search);

        Circle chats = new Circle();
        chats.setRadius(20);
        chats.setOnMouseClicked(e -> onStatusClick());
        Image chatsIcon = new Image("file:images_used/chat_icon/icons8-chat-100.png");
        ImagePattern chatsProfile = new ImagePattern(chatsIcon, 0, 0, 1, 1, true);
        chats.setFill(chatsProfile);
        chats.setCursor(Cursor.HAND);



        Circle statuses = new Circle();
        statuses.setRadius(20);
        statuses.setOnMouseClicked(e -> onChatClick());
        Image statusIcon = new Image("file:images_used/status_icon/icons8-status-100.png");
        ImagePattern statusProfile = new ImagePattern(statusIcon, 0, 0, 1, 1, true);
        statuses.setFill(statusProfile);
        statuses.setCursor(Cursor.HAND);


        Region sideSpacer = new Region();
        sideSpacer.setPrefHeight(USE_COMPUTED_SIZE);
        sideSpacer.setPrefWidth(USE_COMPUTED_SIZE);

        Circle settings = new Circle();
        settings.setRadius(20);
        Image settingsIcon = new Image("file:images_used/settings_icon/icons8-settings-100.png");
        ImagePattern settingsProfile = new ImagePattern(settingsIcon, 0, 0, 1, 1, true);
        settings.setFill(settingsProfile);
        settings.setCursor(Cursor.HAND);


        Circle profile = new Circle();
        profile.setRadius(20);
        profile.setOnMouseClicked(e -> Profile.display());
        Image profileIcon = new Image("file:images_used/belligol.jpg");
        ImagePattern profileProfile = new ImagePattern(profileIcon, 0, 0, 1, 1, true);
        profile.setFill(profileProfile);
        profile.setCursor(Cursor.HAND);


        sideBar.getChildren().addAll(chats, statuses, sideSpacer, settings, profile);
        sideBar.setSpacing(10);
        VBox.setVgrow(sideSpacer, Priority.ALWAYS);
    }


    //When you click chat icon
    public void onStatusClick() {
        //setting middle space to show clubs
        clubList.getChildren().clear();
        middleTitle.getChildren().clear();
        messageSide.getChildren().clear();


        Label clubsLabel = new Label("Clubs");
        clubsLabel.setTextFill(Color.WHITE);
        clubsLabel.setFont(new Font("SansSerif Bold", 23.0));
        Region space = new Region();
        Circle circle = new Circle(15.0);
        Image chatsIcon = new Image("file:images_used/add_chat/icons8-add-to-chat-100.png");
        ImagePattern chatsProfile = new ImagePattern(chatsIcon, 0, 0, 1, 1, true);
        circle.setFill(chatsProfile);
        middleTitle.getChildren().addAll(clubsLabel,space,circle);
        HBox.setHgrow(space, Priority.ALWAYS);
        middleTitle.setPadding(new Insets(5.0));


        Clubs[] Parties = Clubs.manyClubs();

        Arrays.stream(Parties)
                .map(Club -> {
                    HBox hBox = new HBox();
                    Label clubName = new Label(Club.getName());
                    Label lastMessage = new Label(Club.getMessage());
                    clubName.setTextFill(Color.ALICEBLUE);
                    lastMessage.setTextFill(Color.ALICEBLUE);
                    Circle profiler = new Circle();
                    Image another = new Image(Club.getLogo());
                    ImagePattern oneMore = new ImagePattern(another, 0, 0, 1, 1, true);
                    profiler.setRadius(20);
                    profiler.setFill(oneMore);
                    VBox right = new VBox();
                    right.getChildren().addAll(clubName, lastMessage);
                    hBox.getChildren().addAll(profiler, right);
                    hBox.setSpacing(20);
                    right.setSpacing(2);
                    hBox.setCursor(Cursor.HAND);
                    hBox.setOnMouseClicked(e -> {
                        openingChats(Club.getName(), Club.getMessage(), Club.getLogo());
                    });

                    return hBox;
                }).forEach(
                        hBox -> {
                            clubList.getChildren().add(hBox);
                        }
                );





    }

    //when sending a text
    public void onSenderClicked() {
        if (!newMessages.getText().isEmpty()) {
            HBox textMessage = new HBox();
            textMessage.setPrefHeight(USE_COMPUTED_SIZE);
            textMessage.setPrefWidth(USE_COMPUTED_SIZE);
            Label theText = new Label(newMessages.getText());
            theText.setTextFill(Color.ALICEBLUE);
            theText.setFont(new Font("System", 14));
            Circle myProfile = new Circle();
            VBox labelSide = new VBox();
            labelSide.setPrefHeight(USE_COMPUTED_SIZE);
            labelSide.setPrefWidth(USE_COMPUTED_SIZE);
            labelSide.setAlignment(Pos.CENTER);
            labelSide.getChildren().add(theText);
            myProfile.setRadius(20);

            Image mePic = new Image("file:images_used/belligol.jpg");
            ImagePattern mePicPat = new ImagePattern(mePic, 0, 0, 1, 1, true);
            myProfile.setFill(mePicPat);
            textMessage.setSpacing(15);
            Region spacer = new Region();
            spacer.setPrefHeight(USE_COMPUTED_SIZE);
            spacer.setPrefWidth(USE_COMPUTED_SIZE);
            textMessage.getChildren().addAll(spacer, labelSide, myProfile);
            HBox.setHgrow(spacer, Priority.ALWAYS);
            textMessage.setSpacing(10);
            textMessage.setPadding(new Insets(5, 5, 5, 5));
            HBox.setMargin(textMessage, new Insets(5, 5, 5, 5));

            theRealText.getChildren().add(textMessage);
            newMessages.clear();

        }
    }

    //when you click the status icon
    public void onChatClick(){

        messageSide.getChildren().clear();

        middleTitle.getChildren().clear();
        Label clubsLabel = new Label("Status");
        clubsLabel.setTextFill(Color.WHITE);
        clubsLabel.setFont(new Font("SansSerif Bold", 23.0));
        Region space = new Region();
        Circle circle = new Circle(15.0);
        Image statusIcon = new Image("file:images_used/status_icon/icons8-status-100.png");
        ImagePattern statusProfile = new ImagePattern(statusIcon, 0, 0, 1, 1, true);
        circle.setFill(statusProfile);
        middleTitle.getChildren().addAll(clubsLabel,space,circle);
        HBox.setHgrow(space, Priority.ALWAYS);
        middleTitle.setPadding(new Insets(5.0));


        clubList.getChildren().clear();


        Status[] allStatus = Status.manyStatus();

        Arrays.stream(allStatus).
                map(status -> {
                    HBox hBox = new HBox();
                    Label clubName = new Label(status.getName());
                    Label lastMessage = new Label(status.getTime());
                    clubName.setTextFill(Color.ALICEBLUE);
                    lastMessage.setTextFill(Color.ALICEBLUE);
                    Image profPic = new Image(status.getImage());
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
                    hBox.setOnMouseClicked(e -> {
                        openingStatus(status.getName(), status.getImage());
                    });

                    return hBox;
                }).forEach(
                        hBox -> {
                            clubList.getChildren().add(hBox);
                        }
                );



    }

    //when you click on a club to open its texts
    public void openingChats(String theClub, String lastText, String imagePath){
        //Setting the messages side to show messages
        messageSide.getChildren().clear();

        VBox messageSides = new VBox(5.0); // Create main VBox with 5px spacing
        messageSides.setPadding(new Insets(5.0)); // Set padding for all sides

        // Club Name Label
        Label clubNameLabel = new Label(theClub);
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

        Circle profilePicture = new Circle();
        profilePicture.setRadius(20);
        Image pic = new Image(imagePath);
        ImagePattern picture = new ImagePattern(pic, 0, 0, 1, 1, true);
        profilePicture.setFill(picture);

        Label messageLabel = new Label(lastText);
        messageLabel.setFont(new Font("System", 14));
        messageLabel.setTextFill(Color.WHITE);
        VBox.setMargin(messageLabel, new Insets(5.0, 5.0, 5.0, 5.0));

        VBox messageVBox = new VBox(messageLabel);
        messageVBox.setAlignment(Pos.CENTER_LEFT);


        Region spacer = new Region();


        messageHBox.getChildren().addAll(profilePicture, messageVBox, spacer);
        messageHBox.setHgrow(spacer, Priority.ALWAYS);
        messageHBox.setHgrow(messageVBox, Priority.ALWAYS);

        theRealText.getChildren().add(messageHBox);

        // Input and Send Button HBox
        HBox inputHBox = new HBox(20.0);
        inputHBox.setPadding(new Insets(5.0, 5.0, 5.0, 5.0));
        inputHBox.setAlignment(Pos.CENTER);

        newMessages = new TextField();
        newMessages.setPromptText("New message ...");
        newMessages.setStyle("-fx-background-color: #666666; -fx-background-radius: 10;");



        Circle sendButton = new Circle(20);
        Image sendIcon = new Image("file:images_used/send.png");
        ImagePattern senderIcon = new ImagePattern(sendIcon, 0, 0, 1, 1, true);
        sendButton.setFill(senderIcon);
        sendButton.setOnMouseClicked(event -> onSenderClicked());
        sendButton.setCursor(Cursor.HAND);


        Circle microphone = new Circle();
        microphone.setRadius(15);
        Image micIcon = new Image("file:images_used/microphone_icon/icons8-microphone-100.png");
        ImagePattern micPattern = new ImagePattern(micIcon, 0, 0, 1, 1, true);
        microphone.setFill(micPattern);
        microphone.setCursor(Cursor.HAND);

        Circle attach = new Circle();
        attach.setRadius(15);
        Image attachIcon = new Image("file:images_used/clip_icon/icons8-clip-100.png");
        ImagePattern attachPattern = new ImagePattern(attachIcon, 0, 0, 1, 1, true);
        attach.setFill(attachPattern);
        attach.setCursor(Cursor.HAND);

        Circle emoji = new Circle();
        emoji.setRadius(15);
        Image emojiIcon = new Image("file:images_used/crazy_icon/icons8-crazy-100.png");
        ImagePattern emojiPattern = new ImagePattern(emojiIcon, 0, 0, 1, 1, true);
        emoji.setFill(emojiPattern);
        emoji.setCursor(Cursor.HAND);



        inputHBox.getChildren().addAll( emoji, newMessages, microphone, attach, sendButton);

        HBox.setHgrow(newMessages, Priority.ALWAYS);

        Region newRegion = new Region();

        // Add all elements to main VBox
        messageSides.getChildren().addAll(clubNameHBox, separator, theRealText, newRegion, inputHBox);
        messageSides.setVgrow(newRegion, Priority.ALWAYS);

        messageSide.getChildren().add(messageSides);
        messageSide.setVgrow(messageSides, Priority.ALWAYS);
    }

    //when you click on a club to open its status
    public void openingStatus(String theHolder, String whatsPosted){
        messageSide.getChildren().clear();
        VBox messageSider = new VBox();
        messageSider.setId("messageSide");
        messageSider.setPadding(new Insets(5, 5, 5, 5));
        HBox titleBox = new HBox();
        titleBox.setAlignment(Pos.TOP_CENTER);
        Label titleLabel = new Label(theHolder);
        titleLabel.setTextFill(Color .WHITE);
        titleLabel.setFont(new Font("System Bold", 21.0));
        titleBox.getChildren().add(titleLabel);
        Separator separator = new Separator();
        separator.setPrefWidth(200);
        separator.setOpacity(0.14);
        separator.setStyle("-fx-background-color: #666666;");


        HBox statusHolding = new HBox();
        String styles = "-fx-background-image: url(" + whatsPosted + ");" + "-fx-background-repeat: stretch;" + "-fx-background-size: cover;";
        statusHolding.setStyle(styles);
        statusHolding.setMinHeight(600);
        statusHolding.setPrefWidth(600);

        messageSider.getChildren().addAll(titleBox, separator, statusHolding);
        messageSider.setSpacing(20);
        VBox.setVgrow(statusHolding, Priority.ALWAYS);
        HBox.setHgrow(messageSider, Priority.ALWAYS);

        messageSide.getChildren().add(messageSider);
        VBox.setVgrow(messageSider, Priority.ALWAYS);
    }

}