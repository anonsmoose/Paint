package ca.utoronto.utm.paint;

public class Rectangle {
	private int height;
	private int width;
	private Point centre;
	
	public Rectangle(Point c, int height, int width) 
	{
		this.width = width;
		this.centre = c;
		this.height = height;
	}
	
	public Point getCentre()
	{
		return this.centre;
	}
	
	public void setCentre(Point c)
	{
		this.centre = c;
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
	

}
