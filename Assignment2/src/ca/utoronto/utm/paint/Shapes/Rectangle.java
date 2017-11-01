package ca.utoronto.utm.paint.Shapes;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import ca.utoronto.utm.paint.Point;

public class Rectangle implements Shape{
	private int height;
	private int width;
	private Point origin;
	
	public Rectangle(Point o, int height, int width) 
	{
		this.width = width;
		this.origin = o;
		this.height = height;
	}
	
	public Point getOrigin()
	{
		return this.origin;
	}
	
	public void setOrigin(Point c)
	{
		this.origin = c;
	}
	
	public int getheight()
	{
		return this.height;
	}
	
	public void setheight(int l)
	{
		this.height = l;
	}
	
	public int getWidth()
	{
		return this.width;
	}
	
	public void setWidth(int width)
	{
		this.width = width;
	}

	@Override
	public void drawShape(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		int x = getOrigin().getX();
		int y= getOrigin().getY();
		int height = getheight();
		int width = getWidth();
		if(height  > 0 && width > 0)
		{
			g2d.drawRect(x - width, y - height, width, height);
		}
		else if(height > 0 && width < 0)
		{
			g2d.drawRect(x, y - height, -width, height);
		}
		else if(height < 0 && width > 0)
		{
			g2d.drawRect(x - width, y, width, -height);
		}
		else {
			g2d.drawRect(Math.abs(x), Math.abs(y), -width, -height);
		}
		
	}

	@Override
	public ArrayList<Shape> explodeShape() {
		ArrayList<Shape> compositeShapes = new ArrayList<Shape>();
		compositeShapes.add(this);
		return null;
	}
	

}
