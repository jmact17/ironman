package Cartoon;

import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.Group;
import javafx.scene.layout.Pane;

/**
 * This class is responsible for creating and animating Iron Man. 
 * It contains methods to create each body part making up Iron Man as well as methods 
 * changing Iron Man's characteristics, such as his location and the size of his beams.
 * It also has setters and getters for X locations that are used in the Cartoon class.
 */

public class IronMan {

    private Ellipse _faceRed;
    private Rectangle _faceYellow;
    private Rectangle _leftEye;
    private Rectangle _rightEye;
    private Polygon _chest;
    private Polygon _torso;
    private Circle _core;
    private Rectangle _leftArmYellow;
    private Rectangle _rightArmYellow;
    private Rectangle _leftArmRed;
    private Rectangle _rightArmRed;
    private Rectangle _leftHand;
    private Rectangle _rightHand;
    private Rectangle _leftLegYellow;
    private Rectangle _rightLegYellow;
    private Rectangle _leftLegRed;
    private Rectangle _rightLegRed;
    private Polygon _leftHandBeam;
    private Polygon _rightHandBeam;
    private Polygon _leftFootBeam;
    private Polygon _rightFootBeam;
    private Group _ironGroup;
    private double _xLoc;

    /**
     * IronMan's constructor creates all the shapes that make up Iron Man.
     * It accepts a parameter of type Pane so that when Cartoon instantiates it, 
     * the root _cartoonPane can be passed in and receive all the children created by IronMan.
     * It creates the Group _ironGroup that contains Iron Man's body parts so that they all can move as one unit.
     */
    public IronMan(Pane root) {
        _ironGroup = new Group();
        this.createBeams();
        _ironGroup.getChildren().addAll(_leftHandBeam, _rightHandBeam, _leftFootBeam, _rightFootBeam);
        this.createLegs();
        _ironGroup.getChildren().addAll(_leftLegYellow, _rightLegYellow, _leftLegRed, _rightLegRed);
        this.createArms();
        _ironGroup.getChildren().addAll(_leftArmYellow, _rightArmYellow, _leftHand, _rightHand, _leftArmRed, _rightArmRed);
        this.createBody();
        _ironGroup.getChildren().addAll(_chest, _torso, _core);
        this.createFace();
        _ironGroup.getChildren().addAll(_faceRed, _faceYellow);
        // The eyes must be added separately from _ironGroup so that their locations can be retrieved to set the locations of the laser beams.
        root.getChildren().addAll(_ironGroup, _leftEye, _rightEye);
    }

    /**
     * This method creates Iron Man's face.
     * It is private since no other class needs to access it.
     */
    private void createFace() {
        // Make the red part of the head
        _faceRed = new Ellipse(Constants.CENTER_X, Constants.FACE_CENTER_Y, Constants.FACERED_X_RAD, Constants.FACERED_Y_RAD);
        _faceRed.setFill(Color.RED);
        _faceRed.setStroke(Color.BLACK);
        // Make the gold part of the face
        _faceYellow = new Rectangle(Constants.YELLOWFACE_X, Constants.YELLOWFACE_Y, Constants.YELLOWFACE_WIDTH, Constants.YELLOWFACE_HEIGHT);
        _faceYellow.setFill(Color.GOLD);
        _faceYellow.setStroke(Color.BLACK);
        // Make the eyes
        _leftEye = new Rectangle(Constants.EYE_X_LEFT, Constants.EYE_Y, Constants.EYE_WIDTH, Constants.EYE_HEIGHT);
        _leftEye.setFill(Color.WHITE);
        _leftEye.setStroke(Color.BLUE);
        _rightEye = new Rectangle(Constants.EYE_X_RIGHT, Constants.EYE_Y, Constants.EYE_WIDTH, Constants.EYE_HEIGHT);
        _rightEye.setFill(Color.WHITE);
        _rightEye.setStroke(Color.BLUE);
    }

    /**
     * This method creates Iron Man's body, including the chest, torso, and core.
     * It is private since no other class needs to access it.
     */
    private void createBody() {
        // Make the chest
        _chest = new Polygon(Constants.CHEST_X_BOTTOMLEFT, Constants.CHEST_Y_BOTTOM,
            Constants.CHEST_X_TOPLEFT, Constants.CHEST_Y_TOP,
            Constants.CHEST_X_TOPRIGHT, Constants.CHEST_Y_TOP,
            Constants.CHEST_X_BOTTOMRIGHT, Constants.CHEST_Y_BOTTOM);
        _chest.setFill(Color.RED);
        _chest.setStroke(Color.BLACK);
        // Make the torso
        _torso = new Polygon(Constants.TORSO_X_TOPLEFT, Constants.CHEST_Y_BOTTOM,
            Constants.TORSO_X_TOPRIGHT, Constants.CHEST_Y_BOTTOM,
            Constants.TORSO_X_BOTTOMRIGHT, Constants.TORSO_Y_MIDDLE,
            Constants.CENTER_X, Constants.TORSO_Y_BOTTOM,
            Constants.TORSO_X_BOTTOMLEFT, Constants.TORSO_Y_MIDDLE);
        _torso.setFill(Color.rgb(200, 0 ,0));
        _torso.setStroke(Color.BLACK);
        // Make the arc reactor core
        _core = new Circle(Constants.CENTER_X, Constants.CHEST_Y_BOTTOM, Constants.CORE_RAD, Color.WHITE);
        _core.setStroke(Color.BLUE);
    }

