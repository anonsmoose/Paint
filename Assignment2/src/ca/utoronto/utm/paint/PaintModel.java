package ca.utoronto.utm.paint;

import java.util.ArrayList;
import java.util.Observable;

import ca.utoronto.utm.paint.Shapes.Circle;
import ca.utoronto.utm.paint.Shapes.Rectangle;
import ca.utoronto.utm.paint.Shapes.Shape;

public class PaintModel extends Observable {
	private ArrayList<Point> points=new ArrayList<Point>();
	private ArrayList<Circle> circles=new ArrayList<Circle>();
	private ArrayList<Rectangle> rectangles = new ArrayList<Rectangle>();
	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	
	public void addShape(Shape s){
		this.shapes.add(s);
		this.setChanged();
		this.notifyObservers();
	}
	
	public ArrayList<Shape> getShapes(){
		return shapes;
	}
	
	public void addPoint(Point p){
		this.points.add(p);
		this.setChanged();
		this.notifyObservers();
	}
	public ArrayList<Point> getPoints(){
		return points;
	}
	
	public void addCircle(Circle c){
		this.circles.add(c);
		this.setChanged();
		this.notifyObservers();
	}
	public ArrayList<Circle> getCircles(){
		return circles;
	}
	
	public void addRectangle(Rectangle r)
	{
		this.rectangles.add(r);
		this.setChanged();
		this.notifyObservers();
	}
	
	public ArrayList<Rectangle> getRectangles()
	{
		return rectangles;
	}
	

}
