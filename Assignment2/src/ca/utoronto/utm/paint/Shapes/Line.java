package ca.utoronto.utm.paint.Shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.ArrayList;
/**
 * An object representing a straight line between two points for the purposes
 * of being drawn in a paint program
 *
 */
public class Line implements Shape,Serializable {
	private int p1x,p1y,p2x,p2y;
	private Color color;
	private int thickness;
	
	/**
	 * Constructs a line
	 * @param x1 the x of the origin point
	 * @param y1 the y of the origin point
	 * @param x2 the x of the destination point
	 * @param y2 the y of the destination point
	 * @param color the color of the line
	 * @param thickness the lines thickness
	 */
	public Line(int x1,int y1,int x2,int y2,Color color,int thickness){
		this.p1x = x1;
		this.p1y = y1;
		this.p2x = x2;
		this.p2y = y2;
		this.color = color;
		this.thickness = thickness;
	}
	
	public void setDestination(Point p){
		this.p2x = p.getX();
		this.p2y = p.getY();
	}
	
	@Override
	public void drawShape(Graphics g) {
		 Graphics2D g2d = (Graphics2D) g;
		 g2d.setStroke(new BasicStroke(this.thickness));
		 g2d.setColor(this.color);
		 g2d.drawLine(p1x, p1y, p2x, p2y);

	}
	@Override
	public ArrayList<Shape> explodeShape() {
		ArrayList<Shape> compositeShapes = new ArrayList<Shape>();
		compositeShapes.add(this);
		return compositeShapes;
	}

}
