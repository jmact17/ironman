package Cartoon;

import javafx.event.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.animation.Animation;
import javafx.util.Duration;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * The Cartoon class contains the _cartoonPane where all the action happens. It instantiates all the shapes that appear in the cartoon.
 * It is responsible for all the user input and animation that occurs in the cartoon, including the Timelines controlling the 
 * clouds and beams as well as the responses to arrow keys, the space bar, and the Night/Day button. 
 */

public class Cartoon {

    private Pane _cartoonPane;
	private IronMan _ironMan;
    private Cloud _cloud1;
    private Cloud _cloud2;
    private Cloud _cloud3;
    private Cloud _cloud4;
    private int _distance;
    private Label _speedLabel;
    private int _speed;
    private Rectangle _leftLaser;
    private Rectangle _rightLaser;
    private boolean _spaceReleased;

    /**
     * Cartoon's constructor takes two parameters so that when it is instantiated by PaneOrganizer, it can be positioned 
     * within the root BorderPane, and PaneOrganizer can create a label containing information from the Cartoon class.
     * All shapes contained in the cartoon are instantiated here: one Iron Man and four clouds.
     */
    public Cartoon(BorderPane root, Label speedLabel) {
        _cartoonPane = new Pane();
        _cartoonPane.setStyle("-fx-background-color: #B7EEFF;");
        root.setCenter(_cartoonPane);
        _speedLabel = speedLabel;
        _ironMan = new IronMan(_cartoonPane);
        _cloud1 = new Cloud(_cartoonPane);
        _cloud2 = new Cloud(_cartoonPane);
        _cloud3 = new Cloud(_cartoonPane);
        _cloud4 = new Cloud(_cartoonPane);
        _spaceReleased = true;
        _cartoonPane.requestFocus();
        _cartoonPane.setFocusTraversable(true);
        _cartoonPane.addEventHandler(KeyEvent.KEY_PRESSED, new SpacePressHandler());
        _cartoonPane.addEventHandler(KeyEvent.KEY_RELEASED, new SpaceReleaseHandler());
        _cartoonPane.addEventHandler(KeyEvent.KEY_PRESSED, new KeyHandler());
        this.moveCloudTimeline();
        this.beamTimeline();
        _distance = Constants.DISTANCE_START;
        _speed = Constants.SPEED_START;
    }

    /**
     * This method instantiates CloudHandler and creates the Timeline responsible for moving the clouds down the screen.
     * It is private since no other class besides Cartoon would need to access it.
     */
    private void moveCloudTimeline() {
 	   KeyFrame kf = new KeyFrame(Duration.millis(20), new CloudHandler());
 	   Timeline cloudTimeline = new Timeline(kf);
       cloudTimeline.setCycleCount(Animation.INDEFINITE);
 	   cloudTimeline.play();
    }

    /**
     * This class implements the EventHandler that moves the clouds down the screen.
     * It is private since no other class besides Cartoon would need to access it. 
     */
    private class CloudHandler implements EventHandler<ActionEvent>{
    	
    	@Override
    	public void handle(ActionEvent moveCloud) {
    		_cloud1.moveDown(_distance);
    		_cloud2.moveDown(_distance);
    		_cloud3.moveDown(_distance);
    		_cloud4.moveDown(_distance);
    		moveCloud.consume();
    	}
    }
    
    /**
     * This method instantiates BeamHandler and creates the Timeline responsible for animating Iron Man's beams.
     * It is private since no other class besides Cartoon would need to access it.
     */
    private void beamTimeline() {
  	   KeyFrame kf = new KeyFrame(Duration.millis(100), new BeamHandler());
       Timeline beamTimeline = new Timeline(kf);
  	   beamTimeline.setCycleCount(Animation.INDEFINITE);
  	   beamTimeline.play();
     }

    /**
     * This class implements the EventHandler that animates Iron Man's beams.
     * It is private since no other class besides Cartoon would need to access it.
     */
    private class BeamHandler implements EventHandler<ActionEvent>{
    	
    	@Override
    	public void handle(ActionEvent flashBeams) {
     		_ironMan.flashBeams();
     		flashBeams.consume();
     	}
    }
    
