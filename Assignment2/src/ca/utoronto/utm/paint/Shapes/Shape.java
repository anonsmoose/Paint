package ca.utoronto.utm.paint.Shapes;

import java.awt.Graphics;
import java.util.ArrayList;

public interface Shape{
	public void drawShape(Graphics g);
	
	public ArrayList<Shape> explodeShape();
	
}
