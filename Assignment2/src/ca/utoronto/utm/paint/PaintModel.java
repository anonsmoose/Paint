package ca.utoronto.utm.paint;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Observable;

import ca.utoronto.utm.paint.Shapes.Circle;
import ca.utoronto.utm.paint.Shapes.Rectangle;
import ca.utoronto.utm.paint.Shapes.Shape;

public class PaintModel extends Observable {

	private ArrayList<Shape> shapes = new ArrayList<Shape>();

	public void addShape(Shape s){
		if(!this.shapes.contains(s))
			this.shapes.add(s);
		
		this.setChanged();
		this.notifyObservers();
	}
	
	public void notifyBrushChanged(){
		this.setChanged();
		this.notifyObservers();
	}
	
	public ArrayList<Shape> getShapes(){
		return shapes;
	}

}