    /**
     * This class implements the EventHandler that responds to pressing the arrows, 
     * using a switch statement detailing what occurs for each arrow key.
     * It is private since no other class besides Cartoon would need to access it.
     * Left and right arrows move Iron Man horizontally.
     * Up and down arrows change the speed of the clouds and the speed displayed by the speed label.
     */
    private class KeyHandler implements EventHandler<KeyEvent> {
    	
    	@Override
        public void handle(KeyEvent e) {
            KeyCode keyPressed = e.getCode();
            switch (keyPressed) {
                case LEFT: leftArrow();
                	break;
                case RIGHT: rightArrow();
                    break;
                case UP: upArrow();
                    break;
                case DOWN: downArrow();
                	break;
                default:
                    break;
            }
            e.consume();
        }
    }
    
    /**
     * This method, called in KeyHandler, details what happens when the left arrow is pressed. The if statement 
     * prevents _ironMan from moving once it reaches the left border of cartoonPane; otherwise, it moves _ironMan
     * to the left. It is private since no other class needs to access it.
     */
    private void leftArrow() {
    	if (_ironMan.getXLoc() == Constants.LEFT_X_LIMIT) {
         	_ironMan.setLeftLimit();
    	} else if (_ironMan.getXLoc() > Constants.LEFT_X_LIMIT) {
    		_ironMan.moveXLoc(true);
    	}
    	// If the space bar is held down as the left arrow is pressed, the laser beams must move with Iron Man.
    	_leftLaser.setX(_ironMan.getLeftEye());
    	_rightLaser.setX(_ironMan.getRightEye());
    }
    
    /**
     * This method, called in KeyHandler, details what happens when the right arrow is pressed. The if statement 
     * prevents _ironMan from moving once it reaches the right border of cartoonPane; otherwise, it moves _ironMan
     * to the right. It is private since no other class needs to access it.
     */
    private void rightArrow() {
    	if (_ironMan.getXLoc() == Constants.RIGHT_X_LIMIT) {
    		_ironMan.setRightLimit();
    	}
    	else if (_ironMan.getXLoc() < Constants.RIGHT_X_LIMIT) {
    		_ironMan.moveXLoc(false);
    	}
   		// If the space bar is held down as the right arrow is pressed, the laser beams must move with _ironMan.
    	_leftLaser.setX(_ironMan.getLeftEye());
    	_rightLaser.setX(_ironMan.getRightEye());
    }
   
    /**
     * This method, called in KeyHandler, details what happens when the up arrow is pressed. The first if statement 
     * prevents the Clouds from increasing speed when they have reached max speed; otherwise, it increases their speed.
     * The second if statement prevents the speed label from going past max speed (2000 mph); otherwise, it increases
     * the displayed value. This method makes the dynamic speed label change when the up arrow is pressed.
     * It is private since no other class needs to access it.
     */
    private void upArrow() {
    	// if statement stops the distance from incrementing if already reached max speed. 
    	// Otherwise, increases the distance moved by the clouds between each KeyFrame so that they appear to move faster.
    	if (_distance >= Constants.MAX_DISTANCE) {
   			_distance = Constants.MAX_DISTANCE;
   	 	} else {
   	 		_distance += Constants.DISTANCE_INCREMENT;
   	 	}
    	// if statement increments the value displayed by the speed label until it reaches max 2000, then it stops it from increasing 
    	if (_speed < Constants.MAX_SPEED) {
    		_speed += Constants.SPEED_INCREMENT;
    	} else if (_speed == Constants.MAX_SPEED) {
    		_speed = Constants.MAX_SPEED;
    	}
    	// Change the speed label to show Iron Man's new speed
    	_speedLabel.setText("Speed: " + (_speed) + " mph");
    }
    
