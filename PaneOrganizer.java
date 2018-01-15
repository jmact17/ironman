package Cartoon;

import javafx.event.*;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.application.Platform;
import javafx.scene.text.Font;

/**
 * This top-level class organizes the layout of the program and creates everything in the informational pane on the left.
 * It contains the Cartoon class.
 */

public class PaneOrganizer {

    private BorderPane _root;
    private Label _speedLabel;
    private Cartoon _cartoon;

    public PaneOrganizer() {
        _root = new BorderPane();
        this.createLeftPane();
        _cartoon = new Cartoon(_root, _speedLabel);
    }

    /**
     * This method returns the root BorderPane, used in App's start method.
     */
    public Pane getRoot() {
        return _root;
    }

    /**
     * This method creates the section on the left of the window that contains instructions, the dynamic label, and the Quit and Night/Day buttons.
     * It is private since no other class needs to access it.
     */
    private void createLeftPane() {
        VBox leftPane = new VBox();
        leftPane.setPrefSize(Constants.LEFTPANE_WIDTH, Constants.LEFTPANE_HEIGHT);
        leftPane.setStyle("-fx-background-color: #383838;");
        _root.setLeft(leftPane);
        // Make labels
        Label title = new Label("IRON MAN");
        title.setFont(new Font("Impact", 30));
        title.setTextFill(Color.RED);
        Label instructions = new Label("Iron Man needs some flying practice!");
        instructions.setTextFill(Color.WHITE);
        Label instructions1 = new Label("Use the left and right arrows to move and");
        instructions1.setTextFill(Color.WHITE);
        Label instructions2 = new Label("the up and down arrows to change speed.");
        instructions2.setTextFill(Color.WHITE);
        Label instructions3 = new Label("Press the space bar to shoot lasers!");
        instructions3.setTextFill(Color.WHITE);
        _speedLabel = new Label("Speed: 200 mph");
        _speedLabel.setTextFill(Color.WHITE);
        // Make buttons
        Button quit = new Button("Quit");
        Button night = new Button("Night/Day");
        leftPane.getChildren().addAll(title, instructions, instructions1, instructions2, instructions3, _speedLabel, night, quit);
        quit.setOnAction(new QuitHandler());
        night.setOnAction(new ClickHandler());
        quit.setFocusTraversable(false);
        night.setFocusTraversable(false);
        leftPane.setAlignment(Pos.CENTER);
        leftPane.setSpacing(20);
    }

    /**
     * This class implements the EventHandler that exits the program when the Quit button is clicked.
     * It is private since no other class besides PaneOrganizer would need to access it. 
     */
    private class QuitHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent click) {
            Platform.exit();
            click.consume();
        }
    }
    
    /**
     * This class implements the EventHandler that changes the color of the sky and clouds when the Night/Day button is clicked.
     * It is private since no other class besides PaneOrganizer would need to access it.
     */
    private class ClickHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent click) {
            _cartoon.nightSky();
            _cartoon.nightCloud();
            click.consume();
        }
    }

}
