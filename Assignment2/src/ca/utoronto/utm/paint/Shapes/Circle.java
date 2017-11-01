package ca.utoronto.utm.paint.Shapes;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import ca.utoronto.utm.paint.Point;

public class Circle implements Shape {
	private Point centre;
	private int radius;
	
	public Circle(Point centre, int radius){
		this.centre = centre;
		this.radius = radius;
	}

	public Point getCentre() {
		return centre;
	}

	public void setCentre(Point centre) {
		this.centre = centre;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	public String toString()
	{
		return "Centre: " + this.centre + " " + "Radius: " + this.radius;
	}

	@Override
	public void drawShape(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		if(this.radius > 0)
			g2d.drawOval(this.centre.getX() - radius, this.centre.getY() - radius, radius*2, radius*2);
		else g2d.drawOval(this.centre.getX() + radius, this.centre.getY() + radius, -radius*2, -radius*2);
	}

	@Override
	public ArrayList<Shape> explodeShape() {
		ArrayList<Shape> compositeShapes = new ArrayList<Shape>();
		compositeShapes.add(this);
		return null;
	}
}
