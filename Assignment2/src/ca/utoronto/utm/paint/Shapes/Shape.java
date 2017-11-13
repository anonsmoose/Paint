package ca.utoronto.utm.paint.Shapes;

import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;
/**
 * Interface for implementing the command design pattern for the various shapes
 * used for drawing in a paint program.
 *
 */
public interface Shape extends Serializable{
	/**
	 * draws the shape on the input graphics object
	 * @param g the graphics object being drawn on.
	 */
	public void drawShape(Graphics g);
	
	/**
	 * breaks down the shape into a list containing the shape's composite shapes.
	 * @return
	 */
	public ArrayList<Shape> explodeShape();
	
}
