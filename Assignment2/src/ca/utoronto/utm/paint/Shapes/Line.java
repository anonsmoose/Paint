package ca.utoronto.utm.paint.Shapes;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Line implements Shape {
	private int p1x,p1y,p2x,p2y;
	
	public Line(int x1,int y1,int x2,int y2){
		this.p1x = x1;
		this.p1y = y1;
		this.p2x = x2;
		this.p2y = y2;
	}
	@Override
	public void drawShape(Graphics g) {
		 Graphics2D g2d = (Graphics2D) g;
		 g2d.drawLine(p1x, p1y, p2x, p2y);

	}
	@Override
	public ArrayList<Shape> explodeShape() {
		ArrayList<Shape> compositeShapes = new ArrayList<Shape>();
		compositeShapes.add(this);
		return compositeShapes;
	}

}
