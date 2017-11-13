package ca.utoronto.utm.paint;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Observable;

import ca.utoronto.utm.paint.Shapes.Circle;
import ca.utoronto.utm.paint.Shapes.Rectangle;
import ca.utoronto.utm.paint.Shapes.Shape;
import ca.utoronto.utm.paint.server.PaintClientConnectionThread;
import ca.utoronto.utm.paint.server.PaintServer;

public class PaintModel extends Observable {

	private ArrayList<Shape> undoShapes = new ArrayList<Shape>();
	protected ArrayList<Shape> shapes = new ArrayList<Shape>();
	private ArrayList<Shape> tempClientsideShapePreviews = new ArrayList<Shape>();
	
	private boolean connectedToServer = false;
	private PaintClientConnectionThread connectionThread;
	

	public void addShape(Shape s){
		if(!connectedToServer){
			if(!this.shapes.contains(s))
				this.shapes.add(s);
		}
		else{
			this.tempClientsideShapePreviews.add(s);
		}
	
		
		this.setChanged();
		this.notifyObservers();
	}
	
	public void undo()
	{
		if(!(this.shapes.isEmpty()))
		{
			undoShapes.add((this.shapes.get(this.shapes.size() - 1)));
			this.shapes.remove(this.shapes.size() - 1);
		}
		this.setChanged();
		this.notifyObservers();
	}
	
	public void redo()
	{
		if(!(undoShapes.isEmpty()))
		{
			addShape(undoShapes.get(this.undoShapes.size() - 1));
			undoShapes.remove(this.undoShapes.size() - 1);
		}
		this.setChanged();
		this.notifyObservers();
	}
	
	public void notifyBrushChanged(){
		this.setChanged();
		this.notifyObservers();
	}
	
	public void connectToServer(String address,int port){
		this.connectionThread = new PaintClientConnectionThread(this,address,port);
		this.connectionThread.start();
	}
	
	public void startServer(int port){
		PaintServer server = new PaintServer(this.shapes, port);
		server.start();
	}
	
	public ArrayList<Shape> getTempClientShapes(){
		return this.tempClientsideShapePreviews;
	}
	
	public boolean isConnectedToServer(){
		return this.connectedToServer;
	}
	
	public void sendShapeToServer(Shape s){
		if(this.connectionThread != null){
			this.connectionThread.sendObjectToServer(s);
		}
	}
	
	public void setShapes(ArrayList<Shape> shapes){
		this.shapes = shapes;
		this.notifyBrushChanged();
	}
	
	public void removeTempShape(Shape s){
		this.tempClientsideShapePreviews.remove(s);
	}
	
	public void addShapeFromServer(Shape shape){
		this.shapes.add(shape);
		this.tempClientsideShapePreviews.remove(shape);
		this.notifyBrushChanged();
		System.out.println("adding shape");
	}
	
	public void setServerStatus(boolean status){
		this.connectedToServer = status;
	}
	
	public ArrayList<Shape> getShapes(){
		return shapes;
	}

}
