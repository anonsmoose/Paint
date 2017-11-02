package ca.utoronto.utm.paint.Shapes;

import java.awt.Graphics;
import java.util.ArrayList;

public interface Shape{
	public void drawShape(Graphics g, boolean shape);
	
	public abstract boolean getSolid();
	
	public ArrayList<Shape> explodeShape();
	
}
