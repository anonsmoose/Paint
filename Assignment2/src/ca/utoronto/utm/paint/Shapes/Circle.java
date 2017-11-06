package ca.utoronto.utm.paint.Shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import ca.utoronto.utm.paint.Point;

public class Circle implements Shape {
	private Point centre;
	private int radius;
	private int brushSize;
	private String fillStyle;
	private Color primaryColor;
	private Color secondaryColor;
	
	public Circle(Point centre, int radius,Color primaryColor,Color secondaryColor, int brushSize, String fillStyle){
		this.fillStyle = fillStyle;
		this.centre = centre;
		this.radius = radius;
		this.brushSize = brushSize;
		this.primaryColor = primaryColor;
		this.secondaryColor = secondaryColor;
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
		g2d.setColor(this.primaryColor);
		g2d.setStroke(new BasicStroke(this.brushSize));
		
		if(this.radius > 0){
			g2d.drawOval(this.centre.getX() - radius, this.centre.getY() - radius, radius*2, radius*2);
			if(this.fillStyle.equals("Solid"))
				g2d.fillOval(this.centre.getX() - radius, this.centre.getY() - radius, radius*2, radius*2);
			else if(this.fillStyle.equals("Secondary Filled")){
				g2d.setColor(secondaryColor);
				g2d.fillOval(this.centre.getX() - radius, this.centre.getY() - radius, radius*2, radius*2);
			}
		}
		else {
			g2d.drawOval(this.centre.getX() + radius, this.centre.getY() + radius, -radius*2, -radius*2);
			if(this.fillStyle.equals("Solid"))
				g2d.fillOval(this.centre.getX() + radius, this.centre.getY() + radius, -radius*2, -radius*2);
			else if(this.fillStyle.equals("Secondary Filled")){
				g2d.setColor(secondaryColor);
				g2d.fillOval(this.centre.getX() + radius, this.centre.getY() + radius, -radius*2, -radius*2);
			}
		}
	}

	@Override
	public ArrayList<Shape> explodeShape() {
		ArrayList<Shape> compositeShapes = new ArrayList<Shape>();
		compositeShapes.add(this);
		return null;
	}
}