    /**
     * This method creates Iron Man's arms.
     * It is private since no other class needs to access it.
     */
    private void createArms() {
        // Make upper arms
        _leftArmYellow = new Rectangle(Constants.CHEST_X_BOTTOMLEFT, Constants.UPARM_Y, Constants.ARM_WIDTH, Constants.ARM_HEIGHT);
        _leftArmYellow.setFill(Color.GOLD);
        _leftArmYellow.setStroke(Color.BLACK);
        _leftArmYellow.setRotate(20);
        _rightArmYellow = new Rectangle(Constants.UPARM_X_RIGHT, Constants.UPARM_Y, Constants.ARM_WIDTH, Constants.ARM_HEIGHT);
        _rightArmYellow.setFill(Color.GOLD);
        _rightArmYellow.setStroke(Color.BLACK);
        _rightArmYellow.setRotate(-20);
        // Make lower arms
        _leftArmRed = new Rectangle(Constants.LOWARM_X_LEFT, Constants.LOWARM_Y, Constants.ARM_WIDTH, Constants.ARM_HEIGHT);
        _leftArmRed.setFill(Color.RED);
        _leftArmRed.setStroke(Color.BLACK);
        _leftArmRed.setRotate(30);
        _rightArmRed = new Rectangle(Constants.LOWARM_X_RIGHT, Constants.LOWARM_Y, Constants.ARM_WIDTH, Constants.ARM_HEIGHT);
        _rightArmRed.setFill(Color.RED);
        _rightArmRed.setStroke(Color.BLACK);
        _rightArmRed.setRotate(-30);
        // Make hands
        _leftHand = new Rectangle(Constants.HAND_X_LEFT, Constants.HAND_Y, Constants.HAND_WIDTH, Constants.HAND_HEIGHT);
        _leftHand.setFill(Color.RED);
        _leftHand.setStroke(Color.BLACK);
        _rightHand = new Rectangle(Constants.HAND_X_RIGHT, Constants.HAND_Y, Constants.HAND_WIDTH, Constants.HAND_HEIGHT);
        _rightHand.setFill(Color.RED);
        _rightHand.setStroke(Color.BLACK);
    }

    /**
     * This method creates Iron Man's legs
     * It is private since no other class needs to access it.
     */
    private void createLegs() {
        // Make upper legs
        _leftLegYellow = new Rectangle(Constants.UPLEG_X_LEFT, Constants.TORSO_Y_MIDDLE, Constants.LEG_WIDTH, Constants.UPLEG_HEIGHT);
        _leftLegYellow.setFill(Color.GOLD);
        _leftLegYellow.setStroke(Color.BLACK);
        _leftLegYellow.setRotate(3);
        _rightLegYellow = new Rectangle(Constants.UPLEG_X_RIGHT, Constants.TORSO_Y_MIDDLE, Constants.LEG_WIDTH, Constants.UPLEG_HEIGHT);
        _rightLegYellow.setFill(Color.GOLD);
        _rightLegYellow.setStroke(Color.BLACK);
        _rightLegYellow.setRotate(-3);
        // Make lower legs
        _leftLegRed = new Rectangle(Constants.LEG_X_LEFT, Constants.LEG_Y_MIDDLE, Constants.LEG_WIDTH, Constants.LOLEG_HEIGHT);
        _leftLegRed.setFill(Color.RED);
        _leftLegRed.setStroke(Color.BLACK);
        _rightLegRed = new Rectangle(Constants.LEG_X_RIGHT, Constants.LEG_Y_MIDDLE, Constants.LEG_WIDTH, Constants.LOLEG_HEIGHT);
        _rightLegRed.setFill(Color.RED);
        _rightLegRed.setStroke(Color.BLACK);
    }

    /**
     * This method creates beams below Iron Man's hands and feet.
     * It is private since no other class needs to access it.
     */
    private void createBeams() {
        _leftHandBeam = new Polygon(Constants.HAND_X_LEFT, Constants.HANDBEAM_Y_TOP,
            Constants.HAND_X_LEFT + Constants.HAND_WIDTH, Constants.HANDBEAM_Y_TOP,
            (2*Constants.HAND_X_LEFT + Constants.HAND_WIDTH)/2, Constants.HANDBEAM_Y_BOTTOM); // The point of the bottom of the triangle is the midpoint
        _rightHandBeam = new Polygon(Constants.HAND_X_RIGHT, Constants.HANDBEAM_Y_TOP,
            Constants.HAND_X_RIGHT + Constants.HAND_WIDTH, Constants.HANDBEAM_Y_TOP,
            (2*Constants.HAND_X_RIGHT + Constants.HAND_WIDTH)/2, Constants.HANDBEAM_Y_BOTTOM);
        _leftFootBeam = new Polygon(Constants.LEG_X_LEFT, Constants.FOOTBEAM_Y_TOP,
            Constants.LEG_X_LEFT + Constants.LEG_WIDTH, Constants.FOOTBEAM_Y_TOP,
            (2*Constants.LEG_X_LEFT + Constants.LEG_WIDTH)/2, Constants.FOOTBEAM_Y_BOTTOM);
        _rightFootBeam = new Polygon(Constants.LEG_X_RIGHT, Constants.FOOTBEAM_Y_TOP,
            Constants.LEG_X_RIGHT + Constants.LEG_WIDTH, Constants.FOOTBEAM_Y_TOP,
            (2*Constants.LEG_X_RIGHT + Constants.LEG_WIDTH)/2, Constants.FOOTBEAM_Y_BOTTOM);
        this.setBeamColor();
    }

