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
	private PaintServer server;
	
	/**
	 * Adds a shape command to the model's shape list when in a local session of paint
	 * or when connected to a server adds it to a list containing shape commands which 
	 * have been sent to the server but not yet received back, (For the purposes of clientside
	 * prediction)
	 * @param s The shape commands being added
	 */
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
	
	/**
	 * Removes the most recent shape from the list of shapes being drawn and 
	 * add it to a list of shapes that have been undone
	 */
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
	
	/**
	 * Adds the most recent shape added to the undone shapes list back to the list
	 * of shapes to be drawn.
	 */
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
	
	/**
	 * Notifies observers of a change in the model.
	 */
	public void notifyBrushChanged(){
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Attempts to connect to a paint server on the specified address and port, starts a new thread for communicating
	 * with the server.
	 * @param address The string representation of the server address (localhost,ip address, URL etc...)
	 * @param port The port the client is attempting to connect through
	 */
	public void connectToServer(String address,int port){
		this.connectionThread = new PaintClientConnectionThread(this,address,port);
		this.connectionThread.start();
	}
	
	/**
	 * Starts a new PaintServer the listens on the specified port
	 * @param port The port the server is listening on.
	 */
	public void startServer(int port){
		PaintServer server = new PaintServer(this.shapes, port);
		this.server = server;
		server.start();
	}
	
	/**
	 * Get the list of shapes sent to not recieved back from the server
	 * @return
	 */
	public ArrayList<Shape> getTempClientShapes(){
		return this.tempClientsideShapePreviews;
	}
	
	/**
	 * @return Whether or not the client is connected to a server
	 */
	public boolean isConnectedToServer(){
		return this.connectedToServer;
	}
	
	/**
	 * Passes a shape to the Clientside connection thread to be sent to the server
	 * @param s The shape to be sent
	 */
	public void sendShapeToServer(Shape s){
		if(this.connectionThread != null){
			this.connectionThread.sendObjectToServer(s);
		}
	}
	
	/**
	 * Overwrite the current shape list with the specified list
	 * @param shapes The list the models current list is being changed to
	 */
	public void setShapes(ArrayList<Shape> shapes){
		this.shapes = shapes;
		this.notifyBrushChanged();
	}
	
	/**
	 * Removes the temporary client shape from the list
	 * @param s The shape being removed
	 */
	public void removeTempShape(Shape s){
		this.tempClientsideShapePreviews.remove(s);
	}
	
	/**
	 * Directly add a shape to the shape command list
	 * @param shape
	 */
	public void addShapeFromServer(Shape shape){
		this.shapes.add(shape);
		this.tempClientsideShapePreviews.remove(shape);
		this.notifyBrushChanged();
	}
	
	public void setServerStatus(boolean status){
		this.connectedToServer = status;
		this.notifyBrushChanged();
	}
	
	/**
	 * Handles disconnection from a server and restores the paint model to completely local setting
	 */
	public void disconnectFromServer(){
		if(this.connectionThread != null){
			this.connectionThread.close();
			this.connectionThread = null;
		}
		
		if(this.server != null){
			this.server.close();
			this.server = null;
		}
	}
	
	public ArrayList<Shape> getShapes(){
		return shapes;
	}

}
