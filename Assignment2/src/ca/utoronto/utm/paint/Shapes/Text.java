package ca.utoronto.utm.paint.Shapes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Text implements Shape{
	private int fontSize;
	private String text;
	private String fontFamily;
	private Point origin;
	private Color color;
	
	public Text(Point origin, int fs, String t, String ff, Color c)
	{
		this.fontFamily = ff;
		this.origin = origin;
		this.text = t;
		this.fontSize = fs;
		this.color = c;
	}
	
	public void setText(String t)
	{
		this.text = t;
	}
	
	public String getText()
	{
		return this.text;
	}
	
	public void setFontFamily(String ff)
	{
		this.fontFamily = ff;
	}
	
	public String getFontFamily()
	{
		return this.fontFamily;
	}
	
	public void setFontSize(int fs)
	{
		this.fontSize = fs;
	}
	
	public int getFontSize()
	{
		return this.fontSize;
	}
	
	public void setOrigin(Point p)
	{
		this.origin = p;
	}
	
	public Point getOrigin()
	{
		return this.origin;
	}
	@Override
	
	public void drawShape(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(this.color);
		g2d.setFont(new Font(this.fontFamily, Font.PLAIN, this.fontSize));
		g2d.drawString(this.text, this.origin.x, this.origin.y);
		
		
	}

	@Override
	public ArrayList<Shape> explodeShape() {
		// TODO Auto-generated method stub
		return null;
	}

}
