package ca.utoronto.utm.paint.Shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import ca.utoronto.utm.paint.Point;

public class Squiggle implements Shape {
	private ArrayList<Point> compositePoints;
	private Color color;
	private boolean drawn;
	private int thickness;
	
	public Squiggle(){
		compositePoints = new ArrayList<Point>();
		this.drawn = false;
		this.color = Color.black;
		this.thickness = 1;
	}
	
	public Squiggle(Color color,int thickness){
		compositePoints = new ArrayList<Point>();
		drawn = false;
		this.color = color;
		this.thickness = thickness;
	}
	public void addPoint(Point p){
		this.compositePoints.add(p);
	}
	
	public boolean isDrawn(){
		return this.drawn;
	}
	@Override
	public ArrayList<Shape> explodeShape() {
		ArrayList<Shape> compositeShapes = new ArrayList<Shape>();
		int i = 0;
		while(i+1 < compositePoints.size()){ //while there is enough elements to construct additional li
			int x1 = compositePoints.get(i).getX();
			int y1 = compositePoints.get(i).getY();
			int x2 = compositePoints.get(i+1).getX();
			int y2 = compositePoints.get(i+1).getY();
			Shape line = (Shape) new Line(x1,y1,x2,y2,this.color,this.thickness);
			compositeShapes.add(line);
			i++;
		}
		return compositeShapes;
	}
	@Override
	public void drawShape(Graphics g) {
		for(Shape shape: explodeShape()){
			Line line = (Line) shape;
			line.drawShape(g);
		}
		drawn = true;
		
	}
}
