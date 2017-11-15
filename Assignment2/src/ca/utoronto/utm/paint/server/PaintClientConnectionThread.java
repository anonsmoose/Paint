package ca.utoronto.utm.paint.server;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import ca.utoronto.utm.paint.PaintModel;
import ca.utoronto.utm.paint.Shapes.Shape;

/**
 * A thread for handling the client side communication with the server
 * for Paint Online
 */
public class PaintClientConnectionThread extends Thread{
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private Socket socket;
	private PaintModel model;
	private String address;
	private int port;
	
	/**
	 * Constructs the client side connection thread
	 * @param model The model which is receiving shapes from the server
	 * @param address The string representation of the address being connected to
	 * @param port The port through which the thread attempts to connect to the server
	 */
	public PaintClientConnectionThread(PaintModel model,String address, int port){
		super("PaintClientConnectionThread");
		this.address = address;
		this.port = port;
		this.model = model;
	}
	public void run(){
		
		try(Socket socket = new Socket(InetAddress.getByName(address),port);
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			){
				this.socket = socket;
				this.out = out;
				this.model.setServerStatus(true);
				while(!socket.isClosed()){
					try{
						Object inputObject = in.readObject();
						this.processInput(inputObject);
						Thread.sleep(10);
					}
					catch(EOFException e){
						
					} 
					catch(ClassNotFoundException e){
						e.printStackTrace();
					} 
					catch (InterruptedException e) {
						e.printStackTrace();
					}
					catch (SocketException e) {
						//The socket is closed
						socket.close();
						this.model.setServerStatus(false);
					}
				}
				this.model.setServerStatus(false);
			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
	}
	
	/**
	 * Decides how an object received by the server is dealt with based on its type.
	 * @param o The object being handled
	 */
	@SuppressWarnings("unchecked")
	public void processInput(Object o){
		if(o instanceof ArrayList){
			this.model.setShapes((ArrayList<Shape>) o);
			ArrayList<Shape> shape = (ArrayList<Shape>) o;
		}
		
		if(o instanceof Shape){
			this.model.addShapeFromServer((Shape) o);
		}
	}
	
	/**
	 * Sends an object (typically a shape) to the server
	 * @param o The object being sent
	 */
	public void sendObjectToServer(Object o){
		try {
			out.writeObject(o);
			out.flush();
		}catch(SocketException e){
			//The server closed the socket
			this.model.setServerStatus(false);
			this.model.addShape((Shape)o); //re-add the shape
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Closes the client socket and allows the thread to terminate
	 */
	public void close(){
		try {
			this.socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
