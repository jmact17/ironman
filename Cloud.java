package Cartoon;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

/**
 * This class is responsible for creating and animating clouds. It contains methods that make the clouds move and change their color.
 */

 public class Cloud {

     private Shape _cloud;
     
     /**
      * The constructor takes a parameter of type Pane so that when Cartoon instantiates a Cloud, it can pass in _cartoonPane, and 
      * the Cloud instance can be added to the pane.
      */
     public Cloud(Pane root) {
         this.createCloud();
         root.getChildren().add(_cloud);
     }
     
     /**
      * This method creates the cloud composite shape as a union of four circles and sets it at a random location above the visible window.
      * The random Y location, which ranges between -1000 and -100, gives randomness to when the cloud will first appear.
      */
     public void createCloud() {
    	 Circle fluff1 = new Circle(Constants.X1, Constants.Y1, Constants.RAD1, Color.WHITE);
    	 Circle fluff2 = new Circle(Constants.X2, Constants.Y2, Constants.RAD2, Color.WHITE);
    	 Circle fluff3 = new Circle(Constants.X3, Constants.Y3, Constants.RAD3, Color.WHITE);
    	 Circle fluff4 = new Circle(Constants.X4, Constants.Y4, Constants.RAD4, Color.WHITE);
    	 // The union method only accepts two parameters so must call it three times in combining all four Circles into one Shape
         Shape half1 = Shape.union(fluff1, fluff2);
         Shape half2 = Shape.union(fluff3, fluff4);
         _cloud = Shape.union(half1, half2);
         _cloud.setFill(Color.WHITE);
         _cloud.setLayoutX((int) (Constants.CLOUD_X_MIN + (int)(Math.random() * Constants.CLOUD_X_RANGE))); // X range: -150 to 650
         _cloud.setLayoutY((int) (Constants.CLOUD_Y_MIN + (int)(Math.random() * Constants.CLOUD_Y_RANGE))); // Y range: -1000 to -100
     }
     
     /**
      * This method is called in Cartoon's CloudHandler to make the clouds move down the screen.
      * It resets the clouds' location back above the top of the window once they disappear off the bottom.
      * It takes in a parameter of type int representing the vertical distance the cloud moves between
      * each KeyFrame in the cloudTimeline.
      */
     public void moveDown(int distance) {
    	 _cloud.setLayoutY(_cloud.getLayoutY() + distance);
    	 // If the cloud moves off the bottom of the window, its location is reset to a random X within cartoonPane and a random Y above the window 
    	 if (_cloud.getLayoutY() > Constants.CLOUD_OFFSCREEN) { 
    		 _cloud.setLayoutX((int) (Constants.CLOUD_X_MIN + (int)(Math.random() * Constants.CLOUD_X_RANGE)));
             _cloud.setLayoutY((int) (Constants.CLOUD_Y_MIN + (int)(Math.random() * Constants.CLOUD_Y_RANGE)));
    	 }
     }
     
     /**
     * This method changes the color of the cloud, called in Cartoon's nightCloud() method on all four cloud instances.
     * If the cloud is white, it will change to gray, and vice versa.
     */
     public void grayCloud() {
     	if (_cloud.getFill() == Color.WHITE) {
     		_cloud.setFill(Color.DIMGRAY);
     	} else {
     		_cloud.setFill(Color.WHITE);
     	}
     }
 }
