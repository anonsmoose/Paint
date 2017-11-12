package ca.utoronto.utm.paint.Shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.ArrayList;
/**
 * An object representing a square for the purposes of being 
 * drawn in a paint program.
 *
 */
public class Square implements Shape,Serializable {
	private int ydiff;
	private int xdiff;
	private int brushSize;
	private Color primaryColor;
	private Color secondaryColor;
	private Point origin;
	private String fillStyle;
	
	/**
	 * Constructs a new square.
	 * @param o Point corresponding to the origin of the rectangle.
	 * @param ydiff the differance between the origin and the mouse pos
	 * @param xdiff the differance between the origin and the mouse pos
	 * @param color The color of the rectangle.
	 * @param brushSize The thickness of the rectangle.
	 * @param solid If the rectangle is filled or not. 
	 */
	public Square(Point o, int ydiff, int xdiff,Color primaryColor,Color secondaryColor,int brushSize, String fillStyle)
	{
		this.fillStyle = fillStyle;
		this.xdiff = ydiff;
		this.origin = o;
		this.ydiff = ydiff;
		this.primaryColor = primaryColor;
		this.secondaryColor = secondaryColor;
		this.brushSize = brushSize;
	}
	
	public Point getOrigin()
	{
		return this.origin;
	}

	public void setYdiff(int y)
	{
		this.ydiff = y;
	}
	
	public void setXdiff(int x)
	{
		this.xdiff = x;
	}
	
	/**
	 * Set the origin
	 * @param a point corresponding to the origin.
	 */
	public void setOrigin(Point c)
	{
		this.origin = c;
	}
	
	@Override
	public void drawShape(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.setColor(this.primaryColor);
		g2d.setStroke(new BasicStroke(this.brushSize));
		
		int x = getOrigin().getX();
		int y= getOrigin().getY();
		int length = Math.max(Math.abs(this.ydiff),Math.abs(this.xdiff));
		
		if(this.ydiff  > 0)
			y = y - length;
		
		if(this.xdiff > 0)
			x = x - length;
		
		g2d.drawRect(x, y, length, length);
		
		if(this.fillStyle.equals("Solid"))
			g2d.fillRect(x, y, length, length);
		else if(this.fillStyle.equals("Secondary Filled")){
			g2d.setColor(secondaryColor);
			g2d.fillRect(x, y, length, length);
		}

	}

	@Override
	public ArrayList<Shape> explodeShape() {
		// TODO Auto-generated method stub
		return null;
	}

}
