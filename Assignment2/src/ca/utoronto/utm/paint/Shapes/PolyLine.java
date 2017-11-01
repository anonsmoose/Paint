package ca.utoronto.utm.paint.Shapes;

import java.awt.Graphics;
import java.util.ArrayList;

public class PolyLine implements Shape {
	private ArrayList<Line> compositeLines;

	@Override
	public ArrayList<Shape> explodeShape() {
		ArrayList<Shape> compositeShapes = new ArrayList<Shape>();
		for(Line line: compositeLines){
			compositeShapes.add((Shape)line);
		}
		return compositeShapes;
	}
	@Override
	public void drawShape(Graphics g) {
		for(Line line: compositeLines){
			line.drawShape(g);
		}
		
	}
}
