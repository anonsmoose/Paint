package ca.utoronto.utm.paint.Shapes;

import java.awt.Graphics;
import java.util.ArrayList;

public class Square implements Shape {
	private boolean solid;
	@Override
	public void drawShape(Graphics g, boolean s) {
		// TODO Auto-generated method stub

	}
	
	public boolean getSolid()
	{
		return this.solid;
	}
	
	public void setSolid(boolean solid)
	{
		this.solid = solid;
	}

	@Override
	public ArrayList<Shape> explodeShape() {
		// TODO Auto-generated method stub
		return null;
	}

}
