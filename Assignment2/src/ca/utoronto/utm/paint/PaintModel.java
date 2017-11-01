package ca.utoronto.utm.paint;

import java.util.ArrayList;
import java.util.Observable;

import ca.utoronto.utm.paint.Shapes.Circle;
import ca.utoronto.utm.paint.Shapes.Rectangle;
import ca.utoronto.utm.paint.Shapes.Shape;

public class PaintModel extends Observable {

	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	private int brushSize =1;
	
	public void setBrushSize(int size){
		this.brushSize = size;
	}
	
	public int getBrushSize(){
		return this.brushSize;
	}
	public void addShape(Shape s){
		this.shapes.add(s);
		this.setChanged();
		this.notifyObservers();
	}
	
	public ArrayList<Shape> getShapes(){
		return shapes;
	}

}
