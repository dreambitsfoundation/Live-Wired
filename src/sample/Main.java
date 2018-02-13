package sample;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.input.MouseEvent;
import javafx.stage.Window;

import java.awt.*;

public class Main extends Application {
    private double xOffset = 0;
    private double yOffset = 0;
    private boolean monitor = true;
    @Override
    public void start(Stage primaryStage) throws Exception{

        primaryStage.initStyle(StageStyle.UNDECORATED);

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        EventHandler<MouseEvent> addNewDomain = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("clicked");
            }
        };



        try{
            Button newDomainButton = (Button) root.lookup("#newDomain");
            newDomainButton.addEventFilter(MouseEvent.MOUSE_CLICKED, addNewDomain);

            Pane topPanel = (Pane) root.lookup("#topPanel");
            topPanel.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    xOffset = event.getSceneX();
                    yOffset = event.getSceneY();
                }
            });
            topPanel.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    primaryStage.setX(event.getScreenX() - xOffset);
                    primaryStage.setY(event.getScreenY() - yOffset);
                }
            });

            Button monitorButton = (Button) root.lookup("#monitorButton");
            monitorButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if(monitor == true){
                        monitorButton.setStyle("-fx-background-color: red");
                        monitorButton.setText("Monitor OFF");
                        monitor = false;
                    }else {
                        monitorButton.setStyle("-fx-background-color: limegreen");
                        monitorButton.setText("Monitor ON");
                        monitor = true;
                    }
                }
            });

        }catch(Exception e){
            System.out.println("Not Found");
        }

        primaryStage.setScene(new Scene(root, 1024, 700));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public static class Hosts{
        private final SimpleStringProperty firstName;
        private final SimpleStringProperty lastName;

        private Hosts(String host, String Status){}
    }
}
