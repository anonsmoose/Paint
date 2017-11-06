package ca.utoronto.utm.paint.Shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import ca.utoronto.utm.paint.Point;

public class Rectangle implements Shape{
	private int height;
	private int width;
	private int brushSize;
	private Color primaryColor;
	private Color secondaryColor;
	private Point origin;
	private String fillStyle;
	
	/**
	 * Constructs a new rectangle.
	 * @param o Point corresponding to the origin of the rectangle.
	 * @param height The height of the rectangle.
	 * @param width The with of the rectangle.
	 * @param color The color of the rectangle.
	 * @param brushSize The thickness of the rectangle.
	 * @param solid If the rectangle is filled or not. 
	 */
	public Rectangle(Point o, int height, int width,Color primaryColor,Color secondaryColor,int brushSize, String fillStyle)
	{
		this.fillStyle = fillStyle;
		this.width = width;
		this.origin = o;
		this.height = height;
		this.primaryColor = primaryColor;
		this.secondaryColor = secondaryColor;
		this.brushSize = brushSize;
	}
	
	/**
	 *  returns the origin
	 * @return returns the origin of the rectangle.
	 */
	public Point getOrigin()
	{
		return this.origin;
	}
	
	/**
	 * Set the origin
	 * @param a point corresponding to the origin.
	 */
	public void setOrigin(Point c)
	{
		this.origin = c;
	}
	
	/**
	 * Grabs the height of the rectangle.
	 * @return returns the height of the rectangle.
	 */
	public int getheight()
	{
		return this.height;
	}
	
	/**
	 * Set the height of the rectangle.
	 * @param Integer corresponding the height of the rectangle.
	 */
	public void setheight(int l)
	{
		this.height = l;
	}
	
	/**
	 * Grabs the width of the rectangle.
	 * @return returns an integer correspondiing to the width of the rectangle.
	 */
	public int getWidth()
	{
		return this.width;
	}
	
	/**
	 * Set the width of the rectangle.
	 * @param width Sets the width of the rectangle to the given integer.
	 */
	public void setWidth(int width)
	{
		this.width = width;
	}
	/**
	 * Instructions that determine how the rectangle is drawn.
	 */
	@Override
	public void drawShape(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(this.primaryColor);
		g2d.setStroke(new BasicStroke(this.brushSize));
		int x = getOrigin().getX();
		int y= getOrigin().getY();
		int height = getheight();
		int width = getWidth();
		System.out.println("" + x +" " + y +" " +height+" " +width);
		if(width > 0)
			x = x - width;
		else width = -1 * width;
		
		if(height > 0)
			y = y - height;
		else height = -1 * height;
		
		System.out.println("" + x +" " + y +" " +height+" " +width);
		g2d.drawRect(x, y, width, height);
		if(this.fillStyle.equals("Solid"))
			g2d.fillRect(x, y, width, height);
		else if(this.fillStyle.equals("Secondary Filled")){
			g2d.setColor(secondaryColor);
			g2d.fillRect(x, y, width, height);
		}	
	}

	@Override
	public ArrayList<Shape> explodeShape() {
		ArrayList<Shape> compositeShapes = new ArrayList<Shape>();
		compositeShapes.add(this);
		return null;
	}
	

}
