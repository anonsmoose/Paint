package ca.utoronto.utm.paint.Shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import ca.utoronto.utm.paint.Point;

public class Square implements Shape {
	private int ydiff;
	private int xdiff;
	private int brushSize;
	private Color color;
	private Point origin;
	private boolean solid;
	
	/**
	 * Constructs a new rectangle.
	 * @param o Point corresponding to the origin of the rectangle.
	 * @param height The height of the rectangle.
	 * @param width The with of the rectangle.
	 * @param color The color of the rectangle.
	 * @param brushSize The thickness of the rectangle.
	 * @param solid If the rectangle is filled or not. 
	 */
	public Square(Point o, int ydiff, int xdiff,Color color,int brushSize, boolean solid)
	{
		this.solid = solid;
		this.xdiff = ydiff;
		this.origin = o;
		this.ydiff = ydiff;
		this.color = color;
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
		
		g2d.setColor(this.color);
		if(this.color == null){
			g2d.setColor(Color.white);
		}
		
		g2d.setStroke(new BasicStroke(this.brushSize));
		int x = getOrigin().getX();
		int y= getOrigin().getY();
		int length = Math.max(Math.abs(this.ydiff),Math.abs(this.xdiff));
		if(this.ydiff  > 0 && this.xdiff > 0)
		{
			g2d.drawRect(x - length, y - length, length, length);
			if(this.solid)
			{
				g2d.fillRect(x - length, y - length, length, length);
			}
		}
		else if(ydiff > 0 && xdiff < 0)
		{
			g2d.drawRect(x, y - length, length, length);
			if(this.solid)
			{
				g2d.fillRect(x, y - length, length, length);
			}
		}
		else if(ydiff < 0 && xdiff > 0)
		{
			g2d.drawRect(x - length, y, length, length);
			if(this.solid)
			{
				g2d.fillRect(x - length, y, length, length);
			}
		}
		else {
			g2d.drawRect(Math.abs(x), Math.abs(y), length, length);
			if(this.solid)
			{
				g2d.fillRect(Math.abs(x), Math.abs(y), length, length);
			}
		}

	}

	@Override
	public ArrayList<Shape> explodeShape() {
		// TODO Auto-generated method stub
		return null;
	}

}