    /**
     * This method, called in KeyHandler, details what happens when the down arrow is pressed. The first if statement 
     * prevents the Clouds from decreasing speed when they have reached min speed; otherwise, it decreases their speed.
     * The second if statement prevents the speed label from going past min speed (50 mph); otherwise, it decreases
     * the displayed value. This method makes the dynamic speed label change when the down arrow is pressed.
     * It is private since no other class needs to access it.
     */
    private void downArrow() {
    	// if statement stops the distance from incrementing if already reached min speed. 
    	// Otherwise, decreases the distance moved by the clouds between each KeyFrame so that they appear to move slower.
    	if (_distance <= Constants.MIN_DISTANCE) {
    		_distance = Constants.MIN_DISTANCE;
      	} else {
      		_distance -= Constants.DISTANCE_INCREMENT;
      	}
    	// if statement increments the value displayed by the speed label until it reaches min 50, then it stops it from decreasing 
    	if (_speed > Constants.MIN_SPEED) {
    		_speed -= Constants.SPEED_INCREMENT;
    	} else if (_speed == Constants.MIN_SPEED) {
    		_speed = Constants.MIN_SPEED;
    	}
    	// Change the speed label to show Iron Man's new speed
    	_speedLabel.setText("Speed: " + (_speed) + " mph");
    }
    
    /**
	 * This class implements the EventHandler that responds to pressing the space bar, which creates two laser beams.
     * It is private since no other class besides Cartoon would need to access it.
     * When the space bar is held down, Iron Man shoots laser beams from each of his eyes.
     * To prevent the EventHandler from happening multiple times when the space bar is held down, the if statement
     * allows handle() to execute only when boolean _spaceReleased is true. The value is changed to false when the
     * space bar is pressed so that the method does not run again. 
     */
    private class SpacePressHandler implements EventHandler<KeyEvent> {

        @Override
        public void handle(KeyEvent e) {
            KeyCode keyPressed = e.getCode();
            if (keyPressed == KeyCode.SPACE) {
            	if (_spaceReleased == true) {
            		_spaceReleased = false;
            		_leftLaser = new Rectangle(_ironMan.getLeftEye(), 0, Constants.EYE_WIDTH, Constants.EYE_Y);
                    _rightLaser = new Rectangle(_ironMan.getRightEye(), 0, Constants.EYE_WIDTH, Constants.EYE_Y);
                    _leftLaser.setFill(Color.ALICEBLUE);
                    _leftLaser.setStroke(Color.DARKTURQUOISE);
                    _leftLaser.setStrokeWidth(5);
                    _rightLaser.setFill(Color.ALICEBLUE);
                    _rightLaser.setStroke(Color.DARKTURQUOISE);
                    _rightLaser.setStrokeWidth(5);
                    _cartoonPane.getChildren().addAll(_leftLaser, _rightLaser);
            	}
            	
            }
            e.consume();
        }
    }
    
    /**
	 * This class implements the EventHandler that responds to releasing the space bar.
     * It is private since no other class besides Cartoon would need to access it.
     * When the space bar is released, the laser beams disappear. The boolean _spaceReleased
     * is set to true again, which allows pressing the space bar to again create beams.
     */
    private class SpaceReleaseHandler implements EventHandler<KeyEvent> {

        @Override
        public void handle(KeyEvent e) {
            KeyCode keyReleased = e.getCode();
            if (keyReleased == KeyCode.SPACE) {
            	_spaceReleased = true;
            	_cartoonPane.getChildren().removeAll(_leftLaser, _rightLaser);
            }
            e.consume();
        }
    }
    
    /**
     * This method, called by ClickHandler in PaneOrganizer, changes the color of the sky when the "Night/Day" button is clicked.
     * If the sky is currently blue, the button changes it to black, and vice versa.
     */
    public void nightSky() {
    	if (_cartoonPane.getStyle() == "-fx-background-color: #B7EEFF;") {
    		_cartoonPane.setStyle("-fx-background-color: black;");
    	} else {
    		_cartoonPane.setStyle("-fx-background-color: #B7EEFF;");
    	}
    }
    
    /**
     * This method, called by ClickHandler in PaneOrganizer, changes the color of the clouds when the "Night/Day" button is clicked.
     * It calls the method grayCloud(), defined in the Cloud class, on each of the four cloud instances.
     */
    public void nightCloud() {
    	_cloud1.grayCloud();
    	_cloud2.grayCloud();
    	_cloud3.grayCloud();
    	_cloud4.grayCloud();   	
    }

}