    /**
     * This method sets the color of the beams.
     */
    public void setBeamColor() {
        _leftHandBeam.setFill(Color.ALICEBLUE);
        _leftHandBeam.setStroke(Color.DARKTURQUOISE);
        _leftHandBeam.setStrokeWidth(4);
        _rightHandBeam.setFill(Color.ALICEBLUE);
        _rightHandBeam.setStroke(Color.DARKTURQUOISE);
        _rightHandBeam.setStrokeWidth(4);
        _leftFootBeam.setFill(Color.ALICEBLUE);
        _leftFootBeam.setStroke(Color.DARKTURQUOISE);
        _leftFootBeam.setStrokeWidth(4);
        _rightFootBeam.setFill(Color.ALICEBLUE);
        _rightFootBeam.setStroke(Color.DARKTURQUOISE);
        _rightFootBeam.setStrokeWidth(4);
    }
    
    /**
     * This method, called by BeamHandler in the Cartoon class, animates the beams by alternating their stroke width between two widths.
     */
    public void flashBeams() {
    	if (_leftHandBeam.getStrokeWidth() == 4 && 
    	    _rightHandBeam.getStrokeWidth() == 4 && 
    	    _leftFootBeam.getStrokeWidth() == 4 && 
    	    _rightFootBeam.getStrokeWidth() == 4) {
    		_leftHandBeam.setStrokeWidth(6);
    		_rightHandBeam.setStrokeWidth(6);
    		_leftFootBeam.setStrokeWidth(6);
    		_rightFootBeam.setStrokeWidth(6);
    	} else {
    		_leftHandBeam.setStrokeWidth(4);
    		_rightHandBeam.setStrokeWidth(4);
    		_leftFootBeam.setStrokeWidth(4);
    		_rightFootBeam.setStrokeWidth(4);
    	}
    }

    /**
     * This method moves Iron Man left and right and is called in Cartoon's KeyHandler to respond to the left and right arrow keys.
     * It takes a parameter of type boolean, which determines whether Iron Man will move left or right.
     * The eyes are not contained in _ironGroup, so they must be moved as well.
     */
    public void moveXLoc(boolean isLeft) {
        _xLoc = _ironGroup.getLayoutX();
    	double distance = Constants.IRON_X_INCREMENT; // The distance Iron Man will move each time the key is pressed
        // If the key pressed is left, the distance added to the current X location should be negative
    	if (isLeft) {
        	distance *= -1;
        }
        _ironGroup.setLayoutX(_xLoc + distance);
        _leftEye.setX(_leftEye.getX() + distance);
        _rightEye.setX(_rightEye.getX() + distance);  
    }

    /**
	 * This method returns the current X location of Iron Man.
	 * It is called in Cartoon's KeyHandler when setting boundaries for Iron Man's horizontal movement.
     */
    public double getXLoc() {
        return _xLoc;
    }
    
    /**
     * This method, called in Cartoon's KeyHandler, prevents Iron Man from moving past the left border.
     */
    public void setLeftLimit() {
    	_ironGroup.setLayoutX(Constants.LEFT_X_LIMIT);
    	_leftEye.setX(Constants.LEFTEYE_LEFTLIMIT);
    	_rightEye.setX(Constants.RIGHTEYE_LEFTLIMIT);
    }
    
    /**
     * This method, called in Cartoon's KeyHandler, prevents Iron Man from moving past the right border.
     */
    public void setRightLimit() {
    	_ironGroup.setLayoutX(Constants.RIGHT_X_LIMIT);
    	_leftEye.setX(Constants.LEFTEYE_RIGHTLIMIT);
    	_rightEye.setX(Constants.RIGHTEYE_RIGHTLIMIT);
    }
    
    /**
     * This method returns the current X location of Iron Man's left eye.
     * It is called in Cartoon's KeyHandler and SpacePressHandler when creating and moving laser beams. 
     */
    public double getLeftEye() {
    	return _leftEye.getX();
    }
    
    /**
     * This method returns the current X location of Iron Man's right eye.
     * It is called in Cartoon's KeyHandler and SpacePressHandler when creating and moving laser beams.
     */
    public double getRightEye() {
    	return _rightEye.getX();
    }
}
